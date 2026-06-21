class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int[] freq = new int[100001];

        for (int cost : costs) {
            freq[cost]++;
        }

        int count = 0;

        for (int cost = 1; cost <= 100000; cost++) {
            if (freq[cost] == 0) continue;

            int canBuy = Math.min(freq[cost], coins / cost);

            count += canBuy;
            coins -= canBuy * cost;

            if (coins < cost) break;
        }

        return count;
    }
}