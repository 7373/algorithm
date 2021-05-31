package acm;

import java.util.HashSet;

public class Solution12 {

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        System.out.println(getMaxLong("aabaab!bb"));
    }
    public static int getMaxLong(String s){


        HashSet<Character> set=new HashSet<>();
        int res=0;
        for(int i=0;i<s.length();++i){
            if (set.contains(s.charAt(i))){
                for(int j=i-set.size();j<i;++j){
                    if(set.contains(s.charAt(i))){
                        set.remove(s.charAt(j));
                    }
                }
            }
            set.add(s.charAt(i));

            res=Math.max(res,set.size());
        }
        return res;
    }

    /**
     *
     */
}
