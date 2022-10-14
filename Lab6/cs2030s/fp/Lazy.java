package cs2030s.fp;

public class Lazy<T> implements Immutatorable<T> {
  private Constant<? extends T> init;

  protected Lazy(Constant<? extends T> c) {
    this.init = c;
  }

  public T get() {
    return init.init();
  }

  public static <T> Lazy<T> from(T v) {
    return new Lazy<>(() -> v);
  }

  public static <T> Lazy<T> from(Constant<? extends T> v) {
    return new Lazy<T>(v);
  }

  @Override
  public <R> Lazy<R> transform(Immutator<? extends R, ? super T> f) {
    return Lazy.from(() -> f.invoke(this.init.init()));
  }

  public <R> Lazy<R> next(Immutator<? extends Lazy<? extends R>, ? super T> f) {

    @SuppressWarnings("unchecked")
    Lazy<R> result = (Lazy<R>) Lazy.from(() -> f.invoke(this.init.init()));

    return result;

  }

  @Override
  public String toString() {
    return this.init.init().toString();
  }

}
