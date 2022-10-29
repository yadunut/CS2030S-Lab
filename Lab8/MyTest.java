import java.util.ArrayList;
import java.util.List;

import cs2030s.fp.InfiniteList;

public class MyTest {

    public static void main(String[] args) {
        List<Integer> generateHistory = new ArrayList<>();
        List<Integer> doublerHistory = new ArrayList<>();
        InfiniteList.generate(() -> {
            generateHistory.add(1);
            return 1;
        }).map(x -> {
            doublerHistory.add(x);
            return x * 2;
        }).tail().head();
        System.out.println(generateHistory);
        System.out.println(doublerHistory);
        System.out.println(InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).head());
    }
}
