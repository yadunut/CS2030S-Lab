package cs2030s.fp;

public class Memo<T> extends Lazy<T> {
  private Actually<T> value;

  protected Memo(Constant<? extends T> c) {
    super(c);
    // if actually is err, it is uninitialised
    this.value = Actually.<T>err(null);
  }

  protected Memo(T v) {
    super(() -> v);
    this.value = Actually.ok(v);
  }

  public static <T> Memo<T> from(T v) {
    return new Memo<T>(v);
  }

  public static <T> Memo<T> from(Constant<? extends T> v) {
    return new Memo<T>(v);
  }

  public T get() {
    T result = this.value.except(() -> super.get());
    this.value = Actually.ok(result);
    return result;
  }

  public <R, S> Memo<R> combine(Memo<? extends S> other, Combiner<? extends R, ? super T, ? super S> combiner) {
    return Memo.<R>from(() -> combiner.combine(this.get(), other.get()));
  }

  @Override
  public <R> Memo<R> transform(Immutator<? extends R, ? super T> f) {
    return Memo.<R>from(() -> f.invoke(this.get()));
  }

  public <R> Memo<R> next(Immutator<? extends Lazy<? extends R>, ? super T> f) {
    return Memo.<R>from(() -> f.invoke(this.get()).get());
  }

  @Override
  public String toString() {
    return this.value.next(t -> Actually.ok(String.valueOf(t))).unless("?");
  }
}