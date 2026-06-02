class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < landStartTime.length; i++) {
            for (int j = 0; j < waterStartTime.length; j++) {

                // Land -> Water
                int landFinish = landStartTime[i] + landDuration[i];
                int waterFinish = Math.max(landFinish, waterStartTime[j])
                                  + waterDuration[j];

                ans = Math.min(ans, waterFinish);

                // Water -> Land
                int waterFinishFirst = waterStartTime[j] + waterDuration[j];
                int landFinishSecond = Math.max(waterFinishFirst, landStartTime[i])
                                       + landDuration[i];

                ans = Math.min(ans, landFinishSecond);
            }
        }

        return ans;
    }
}