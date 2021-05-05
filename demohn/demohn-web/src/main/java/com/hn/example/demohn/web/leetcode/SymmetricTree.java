package com.hn.example.demohn.web.leetcode;

/**
 * @author yuqing
 * @date 2021-04-30
 * @description
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return doJudge(root,root);
    }
    private boolean doJudge(TreeNode p,TreeNode q) {
        if(p == null && q == null) {
            return true;
        }
        if(p ==null || q == null) {
            return false;
        }
        if(p.val == q.val) {
            return doJudge(p.left,q.right) && doJudge(p.right,q.left);
        }
        return false;
    }
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
