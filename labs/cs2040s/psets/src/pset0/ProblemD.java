package pset0;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
public class ProblemD {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> result = Arrays.stream(sc.nextLine().split("/")).map(Integer::new).collect(Collectors.toList());
        if (result.get(0) > 12) {
            System.out.println("EU");
        }
        else if (result.get(1) > 12) {
            System.out.println("US");
        } else {
            System.out.println("either");
        }
    }
}
