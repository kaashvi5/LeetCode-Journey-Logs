class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        String digits = "123456789";
        List<Integer> result = new ArrayList<>();
        
        for (int length = 2; length <= 9; length++) {
            for (int start = 0; start + length <= 9; start++) {
                String sub = digits.substring(start, start + length);
                int num = Integer.parseInt(sub);
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        Collections.sort(result);
        return result;
    }
}