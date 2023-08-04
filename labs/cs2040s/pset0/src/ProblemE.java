import java.util.Scanner;

public class ProblemE {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        if (data.contains(":)") && data.contains(":(")) {
            System.out.println("double agent");
        } else if (data.contains(":)")) {
            System.out.println("alive");
        } else if (data.contains(":(")) {
            System.out.println("undead");
        } else {
            System.out.println("machine");
        }
    }
}
