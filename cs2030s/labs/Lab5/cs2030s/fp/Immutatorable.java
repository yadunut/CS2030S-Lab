package cs2030s.fp;

/**
 * The Immutatorable interface that can
 * transform when given something that is
 * Immutator.
 * Contains a single abstract method transform.
 * CS2030S Lab 4
 * AY22/23 Semester 1
 * @author Yadunand Prem (10B)
 */

public interface Immutatorable<T> {
  public <R> Immutatorable<R> transform(Immutator<? extends R, ? super T> immutator);
}
