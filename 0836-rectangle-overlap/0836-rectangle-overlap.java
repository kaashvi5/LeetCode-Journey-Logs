class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        
        // No overlap if one rectangle is completely
        // to the left/right/above/below the other
        if (rec1[2] <= rec2[0] ||   // rec1 is left
            rec2[2] <= rec1[0] ||   // rec2 is left
            rec1[3] <= rec2[1] ||   // rec1 is below
            rec2[3] <= rec1[1]) {   // rec2 is below
            return false;
        }

        return true;
    }
}