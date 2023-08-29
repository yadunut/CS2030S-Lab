package pset0;

import java.util.Scanner;

public class bokhyllor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int shelves = sc.nextInt();
        int shelvesCount = 0;
        int currentShelf = shelves;

        while (a != 0 || b != 0 || c != 0) {
            if (c > 0 && currentShelf >= 3) {
                c--;
                currentShelf -= 3;
            } else if (b > 0 && currentShelf >= 2) {
                b--;
                currentShelf -= 2;
            } else if (a > 0 && currentShelf >= 1) {
                a--;
                currentShelf -= 1;
            } else {
                shelvesCount++;
                currentShelf = shelves;
            }
            if (currentShelf == 0) {
                shelvesCount++;
                currentShelf = shelves;
            }
        }
        if (currentShelf != shelves) {
            shelvesCount++;
        }
        System.out.println(Math.max(shelvesCount, 2));
    }

}
