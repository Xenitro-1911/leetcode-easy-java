package problems.p234_palindrome_linked_list;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode Slow = head;
        ListNode Fast = head;
        ListNode prev = null;
        ListNode x = head;
        while (Fast != null && Fast.next != null) {
            Slow = Slow.next;
            Fast = Fast.next.next;
        }
        while(Slow!=null){
            ListNode temp = Slow.next;
            Slow.next = prev;
            prev = Slow;
            Slow = temp;
        }
        while(prev!=null){
            if(prev.val!=x.val){
                return false;
            }
            prev=prev.next;
            x=x.next;
        }
        return true;
    }
}