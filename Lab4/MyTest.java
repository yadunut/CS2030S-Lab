class MyTest {
  public static void main(String[] args) { 
    Probably<Integer> maybeInt = Probably.just(10);
    System.out.println( maybeInt.transform(new Immutator<Integer,Integer>() {
      public Integer invoke(Integer t1) {
        return t1+1;
      }
    }));
  }
}
