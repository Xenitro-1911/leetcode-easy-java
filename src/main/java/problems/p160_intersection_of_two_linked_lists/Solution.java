package problems.p160_intersection_of_two_linked_lists;

class ListNode {
      int val;
      ListNode next;
        ListNode(int x) {
          val = x;
          next = null;
      }
  }

public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null){
            return null;
        }
        int a=0,b=0;
        ListNode headA2= headA;
        ListNode headB2 = headB;
        while(headA2!=null){
            a++;
            headA2=headA2.next;
        }
        while(headB2!=null){
            b++;
            headB2=headB2.next;
        }

        for(int i=1;i<=(Math.abs(a-b));i++){
            if(a>b){
                headA=headA.next;
            }else{
                headB=headB.next;
            }
        }

        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }
}