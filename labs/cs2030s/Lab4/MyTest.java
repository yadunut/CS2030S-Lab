interface Function<T, R> {
  R apply(T t);
}

interface Functor<T, F extends Functor<?, ?>> {
  <R> F map(Function<T, R> f);
}

class Identity<T> implements Functor<T, Identity<?>> {
  private final T value;

  public Identity(T value) {
    this.value = value;
  }

  @Override
  public <R> Identity<R> map(Function<T, R> f) {
    final R result = f.apply(this.value);
    return new Identity<>(result);
  }

}

class Incr implements Immutator<Integer, Integer> {

  @Override
  public Integer invoke(Integer param) {
    return param + 1;
  }
}

class Length implements Immutator<Integer, String> {

  @Override
  public Integer invoke(String param) {
    return param.length();
  }
}

class Print implements Action<Object> {

  @Override
  public void call(Object item) {
    System.out.println(item);

  }

}

class MyTest {

  public static void main(String[] args) {
    Probably<Integer> maybeInt = Probably.just(10);
    maybeInt.act(new Print());
    System.out.println(Probably.just(2030).check(new IsModEq(0, 2)));

    Identity<String> idString = new Identity<>("abc");
    Identity<Integer> idInt = idString.map(String::length);
  }
}
