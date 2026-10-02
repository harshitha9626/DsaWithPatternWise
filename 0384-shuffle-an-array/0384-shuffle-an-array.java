class Solution {

int org[];
Random r=new Random();
    public Solution(int[] nums) {
        org=nums.clone();
    }
    
    public int[] reset() {
        return org.clone();
        
    }
    
    public int[] shuffle() {
        int arr[]=org.clone();
        for(int i=arr.length-1;i>0;i--)
        {
            int j=r.nextInt(i+1);
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }
        return arr;
        
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */