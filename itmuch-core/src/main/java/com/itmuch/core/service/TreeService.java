package com.itmuch.core.service;

import com.itmuch.core.constants.CodeConstant;
import com.itmuch.core.entity.BaseEntity;
import com.itmuch.core.persistence.TreeMapper;
import com.itmuch.core.util.ErrorMsgUtil;
import com.itmuch.core.web.converter.Result;

public class TreeService<D extends TreeMapper<T>, T extends BaseEntity> extends BaseService<D, T> {

    public Result deleteById4Tree(Integer id, String title, String msg) {
        Integer count = this.dao.selectCountChildById(id);
        if (count != 0) {
            return ErrorMsgUtil.error("非法操作", "该节点下存在子节点,请先删除子节点", CodeConstant.PARAMTER_ERROR_CODE);
        }
        Integer delCount = super.deleteById(id);
        if (delCount > 0) {
            return new Result(title, msg);
        }
        return null;
    }
}
