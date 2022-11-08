import cs2030s.fp.Saveable;
import java.util.NoSuchElementException;

class Test1 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    i.expectCompile("Saveable<String> u = Saveable.of(\"PE2\") compiles",
        "cs2030s.fp.Saveable<String> u = cs2030s.fp.Saveable.of(\"PE2\")",
        true);

    i.expectReturn("Saveable.of(\"PE2\").toString()",
        () -> Saveable.of("PE2").toString(),
        "Saveable[PE2]");

    i.expectReturn("Saveable.of(\"PE2\").map(x -> x + \"!\");",
        () -> Saveable.of("PE2").map(x -> x + "!").toString(),
        "Saveable[PE2!]");

    i.expectReturn("Saveable.of(\"PE2\").map(x -> x + \"!\").map(x -> x + \"?\");",
        () -> Saveable.of("PE2").map(x -> x + "!").map(x -> x + "?").toString(),
        "Saveable[PE2!?]");

    i.expectReturn("Saveable.of(\"PE2\").map(x -> x + \"!\").undo()",
        () -> Saveable.of("PE2").map(x -> x + "!").undo().toString(),
        "Saveable[PE2]");

    i.expectReturn("Saveable.of(\"PE2\").map(x -> x + \"!\").undo().map(x -> x + \"?\")",
        () -> Saveable.of("PE2").map(x -> x + "!").undo().map(x -> x + "?").toString(),
        "Saveable[PE2?]");

    i.expectReturn("Saveable.of(\"PE2\").map(x -> x + \"!\").undo().map(x -> x + \"?\").undo()",
        () -> Saveable.of("PE2").map(x -> x + "!").undo().map(x -> x + "?").undo().toString(),
        "Saveable[PE2]");

    i.expectReturn("Saveable.of(\"PE2\").map(x -> x + \"!\").map(x -> x + \"?\").undo()",
        () -> Saveable.of("PE2").map(x -> x + "!").map(x -> x + "?").undo().toString(),
        "Saveable[PE2!]");

    i.expectReturn("Saveable.of(\"PE2\").map(x -> x + \"!\").map(x -> x + \"?\").undo().undo()",
        () -> Saveable.of("PE2").map(x -> x + "!").map(x -> x + "?").undo().undo().toString(),
        "Saveable[PE2]");

    i.expectException("Saveable.of(\"PE2\").map(x -> x + \"!\").map(x -> x + \"?\")" +
        ".undo().undo().undo()",
        () -> Saveable.of("PE2").map(x -> x + "!").map(x -> x + "?").undo().undo().undo(),
        new NoSuchElementException());

    i.expectException("Saveable.of(\"PE2\").undo()",
        () -> Saveable.of("PE2").undo(),
        new NoSuchElementException());

    // with redo
    i.expectReturn("Saveable.of(\"PE2\").map(x -> x + \"!\").undo()",
        () -> Saveable.of("PE2").map(x -> x + "!").undo().toString(),
        "Saveable[PE2]");

    i.expectReturn("Saveable.of(\"PE2\").map(x -> x + \"!\").undo().redo()",
        () -> Saveable.of("PE2").map(x -> x + "!").undo().redo().toString(),
        "Saveable[PE2!]");

    i.expectReturn("Saveable.of(\"PE2\").map(x -> x + \"!\").undo().redo().map(x -> x + \"?\")",
        () -> Saveable.of("PE2").map(x -> x + "!").undo().redo().map(x -> x + "?").toString(),
        "Saveable[PE2!?]");

    i.expectException("Saveable.of(\"PE2\").map(x -> x + \"!\").undo().map(x -> x + \"?\")" +
        ".redo()",
        () -> Saveable.of("PE2").map(x -> x + "!").undo().map(x -> x + "?").redo(),
        new NoSuchElementException());

    i.expectReturn("Saveable.of(\"PE2\").map(x -> x + \"!\").map(x -> x + \"?\").undo().redo()",
        () -> Saveable.of("PE2").map(x -> x + "!").map(x -> x + "?").undo().redo().toString(),
        "Saveable[PE2!?]");

    i.expectReturn("Saveable.of(\"PE2\").map(x -> x + \"!\").undo().map(x -> x + \"?\").undo().redo()",
        () -> Saveable.of("PE2").map(x -> x + "!").undo().map(x -> x + "?").undo().redo().toString(),
        "Saveable[PE2?]");

    i.expectReturn("Saveable.of(\"PE2\").map(x -> x + \"!\").map(x -> x + \"?\")" +
        ".undo().undo().redo()",
        () -> Saveable.of("PE2").map(x -> x + "!").map(x -> x + "?").undo().undo().redo()
        .toString(),
        "Saveable[PE2!]");

    i.expectReturn("Saveable.of(\"PE2\").map(x -> x + \"!\").map(x -> x + \"?\")" +
        ".undo().undo().redo().redo()",
        () -> Saveable.of("PE2").map(x -> x + "!").map(x -> x + "?").undo().undo().redo().redo()
        .toString(),
        "Saveable[PE2!?]");

    i.expectException("Saveable.of(\"PE2\").map(x -> x + \"!\").undo().redo().redo()",
        () -> Saveable.of("PE2").map(x -> x + "!").map(x -> x + "?").undo().redo().redo(),
        new NoSuchElementException());

    i.expectCompile("Saveable<Number> x = Saveable.<Number>of(4).map(hash); compiles",
        "cs2030s.fp.Immutator<Integer,Object> hash = o -> o.hashCode();\n" +
        "cs2030s.fp.Saveable<Number> x = cs2030s.fp.Saveable.<Number>of(4).map(hash);",
        true);
  }
}
