package algorithmUtils.acm;
import java.util.*;
    public class Main {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int a, b;
            while(scanner.hasNext()) {
//                int count = Integer.parseInt(scanner.nextLine());
                String str = scanner.nextLine();
                a = scanner.nextInt();
                b = scanner.nextInt();
                getff(a,b);
                System.out.println(a + b);
            }
        }


       public static void  getff(int a,int b){

            for(int i = a;i<=b;i++){
//                System.out.print(i);
                if(i%3==0&&i%5==0){
                    System.out.println("foobar");
                }
              else  if(i%3==0){
                    System.out.println("foo");
                }
               else if(i%5==0){
                    System.out.println("bar");
                }
//                else  if(i%7==0){
//                    System.out.print("foobar");
//                }
                else {
                    System.out.println(i);
                }


            }
        }
}
