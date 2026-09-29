class Solution {
    int m, n;
    char[][] grid;
    Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        if ((m + n - 1) % 2 != 0) return false;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;

        memo = new Boolean[m][n][m + n];

        return dfs(0, 0, 0);
    }

    private boolean dfs(int i, int j, int balance) {
        if (balance < 0) return false;

        int remaining = (m - 1 - i) + (n - 1 - j);

        if (balance > remaining + 1) return false;

        if (memo[i][j][balance] != null) {
            return memo[i][j][balance];
        }

        int nextBalance = balance + (grid[i][j] == '(' ? 1 : -1);

        if (nextBalance < 0) {
            return memo[i][j][balance] = false;
        }

        if (i == m - 1 && j == n - 1) {
            return memo[i][j][balance] = (nextBalance == 0);
        }

        boolean result = false;

        if (i + 1 < m) {
            result = dfs(i + 1, j, nextBalance);
        }

        if (!result && j + 1 < n) {
            result = dfs(i, j + 1, nextBalance);
        }

        return memo[i][j][balance] = result;
    }
}