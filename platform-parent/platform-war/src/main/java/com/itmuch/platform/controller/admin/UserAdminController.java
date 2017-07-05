package com.itmuch.platform.controller.admin;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmuch.core.constants.CodeConstant;
import com.itmuch.core.page.PageInfo;
import com.itmuch.core.page.PageVo;
import com.itmuch.core.util.ErrorMsgUtil;
import com.itmuch.core.web.converter.Result;
import com.itmuch.platform.admin.service.UserService;
import com.itmuch.platform.admin.vo.UserAdminEditVo;
import com.itmuch.platform.admin.vo.UserAdminRegVo;
import com.itmuch.platform.admin.vo.UserVo;

@Controller
@RequestMapping(value = "${adminPath}/user")
public class UserAdminController {
    @Resource
    private UserService userService;

    @RequestMapping("")
    @RequiresPermissions("sys:user:view")
    public String index() {
        return "admin/admin/admin";
    }

    @ResponseBody
    @RequestMapping("/list-paged")
    @RequiresAuthentication
    public PageInfo<UserVo> listAll(PageVo vo) {
        PageInfo<UserVo> info = this.userService.listAllPaged(vo);
        return info;
    }

    //    @RequestMapping(value = "/get-count-by-login-name")
    //    @ResponseBody
    //    @RequiresAuthentication
    //    public Result getCountByLoginName(String loginName) {
    //        return new Result(this.adminService.selectCountByLoginName(loginName));
    //    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:user:insert")
    public Result insert(@RequestBody @Valid UserAdminRegVo user, BindingResult result) {
        if (result.hasErrors()) {
            return ErrorMsgUtil.invalidResult(result);
        }

        Integer id = this.userService.selectIdByUsername(user.getUsername());
        if (id == null) {
            this.userService.insert(user);
            return new Result("成功", "添加会员成功.");
        }
        // 已经存在该名称用户
        else {
            return ErrorMsgUtil.error("失败", "添加会员失败, 用户名重复.", CodeConstant.DUPLICATE_DATA);
        }

    }

    @RequestMapping(value = "/edit-by-id", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:user:edit")
    public Result updateByI(@RequestBody @Valid UserAdminEditVo vo, BindingResult result) {
        if (result.hasErrors()) {
            return ErrorMsgUtil.invalidResult(result);
        } else {
            return this.userService.updateUserById(vo);
        }
    }

    @RequestMapping(value = "/del-by-id")
    @ResponseBody
    @RequiresPermissions("sys:user:del")
    public Result deleteById(Integer id) {
        if (1 == id) {
            return new Result("失败", "该用户不允许删除.");
        }

        this.userService.deleteById(id);
        return new Result("成功", "删除用户成功.");
    }
}
