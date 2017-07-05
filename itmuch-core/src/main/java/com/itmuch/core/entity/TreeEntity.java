package com.itmuch.core.entity;

import java.io.Serializable;
import java.util.List;

public class TreeEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer id;
    protected Integer parentId;
    protected List<T> children;
    protected String text;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<T> getChildren() {
        return this.children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
