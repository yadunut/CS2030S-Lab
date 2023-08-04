import java.util.Scanner;

public class ProblemC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.nextInt() + sc.nextInt() != sc.nextInt()) System.out.println("wrong!");
        else System.out.println("correct!");
    }
}
