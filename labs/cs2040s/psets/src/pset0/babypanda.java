package pset0;

import java.util.Scanner;

public class babypanda {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLong();
        System.out.println(Long.toBinaryString(sc.nextLong()).chars().filter(c -> c == '1').count());
    }
}
