import cs2030s.fp.Immutator;
import cs2030s.fp.Saveable;
import java.util.NoSuchElementException;

class Test3 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    System.out.println("Immutator<Saveable<Integer>, Integer> f = " +
        "x -> Saveable.of(x).map(y -> y + 1).map(y -> y + 10);");
    Immutator<Saveable<Integer>, Integer> f = 
        x -> Saveable.of(x).map(y -> y + 1).map(y -> y + 10);

    i.expectReturn("Saveable.of(0).flatMap1(f)",
        () -> Saveable.of(0).flatMap1(f).toString(),
        "Saveable[11]");

    i.expectReturn("Saveable.of(0).flatMap1(f).flatMap1(f)",
        () -> Saveable.of(0).flatMap1(f).flatMap1(f).toString(),
        "Saveable[22]");

    i.expectReturn("Saveable.of(0).flatMap1(f).undo()",
        () -> Saveable.of(0).flatMap1(f).undo().toString(),
        "Saveable[1]");

    i.expectReturn("Saveable.of(0).flatMap1(f).undo().undo()",
        () -> Saveable.of(0).flatMap1(f).undo().undo().toString(),
        "Saveable[0]");

    i.expectReturn("Saveable.of(0).flatMap1(f).flatMap1(f).undo()",
        () -> Saveable.of(0).flatMap1(f).flatMap1(f).undo().toString(),
        "Saveable[12]");

    i.expectReturn("Saveable.of(0).flatMap1(f).flatMap1(f).undo().undo()",
        () -> Saveable.of(0).flatMap1(f).flatMap1(f).undo().undo().toString(),
        "Saveable[11]");

    i.expectException("Saveable.of(0).flatMap1(f).flatMap1(f).undo().undo().undo()",
        () -> Saveable.of(0).flatMap1(f).flatMap1(f).undo().undo().undo().toString(),
        new NoSuchElementException());

    i.expectReturn("Saveable.of(0).flatMap2(f)",
        () -> Saveable.of(0).flatMap2(f).toString(),
        "Saveable[11]");

    i.expectReturn("Saveable.of(0).flatMap2(f).flatMap2(f)",
        () -> Saveable.of(0).flatMap2(f).flatMap2(f).toString(),
        "Saveable[22]");

    i.expectReturn("Saveable.of(0).flatMap2(f).undo()",
        () -> Saveable.of(0).flatMap2(f).undo().toString(),
        "Saveable[0]");
    i.expectException("Saveable.of(0).flatMap2(f).undo().undo()",
        () -> Saveable.of(0).flatMap2(f).undo().undo(),
        new NoSuchElementException());

    i.expectReturn("Saveable.of(0).flatMap2(f).flatMap2(f).undo()",
        () -> Saveable.of(0).flatMap2(f).flatMap2(f).undo().toString(),
        "Saveable[11]");

    i.expectReturn("Saveable.of(0).flatMap2(f).flatMap2(f).undo().undo()",
        () -> Saveable.of(0).flatMap2(f).flatMap2(f).undo().undo().toString(),
        "Saveable[0]");

    i.expectException("Saveable.of(0).flatMap2(f).flatMap2(f).undo().undo().undo()",
        () -> Saveable.of(0).flatMap2(f).flatMap2(f).undo().undo().undo().toString(),
        new NoSuchElementException());

    System.out.println("Immutator<Saveable<Integer>, Object> hash = " +
        "o -> Saveable.<Integer>of(o.hashCode() + 10);");
    Immutator<Saveable<Integer>, Object> hash = o -> Saveable.<Integer>of(o.hashCode() + 10);

    i.expectCompile("Saveable.<Number>of(4).flatMap1(hash);",
        "cs2030s.fp.Immutator<cs2030s.fp.Saveable<Integer>, Object> hash = " + 
        "o -> cs2030s.fp.Saveable.<Integer>of(o.hashCode() + 10);\n" +
        "cs2030s.fp.Saveable.<Number>of(4).flatMap1(hash);",
        true);
    i.expectCompile("Saveable.<Number>of(4).flatMap2(hash);",
        "cs2030s.fp.Immutator<cs2030s.fp.Saveable<Integer>, Object> hash = " +
        "o -> cs2030s.fp.Saveable.<Integer>of(o.hashCode() + 10);\n" +
        "cs2030s.fp.Saveable.<Number>of(4).flatMap2(hash);",
        true);
  }
}
