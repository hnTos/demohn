package com.hn.example.demohn.web.leetcode.middle;

/**
 * @author yuqing
 * @date 2021-05-05
 * @description
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) {
            return null;
        }
        ListNode newHead = new ListNode(0,head);
        ListNode second = head;
        ListNode first = head;
        for(int i=0;i<n;++i) {
            first = first.next;
            if(first == null) {
                return head;// 长度不到n
            }
        }
        while(first != null) {
            second = second.next;
            first = first.next;
        }
        second.next = second.next.next;
        return head;
    }
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;

        }
        ListNode(int val, ListNode next) { this.val = val;
        this.next = next; }
    }
}
