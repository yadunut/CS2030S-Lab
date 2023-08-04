import java.util.Scanner;

public class ProblemA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int a = sc.nextInt();
            System.out.println(a / 4.0);
        }
    }
}