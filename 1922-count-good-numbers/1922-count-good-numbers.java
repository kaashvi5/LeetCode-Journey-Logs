class Solution {
    static final long MOD = 1_000_000_007;

    public int countGoodNumbers(long n) {

        long even = (n + 1) / 2;
        long odd = n / 2;

        long ans = (power(5, even) * power(4, odd)) % MOD;

        return (int) ans;
    }

    private long power(long base, long exponent) {

        long result = 1;

        while (exponent > 0) {

            // If exponent is odd
            if (exponent % 2 == 1) {
                result = (result * base) % MOD;
            }

            base = (base * base) % MOD;
            exponent /= 2;
        }

        return result;
    }
}