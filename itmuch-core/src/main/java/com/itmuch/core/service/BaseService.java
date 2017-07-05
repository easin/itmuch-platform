package com.itmuch.core.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.itmuch.core.entity.BaseEntity;
import com.itmuch.core.persistence.BaseMapper;
import com.itmuch.core.util.SubjectUtil;

public abstract class BaseService<D extends BaseMapper<T>, T extends BaseEntity> {
    @Autowired
    protected D dao;

    /**
     * 通过id查询 实体
     * @param id id
     * @return 实体
     */
    public T selectById(Integer id) {
        return this.dao.selectById(id);
    }

    /**
     * 插入
     * @param entity 实体
     * @return 操作了多少条
     */
    public Integer insert(T entity) {
        Integer id = SubjectUtil.getUser().getId();
        entity.setCreateId(id);
        entity.setCreateTime(new Date());
        return this.dao.insert(entity);
    }

    /**
     * 通过id,修改实体
     * @param entity 实体
     * @return 操作了多少条数据
     */
    public Integer updateById(T entity) {
        entity.setUpdateTime(new Date());
        entity.setUpdateId(SubjectUtil.getUser().getId());
        return this.dao.updateById(entity);
    }

    /**
     * 通过id逻辑删除实体(操作del_flag字段设置为1)
     * @param id id
     * @return 提示信息
     */
    public Integer deleteById(Integer id) {

        return this.dao.deleteById(id);
    }

    /**
     * 通过id真删除实体
     * @param id id
     * @return 操作了多少条
     */
    public Integer deleteByIdReal(Integer id) {
        return this.dao.deleteByIdReal(id);
    }

}
