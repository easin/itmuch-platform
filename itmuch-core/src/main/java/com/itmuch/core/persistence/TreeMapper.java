package com.itmuch.core.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TreeMapper<T> extends BaseMapper<T> {
    /**
     * 通过类型查询所有
     * @return 所有
     */
    List<T> selectAllByType(@Param("type") Integer type);

    /**
     * 通过id, 查询该id下的一级子节点个数
     * @param id id
     * @return 一级子节点的个数
     */
    Integer selectCountChildById(Integer id);
}
