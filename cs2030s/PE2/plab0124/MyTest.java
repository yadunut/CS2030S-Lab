import cs2030s.fp.Saveable;
import cs2030s.fp.Immutator;
import java.util.stream.Stream;
import java.util.List;

class MyTest {
  public static void main(String[] args){
   Immutator<Saveable<Integer>, Object> hash = o -> Saveable.<Integer>of(o.hashCode() + 10); 
   Saveable.<Number>of(4).flatMap2(hash);
  }
}
