package cs2030s.fp;

public abstract class Actually<T> implements Immutatorable<T>, Actionable<T> {
  public abstract T unwrap() throws Exception;

  public abstract T except(Constant<? extends T> c);

  public abstract void finish(Action<? super T> action);

  public abstract T unless(T other);

  public abstract <R> Actually<R> next(Immutator<Actually<R>, ? super T> immutator);

  public static <T> Actually<T> ok(T value) {
    return new Success<T>(value);
  }

  public static <T> Actually<T> err(Exception e) {
    Actually<T> failure = (Actually<T>) new Failure(e);
    return failure;
  }

  private static class Success<T> extends Actually<T> {
    private final T value;

    private Success(T value) {
      this.value = value;
    }

    @Override
    public T unwrap() {
      return this.value;
    }

    @Override
    public T except(Constant<? extends T> c) {
      return this.value;
    }

    @Override
    public void finish(Action<? super T> action) {
      action.call(this.value);
    }

    @Override
    public T unless(T other) {
      return this.value;
    }

    @Override
    public <R> Actually<R> next(Immutator<Actually<R>, ? super T> immutator) {
      try {
        return immutator.invoke(this.value);
      } catch (Exception e) {
        return Actually.err(e);
      }
    }

    @Override
    public <R> Immutatorable<R> transform(Immutator<? extends R, ? super T> immutator) {
      try {
        return Actually.ok(immutator.invoke(this.value));
      } catch (Exception e) {
        return Actually.err(e);
      }
    }

    @Override
    public void act(Action<? super T> action) {
      action.call(this.value);
    }

    @Override
    public String toString() {
      return "<" + value + ">";
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Success<?>) {
        Success<?> other = (Success<?>) obj;
        if (this.value == other.value) {
          return true;
        }
        if (this.value != null && this.value.equals(other.value)) {
          return true;
        }
      }
      return false;
    }

  }

  private static class Failure extends Actually<Object> {
    private final Exception e;

    Failure(Exception e) {
      this.e = e;
    }

    @Override
    public Object unwrap() throws Exception {
      throw e;
    }

    @Override
    public Object except(Constant<? extends Object> c) {
      return c.init();
    }

    @Override
    public Object unless(Object other) {
      return other;
    }

    @Override
    public void finish(Action<? super Object> action) {
      return;
    }

    @Override
    public <R> Immutatorable<R> transform(Immutator<? extends R, ? super Object> immutator) {
      return Actually.err(this.e);
    }

    @Override
    public <R> Actually<R> next(Immutator<Actually<R>, ? super Object> immutator) {
      return Actually.err(this.e);

    }

    @Override
    public void act(Action<? super Object> action) {
      // Do nothing
      return;
    }

    @Override
    public String toString() {
      return "[" + e.getClass().getName() + "] " + e.getMessage();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj instanceof Failure) {
        Failure other = (Failure) obj;
        if (this.e == other.e) {
          return true;
        }
        if (this.e == null || other.e == null) {
          return false;
        }
        if (this.e.getMessage() == null || other.e.getMessage() == null) {
          return false;
        }
        return this.e.getMessage() == other.e.getMessage();
      }
      return false;
    }

  }

}
