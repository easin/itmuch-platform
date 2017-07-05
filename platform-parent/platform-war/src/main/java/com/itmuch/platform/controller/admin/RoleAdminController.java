package com.itmuch.platform.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmuch.core.page.PageInfo;
import com.itmuch.core.page.PageVo;
import com.itmuch.core.web.converter.Result;
import com.itmuch.platform.admin.domain.Role;
import com.itmuch.platform.admin.service.RoleService;
import com.itmuch.platform.admin.vo.RoleVo;

@Controller
@RequestMapping(value = "${adminPath}/role")
public class RoleAdminController {
    @Resource
    private RoleService roleService;

    @RequestMapping("")
    @RequiresPermissions("sys:role:view")
    public String index() {
        return "admin/role/role";
    }

    @ResponseBody
    @RequestMapping("/list-paged")
    @RequiresAuthentication
    public PageInfo<RoleVo> listPaged(PageVo vo) {
        PageInfo<RoleVo> info = this.roleService.listAllPaged(vo);
        return info;
    }

    /**
     * 用于用户管理时, 查询所有角色
     * @return 所有角色
     */
    @ResponseBody
    @RequestMapping("/list-all")
    @RequiresAuthentication
    public List<Role> list() {
        return this.roleService.listAll();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:role:insert")
    public Result insert(@RequestBody RoleVo role) {
        this.roleService.insert(role);
        return new Result("成功", "插入角色成功.");
    }

    @RequestMapping(value = "/edit-by-id", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:role:edit")
    public Result update(@RequestBody RoleVo vo) {
        this.roleService.updateById(vo);
        return new Result("成功", "修改角色成功.");
    }

    @RequestMapping(value = "/del-by-id")
    @ResponseBody
    @RequiresPermissions("sys:role:del")
    public Result deleteById(Integer id) {
        return this.roleService.deleteRoleById(id);
    }
}
