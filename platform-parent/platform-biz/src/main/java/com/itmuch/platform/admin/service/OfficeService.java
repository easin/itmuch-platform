package com.itmuch.platform.admin.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.itmuch.core.service.TreeService;
import com.itmuch.core.util.DozerUtil;
import com.itmuch.core.util.TreeUtil;
import com.itmuch.core.web.converter.Result;
import com.itmuch.platform.admin.domain.Office;
import com.itmuch.platform.admin.persistence.OfficeMapper;
import com.itmuch.platform.admin.vo.OfficeTreeVo;

@Service
public class OfficeService extends TreeService<OfficeMapper, Office> {
    @Resource
    private TreeUtil<OfficeTreeVo> treeUtil;

    //    @Resource
    //    private DictService dictService;
    //    @Resource
    //    private AreaService areaService;

    public List<OfficeTreeVo> selectTreeByType(Integer officeType) {
        List<Office> list = this.dao.selectAllByType(officeType);
        List<OfficeTreeVo> voList = Lists.newArrayList();

        // Map<String, Dict> map = this.dictService.selectByType("sys_office_type");

        for (Office office : list) {
            OfficeTreeVo officeVo = DozerUtil.map(office, OfficeTreeVo.class);
            // String type = officeVo.getType();
            // Dict dict = map.get(type);
            // officeVo.setTypeName(dict.getLabel());

            //            Area area = this.areaService.selectById(office.getAreaId());
            //            if (area != null) {
            //                officeVo.setAreaName(area.getName());
            //            }

            officeVo.setText(officeVo.getName());
            voList.add(officeVo);

        }

        return this.treeUtil.genTree(voList);
    }

    @Override
    public Result deleteById4Tree(Integer id, String title, String msg) {
        return super.deleteById4Tree(id, title, msg);
    }

    /**
     * 查询所有机构
     * @return
     */
    // TODO 需引入缓存
    public Map<Integer, Office> selectAll() {
        List<Office> list = this.dao.selectAllByType(null);
        Map<Integer, Office> map = Maps.newHashMap();
        for (Office office : list) {
            map.put(office.getId(), office);
        }
        return map;
    }
}
