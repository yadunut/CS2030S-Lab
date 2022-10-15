/**
 * Represents the Or type, a conditional which returns true if either value is
 * true. This operator short circuits, which means that if the left value is
 * true, the right value is not evaluated
 */
class Or implements Cond {
  private Cond lVal;
  private Cond rVal;

  /**
   * Constructor for the Or conditional.
   * 
   * @param lVal the left value in the Or Operation.
   * @param rVal This is only evaluated if lVal is false
   */
  public Or(Cond lVal, Cond rVal) {
    this.lVal = lVal;
    this.rVal = rVal;
  }

  @Override
  public boolean eval() {
    if (this.lVal.eval()) {
      return true;
    }
    return this.rVal.eval();
  }

  @Override
  public String toString() {
    return "(" + this.lVal + " | " + this.rVal + ")";
  }

  @Override
  public Cond neg() {
    return new And(lVal.neg(), rVal.neg());
  }
}