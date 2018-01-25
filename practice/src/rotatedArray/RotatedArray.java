package rotatedArray;

public class RotatedArray {

	public static int rsearch(int[] array, int tosearch, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;

			if (array[mid] == tosearch)
				return tosearch;
			
			if (tosearch < array[mid] && tosearch >array[start])
				return bsearch(array, tosearch, start, mid);
			else
				return bsearch(array, tosearch, mid + 1, end);
		}
		return -1;
	}

	public static int bsearch(int[] array, int tosearch, int start, int end) {

		
		if (start < end) {
			int mid = (start + end) / 2;

			if (array[mid] == tosearch)
				return tosearch;
			
			if (tosearch < array[mid] && tosearch >=array[start])
				return bsearch(array, tosearch, start, mid);
			else
				return bsearch(array, tosearch, mid + 1, end);
		}

		return -1;

	}
	public static void reverse(int[] array,int start,int end){
		while(start<end){
			int temp = array[start];
			array[start]=array[end];
			array[end]=temp;
			start++;
			end--;					
		}			
	}
	
	public static void sortRotatedArray(int[] array){
		for(int i=0;i<=array.length-2;i++){
			if(array[i]>array[i+1]){
				reverse(array,0,i);
				reverse(array,i+1,array.length-1);
				reverse(array,0,array.length-1);				
			}			
		}		
	}

	public static void main(String args[]) {

		int[] array = { 4,5,6,8,1,2,3};
		//int[] array1 = { 5,6,8,1,2,3,4};

		int[] array1 = { 1, 2, 3, 4, 5, 6, 8 };

		//int x = bsearch(array1, 9, 0, array1.length );
		//int y= rsearch (array, 1, 0, array1.length );
		sortRotatedArray(array);
		for(int i=0;i<7;i++)
			System.out.println(array[i]);
			
		//System.out.println(y);

	}

}
