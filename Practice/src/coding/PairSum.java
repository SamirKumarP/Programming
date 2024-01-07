package coding;

public class PairSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	void printPairSums(int[] array, int sum) {
		int left = 0, right = array.length -1;
		while(left < right) {
			int total = array[left] + array[right];
			if(sum == total) {
				System.out.println(array[left] + " " + array[right]);
				left++; right --;
			} else if (sum < total) {
				right --;
			} else {
				left ++;
			}
			
		}
	}

}
