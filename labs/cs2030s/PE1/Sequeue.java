public class Sequeue<T> extends Pair<T, Queueable<T>> implements Queueable {

    public Sequeue(T fst, Queueable<T> snd) {
        super(fst, snd);
        // TODO Auto-generated constructor stub
    }

    public enq(T e) {
        return new Sequeue<>(getFst(), getSnd().enq(e));
    }

}