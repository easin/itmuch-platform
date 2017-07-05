package com.itmuch.core.persistence;

public interface BaseMapper<T> {
    /**
     * 通过id查询 实体
     * @param id id
     * @return 实体
     */
    public T selectById(Integer id);

    /**
     * 插入
     * @param entity 实体
     * @return 操作了多少条
     */
    public Integer insert(T entity);

    /**
     * 通过id,修改实体
     * @param entity 实体
     * @return 操作了多少条数据
     */
    public Integer updateById(T entity);

    /**
     * 通过id逻辑删除实体(操作del_flag字段设置为1)
     * @param id id
     * @return 操作了多少条
     */
    public Integer deleteById(Integer id);

    /**
     * 通过id真删除实体
     * @param id id
     * @return 操作了多少条
     */
    public Integer deleteByIdReal(Integer id);

}
