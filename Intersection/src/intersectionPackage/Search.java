package intersectionPackage;

public class Search {
	
	public static  boolean searching(int toSearch,int[] whereToSearch ){
		
		boolean flag=  binarySearch(whereToSearch, toSearch,0,whereToSearch.length);
		
			
		return flag;
		
	}
	
	public static boolean binarySearch(int[] whereToSearch , int toSearch, int start,int end){
		
		if(start<=end){
			int mid = (start+end)/2;
			
			if(whereToSearch[mid]==toSearch)
			   return true;
			  
			if(whereToSearch[mid] > toSearch){
				return binarySearch(whereToSearch,toSearch,start,mid-1);
			}
			return binarySearch(whereToSearch,toSearch,mid+1,end);
		}
		
		return false;
	}
 

}
