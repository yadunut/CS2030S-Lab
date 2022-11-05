
import cs2030s.fp.InfiniteList;

public class MyTest {

    public static void main(String[] args) {
        InfiniteList<Integer> list = InfiniteList.iterate(1, x -> x + 1);
        System.out.println(list.takeWhile(x -> x < 10).reduce(0, (x, y) -> {
            System.out.println("vals: x: " + x + " y: " + y);
            return x + y;
        }));
    }
}
