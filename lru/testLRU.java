package algorithmUtils.lru;

import org.testng.annotations.Test;

import java.util.Arrays;

public class testLRU {



    @Test
    public void testLRF(){
        LRULinkedHashSet<String> LRULinkedHashSet=new LRULinkedHashSet<>(2);

        LRULinkedHashSet.add("1");
         System.out.println(Arrays.toString(LRULinkedHashSet.toArray()));
        LRULinkedHashSet.add("2");
        System.out.println(Arrays.toString(LRULinkedHashSet.toArray()));

        LRULinkedHashSet.add("3");
        System.out.println(Arrays.toString(LRULinkedHashSet.toArray()));

        LRULinkedHashSet.add("4");
        System.out.println(Arrays.toString(LRULinkedHashSet.toArray()));


    }
}
