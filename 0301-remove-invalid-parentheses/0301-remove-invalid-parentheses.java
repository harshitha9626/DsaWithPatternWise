class Solution {

    Set<String> result = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        int[] remove = countRemovals(s);

        dfs(s, 0, 0, remove[0], remove[1], new StringBuilder());

        return new ArrayList<>(result);
    }

    private int[] countRemovals(String s) {
        int left = 0;
        int right = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        return new int[]{left, right};
    }

    private void dfs(String s, int index, int balance,
                     int leftRem, int rightRem,
                     StringBuilder current) {

        if (index == s.length()) {
            if (balance == 0 && leftRem == 0 && rightRem == 0) {
                result.add(current.toString());
            }
            return;
        }

        char c = s.charAt(index);

        if (c == '(') {

            if (leftRem > 0) {
                dfs(s, index + 1, balance,
                    leftRem - 1, rightRem, current);
            }

            current.append(c);

            dfs(s, index + 1, balance + 1,
                leftRem, rightRem, current);

            current.deleteCharAt(current.length() - 1);

        } else if (c == ')') {

            if (rightRem > 0) {
                dfs(s, index + 1, balance,
                    leftRem, rightRem - 1, current);
            }

            if (balance > 0) {
                current.append(c);

                dfs(s, index + 1, balance - 1,
                    leftRem, rightRem, current);

                current.deleteCharAt(current.length() - 1);
            }

        } else {

            current.append(c);

            dfs(s, index + 1, balance,
                leftRem, rightRem, current);

            current.deleteCharAt(current.length() - 1);
        }
    }
}