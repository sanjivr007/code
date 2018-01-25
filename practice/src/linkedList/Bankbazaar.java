package linkedList;

import java.util.stream.Collectors;

public class Bankbazaar {
	
	public static class LinkedListNode {
        int val;
        LinkedListNode next;

        LinkedListNode(int node_value) {
            val = node_value;
            next = null;
        }
    };
    
    public static LinkedListNode _insert_node_into_singlylinkedlist(LinkedListNode head, LinkedListNode tail, int val) {
        if(head == null) {
            head = new LinkedListNode(val);
            tail = head;
        }
        else {
            tail.next = new LinkedListNode(val);
            tail = tail.next;
        }
        return tail;
    }
	
	static LinkedListNode mergeInBetween(LinkedListNode list1, LinkedListNode list2, int a, int b) {
		
		LinkedListNode start= list1;
		LinkedListNode end= list1;
		
		while(a>2){
			start=start.next;
			a--;
		}
		
		while(b>0){
			end=end.next;
			b--;
		}
		
		start.next=list2;
		while(list2.next != null){
			list2=list2.next;
		}
		list2.next=end;
		
		
		return list1;

    }

}
