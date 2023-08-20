package demos;

import java.util.*;

public class rankproblem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int teams = sc.nextInt(); int matches = sc.nextInt(); sc.nextLine();

        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 1; i <=teams; i++) {
            result.add(i);
        }

        while(matches-- > 0) {
            String[] line = sc.nextLine().split(" ");
            int i = Integer.parseInt(line[0].substring(1));
            int j = Integer.parseInt(line[1].substring(1));
            int n = result.indexOf(i);
            int m = result.indexOf(j);

            if (n > m) {
                result.remove(m);
                result.add(n, j);
            }
        }
        result.forEach(t -> System.out.print("T" + t + " "));
    }
}
