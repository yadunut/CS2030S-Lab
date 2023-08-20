package pset1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class falcondive {
    public static void main(String[] args) throws IOException {
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String[] nums = bi.readLine().split(" ");
        int M = Integer.parseInt(nums[0]);
        int N = Integer.parseInt(nums[1]);
        char c = nums[2].strip().charAt(1);

        char[][] frame1 = new char[M][N];
        char[][] frame2 = new char[M][N];

        for (int y = 0; y < M; y++) {
            frame1[y] = bi.readLine().toCharArray();
        }
        bi.readLine();
        for (int y = 0; y < M; y++) {
            frame2[y] = bi.readLine().toCharArray();
        }

        int x1 = -1, x2 = -1, y1 = -1, y2 = -1;
        boolean b = false;

        for (int y = 0; y < M; y++) {
            if (b) {
                break;
            }
            for (int x = 0; x < N; x++) {
                if (frame1[y][x] == c) {
                    if (x1 == -1) {
                        x1 = x;
                        y1 = y;
                    }
                }
                if (frame2[y][x] == c) {
                    if (x2 == -1) {
                        x2 = x;
                        y2 = y;
                    }
                }
                if (x1 != -1 && x2 != -1) {
                    b = true;
                    break;
                }
            }
        }
        int deltaX = x2 - x1, deltaY = y2 - y1;
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                if (x - deltaX < 0 || y - deltaY < 0 || x - deltaX >= N || y - deltaY >= M) {
                } else {
                    if (frame2[y - deltaY][x - deltaX] == c) {
                        pw.print(c);
                        continue;
                    }
                }
                pw.print(frame1[y][x] != c ? frame1[y][x] : frame2[y][x]);
            }
            pw.println();
        }
        pw.flush();
    }
}
