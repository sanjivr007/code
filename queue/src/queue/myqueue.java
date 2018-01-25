package queue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


class Node{
	int value;
	Node left,right;
	Node(int value){
		this.value=value;
		left=null;
		right=null;
	}
}

public class myqueue {
	
	Node root;
	
	myqueue(){
		root=null;
	}
	
	public void preOrder(Node node){
		if (node == null)
			return;
		 
		 preOrder(node.left);
		 System.out.print(node.value + " ");
		 preOrder(node.right);
		 
	}
	
	void printPreorder()   {     preOrder(root);  }
	
	public static void main(String[] args){
		myqueue tree = new myqueue();
		tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.printPreorder();
		
	}

	/*public static void main(String[] args){
		try {
				    
			RandomAccessFile file = new RandomAccessFile("/home/sanjiv/Desktop/queue.txt", "rw");
		    int numberOfThread = 20;
		     int bufferSize = 512;
		     file.seek(getWritePointer());
		    ExecutorService pool = Executors.newFixedThreadPool(numberOfThread);
		    AtomicInteger byteCounter = new AtomicInteger(0);
		    
		    String[] arr = {"Ankit\n","Bohra\n","Xyz\n"};
		    for(int j=0;j<3;j++){
		    	 byte[] yourText = arr[j].getBytes(); 
		    	 System.out.println(arr[j]);
		    	
		    	
		    	
		    	 try {
		    		    file.seek(file.getFilePointer()+2);
	                    file.write(yourText);
	                } catch (IOException e) {
	                    //exception handle
	                }
		
		    	 
		    	 
		    	 
		    }
            file.seek(0);
		    System.out.print("--"+file.readLine());
		
		    file.close();
		} catch (Exception e) {
		   
		}
		
	}*/

	

}
