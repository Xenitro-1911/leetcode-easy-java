package problems.p203_remove_linked_list_elements;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {

    public ListNode removeElements(ListNode head, int val) {
        if(head==null){
            return null;
        }
        ListNode current = head;
        ListNode x = new ListNode(0);;
        x.next = current;
        ListNode prev = x;

        while (current != null) {
            if(current.val == val){
                prev.next = current.next;
                current=current.next;
            }else{
                current = current.next;
                prev = prev.next;
            }
        }
        return x.next;
    }
}