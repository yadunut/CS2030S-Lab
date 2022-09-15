/**
 * The Immutatorable interface that can
 * transform when given something that is
 * Immutator.
 *
 * Contains a single abstract method transform.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author Yadunand Prem (10B)
 */

interface Immutatorable<T> {
  public <R> R transform(Immutator<R, ? super T> immutator);
}
