/**
 * Represents a conditional type.
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 */
interface Cond {
  /**
   * Evaluates the given conditional.
   * 
   * @return the boolean result of the evaluation
   */
  boolean eval();

  /**
   * negates the value of the conditional, without evaluating it.
   * 
   * @return a new conditional, with the negated value
   */
  Cond neg();
}
