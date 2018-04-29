package harry.test.solution;

/**
 * 
 * @author harry
 *
 */
public class ListNode {
	int val;
	ListNode next;
	
	public ListNode(int val){
		this.val = val;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("[");
		ListNode point = this;
		ListNode point_next = next;
		sb.append(point.val);
		while(point_next != null){
			point = point.next;
			sb.append(",");
			sb.append(point.val);
			point_next = point.next;
		}
		
		sb.append("]");
		
		return sb.toString();
	}
}
