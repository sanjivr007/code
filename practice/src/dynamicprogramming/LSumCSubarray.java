package dynamicprogramming;

public class LSumCSubarray {
	// find largest contigious subarray of maximum sum
	//[-2,-3,4,-1,-2,1,5,-3]
	public static void main(String[] args){
		
		int[] data = {-2,-3,4,-1,-2,1,5,-3};
		int max_till_now=data[0];
		int max_now=data[0];
		int index1=0;
		int start=0,end=0;
		for(int i=0;i<data.length;i++){
			if(data[i]>max_now+data[i])
				index1=i;
			
				
		  max_now=max(max_now+data[i],data[i]);
		  if(max_till_now<max_now){
			  start=index1;end=i;
		  }
		  max_till_now = max(max_till_now,max_now);
		    
		  
		  System.out.println(start+" "+end);					
		}
		System.out.println(max_till_now);
			
	}

	public static int max(int a,int b){
		return a>b?a:b;
		
	}
}