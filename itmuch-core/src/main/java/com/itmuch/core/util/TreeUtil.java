package com.itmuch.core.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.itmuch.core.entity.TreeEntity;

@Component
public class TreeUtil<T extends TreeEntity<T>> {
    /**
     * 给出查询到的数据,生成树形结构, (为easyui提供树形结构)
     * @param list 查询到的数据
     * @return 树形结构
     */
    // TODO 算法不OK
    public List<T> genTree(List<T> list) {

        List<T> nodeList = new ArrayList<T>();
        for (T node1 : list) {
            boolean mark = false;
            for (T node2 : list) {
                if ((node1.getParentId() != null) && node1.getParentId().equals(node2.getId())) {
                    mark = true;
                    if (node2.getChildren() == null) {
                        node2.setChildren(new ArrayList<T>());
                    }
                    node2.getChildren().add(node1);
                    break;
                }
            }
            if (!mark) {
                nodeList.add(node1);
            }
        }
        return nodeList;
    }
}
