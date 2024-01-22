import cs2030s.fp.Immutator;
import cs2030s.fp.Memo;
import cs2030s.fp.Combiner;
import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper around a lazily evaluated and memoized
 * list that can be generated with a lambda expression.
 *
 * @author Adi Yoga S. Prabawa
 * @version CS2030S AY 22/23 Sem 1
 */
class MemoList<T> {
  /** The wrapped java.util.List object */
  private List<Memo<T>> list;

  /**
   * A private constructor to initialize the list to the given one.
   *
   * @param list The given java.util.List to wrap around.
   */
  private MemoList(List<Memo<T>> list) {
    this.list = list;
  }

  /**
   * Generate the content of the list. Given x and a lambda f,
   * generate the list of n elements as [x, f(x), f(f(x)), f(f(f(x))),
   * ... ]
   *
   * @param <T>  The type of the elements in the list.
   * @param n    The number of elements.
   * @param seed The first element.
   * @param f    The immutator function on the elements.
   * @return The created list.
   */
  public static <T> MemoList<T> generate(int n, T seed, Immutator<? extends T, ? super T> f) {
    MemoList<T> memoList = new MemoList<>(new ArrayList<>());
    Memo<T> curr = Memo.from(seed);
    for (int i = 0; i < n; i++) {
      memoList.list.add(curr);
      curr = curr.transform(f);
    }
    return memoList;
  }

  /**
   * Generate the content of a list, given the size of the list, the first to elements fst, snd and Combiner f, a function to combine fst and snd to generate the next value
   *
   * @param <T> The type of elements in the list.
   * @param n   The number of elements.
   * @param fst The first element.
   * @param snd The second element.
   * @param f   The combiner function go generate the next value based on the previous 2 values
   * @return    The created list.
   */

  public static <T> MemoList<T> generate(int n, T fst, T snd, Combiner<? extends T, ? super T, ? super T> f) {
    MemoList<T> memoList = new MemoList<>(new ArrayList<>());
    Memo<T> firstMemo = Memo.from(fst);
    Memo<T> secondMemo = Memo.from(snd);
    memoList.list.add(firstMemo);
    for (int i = 1; i < n; i++) {
      memoList.list.add(secondMemo);
      Memo<T> temp = secondMemo;
      secondMemo = firstMemo.combine(secondMemo, f);
      firstMemo = temp;
    }
    return memoList;
  }

  /**
   * Generate a new list given an immutator function, which is invoked on each value in the initial list.
   *
   * @param <R>       The return type of the elements in the list.
   * @param immutator The immutator function on the elements.
   * @return          The created list.
   */

  public <R> MemoList<R> map(Immutator<? extends R, ? super T> immutator) {
    MemoList<R> memoList = new MemoList<>(new ArrayList<>());
    for (int i = 0; i < this.list.size(); i++) {
      memoList.list.add(this.list.get(i).transform(immutator));
    }
    return memoList;
  }

  /**
   * Generate a new list given an immutator function, which returns a list. The list is then added to the returning list
   *
   * @param <R>       The return type of the elements in the list.
   * @param immutator The immutator function on the elements, which returns a MemoList.
   * @return          The created list.
   */

  public <R> MemoList<R> flatMap(Immutator<MemoList<R>, ? super T> immutator) {
    MemoList<R> memoList = new MemoList<>(new ArrayList<>());
    for (int i = 0; i < this.list.size(); i++) {
      MemoList<R> temp = immutator.invoke(this.get(i));
      for (int j = 0; j < temp.list.size(); j++) {
        memoList.list.add(temp.list.get(j));
      }
    }
    return memoList;
  }

  /**
   * Return the element at index i of the list.
   *
   * @param i The index of the element to retrieved (0 for the 1st element).
   * @return The element at index i.
   */
  public T get(int i) {
    return this.list.get(i).get();
  }

  /**
   * Find the index of a given element.
   *
   * @param v The value of the element to look for.
   * @return The index of the element in the list. -1 is element is not in the
   *         list.
   */
  public int indexOf(T v) {
    return this.list.indexOf(Memo.from(() -> v));
  }

  /**
   * Return the string representation of the list.
   *
   * @return The string representation of the list.
   */
  @Override
  public String toString() {
    return this.list.toString();
  }
}
