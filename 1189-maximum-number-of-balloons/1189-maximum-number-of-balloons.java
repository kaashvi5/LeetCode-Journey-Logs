import java.util.HashMap;

class Solution {
    public int maxNumberOfBalloons(String text) {

        HashMap<Character,Integer> hm = new HashMap<>();

        for(int i = 0; i < text.length(); i++){
            char ch = text.charAt(i);

            if(ch=='b'||ch=='a'||ch=='l'||ch=='o'||ch=='n'){
                if(hm.containsKey(ch)){
                    int old_fre = hm.get(ch);
                    hm.put(ch, old_fre+1);
                }else{
                    hm.put(ch,1);
                }
            }
        }

        int min = (int)(1e9);
        String str = "balon";   // use lowercase and no duplicates

        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            int fre = hm.getOrDefault(ch,0);

            if(ch=='l'||ch=='o'){
                min = Math.min(min, fre/2);
            }else{
                min = Math.min(min, fre);
            }
        }

        return min;
    }
}