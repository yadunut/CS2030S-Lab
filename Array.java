/**
 * The Array<T> for CS2030S 
 *
 * @author Yadunand Prem
 * @version CS2030S AY21/22 Semester 2
 */

class Array<T extends Comparable<T>> { 
  private T[] array;

  public Array(int size) {
    // The only way to add values to `array` is via set(), and we can only put 
    // objects of type T via that method. Thus, it is safe to cast Comparable[] 
    // to T[].
    @SuppressWarnings({"unchecked", "rawtypes"})
    T[] temp = (T[]) new Comparable[size];
    this.array = temp;
  }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return this.array[index];
  }

  public T min() {
    T result = this.array[0];
    for (T i: this.array) {
      if (i.compareTo(result) < 0) {
        result = i;
      }
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
