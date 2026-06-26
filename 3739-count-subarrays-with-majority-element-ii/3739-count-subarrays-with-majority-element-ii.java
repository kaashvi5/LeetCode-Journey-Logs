class Solution {
    
    class Fenwick {
        long[] bit;
        int n;

        Fenwick(int n) {
            this.n = n;
            bit = new long[n + 1];
        }

        void update(int idx, long val) {
            while (idx <= n) {
                bit[idx] += val;
                idx += idx & -idx;
            }
        }

        long query(int idx) {
            long sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        int[] pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + (nums[i] == target ? 1 : -1);
        }

        int[] vals = pref.clone();
        Arrays.sort(vals);

        Map<Integer, Integer> rank = new HashMap<>();
        int id = 1;

        for (int x : vals) {
            if (!rank.containsKey(x)) {
                rank.put(x, id++);
            }
        }

        Fenwick bit = new Fenwick(id);

        long ans = 0;

        for (int x : pref) {
            int r = rank.get(x);

            // count previous prefix sums < current prefix sum
            ans += bit.query(r - 1);

            bit.update(r, 1);
        }

        return ans;
    }
}