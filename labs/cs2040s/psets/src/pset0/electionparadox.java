package pset0;

import java.util.Arrays;
import java.util.Scanner;

public class electionparadox {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).map(i -> -i).sorted().map(i -> -i).toArray();
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length / 2) {
                result += arr[i];
                continue;
            }
            result += arr[i]/2;
        }
        System.out.println(result);
    }
}
