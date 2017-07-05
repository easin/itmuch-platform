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

import com.itmuch.core.web.converter.Result;
import com.itmuch.platform.admin.domain.Office;
import com.itmuch.platform.admin.service.OfficeService;
import com.itmuch.platform.admin.vo.OfficeTreeVo;
import com.itmuch.platform.controller.common.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/office")
public class OfficeAdminController extends BaseController {

    @Resource
    private OfficeService officeService;

    @RequestMapping("")
    @RequiresPermissions("sys:office:view")
    public String index() {
        return "admin/office/office";
    }

    @ResponseBody
    @RequestMapping("/get-tree")
    @RequiresAuthentication
    public List<OfficeTreeVo> getTree() {
        return this.officeService.selectTreeByType(null);
    }

    /**
     * 用于首页,选择公司/部门
     * @param type
     * @return 公司/部门
     */
    @ResponseBody
    @RequestMapping("/get-tree-by-type")
    @RequiresPermissions("sys:office:view")
    public List<OfficeTreeVo> getTreeByType(Integer type) {
        return this.officeService.selectTreeByType(type);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:office:insert")
    public Result insert(@RequestBody Office office) {
        this.officeService.insert(office);
        return new Result("成功", "插入机构信息成功.");
    }

    @RequestMapping(value = "/edit-by-id", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:office:edit")
    public Result update(@RequestBody Office office) {
        this.officeService.updateById(office);

        return new Result("成功", "修改机构信息成功.");
    }

    @RequestMapping(value = "/del-by-id")
    @ResponseBody
    @RequiresPermissions("sys:office:del")
    public Result deleteById(Integer id) {
        return this.officeService.deleteById4Tree(id, "成功", "删除机构信息成功.");
    }
}
