package intersectionPackage;

public class intersect {
	
	public static void main(String args[]){
		String a="ram";
		MergeSort customSort = new MergeSort();
		int arr1[] = {20,5,4,7,8};
		int arr2[] = {18,100,1,4,5,8};
		int[] sortedArray;
		int[] intersectedValues;
		boolean array1GotSorted=true;
		if(arr1.length>=arr2.length)
			sortedArray=customSort.sort(arr1);		
		else{
			sortedArray=customSort.sort(arr2);
			array1GotSorted=false;
		}
		if(array1GotSorted)	
			intersectedValues= searchAndGetIntersection(arr2,sortedArray);
		else
			intersectedValues = searchAndGetIntersection(arr1,sortedArray);
		
	}

	private static int[] searchAndGetIntersection(int[] arr, int[] sortedArray) {
		// TODO Auto-generated method stub
		boolean isPresent=false;
		int[] intersectedValues = null;
		int index = 0;
		for(int each=0;each<arr.length;each++){
			isPresent= Search.searching(arr[each],sortedArray);
			if(isPresent){
				intersectedValues[index]=arr[each];
				index++;
			}
			
		}
		return intersectedValues;
		
	}

	
	
	

}
