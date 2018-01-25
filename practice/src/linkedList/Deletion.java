package linkedList;

public class Deletion {
	public Node head;
	
	public class Node{
		int data;
		Node next;
		Node(int data){
			this.data=data;
			this.next=null;
			}
	}
	
	public void insertNode(){		
		head = new Node(10);
		head.next=new Node(20);
		/*head.next.next=new Node(30);
		head.next.next.next=new Node(40);	*/	
	}	
	
	public void deleteNode( Node toitr, int toDelete){
		//Node toitr = head;
		
	   if(toitr.data== toDelete){
		   if(toitr.next==null){
			   System.out.println("There is only one node. The list "
                       + "can't be made empty ");
      return;
		   }
		   toitr.data = toitr.next.data;
		   
           toitr.next=toitr.next.next;

           // free memory
           System.gc();

           return;
		}
	   Node prev = toitr;
	   while(prev.next !=null && prev.next.data !=toDelete){
		   prev=prev.next;
	   }
	   if(prev==null)
		   System.out.println("NOde not present");
	   prev.next=prev.next.next;	
		
		
		 System.gc();	
		return;
		
		}
	public void printEachNodeData(){
		while(head!=null){
			System.out.println(head.data);
			head=head.next;
		}
		
	}
	
	public void callDelete(int val){
		deleteNode(head,val);
	}
	
	public void reversal(Node prev,Node curr){
		
		if(curr.next==null){
			curr.next=prev;
			head=curr;
			return;
		}
		Node temp=curr.next;
		curr.next=prev;
		prev=curr;
		reversal(curr,temp);
		return;
		
	}
	public void callReversal(){
		reversal(null,head);
	}
	
	public static void main(String args[]){
		Deletion del = new Deletion();
		del.insertNode();
		//del.callDelete(30);
		del.callReversal();
		del.printEachNodeData();
	
	}

}
