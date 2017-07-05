package com.itmuch.platform.admin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.itmuch.core.service.TreeService;
import com.itmuch.core.util.DozerUtil;
import com.itmuch.core.util.TreeUtil;
import com.itmuch.core.web.converter.Result;
import com.itmuch.platform.admin.domain.Area;
import com.itmuch.platform.admin.persistence.AreaMapper;
import com.itmuch.platform.admin.vo.AreaTreeVo;

@Service
public class AreaService extends TreeService<AreaMapper, Area> {
    @Resource
    private TreeUtil<AreaTreeVo> treeUtil;

    public List<AreaTreeVo> selectTreeByType(Integer areaType) {

        List<Area> list = this.dao.selectAllByType(areaType);
        List<AreaTreeVo> voList = Lists.newArrayList();

        for (Area office : list) {
            AreaTreeVo vo = DozerUtil.map(office, AreaTreeVo.class);
            vo.setText(vo.getName());

            voList.add(vo);
        }

        return this.treeUtil.genTree(voList);
    }

    @Override
    public Result deleteById4Tree(Integer id, String title, String msg) {
        return super.deleteById4Tree(id, title, msg);
    }
}
