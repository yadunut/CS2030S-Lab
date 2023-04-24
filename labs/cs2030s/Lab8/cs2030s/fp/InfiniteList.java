package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A list of infinite items.
 * 
 * @author Yadunand Prem
 * @version CS2030S AY 22/23 Sem 1
 */

public class InfiniteList<T> {
  private Memo<Actually<T>> head;
  private Memo<InfiniteList<T>> tail;

  public static final End END = new End();

  /**
   * end() returns the END element.
   * 
   * @param <T> The type of each element in the list. Does not matter as it's the
   *            end element
   * @return the End Element
   */
  public static <T> InfiniteList<T> end() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> result = (InfiniteList<T>) END;
    return result;
  }

  /**
   * A private constructor for InfiniteList. Use generate / iterate to generate
   * 
   * @param head The head element of the list, of type T
   * @param tail The tail of the list, which is an infinite list
   */
  private InfiniteList(Memo<Actually<T>> head, Memo<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * Generates a list by calling prod.init()
   * 
   * @param <T>  The type of each element in the list
   * @param prod The producer to generate a list of items for the infinite list
   * @return The infiniteList with a generator for the tail
   */
  public static <T> InfiniteList<T> generate(Constant<T> prod) {
    Memo<Actually<T>> head = Memo.from(() -> Actually.ok(prod.init()));
    Memo<InfiniteList<T>> tail = Memo.from(() -> generate(prod));
    return new InfiniteList<>(head, tail);
  }

  /**
   * Generates a list by calling the iterator with the seed value.
   * 
   * @param <T>  The type of each element in the list
   * @param seed The initial value to be passed to the iterator
   * @param func The function to generate the next value in the InfiniteList
   * @return The infiniteList
   */
  public static <T> InfiniteList<T> iterate(T seed, Immutator<T, T> func) {
    Memo<Actually<T>> head = Memo.from(Actually.ok(seed));
    Memo<InfiniteList<T>> tail = Memo.from(() -> iterate(func.invoke(seed), func));
    return new InfiniteList<>(head, tail);
  }

  /**
   * Gets the first valid value of the list.
   * 
   * @return Returns of type T
   */
  public T head() {
    return this.head.get().except(() -> this.tail.get().head());
  }

  /**
   * Returns the closest tail element of the list with a valid head.
   * 
   * @return The InfiniteList tail
   */

  public InfiniteList<T> tail() {
    return this.head.get().transform(h -> this.tail.get()).except(() -> this.tail.get().tail());
  }

  /**
   * Transforms the values from type T to type R lazily.
   * 
   * @param <R> The type of each element in the returned list
   * @param f   The function used to map from type T to R
   * @return The InfiniteList with elements of type R
   */
  public <R> InfiniteList<R> map(Immutator<? extends R, ? super T> f) {
    Memo<Actually<R>> head = Memo.from(() -> this.head.get().transform(f));
    Memo<InfiniteList<R>> tail = Memo.from(() -> this.tail.get().map(f));
    return new InfiniteList<R>(head, tail);
  }

  /**
   * Filters the value in the infinite list based on the predicate provided
   * lazily.
   * 
   * @param pred The predicate to filter the values on
   * @return The InfiniteList with filtered elements
   */
  public InfiniteList<T> filter(Immutator<Boolean, ? super T> pred) {
    Memo<Actually<T>> head = Memo.from(() -> this.head.get().check(pred));
    Memo<InfiniteList<T>> tail = Memo.from(() -> this.tail.get().filter(pred));
    return new InfiniteList<>(head, tail);
  }

  /**
   * Limits the length of the InfiniteList, converting it to a finite
   * InfiniteList.
   * 
   * @param n The size to limit the list to
   * @return The Finite infinite list of size n
   */
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

  /**
   * Takes values from the InfiniteList until predicate evaluates to false lazily.
   * 
   * @param pred The predicate to decide whether to stop taking values
   * @return The Maybe (if predicate is always true) InfiniteList of elements
   */
  public InfiniteList<T> takeWhile(Immutator<Boolean, ? super T> pred) {
    Memo<Actually<T>> head = Memo.from(() -> Actually.ok(this.head()).check(pred));
    Memo<InfiniteList<T>> tail = Memo.from(() -> {
      // if head is err, then return return end
      return head.get()
          .transform(h -> this.tail().takeWhile(pred))
          .unless(InfiniteList.end());
    });

    return new InfiniteList<>(head, tail);
  }

  /**
   * Converts the InfiniteList to a List type.
   * Warning: If list is not limited, this function will cause a stack overflow.
   * 
   * @return The finite list of elements converted to a List
   */
  public List<T> toList() {
    return this.reduce(new ArrayList<>(), (acc, i) -> {
      acc.add(i);
      return acc;
    });
  }

  /**
   * Reduces the elements to type U by calling acc on each element of the list,
   * with the initial value of id.
   * Warning: If list is not limited, this function will cause a stack overflow.
   * 
   * @param <U> The return type of the function
   * @param id  The initial value to be passed to the accumulator
   * @param acc The function used to reduce the values in the list. Initial value
   *            will be head of list and id
   * @return Returns U by repeatedly calling acc on each element of the list
   */
  public <U> U reduce(U id, Combiner<U, U, ? super T> acc) {
    return this.head.get()
        .transform((h) -> this.tail.get()
            .reduce(acc.combine(id, this.head.get().unless(null)), acc))
        .except(() -> this.tail.get().reduce(id, acc));

  }

  /**
   * Counts the number of elements in the list.
   * Warning: If list is not limited, this function will cause a stack overflow.
   * 
   * @return The size of the list
   */
  public long count() {
    return this.reduce(0L, (a, b) -> a + 1L);
  }

  /**
   * Function to check if its the end of the list.
   * 
   * @return returns true if its the end of the list
   */
  public boolean isEnd() {
    return false;
  }

  @Override
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
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

    @Override
    public <U> U reduce(U id, Combiner<U, U, ? super Object> acc) {
      return id;
    }

    @Override
    public InfiniteList<Object> takeWhile(Immutator<Boolean, ? super Object> pred) {
      return InfiniteList.end();
    }

  }

}