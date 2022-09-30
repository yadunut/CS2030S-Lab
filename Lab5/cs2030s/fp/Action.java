package cs2030s.fp;
/**
 * The Action interface that can be called
 * on an object of type T to act.
 * Contains a single abstract method call.
 * CS2030S Lab 4
 * AY22/23 Semester 1
 * @author Yadunand Prem (10B)
 */

public interface Action<T> {
  void call(T item);
}
