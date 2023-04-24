/**
 * CS2030S PE2 
 * AY2022/23 Semester 1.
 * 
 * @author A0253252M
 **/

package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Saveable is a container which allows us to have an undo / redo history over the value in the box.
 * 
 * @param <T> the type of the value in the box
 **/

public class Saveable<T> {
  /**
   * The history of changes made to T.
   **/
  List<T> list;

  /**
   * The current position in history.
   **/
  int index = 0;

  private Saveable(List<T> list, int index) {
    this.list = list;
    this.index = index;
  }

  /**
   * Create a new Saveable of type T.
   * @param <T> The type of the value in Saveable
   * @param value The value to be stored in Saveable
   * @return The new Saveable created
   **/

  public static <T> Saveable<T> of(T value) {
    return new Saveable<T>(List.of(value), 0);
  }

  /**
   * Maps the value in Saveable and creates a "checkpoint". 
   * This will allow for undo to be called on it. 
   * @param mapper The function to modify the value in the Saveable
   * @return The new Saveable created with a undo history attached
   **/
  public Saveable<T> map(Immutator<? extends T, ? super T> mapper) {
    T newVal = mapper.invoke(this.get());

    List<T> newList = new ArrayList<>();
    for (int i = 0; i <= this.index; i++) {
      newList.add(this.list.get(i));
    }

    newList.add(newVal);
    return new Saveable<>(newList, this.index + 1);
  }

  /**
   * Undo's the last operation done on the saveable. If no previous history 
   * exists, it throws an NoSuchElementException.
   * 
   * @return The Saveable with the last map rolled back
   **/
  public Saveable<T> undo() {
    if (this.index <= 0) {
      throw new NoSuchElementException();
    }
    return new Saveable<>(this.list, this.index - 1);
  }

  /**
   * Goes back forward in time to undo the last undo. If no future history 
   * exists, it throws a NoSuchElementException.
   * 
   * @return The Saveable with the last undo rolled back
   **/
  public Saveable<T> redo() {
    if (this.index >= this.list.size() - 1) {
      throw new NoSuchElementException();
    }

    return new Saveable<>(this.list, this.index + 1);
  }

  /**
   * Flatmap1 maps the value from T to Saveable. It keeps the 
   * history of the Saveable returned by mapper
   * 
   * @param mapper The function to map from type T to Saveable
   * @return The Saveable with the last undo rolled back
   **/
  public Saveable<T> flatMap1(Immutator<
      ? extends Saveable<? extends T>, 
      ? super T> mapper) {

    // This cast is ok, as it is casting ? extends Saveable to Saveable, which
    // is ok, and also ? extends T to T, which is also ok.
    @SuppressWarnings("unchecked")
    Saveable<T> result = (Saveable<T>) mapper.invoke(this.get());

    return result;
  }

  /**
   * Flatmap2 maps the value from T to Saveable. It keeps the 
   * history of original Saveable.
   * 
   * @param mapper The function to map from type T to Saveable
   * @return The Saveable with the last undo rolled back
   **/
  public Saveable<T> flatMap2(Immutator<
      ? extends Saveable<? extends T>,
      ? super T> mapper) {

    // This cast is ok, as it is casting ? extends Saveable to Saveable, which
    // is ok, and also ? extends T to T, which is also ok.
    @SuppressWarnings("unchecked")
    Saveable<T> result = (Saveable<T>) mapper.invoke(this.get());

    Saveable<T> tmp = this.map(i -> result.get());
    return tmp;
  }

  private T get() {
    return this.list.get(this.index);
  }

  @Override
  public String toString() {
    return "Saveable[" + this.get() + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o instanceof Saveable<?>) {
      Saveable<?> tmp = (Saveable<?>) o;
      if (tmp.get() == this.get()) {
        return true;
      }
      if (tmp.get().equals(this.get())) {
        return true;
      }
    }
    return false;
  }
}
