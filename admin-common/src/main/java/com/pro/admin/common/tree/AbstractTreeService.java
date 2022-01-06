package com.pro.admin.common.tree;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 李俊平
 * @Date: 2020-11-06 17:40
 */
public abstract class AbstractTreeService<T extends TreeNode<T>> implements ITree<T> {

  @Override
  public List<T> buildTree(List<T> treeNodes) {
    if (CollectionUtils.isEmpty(treeNodes)) {
      return Collections.emptyList();
    }

    return treeNodes.stream()
        .filter(treeNode -> treeNode.getPid() == 0)
        .peek((treeNode) -> treeNode.setChildren(getChildren(treeNode, treeNodes)))
        .sorted(Comparator.comparingInt(treeNode -> (treeNode.getSort() == null ? 0 : treeNode.getSort())))
        .collect(Collectors.toList());
  }

  @Override
  public List<T> getChildren(T root, List<T> t) {
    return t.stream()
        .filter(treeNode -> treeNode.getPid().equals(root.getId()))
        .peek(treeNode -> treeNode.setChildren(getChildren(treeNode, t)))
        .sorted(Comparator.comparingInt(treeNode -> (treeNode.getSort() == null ? 0 : treeNode.getSort())))
        .collect(Collectors.toList());
  }
}
