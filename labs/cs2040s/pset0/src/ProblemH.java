import java.util.Scanner;

public class ProblemH {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lectures = 0;
        int awake = 0;
        sc.nextLine();
        String[] result = sc.nextLine().split("");
        for (String r : result) {
            if (r.equals("0")) {
                if (awake == 0) continue;
                else {
                    awake --;
                    lectures++;
                }
            } else {
                awake = 2;
                lectures++;
            }
        };
        System.out.println(lectures);
    }
}
