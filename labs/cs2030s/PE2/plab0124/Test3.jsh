import cs2030s.fp.Immutator;
import cs2030s.fp.Saveable;

Immutator<Saveable<Integer>, Integer> f = x -> Saveable.of(x).map(y -> y + 1).map(y -> y + 10);
Saveable<Integer> zero = Saveable.of(0)
zero.flatMap1(f)
zero.flatMap1(f).flatMap1(f)

zero.flatMap1(f).undo()
zero.flatMap1(f).undo().undo()
zero.flatMap1(f).flatMap1(f).undo()
zero.flatMap1(f).flatMap1(f).undo().undo()
zero.flatMap1(f).flatMap1(f).undo().undo().undo()

zero.flatMap2(f)
zero.flatMap2(f).flatMap2(f)

zero.flatMap2(f).undo()
zero.flatMap2(f).undo().undo()
zero.flatMap2(f).flatMap2(f).undo()
zero.flatMap2(f).flatMap2(f).undo().undo()
zero.flatMap2(f).flatMap2(f).undo().undo().undo()

Immutator<Saveable<Integer>, Object> hash = o -> Saveable.<Integer>of(o.hashCode() + 10);
Saveable.<Number>of(4).flatMap1(hash); // should compile
Saveable.<Number>of(4).flatMap2(hash); // should compile
