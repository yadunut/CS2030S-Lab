import cs2030s.fp.Immutator
import cs2030s.fp.Saveable

Saveable<String> u = Saveable.of("PE2")
u.map(x -> x + "!")
u.map(x -> x + "!").map(x -> x + "?")

u.map(x -> x + "!").undo()
u.map(x -> x + "!").undo().map(x -> x + "?")
u.map(x -> x + "!").map(x -> x + "?").undo()
u.map(x -> x + "!").undo().map(x -> x + "?").undo()
u.map(x -> x + "!").map(x -> x + "?").undo().undo()
u.map(x -> x + "!").map(x -> x + "?").undo().undo().undo()
u.undo()

u.map(x -> x + "!").undo()
u.map(x -> x + "!").undo().redo()
u.map(x -> x + "!").undo().redo().map(x -> x + "?")
u.map(x -> x + "!").undo().map(x -> x + "?").redo()
u.map(x -> x + "!").map(x -> x + "?").undo().redo()
u.map(x -> x + "!").undo().map(x -> x + "?").undo().redo()
u.map(x -> x + "!").map(x -> x + "?").undo().undo().redo()
u.map(x -> x + "!").map(x -> x + "?").undo().undo().redo().redo()
u.map(x -> x + "!").undo().redo().redo()

Immutator<Integer,Object> hash = o -> o.hashCode();
Saveable<Number> x = Saveable.<Number>of(4).map(hash); // should compile
