/**
 * CS2030S PE2 
 * AY2022/23 Semester 2.
 * 
 * @author A0253252M
 **/
class Pair<T,S> {
  private T fst;
  private S snd;
  public Pair(T fst, S snd) {
    this.fst = fst;
    this.snd = snd;
  }
  public T getFst() {
    return this.fst;
  }
  public S getSnd() {
    return this.snd;
  }
  public void setFst(T fst) {
    this.fst = fst;
  }
  public void setSnd(S snd) {
    this.snd = snd;
  }
  @Override
  public String toString() {
    return "(" + this.fst + ", " + this.snd + ")";
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if(!(obj instanceof Pair<?,?>)) {
      return false;
    }
    Pair<?,?> pr = (Pair<?,?>) obj;
    return this.fst.equals(pr.fst) &&
           this.snd.equals(pr.snd);
  }
}
