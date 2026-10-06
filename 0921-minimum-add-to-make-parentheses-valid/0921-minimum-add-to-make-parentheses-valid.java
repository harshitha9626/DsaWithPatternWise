class Solution {
    public int minAddToMakeValid(String s) {
        int balance = 0;
        int add = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                balance++;
            } else {
                balance--;
            }

            if (balance < 0) {
                add++;
                balance = 0;
            }
        }

        return add + balance;
    }
}