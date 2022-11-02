package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InfiniteList<T> {
  private Memo<Actually<T>> head;
  private Memo<InfiniteList<T>> tail;

  public static final End END = new End();

  public static <T> InfiniteList<T> end() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> result = (InfiniteList<T>) END;
    return result;
  }

  private InfiniteList(Memo<Actually<T>> head, Memo<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  // You may add other private constructor but it's not necessary.

  public static <T> InfiniteList<T> generate(Constant<T> prod) {
    Memo<Actually<T>> head = Memo.from(() -> Actually.ok(prod.init()));
    Memo<InfiniteList<T>> tail = Memo.from(() -> generate(prod));
    return new InfiniteList<>(head, tail);
  }

  public static <T> InfiniteList<T> iterate(T seed, Immutator<T, T> func) {
    Memo<Actually<T>> head = Memo.from(Actually.ok(seed));
    Memo<InfiniteList<T>> tail = Memo.from(() -> iterate(func.invoke(seed), func));
    return new InfiniteList<>(head, tail);
  }

  public T head() {
    return this.head.get().except(() -> this.tail.get().head());
  }

  public InfiniteList<T> tail() {
    return this.head.get().transform(h -> this.tail.get()).except(() -> this.tail.get().tail());
  }

  public <R> InfiniteList<R> map(Immutator<? extends R, ? super T> f) {
    Memo<Actually<R>> head = Memo.from(() -> this.head.get().transform(f));
    Memo<InfiniteList<R>> tail = Memo.from(() -> this.tail.get().map(f));
    return new InfiniteList<R>(head, tail);
  }

  public InfiniteList<T> filter(Immutator<Boolean, ? super T> pred) {
    Memo<Actually<T>> head = Memo.from(() -> this.head.get().check(pred));
    Memo<InfiniteList<T>> tail = Memo.from(() -> this.tail.get().filter(pred));
    return new InfiniteList<>(head, tail);
  }

  public InfiniteList<T> limit(long n) {
    if (n <= 0) {
      return InfiniteList.end();
    }

    Memo<InfiniteList<T>> tail = Memo.from(
        () -> this.head.get()
            .transform(h -> this.tail.get().limit(n - 1))
            .except(() -> this.tail.get().limit(n)));
    return new InfiniteList<>(this.head, tail);
  }

  public InfiniteList<T> takeWhile(Immutator<Boolean, ? super T> pred) {
    // TODO
    return new InfiniteList<>(null, null);
  }

  public List<T> toList() {
    List<T> arrayList = new ArrayList<>();
    arrayList.add(this.head());
    arrayList.addAll(this.tail().toList());
    return arrayList;
  }

  public <U> U reduce(U id, Combiner<U, U, ? super T> acc) {
    // TODO
    return null;
  }

  public long count() {
    // TODO
    return 0L;
  }

  @Override
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }

  public boolean isEnd() {
    return false;
  }

  private static class End extends InfiniteList<Object> {
    private End() {
      super(null, null);
    }

    @Override
    public Object head() {
      throw new NoSuchElementException();
    }

    @Override
    public InfiniteList<Object> tail() {
      throw new NoSuchElementException();
    }

    @Override
    public boolean isEnd() {
      return true;
    }

    @Override
    public String toString() {
      return "-";
    }

    @Override
    public InfiniteList<Object> limit(long n) {
      return InfiniteList.end();
    }

    @Override
    public InfiniteList<Object> filter(Immutator<Boolean, ? super Object> pred) {
      return InfiniteList.end();
    }

    @Override
    public <R> InfiniteList<R> map(Immutator<? extends R, ? super Object> f) {
      return InfiniteList.end();
    }

    @Override
    public List<Object> toList() {
      return List.of();
    }

  }

}