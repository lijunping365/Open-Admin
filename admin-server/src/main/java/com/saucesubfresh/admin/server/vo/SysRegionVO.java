package com.saucesubfresh.admin.server.vo;

import com.saucesubfresh.admin.common.tree.TreeNode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: 李俊平
 * @Date: 2020-11-04 13:39
 */
@Data
public class SysRegionVO extends TreeNode<SysRegionVO> implements Serializable {

  private static final long serialVersionUID = -5860874741778606083L;

  private Long value;

  private String label;
}
