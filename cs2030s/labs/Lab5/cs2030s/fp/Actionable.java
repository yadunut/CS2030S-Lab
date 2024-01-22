package cs2030s.fp;

/**
 * The Actionable interface that can
 * act when given an action.
 * Contains a single abstract method act.
 * CS2030S Lab 4
 * AY22/23 Semester 1
 * @author Yadunand Prem (10B)
 */

// act consumes the T and passes it to action, thus super
public interface Actionable<T> {
  public void act(Action<? super T> action);
}
