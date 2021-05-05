package com.hn.example.demohn.web.leetcode.middle;

/**
 * @author yuqing
 * @date 2021-05-05
 * @description
 */
public class DetectCycle {
    public ListNode detectCycle(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null) {
            slow =slow.next;
            if(fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if(slow.equals(fast)) {
                ListNode pNode = head;
                while(!pNode.equals(slow)) {
                    pNode = pNode.next;
                    slow = slow.next;
                }
                return pNode;
            }

        }
        return null;
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
