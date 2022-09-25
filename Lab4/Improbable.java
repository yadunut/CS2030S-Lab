/**
 * A generic Immutator that takes in an object
 * that is T and returns an object that is probably T.
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author Yadunand Prem (10B)
 */

class Improbable<T> implements Immutator<Probably<T>, T> {
  @Override
  public Probably<T> invoke(T param) {
    return Probably.just(param);
  }
}
