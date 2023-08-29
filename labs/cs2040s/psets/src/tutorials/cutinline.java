package tutorials;
/*
Yadunand Prem
A0253252M
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class cutinline {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        ArrayList<String> names = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            names.add(sc.nextLine());
        }
        int C = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < C; i++) {
            String tokens[] = sc.nextLine().split(" ");
            switch (tokens[0]) {
                case "leave": {
                    names.remove(tokens[1]);
                    break;
                }
                case "cut": {
                    names.add(names.indexOf(tokens[2]), tokens[1]);
                }
            }
        }

        names.forEach(System.out::println);
    }
}
