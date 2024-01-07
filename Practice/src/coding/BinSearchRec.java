package coding;

public class BinSearchRec {

	public static void main(String[] args) {
		BinSearchRec ob = new BinSearchRec(); 
        int arr[] = { 2, 3, 4, 10, 40 }; 
        int x = 10; 
        int result = ob.binarySearch(arr, x, 0, arr.length - 1); 
        if (result == -1) 
            System.out.println("Element not present"); 
        else
            System.out.println("Element found at "
                               + "index " + result); 

	}

	private int binarySearch(int[] arr, int x, int l, int r) {
		if (l <= r) {
			int m = l + (r - 1) / 2;
			if (arr[m] == x) {
				return m;
			} else if (arr[m] < x) {
				binarySearch(arr, x, m++, r);
			} else {
				binarySearch(arr, x, l, m--);
			}
		}
		
		return -1;
	}

}
