import java.util.stream.Collectors;
import java.util.List;

class Test5 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    i.expectReturn("Streaming.isPerfect(4)",
        () -> Streaming.isPerfect(4),
        false);

    i.expectReturn("Streaming.isPerfect(5)",
        () -> Streaming.isPerfect(5),
        false);

    i.expectReturn("Streaming.isPerfect(6)",
        () -> Streaming.isPerfect(6),
        true);

    i.expectReturn("Streaming.isPerfect(7)",
        () -> Streaming.isPerfect(7),
        false);

    i.expectReturn("Streaming.isPerfect(8)",
        () -> Streaming.isPerfect(8),
        false);

    i.expectReturn("Streaming.isPerfect(26)",
        () -> Streaming.isPerfect(26),
        false);

    i.expectReturn("Streaming.isPerfect(27)",
        () -> Streaming.isPerfect(27),
        false);

    i.expectReturn("Streaming.isPerfect(28)",
        () -> Streaming.isPerfect(28),
        true);

    i.expectReturn("Streaming.isPerfect(29)",
        () -> Streaming.isPerfect(29),
        false);

    i.expectReturn("Streaming.isPerfect(30)",
        () -> Streaming.isPerfect(30),
        false);

    i.expectReturn("Streaming.allPerfect(1, 10)",
        () -> Streaming.allPerfect(1, 10),
        List.of(6));

    i.expectReturn("Streaming.allPerfect(1, 100)",
        () -> Streaming.allPerfect(1, 100),
        List.of(6, 28));
  }
}
