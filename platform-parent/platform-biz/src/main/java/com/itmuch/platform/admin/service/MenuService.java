package com.itmuch.platform.admin.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.itmuch.core.service.TreeService;
import com.itmuch.core.util.DozerUtil;
import com.itmuch.core.util.TreeUtil;
import com.itmuch.core.web.converter.Result;
import com.itmuch.platform.admin.domain.Menu;
import com.itmuch.platform.admin.persistence.MenuMapper;
import com.itmuch.platform.admin.vo.MenuTreeVo;

@Service
public class MenuService extends TreeService<MenuMapper, Menu> {
    @Resource
    private TreeUtil<MenuTreeVo> treeUtil;

    @Value("${adminPath}")
    protected String adminPath;

    @Resource
    private UserService adminService;

    public List<MenuTreeVo> selectTreeByType(Integer officeType) {
        List<Menu> list = this.dao.selectAllByType(officeType);
        List<MenuTreeVo> menuVoList = Lists.newArrayList();
        if ((list != null) && !list.isEmpty()) {
            for (Menu menu : list) {
                MenuTreeVo vo = DozerUtil.map(menu, MenuTreeVo.class);
                vo.setText(vo.getName());
                menuVoList.add(vo);
            }
        }

        return this.treeUtil.genTree(menuVoList);
    }

    @Override
    public Result deleteById4Tree(Integer id, String title, String msg) {
        return super.deleteById4Tree(id, title, msg);
    }

    /**
     * 通过当前用户,查询用户有权限的菜单
     * @param id 用户id
     * @return 有权管理的菜单
     */
    public List<MenuTreeVo> selectTreeByUser(HttpServletRequest req, Integer id) {
        List<Menu> menus = null;
        // 如果是超管, 则查询所有可见菜单
        if (id == 1) {
            menus = this.dao.selectAllVisable();
        }
        // 并非超管
        else {
            List<Integer> roleIds = this.adminService.selectRoleIdListByUserId(id);
            if ((roleIds != null) && !roleIds.isEmpty()) {
                // 2. 通过角色id, 查询角色可见的菜单
                menus = this.dao.selectVisableByRoleIds(roleIds);

            }
        }
        List<MenuTreeVo> menuVoList = Lists.newArrayList();
        if ((menus != null) && !menus.isEmpty()) {
            for (Menu menu : menus) {
                MenuTreeVo vo = DozerUtil.map(menu, MenuTreeVo.class);
                vo.setText(vo.getName());
                String href = vo.getHref();
                if (!StringUtils.isEmpty(href)) {
                    if (!href.startsWith("/")) {
                        vo.setHref(req.getContextPath() + this.adminPath + "/" + href);
                    } else {
                        String contextPath = req.getContextPath();
                        vo.setHref(contextPath + href);

                    }
                }
                menuVoList.add(vo);
            }
        }
        return this.treeUtil.genTree(menuVoList);
    }
}
