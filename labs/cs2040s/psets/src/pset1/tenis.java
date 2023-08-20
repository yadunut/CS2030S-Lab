package pset1;

import java.util.Arrays;
import java.util.Scanner;

public class tenis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] names = sc.nextLine().split(" ");
        int federer = Arrays.asList(names).indexOf("federer");
        int numMatches = Integer.parseInt(sc.nextLine());
        while (numMatches-- > 0) {
            System.out.println(handleMatch(sc.nextLine(), federer) ? "da" : "ne");
        }
    }

    public static boolean handleMatch(String match, int federer) {
        int[][] sets = Arrays.stream(match.split(" ")).map(s -> Arrays.stream(s.split(":")).mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);
        if (sets.length > 3) {
            return false;
        }

        int left = 0, right = 0;
        for (int i = 0; i < sets.length; i++) {
            if (i == 2) {
                // handle condition where set 3 is not needed to be played
                if (left == 2 || right == 2) {
                    return false;
                }
            }
            int[] sorted = Arrays.stream(sets[i]).sorted().toArray();
            int a = sorted[0], b = sorted[1];
            if (i < 2 && b > 7) {
                return false;
            }
            if ((b - a >= 2 && b >= 6) || (i <= 1 && (a == 6 && b == 7))) {
                if (sets[i][0] > sets[i][1]) {
                    left++;
                } else {
                    right++;
                }
            } else {
                return false;
            }

        }
        if (left < 2 && right < 2) {
            return false;
        }
        if (left > 2 || right > 2) {
            return false;
        }
        if (federer == -1) {
            return true;
        }
        if (federer == 0 && right > 0) {
            return false;
        }
        if (federer == 1 && left > 0) {
            return false;
        }
        return true;
    }

}


