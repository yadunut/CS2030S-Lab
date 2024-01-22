/**
 * Represents the And type, a conditional which returns true if both left and
 * right values are true. This operator short circuits, which means if the first
 * value is false, the right value is not evaluated
 */
class And implements Cond {
  private Cond lVal;
  private Cond rVal;

  /**
   * Constructor for the And conditional.
   * 
   * @param lVal the left value in the And Operation.
   * @param rVal This is only evaluated if lVal is true
   */
  public And(Cond lVal, Cond rVal) {
    this.lVal = lVal;
    this.rVal = rVal;
  }

  @Override
  public boolean eval() {
    if (!this.lVal.eval()) {
      return false;
    }
    return this.rVal.eval();
  }

  @Override
  public String toString() {
    return "(" + this.lVal + " & " + this.rVal + ")";
  }

  @Override
  public Cond neg() {
    return new Or(lVal.neg(), rVal.neg());
  }
}