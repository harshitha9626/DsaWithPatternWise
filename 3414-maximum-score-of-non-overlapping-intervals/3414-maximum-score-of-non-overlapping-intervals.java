import java.util.*;

class Solution {

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        // [start, end, weight, originalIndex]
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by starting position
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // next[i] = first interval whose start > arr[i][1]
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = binarySearch(arr, i + 1, n, arr[i][1]);
        }

        /*
         * dp[i][k] = maximum score starting from i
         *            with at most k intervals remaining.
         */
        long[][] dp = new long[n + 1][5];

        /*
         * best[(i * 5) + k] stores the selected original indices.
         * Maximum 4 indices.
         */
        int[][] best = new int[(n + 1) * 5][4];

        // -1 means empty slot
        for (int[] x : best) {
            Arrays.fill(x, -1);
        }

        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                // Option 1: Skip current interval
                long skipScore = dp[i + 1][k];
                int[] skipArray = best[(i + 1) * 5 + k];

                // Option 2: Take current interval
                long takeScore = arr[i][2] + dp[next[i]][k - 1];

                int[] takeArray = addIndex(
                        best[next[i] * 5 + (k - 1)],
                        arr[i][3]
                );

                int state = i * 5 + k;

                if (takeScore > skipScore) {

                    dp[i][k] = takeScore;
                    best[state] = takeArray;

                } else if (takeScore < skipScore) {

                    dp[i][k] = skipScore;
                    best[state] = copy(skipArray);

                } else {

                    // Same score → lexicographically smaller indices
                    dp[i][k] = skipScore;

                    if (compare(takeArray, skipArray) < 0) {
                        best[state] = takeArray;
                    } else {
                        best[state] = copy(skipArray);
                    }
                }
            }
        }

        int[] answer = best[4];

        // Remove -1 values
        int count = 0;
        while (count < 4 && answer[count] != -1) {
            count++;
        }

        return Arrays.copyOf(answer, count);
    }

    // Find first index j such that arr[j][0] > end
    private int binarySearch(int[][] arr, int left, int right, int end) {

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid][0] > end) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // Add index and keep indices sorted
    private int[] addIndex(int[] old, int index) {

        int[] result = new int[4];

        Arrays.fill(result, -1);

        int size = 0;

        while (size < 4 && old[size] != -1) {
            result[size] = old[size];
            size++;
        }

        result[size++] = index;

        // Sort only the selected indices
        Arrays.sort(result, 0, size);

        return result;
    }

    private int[] copy(int[] arr) {
        return Arrays.copyOf(arr, 4);
    }

    // Lexicographical comparison
    private int compare(int[] a, int[] b) {

        int sizeA = 0;
        int sizeB = 0;

        while (sizeA < 4 && a[sizeA] != -1)
            sizeA++;

        while (sizeB < 4 && b[sizeB] != -1)
            sizeB++;

        int len = Math.min(sizeA, sizeB);

        for (int i = 0; i < len; i++) {

            if (a[i] != b[i])
                return Integer.compare(a[i], b[i]);
        }

        // If one is prefix of another, shorter is smaller
        return Integer.compare(sizeA, sizeB);
    }
}