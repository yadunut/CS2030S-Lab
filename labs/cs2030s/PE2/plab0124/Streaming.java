import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CS2030S PE2 
 * AY2022/23 Semester 1.
 * 
 * @author A0253252M
 **/

public class Streaming {
  public static Stream<Integer> evenTriangular(int n) {
    return Stream.iterate(1, i -> i + 1)
      .map(i -> i * (i + 1) / 2)
      .filter(i -> i % 2 == 0)
      .limit(n);
  }
  
  // return a list of positive divisors for n
  // go down from n, 
  public static boolean isPerfect(int n) {
    return Stream.iterate(n - 1, i -> i - 1)
      .takeWhile(i -> i > 0)
      .filter(i -> n % i == 0)
      .reduce(0, (x, y) -> x + y) == n;
  }

  public static List<Integer> allPerfect(int start, int end) {
    return Stream.iterate(start, i -> i + 1)
      .takeWhile(i -> i <= end)
      .filter(i -> isPerfect(i))
      .collect(Collectors.toList());
  }
  
  public static <T> List<T> decode(Stream<Pair<Integer, T>> stream) {
    return stream.flatMap(p -> 
        Stream.generate(() -> p.getSnd()).limit(p.getFst())
        )
      .collect(Collectors.toList());
  }
}
