package com.hn.example.demohn.web.leetcode;

/**
 * @author yuqing
 * @date 2021-04-26
 * @description
 */
public class CircleLinkedList {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) {
            return Boolean.FALSE;
        }
        ListNode slow = head;
        ListNode quick = head.next;
        while(!slow.equals(quick)) {
            if(quick == null || quick.next ==null) {
                return Boolean.FALSE;
            }
            slow = slow.next;
            quick =quick.next.next;
        }
        return Boolean.TRUE;
    }
    class ListNode {
          int val;
          ListNode next;
          ListNode(int x) {
              val = x;
              next = null;
          }
      }
}
