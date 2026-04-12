package problems.p083_remove_duplicates_from_sorted_list;


class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode x = new ListNode(0);
        ListNode y = x;
        while(head!=null){
            if(((head.next!=null)&&head.val!=head.next.val)||(head.next==null)){
                x.next = new ListNode(head.val);
                x = x.next;
            }
            head = head.next;
        }
        return y.next;
    }
}