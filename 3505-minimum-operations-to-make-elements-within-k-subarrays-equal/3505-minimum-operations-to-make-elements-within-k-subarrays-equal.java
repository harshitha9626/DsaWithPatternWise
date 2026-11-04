import java.util.*;

class Solution {

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = getWindowCosts(nums, x);

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {

                dp[i][j] = dp[i - 1][j];

                if (i >= x) {
                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[i - x][j - 1] + cost[i - x]
                    );
                }
            }
        }

        return dp[n][k];
    }

    private long[] getWindowCosts(int[] nums, int x) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        TreeMap<Integer, Integer> lower = new TreeMap<>();
        TreeMap<Integer, Integer> upper = new TreeMap<>();

        long lowerSum = 0;
        long upperSum = 0;

        int lowerSize = 0;
        int upperSize = 0;

        for (int i = 0; i < n; i++) {
            int val = nums[i];

            if (lower.isEmpty() || val <= lower.lastKey()) {
                add(lower, val);
                lowerSum += val;
                lowerSize++;
            } else {
                add(upper, val);
                upperSum += val;
                upperSize++;
            }

            if (i >= x) {
                int remove = nums[i - x];

                if (lower.containsKey(remove)) {
                    remove(lower, remove);
                    lowerSum -= remove;
                    lowerSize--;
                } else {
                    remove(upper, remove);
                    upperSum -= remove;
                    upperSize--;
                }
            }

            while (lowerSize > upperSize + 1) {
                int move = lower.lastKey();

                remove(lower, move);
                lowerSum -= move;
                lowerSize--;

                add(upper, move);
                upperSum += move;
                upperSize++;
            }

            while (lowerSize < upperSize) {
                int move = upper.firstKey();

                remove(upper, move);
                upperSum -= move;
                upperSize--;

                add(lower, move);
                lowerSum += move;
                lowerSize++;
            }

            if (i >= x - 1) {
                int start = i - x + 1;
                int median = lower.lastKey();

                long leftCost = (long) median * lowerSize - lowerSum;
                long rightCost = upperSum - (long) median * upperSize;

                cost[start] = leftCost + rightCost;
            }
        }

        return cost;
    }

    private void add(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    private void remove(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);

        if (count == 1) {
            map.remove(val);
        } else {
            map.put(val, count - 1);
        }
    }
}