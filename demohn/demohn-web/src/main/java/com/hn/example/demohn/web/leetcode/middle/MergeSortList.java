package com.hn.example.demohn.web.leetcode.middle;

/**
 * @author yuqing
 * @date 2021-05-05
 * @description
 */
public class MergeSortList {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    public ListNode sortList(ListNode head) {
        if(head == null) {
            return head;
        }
        int length =0;
        ListNode cur = head;
        while(cur!= null) {
            length++;
            cur = cur.next;
        }
        ListNode dummyNode = new ListNode(0,head);
        for(int subLength=1;subLength<length;subLength <<=1){
            ListNode prev = dummyNode;
            ListNode current = dummyNode.next;

            while(current != null) {
                ListNode head1 = current;
                for(int i =1;i<subLength && current.next !=null;++i ) {
                    current = current.next;
                }
                ListNode head2 = current.next;
                current.next = null;
                current = head2;
                for(int j=1;j < subLength && current != null &&  current.next != null;++j) {
                    current = current.next;
                }
                ListNode next = null;
                if(current != null) {
                    next = current.next;
                    current.next = null;
                }
                ListNode mergedNode = merged(head1,head2);
                prev.next = mergedNode;
                while(prev.next != null) {
                    prev = prev.next;
                }
                current = next;
            }
        }
        return dummyNode.next;

    }
    public ListNode merged(ListNode head1,ListNode head2){
        ListNode dummyNode = new ListNode(0);
        ListNode tmp1 = head1;
        ListNode tmp2 = head2;
        ListNode tmp = dummyNode;
        while(tmp1 != null && tmp2 != null){
            if(tmp1.val <= tmp2.val) {
                tmp.next = tmp1;
                tmp1 = tmp1.next;
            } else {
                tmp.next = tmp2;
                tmp2 = tmp2.next;
            }
            tmp = tmp.next;
        }
        if(tmp1 != null) {
            tmp.next = tmp1;
        } else {
            tmp.next = tmp2;
        }
        return dummyNode.next;
    }
}
