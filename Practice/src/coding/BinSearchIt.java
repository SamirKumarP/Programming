package coding;

public class BinSearchIt {

	public static void main(String[] args) {
		BinSearchIt ob = new BinSearchIt(); 
        int arr[] = { 2, 3, 4, 10, 40 }; 
        int x = 10; 
        int result = ob.binarySearch(arr, x); 
        if (result == -1) 
            System.out.println("Element not present"); 
        else
            System.out.println("Element found at "
                               + "index " + result); 

	}

	private int binarySearch(int[] arr, int x) {
		int l = 0, r = arr.length -1;
		while(l < r) {
			int m = l + (r-1)/2;
			if(arr[m] == x) {
				return m;
			} else if (arr[m] < x) {
				l = m++;
			} else {
				r = m--;
			}
		}
		return -1;
	}

}
