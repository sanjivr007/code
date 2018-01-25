package truck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Truck {
	
	public  static int abs(int a){
		if (a>=0)
			return a;
		else
			return -a;
		  
	}
	
	
	public static void main(String args[] ) throws Exception {
	      
	       /* Scanner s = new Scanner(System.in);
	        String name = s.nextLine();                 // Reading input from STDIN
	        System.out.println("Hi, " + name + "."); */   // Writing output to STDOUT

	        Scanner sc=new Scanner(System.in);
			int N;//Number of items
			N = sc.nextInt();
			List<String> givenString= new ArrayList<>(N);
			
			
			
			for(int i = 0;i < N ;i++)
			{
				String x=  sc.next();
				givenString.add(x);
				
				
			}

			for(int i=0;i<N;i++){
				String eachString = givenString.get(i);
				int value=0;
				int decount=0;
				for(int itr=0;itr<eachString.length();itr++){
					if(eachString.charAt(itr)=='A'){
						value=value+1;
						
					}
					else if(eachString.charAt(itr)=='C')
						value=value-1;
					else
						decount++;
						
				}
				
				if(value>=0){
					value=value+decount;
				}
				else
				   value=abs(value-decount);
				
				System.out.println(value);
				     
			}
			
			
	        // Write your code here

	    }

}
