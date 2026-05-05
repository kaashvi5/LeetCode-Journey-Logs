class Solution{
    public HashMap<Integer,Integer>get(int[]arr){
        HashMap<Integer,Integer>hm=new HashMap();
        for(int i=0;i<arr.length;i++){
            if(hm.containsKey(arr[i])==true){
                int oldFre=hm.get(arr[i]);
                hm.put(arr[i],oldFre+1);
            }else{
                hm.put(arr[i],1);///put method-> add when key doesn't exists and update when key exists
            }
        }
        return hm;
    }
    public int[] intersection(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer>hm1=get(nums1);
        HashMap<Integer,Integer>hm2=get(nums2);
        ArrayList<Integer>list=new ArrayList();
        for(int i=0;i<=1000;i++){
            if(hm1.containsKey(i)==true&&hm2.containsKey(i)){
                list.add(i);
            }
        }
        int[]ans=new int[list.size()];
        for(int i=0;i<list.size();i++){
            ans[i]=list.get(i);
        }
        return ans;
    }
}      