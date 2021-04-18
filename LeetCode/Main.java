package acm;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            if (str.equals("")) {
                continue;
            }
            String[] params = str.split(" ");
            int n = Integer.parseInt(params[0]), k = Integer.parseInt(params[1]);
            int[] res = new int[n];
            int start = 0, index = 0;
            if (params.length > 2) {
                start = 2;
            } else {
                params = br.readLine().split(" ");
            }
            for (int i = start; i < params.length; i++) {
                res[index++] = Integer.parseInt(params[i]);
            }
            Arrays.sort(res);
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < k; i++) {
                ans.append(res[i]).append(" ");
            }
            System.out.println(ans.toString().trim());
        }

    }
}
