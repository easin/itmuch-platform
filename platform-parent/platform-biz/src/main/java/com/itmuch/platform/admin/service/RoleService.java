package com.itmuch.platform.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.itmuch.core.page.PageInfo;
import com.itmuch.core.page.PageVo;
import com.itmuch.core.service.BaseService;
import com.itmuch.core.util.DozerUtil;
import com.itmuch.core.web.converter.Result;
import com.itmuch.platform.admin.domain.Role;
import com.itmuch.platform.admin.persistence.RoleMapper;
import com.itmuch.platform.admin.vo.RoleVo;

@Service
public class RoleService extends BaseService<RoleMapper, Role> {

    public PageInfo<RoleVo> listAllPaged(PageVo vo) {
        String order = vo.getOrder("a.create_time");
        PageHelper.startPage(vo.getPage(), vo.getRows(), order);

        List<Role> list = this.dao.listAll();
        PageInfo<Role> info = new PageInfo<Role>(list);

        if ((list != null) && !list.isEmpty()) {
            List<RoleVo> voList = Lists.newArrayList();
            for (Role role : list) {
                RoleVo roleVo = DozerUtil.map(role, RoleVo.class);
                List<Integer> menuIds = this.dao.selectMenuIdsByRoleId(role.getId());
                roleVo.setMenuIds(menuIds);
                voList.add(roleVo);
            }
            PageInfo<RoleVo> info2 = new PageInfo<RoleVo>(null);
            info2.setTotal(info.getTotal());
            info2.setList(voList);
            return info2;
        }
        return null;
    }

    public List<Role> listAll() {
        return this.dao.listAll();
    }

    //    public List<Role> listAll2() {
    //        return this.dao.listAll2();
    //    }

    // TODO 数据权限暂时不做
    public Integer insert(RoleVo vo) {
        Role role = DozerUtil.map(vo, Role.class);

        super.insert(role);
        Integer roleId = role.getId();

        // 插入到sys_role_menu中间表
        List<Integer> menuIds = vo.getMenuIds();
        if ((menuIds != null) && !menuIds.isEmpty()) {
            this.dao.insertRoleMenu(roleId, menuIds);
        }
        return 0;
    }

    public Integer updateById(RoleVo vo) {
        // 1. 修改实体
        Role role = DozerUtil.map(vo, Role.class);
        super.updateById(role);

        // 2. 清除中间表
        this.dao.deleteRoleMenuById(vo.getId());

        // 3. 插入中间表
        List<Integer> menuIds = vo.getMenuIds();
        if ((menuIds != null) && !menuIds.isEmpty()) {
            this.dao.insertRoleMenu(vo.getId(), menuIds);
        }
        return 0;
    }

    public Result deleteRoleById(Integer id) {
        // 1. 查询该角色下有多少用户
        Integer count = this.dao.selectCountAdminIdByRoleId(id);
        // 2. 角色下存在用户, 不允许删除
        if (count != 0) {
            return new Result("非法操作", "该角色下有用户存在, 该角色不允许删除!");
        }
        super.deleteById(id);
        return new Result("成功", "角色删除成功.");
    }
}
