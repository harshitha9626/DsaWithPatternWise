import java.util.*;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {

        int n = nums.size();

        // Calculate prefix sums
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        // Store prefix sums with their frequencies
        TreeMap<Long, Integer> map = new TreeMap<>();

        long ans = Long.MAX_VALUE;

        for (int j = l; j <= n; j++) {

            // Add valid starting index
            long add = prefix[j - l];
            map.put(add, map.getOrDefault(add, 0) + 1);

            // Remove starting indices outside the window
            int removeIndex = j - r - 1;

            if (removeIndex >= 0) {
                long remove = prefix[removeIndex];

                map.put(remove, map.get(remove) - 1);

                if (map.get(remove) == 0) {
                    map.remove(remove);
                }
            }

            // Find the largest prefix sum smaller than prefix[j]
            Long smaller = map.lowerKey(prefix[j]);

            if (smaller != null) {
                long sum = prefix[j] - smaller;
                ans = Math.min(ans, sum);
            }
        }

        return ans == Long.MAX_VALUE ? -1 : (int) ans;
    }
}