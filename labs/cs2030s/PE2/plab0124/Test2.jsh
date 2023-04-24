import cs2030s.fp.Saveable

Saveable<Integer> u = Saveable.of(10)
u.equals(u)
u.map(x -> x + 4).equals(u.map(x -> x + 4))
u.map(x -> x + 4).undo().equals(Saveable.of(14))
u.map(x -> x + 4).undo().equals(u.map(x -> x + 4).undo())
u.map(x -> x + 4).undo().redo().equals(u.map(x -> x + 4))
u.map(x -> x + 4).equals(Saveable.of(4).map(x -> x + 10))

Saveable.of("hi").equals(Saveable.of(new String("hi")));
