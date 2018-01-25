package intersectionPackage;

public class MergeSort {
	
	public  int[] sort(int[] arr) {
		// TODO Auto-generated method stub
		mergeSorting(arr,0,arr.length);
		
		return null;
	}
	
	public void mergeSorting(int arr[],int left,int right){
		
		if(left<right){
			
			int mid = (left+right)/2;
			mergeSorting(arr,left,mid);
			mergeSorting(arr,mid+1,right);
			merge(arr,left,mid,right);
		}
		
		
	}
	
//	1,2,3,4,5 
//  0 1 2 3 4	
   public void merge(int arr[],int l,int m , int r){
	   
	   int i=0,j=0,k=0;
	   int leftSize = m-l+1;
	   int rightSize=r-m;
	   
	   int left[] = new int[rightSize];
	   int right[]= new int[rightSize];
	   
	   for(int x=0;x<leftSize;x++)
		   left[x]=arr[l+x];
	   for(int y=0;y<rightSize;y++)
		   right[y]=arr[m+1+y];
	   
	   k=l;
	   
	   while(i<leftSize && j<rightSize ){
		   if(left[i] <= right[j]){
			   arr[k] = left[i];
		       i++;
			   k++;
		   }
		   else{
			   arr[k]=right[j];
			   j++;
			   k++;
		   }
		   
		   
	   }   
	   
	   
   }	

}
