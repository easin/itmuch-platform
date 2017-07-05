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
import com.itmuch.platform.admin.domain.Area;
import com.itmuch.platform.admin.service.AreaService;
import com.itmuch.platform.admin.vo.AreaTreeVo;
import com.itmuch.platform.controller.common.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/area")
public class AreaAdminController extends BaseController {

    @Resource
    private AreaService areaService;

    @RequestMapping("")
    @RequiresPermissions("sys:area:view")
    public String index() {
        return "admin/area/area";
    }

    @ResponseBody
    @RequestMapping("/get-tree")
    @RequiresAuthentication
    public List<AreaTreeVo> getTree() {
        return this.areaService.selectTreeByType(null);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:area:insert")
    public Result insert(@RequestBody Area area) {
        this.areaService.insert(area);
        return new Result("成功", "插入区域信息成功.");
    }

    @RequestMapping(value = "/edit-by-id", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("sys:area:edit")
    public Result update(@RequestBody Area area) {
        this.areaService.updateById(area);
        return new Result("成功", "修改区域信息成功.");
    }

    @RequestMapping(value = "/del-by-id")
    @ResponseBody
    @RequiresPermissions("sys:area:del")
    public Result deleteById(Integer id) {
        return this.areaService.deleteById4Tree(id, "成功", "删除区域信息成功.");
    }
}
