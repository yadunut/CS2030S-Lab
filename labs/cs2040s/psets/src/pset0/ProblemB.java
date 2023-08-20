package pset0;
import java.util.Scanner;

public class ProblemB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nums = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < nums; i++) {
            int result = Integer.parseInt(sc.nextLine().replaceAll(" ", "")) + Integer.parseInt(sc.nextLine().replaceAll(" ", ""));
            System.out.println(String.valueOf(result).replaceAll("", " ").trim());
        }
    }
}
