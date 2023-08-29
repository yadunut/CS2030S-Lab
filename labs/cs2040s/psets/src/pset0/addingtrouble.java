package pset0;

import java.util.Scanner;

public class addingtrouble {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.nextInt() + sc.nextInt() != sc.nextInt()) System.out.println("wrong!");
        else System.out.println("correct!");
    }
}
