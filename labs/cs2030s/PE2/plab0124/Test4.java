import java.util.stream.Collectors;
import java.util.List;

class Test4 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    i.expectReturn("Streaming.evenTriangular(5).count()",
        () -> Streaming.evenTriangular(5).count(),
        5L);

    i.expectReturn("Streaming.evenTriangular(5).collect(Collectors.toList())",
        () -> Streaming.evenTriangular(5).collect(Collectors.toList()),
        List.of(6,10,28,36,66));

    i.expectReturn("Streaming.evenTriangular(10).collect(Collectors.toList())",
        () -> Streaming.evenTriangular(10).collect(Collectors.toList()),
        List.of(6,10,28,36,66,78,120,136,190,210));
  }
}
