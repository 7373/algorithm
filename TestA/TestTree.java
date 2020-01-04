package algorithmUtils.TestA;

import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * *********************************************************************
 * <br/>
 * @Title TestTree

 * @date 2019/2/16 16:44
 * *********************************************************************
 */
public class TestTree {



    @Test
    public  void test(){
        a a=new a();
        a.setF(true);
        a.setStr("123");
        List<String> list1 = Lists.newArrayList();
        list1.add("1");
        list1.add("1");
        list1.add("2");
        list1.add("3");
        a.setStringList(list1);
        a b=a;
        System.out.println(a==b);
        System.out.println(b.isF()+b.getStr()+ Arrays.toString(b.getStringList().toArray()));
    }
     class a{
        boolean f;
        String str;
        List<String> stringList;
        public boolean isF() {
            return f;
        }

        public void setF(boolean f) {
            this.f = f;
        }

         public String getStr() {
             return str;
         }

         public void setStr(String str) {
             this.str = str;
         }

         public List<String> getStringList() {
             return stringList;
         }

         public void setStringList(List<String> stringList) {
             this.stringList = stringList;
         }
     }
}
