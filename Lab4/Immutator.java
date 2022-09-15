/**
 * The Immutator interface that can transform 
 * to type T2, an object of type T1.
 *
 * Contains a single abstract method invoke.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author Yadunand Prem (10B)
 */

interface Immutator<R,P> {
  public R invoke(P param);
}
