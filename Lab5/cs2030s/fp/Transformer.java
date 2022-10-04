package cs2030s.fp;

public abstract class Transformer<R, P> implements Immutator<R, P> {
  public <N> Transformer<R, N> after(Transformer<P, N> g) {
    Transformer<R, P> f = this;
    return new Transformer<R, N>() {
      @Override
      public R invoke(N param) {
        return f.invoke(g.invoke(param));
      }
    };
  }

  public <T> Transformer<T, P> before(Transformer<T, R> g) {
    Transformer<R, P> f = this;
    return new Transformer<T, P>() {
      @Override
      public T invoke(P param) {
        return g.invoke(f.invoke(param));
      }
    };
  }
}
