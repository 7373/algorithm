package acm;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * TOPk
 */
public class Solution8 {

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] topKFrequent(int[] nums, int k) {

        int[] result = new int[k];
        HashMap<Integer, Integer> filterMap = new HashMap<>();
//        TreeMap<Integer, Integer> map = new TreeMap();
        for (int temp : nums) {
            filterMap.merge(temp, 1, Integer::sum);
            /**
             *  Integer value= filterMap.get(temp);
             *             if(null!=value){
             *                 filterMap.put(temp,value+1);
             *             }
             *             else {
             *                 filterMap.put(temp,1);
             *             }
             */
        }
        return filterMap.entrySet()
                .stream()
                .sorted((m1, m2) -> m2.getValue() - m1.getValue())
                .sorted((e1,e2)->e1.getKey().compareTo(e2.getKey()))
                .limit(k)
                .mapToInt(Map.Entry::getKey)
                .toArray();
        /**
         return Arrays.stream(nums)
         .boxed()
         .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum))
         .entrySet()
         .stream()
         .sorted((m1, m2) -> m2.getValue() - m1.getValue())
         .limit(k)
         .mapToInt(Map.Entry::getKey)
         .toArray();
         */
        /**
         * 方法二 用优先队列排队  小根堆
         */
//        return nums;

        /**
         *
         * 1、Collections.sort(
         * 2、Map<String, String> map = new TreeMap<String, String>(
         *                 new Comparator<String>() {
         *                     public int compare(String obj1, String obj2) {
         *                         // 降序排序
         *                         return obj2.compareTo(obj1);
         *                     }
         *                 });
         */

    }

    @Test
    public void sortTreeMap() {
//        Map<String,String> map =new TreeMap<>((String k1, String k2) ->{
//            return k1.compareTo(k2);
//        });

        /**
         * 比较的是KEY
         */
        Map<String, String> map = new TreeMap<String, String>(
                new Comparator<String>() {
                    @Override
                    public int compare(String obj1, String obj2) {
                        // 降序排序 key
                        return obj2.compareTo(obj1);
                    }
                });
        map.put("a", "2");
        map.put("c", "5");
        map.put("d", "6");
        map.put("b", "1");
        map.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });



        //这里将map.entrySet()转换成list
        List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,String>>() {
            //升序排序
            @Override
            public int compare(Map.Entry<String, String> o1,
                               Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }

        });
        for(Map.Entry<String,String> mapping:list){
            System.out.println("");
            System.out.println(mapping.getKey()+":"+mapping.getValue());
        }
    }


    public List<String> topKFrequent(String[] words, int k) {
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((o1, o2) -> {
                    if (o1.getValue().equals(o2.getValue())) {
                        return o1.getKey().compareTo(o2.getKey());
                    } else {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                })
                .map(Map.Entry::getKey)
                .limit(k)
//                .collect()
                .collect(Collectors.toList());


//        return Stream.of(words)
//                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum))
//                .entrySet()
//                .stream()
//                .sorted((o1, o2) -> {
//                    if (o1.getValue().equals(o2.getValue())) {
//                        return o1.getKey().compareTo(o2.getKey());
//                    } else {
//                        return o2.getValue().compareTo(o1.getValue());
//                    }
//                })
//                .map(Map.Entry::getKey)
//                .limit(k)
//                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(3*0.1 == 0.3);
        String str;
        while ((str = sc.nextLine()) != null) {
            HashMap<Character, Integer> filterMap = new HashMap<>();
            for (char temp : str.toCharArray()) {
                filterMap.merge(temp, 1, Integer::sum);
            }

            List<Map.Entry<Character,Integer>> list = new ArrayList<Map.Entry<Character,Integer>>(filterMap.entrySet());
            //然后通过比较器来实现排序
            //升序排序
            Collections.sort(list, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));
            for(Map.Entry<Character,Integer> mapping:list){
                System.out.print(mapping.getKey()+""+mapping.getValue());
            }
            System.out.println("");
        }
    }

}
