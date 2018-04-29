package harry.test.solution;

import org.junit.Test;

/**
 * 
 * @author harry
 *
 */
public class Solution {
	@Test
	public void testMergeListNode(){
		ListNode s1 = new ListNode(1);
		s1.next = new ListNode(2); 
		s1.next.next = new ListNode(4);
		
		ListNode s2 = new ListNode(1);
		s2.next = new ListNode(3);
		s2.next.next = new ListNode(4);
		
		/*ListNode s1 = null;
		ListNode s2 = null;*/
		
		/*ListNode s1 = new ListNode(1);
		ListNode s2 = null;*/
		
		/*ListNode s1 = null;
		ListNode s2 = new ListNode(1);*/
		
		/*ListNode s1 = new ListNode(1);
		ListNode s2 = new ListNode(2);*/
		
		/*ListNode s1 = new ListNode(2);
		ListNode s2 = new ListNode(1);*/
		
		/*ListNode s1 = new ListNode(1);
		s1.next = new ListNode(2);
		
		ListNode s2 = new ListNode(1);*/
		
		/*ListNode s1 = new ListNode(1);
		s1.next = new ListNode(2);
		
		ListNode s2 = new ListNode(3);*/
		
		/*ListNode s1 = new ListNode(1);
		
		ListNode s2 = new ListNode(2);
		s2.next = new ListNode(3);*/
		
		
		System.out.println(mergeListNode(s1, s2));
	}
	
	public ListNode mergeListNode(ListNode s1,ListNode s2){
		ListNode result = new ListNode(0);
		ListNode head = result;
		ListNode point = null;
		
		do{
			if(s1 == null && s2 != null){
				point = s2;
				s2 = s2.next;
			}else if(s1 != null && s2 == null){
				point = s1;
				s1 = s1.next;
			}else if(s1 != null && s2 != null){
				if(s1.val <= s2.val){
					point = s1;
					s1 = s1.next;
				}else{
					point = s2;
					s2 = s2.next;
				}
			}else{
				break;
			}
			
			result.next = new ListNode(point.val);
			result = result.next;
		}while(point != null);
		
		return head.next;
	}
}
