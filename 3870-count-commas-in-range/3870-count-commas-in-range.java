class Solution {
    public int countCommas(int n) {
        int count = 0;

        // Numbers with 4 to 6 digits have exactly 1 comma
        if (n >= 1000) {
            count += n - 999;
        }

        // Numbers with 7 to 9 digits would have 2 commas
        if (n >= 1000000) {
            count += (n - 999999) * 2;
        }

        return count;
    }
}