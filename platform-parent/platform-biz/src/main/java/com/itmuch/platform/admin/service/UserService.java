package com.itmuch.platform.admin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.itmuch.core.constants.CodeConstant;
import com.itmuch.core.page.PageInfo;
import com.itmuch.core.page.PageVo;
import com.itmuch.core.service.BaseService;
import com.itmuch.core.util.DozerUtil;
import com.itmuch.core.util.ErrorMsgUtil;
import com.itmuch.core.util.SaltUtil;
import com.itmuch.core.util.SubjectUtil;
import com.itmuch.core.web.converter.Result;
import com.itmuch.platform.admin.domain.Office;
import com.itmuch.platform.admin.domain.User;
import com.itmuch.platform.admin.persistence.UserMapper;
import com.itmuch.platform.admin.vo.UserAdminEditVo;
import com.itmuch.platform.admin.vo.UserAdminRegVo;
import com.itmuch.platform.admin.vo.UserVo;

@Service
public class UserService extends BaseService<UserMapper, User> {
    @Resource
    private OfficeService officeService;

    private static final int SALT_SIZE = 8;

    public PageInfo<UserVo> listAllPaged(PageVo vo) {
        String order = vo.getOrder("create_time");
        PageHelper.startPage(vo.getPage(), vo.getRows(), order);

        List<User> list = this.dao.listAll();
        PageInfo<User> info = new PageInfo<User>(list);

        List<UserVo> voList = Lists.newArrayList();

        Map<Integer, Office> map = this.officeService.selectAll();
        if ((list != null) && !list.isEmpty()) {

            for (User admin : list) {
                UserVo adminVo = DozerUtil.map(admin, UserVo.class);
                adminVo.setPassword(null);

                // 查询用户的机构
                String companyId = adminVo.getCompanyId();
                adminVo.setCompanyName(this.getName(map, companyId));
                String officeId = adminVo.getOfficeId();
                adminVo.setOfficeName(this.getName(map, officeId));

                // 查询用户的角色
                List<Integer> roleIds = this.dao.selectRoleIdListByUserId(admin.getId());
                // 之所以要加这个判断,是因为easyui 的combobox
                if ((roleIds != null) && roleIds.isEmpty()) {

                }
                adminVo.setRoleIds(roleIds);
                voList.add(adminVo);
            }
            PageInfo<UserVo> pageInfo = new PageInfo<UserVo>(null);
            pageInfo.setTotal(info.getTotal());
            pageInfo.setList(voList);

            return pageInfo;
        }
        return null;

    }

    private String getName(Map<Integer, Office> map, String id) {
        if (id != null) {
            Office office = map.get(id);
            if (office != null) {
                return office.getName() != null ? office.getName() : "";
            } else {
                return "";
            }
        }
        return "";
    }

    public Integer insert(UserAdminRegVo vo) {
        User user = DozerUtil.map(vo, User.class);

        String saltString = SaltUtil.genSaltString(SALT_SIZE);
        user.setSalt(saltString);
        String password = user.getPassword();
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(new Md5Hash(password, saltString).toString());
        }

        // 1. 插入到实体
        super.insert(user);
        Integer id = user.getId();

        // 2. 插入到中间表
        List<Integer> roleIds = vo.getRoleIds();
        if ((roleIds != null) && !roleIds.isEmpty()) {
            this.dao.insertUserRole(id, roleIds);
        }
        return 0;
    }

    public Result updateUserById(UserAdminEditVo vo) {
        Integer idSession = SubjectUtil.getUser().getId();
        if (idSession != 1) {
            if (vo.getId() == 1) {
                return ErrorMsgUtil.error("失败", "该用户不允许被其他用户修改.", CodeConstant.DUPLICATE_DATA);
            }

            // 对于非id=1的用户,不允许修改自己的角色
            if (vo.getRoleIds() != null) {
                List<Integer> roleIdsDB = this.dao.selectRoleIdListByUserId(vo.getId());
                if (!vo.getRoleIds().equals(roleIdsDB)) {
                    return ErrorMsgUtil.error("失败", "用户不允许修改自己的角色.", CodeConstant.PARAMTER_ERROR_CODE);
                }
            }
        }

        User userById = this.dao.selectById(vo.getId());
        // 如果修改了用户名
        if (!userById.getUsername().equals(vo.getUsername())) {
            Integer id = this.selectIdByUsername(vo.getUsername());
            if (id != null) {
                return ErrorMsgUtil.error("失败", "修改会员失败, 用户名重复.", CodeConstant.DUPLICATE_DATA);
            }
        }

        // 以下是允许修改的情况
        User user = DozerUtil.map(vo, User.class);
        String plainPassword = vo.getPassword();
        if (!StringUtils.isEmpty(plainPassword)) {
            user.setPassword(new Md5Hash(plainPassword, userById.getSalt()).toString());
        }
        // 1. 修改基础表        
        super.updateById(user);

        // 2. 清除中间表
        this.dao.deleteUserRoleByUserId(vo.getId());

        // 3. 插入中间表
        List<Integer> roleIds = vo.getRoleIds();
        if ((roleIds != null) && !roleIds.isEmpty()) {
            this.dao.insertUserRole(vo.getId(), roleIds);
        }
        return new Result("成功", "修改会员成功.");

    }

    /**
     * 通过登录名, 查询用户
     * @param loginName 登录名
     * @return 用户
     */
    public User selectByUsername(String loginName) {

        return this.dao.selectByUsername(loginName);
    }

    /**
     * 通过用户id, 查询用户拥有的角色id列表
     * @param id id
     * @return 角色id列表
     */
    public List<Integer> selectRoleIdListByUserId(Integer id) {
        return this.dao.selectRoleIdListByUserId(id);
    }

    /**
     * 查询所有的权限字符串, 用于超管授权
     * @return 所有权限字符串
     */
    public List<String> selectAllPermissions() {
        return this.dao.selectAllPermissions();
    }

    /**
     * 通过角色id列表, 查询权限字符串列表
     * @param roleIds 角色id列表
     * @return 权限字符串列表
     */
    public List<String> selectPermissionsByRoleIds(List<Integer> roleIds) {

        return this.dao.selectPermissionsByRoleIds(roleIds);
    }

    @Override
    public Integer deleteById(Integer id) {

        // 1. 删除用户 sys_admin表
        super.deleteById(id);
        // 2. 删除sys_admin_role表数据, 避免脏数据
        this.dao.deleteUserRoleByUserId(id);
        return 0;
    }

    /**
     * 通过登录名查询用户id
     * @param loginName 登录名
     * @return 个数
     */
    public Integer selectIdByUsername(String loginName) {
        return this.dao.selectIdByUsername(loginName);
    }
}
