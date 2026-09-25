class Solution {

    int index = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parseExpression(expression);
        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);
        return answer;
    }

    private Set<String> parseExpression(String s) {
        Set<String> result = parseTerm(s);

        while (index < s.length() && s.charAt(index) == ',') {
            index++;
            result.addAll(parseTerm(s));
        }

        return result;
    }

    private Set<String> parseTerm(String s) {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length()
                && s.charAt(index) != ','
                && s.charAt(index) != '}') {

            result = combine(result, parseFactor(s));
        }

        return result;
    }

    private Set<String> parseFactor(String s) {
        Set<String> result;

        if (s.charAt(index) == '{') {
            index++;
            result = parseExpression(s);
            index++;
        } else {
            result = new HashSet<>();
            result.add(String.valueOf(s.charAt(index)));
            index++;
        }

        return result;
    }

    private Set<String> combine(Set<String> left, Set<String> right) {
        Set<String> result = new HashSet<>();

        for (String a : left) {
            for (String b : right) {
                result.add(a + b);
            }
        }

        return result;
    }
}