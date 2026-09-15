class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] pal = new boolean[n][n];

        for (int r = 0; r < n; r++) {
            for (int l = r; l >= 0; l--) {
                if (s.charAt(l) == s.charAt(r) &&
                    (r - l <= 2 || pal[l + 1][r - 1])) {
                    pal[l][r] = true;
                }
            }
        }

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];

            for (int j = 0; j < i; j++) {
                if (i - j >= k && pal[j][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n];
    }
}