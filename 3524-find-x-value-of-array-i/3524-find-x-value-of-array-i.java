class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] ans = new long[k];


        long[] prev = new long[k];

        for (int num : nums) {

            long[] curr = new long[k];

            
            curr[num % k]++;

        
            for (int r = 0; r < k; r++) {
                if (prev[r] > 0) {
                    int newRemainder = (int)((long) r * num % k);
                    curr[newRemainder] += prev[r];
                }
            }

        
            for (int r = 0; r < k; r++) {
                ans[r] += curr[r];
            }

            prev = curr;
        }

        return ans;
    }
}