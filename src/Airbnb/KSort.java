package Airbnb;
import java.util.*;
public class KSort {
	/**
	 * Given an array of n elements, where each element is at most k away from its target position, devise an algorithm that sorts in O(n log k) time.
	 * For example, let us consider k is 2, an element at index 7 in the sorted array, can be at indexes 5, 6, 7, 8, 9 in the given array.
	 *
	 * Reference: http://www.geeksforgeeks.org/nearly-sorted-algorithm/
	 *
	 */
	/**
	 * Time Complexity: O(k*n);
	 * Space Complexity: O(1);
	 * **/
	public void insertSort(int[] nums,int k){
		for(int i = 1;i < nums.length;i++){
			int target = nums[i];
			int j = i-1;
			//at most k swap
			while(j >= 0&&nums[j] > target){
				nums[j+1] = nums[j];
				j--;
			}
			nums[j+1] = target;
		}
		for(int i : nums){
			System.out.println(i);
		}
	}
	/**
	 * Time Complexity: (n+k)*log(k);
	 * Space Complexity: O(k);
	 * For every 2*k elements we can ensure that the first k is at its correct position.
	 * **/
	public void heapSort(int[] nums,int k){
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k+1);
		/**This takes k*log(k)**/
		for(int i = 0;i <= k;i++){
			pq.offer(nums[i]);
		}
		int i = k + 1;
		int idx = 0;
		/**This takes (n-k)*log(k)**/
		while(i < nums.length){
			nums[idx++] = pq.poll();
			pq.offer(nums[i++]);
		}
		/**This takes k*log(k)**/
		while(idx < nums.length){
			nums[idx++] = pq.poll();
		}
		for(int num : nums){
			System.out.println(num);
		}
	}
	/************************************************
	 * 
	 * 
	 * 
	 * **********************************************/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  int k = 3;
	      int[] arr = {2, 6, 3, 12, 56, 8};
	      new KSort().heapSort(arr,k);
	}

}
