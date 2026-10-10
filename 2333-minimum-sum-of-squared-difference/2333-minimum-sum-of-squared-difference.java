class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {

        long operations = (long) k1 + k2;
        int n = nums1.length;

        int[] diff = new int[n];
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        // No need to perform more operations than the total difference.
        long totalDiff = 0;
        for (int d : diff) {
            totalDiff += d;
        }

        if (operations >= totalDiff) {
            return 0;
        }

        // Count how many differences have each value.
        long[] freq = new long[maxDiff + 1];

        for (int d : diff) {
            freq[d]++;
        }

        // Reduce the largest differences level by level.
        for (int d = maxDiff; d > 0 && operations > 0; d--) {

            long count = freq[d];
            if (count == 0) {
                continue;
            }

            long cost = count;

            // Can we reduce all differences at level d by one?
            if (operations >= cost) {
                freq[d] -= count;
                freq[d - 1] += count;
                operations -= cost;
            } else {
                // Reduce only some of them.
                freq[d] -= operations;
                freq[d - 1] += operations;
                break;
            }
        }

        long sum = 0;

        for (int d = 1; d <= maxDiff; d++) {
            sum += freq[d] * d * d;
        }

        return sum;
    }
}