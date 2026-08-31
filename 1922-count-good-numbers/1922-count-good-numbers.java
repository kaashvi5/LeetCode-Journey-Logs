class Solution {
    static final long MOD = 1_000_000_007;

    public int countGoodNumbers(long n) {

        long even = (n + 1) / 2;
        long odd = n / 2;

        long ans = (power(5, even)%MOD * power(4, odd)%MOD) % MOD;

        return (int) ans;
    }

    private long power(long base, long exponent) {

        if(exponent==0){
            return 1;
        }

        //long half=power(base,exponent/2);
        if(exponent%2==0){
            return  power((base*base)%MOD,exponent/2)%MOD;
        }
        return (base*power(base,exponent-1))%MOD;
    }
}