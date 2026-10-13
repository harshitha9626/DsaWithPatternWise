import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        long sum = 0;

        // Sort by absolute values using boxed integers
        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
        }

        Arrays.sort(arr, (a, b) -> Long.compare(Math.abs((long) b), Math.abs((long) a)));

        for (int i = 0; i < n; i++) {
            long square = (long) arr[i] * arr[i];

            if (i < (n + 1) / 2) {
                sum += square;
            } else {
                sum -= square;
            }
        }

        return sum;
    }
}