class And implements Cond {
  private Cond lVal;
  private Cond rVal;

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