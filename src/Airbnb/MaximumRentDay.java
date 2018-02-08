package Airbnb;

public class MaximumRentDay {
	public int maxDay(int[] nums){
		if(nums == null||nums.length == 0)
			return 0;
		int include = nums[0];
		int exclude = 0;
		for(int i = 1;i < nums.length;i++){
			int lastInclude = include;
			include = Math.max((exclude + nums[i]),nums[i]);
			exclude = Math.max(exclude,lastInclude);
		}
		return Math.max(include, exclude);
	}
/*************************************************/
/******************Reference*********************/
	  public int getMaxRentalDays(int[] nums) {
		    if (nums == null || nums.length == 0) {
		      return 0;
		    } 
		    int prev2 = 0;
		    int prev1 = nums[0];
		    for (int i = 2; i <= nums.length; i++) {
		      int curr = Math.max(prev1, prev2 + nums[i - 1]);
		      prev2 = prev1;
		      prev1 = curr;
		    } 
		    return prev1;
		  }
/*************************************************/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MaximumRentDay myS = new MaximumRentDay();
		  int[] input = {0,0,-1,-1,0,0,1,0,2,0,0,1,0,2,-3,4};
		  int[] arr = {4,10,3,1,5};
		  System.out.println(myS.maxDay(input));
		  System.out.println(myS.getMaxRentalDays(input));
		  System.out.println(myS.maxDay(arr));
		  System.out.println(myS.getMaxRentalDays(arr));
	}

}
