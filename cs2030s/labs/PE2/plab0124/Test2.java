import cs2030s.fp.Saveable;

class Test2 {
  public static void main(String[] args) {
    CS2030STest i = new CS2030STest();

    i.expectReturn("Saveable.of(10).equals(Saveable.of(10))",
        () -> Saveable.of(10).equals(Saveable.of(10)),
        true);

    i.expectReturn("Saveable.of(10).map(x -> x + 4).equals(Saveable.of(14))",
        () -> Saveable.of(10).map(x -> x + 4).equals(Saveable.of(14)),
        true);
    i.expectReturn("Saveable.of(10).map(x -> x + 4).equals(Saveable.of(10).map(x -> x + 4))",
        () -> Saveable.of(10).map(x -> x + 4).equals(Saveable.of(10).map(x -> x + 4)),
        true);
    i.expectReturn("Saveable.of(10).map(x -> x + 4).undo().equals(Saveable.of(14))",
        () -> Saveable.of(10).map(x -> x + 4).undo().equals(Saveable.of(14)),
        false);
    i.expectReturn("Saveable.of(10).map(x -> x + 4).undo().equals(Saveable.of(10).map(x -> x + 4)" +
        ".undo())",
        () -> Saveable.of(10).map(x -> x + 4).undo().equals(Saveable.of(10).map(x -> x + 4).undo()),
        true);
    i.expectReturn("Saveable.of(10).map(x -> x + 4).undo().redo().equals(Saveable.of(10)" +
        ".map(x -> x + 4))",
        () -> Saveable.of(10).map(x -> x + 4).undo().redo().equals(Saveable.of(10).map(x -> x + 4)),
        true);
    i.expectReturn("Saveable.of(10).map(x -> x + 4).map(x -> x).equals(Saveable.of(4)" + 
        ".map(x -> x + 10)",
        () -> Saveable.of(10).map(x -> x + 4).equals(Saveable.of(4).map(x -> x + 10)),
        true);
    i.expectReturn("Saveable.of(\"hi\").equals(Saveable.of(new String(\"hi\")))",
        () -> Saveable.of("hi").equals(Saveable.of(new String("hi"))),
        true);
  }
}
