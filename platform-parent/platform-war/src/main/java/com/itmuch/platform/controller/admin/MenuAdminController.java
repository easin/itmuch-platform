package com.itmuch.platform.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmuch.core.util.SubjectUtil;
import com.itmuch.core.web.converter.Result;
import com.itmuch.platform.admin.domain.Menu;
import com.itmuch.platform.admin.service.MenuService;
import com.itmuch.platform.admin.vo.MenuTreeVo;
import com.itmuch.platform.controller.common.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/menu")
public class MenuAdminController extends BaseController {

    @Resource
    private MenuService menuService;

    @RequestMapping("")
    @RequiresPermissions("sys:menu:view")
    public String index() {
        return "admin/menu/menu";
    }

    @ResponseBody
    @RequestMapping("/get-tree")
    @RequiresAuthentication
    public List<MenuTreeVo> getTree() {
        return this.menuService.selectTreeByType(null);
    }

    /**
     * 查询当前用户有权管理的可见菜单 用户后台首页左侧的树
     * @return 可见菜单
     */
    @ResponseBody
    @RequestMapping("/get-tree-by-user")
    @RequiresAuthentication
    public List<MenuTreeVo> selectTreeByUser(HttpServletRequest req) {
        Integer id = SubjectUtil.getUser().getId();
        return this.menuService.selectTreeByUser(req, id);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:menu:insert")
    public Result insert(@RequestBody Menu menu) {
        this.menuService.insert(menu);
        return new Result("成功", "插入菜单信息成功.");
    }

    @RequestMapping(value = "/edit-by-id", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:menu:edit")
    public Result update(@RequestBody Menu menu) {
        this.menuService.updateById(menu);
        return new Result("成功", "修改菜单信息成功.");
    }

    @RequestMapping(value = "/del-by-id")
    @ResponseBody
    @RequiresPermissions("sys:menu:del")
    public Result deleteById(Integer id) {
        return this.menuService.deleteById4Tree(id, "成功", "删除菜单信息成功.");
    }
}
