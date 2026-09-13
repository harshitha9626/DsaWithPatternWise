class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int maxOverlap = 0;

        // Try every possible vertical shift
        for (int dx = -(n - 1); dx <= n - 1; dx++) {

            // Try every possible horizontal shift
            for (int dy = -(n - 1); dy <= n - 1; dy++) {

                int overlap = 0;

                // Compare every cell
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {

                        int x = i + dx;
                        int y = j + dy;

                        // Check whether shifted position is inside img2
                        if (x >= 0 && x < n &&
                            y >= 0 && y < n) {

                            if (img1[i][j] == 1 && img2[x][y] == 1) {
                                overlap++;
                            }
                        }
                    }
                }

                maxOverlap = Math.max(maxOverlap, overlap);
            }
        }

        return maxOverlap;
    }
}