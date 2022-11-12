import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

public class Streaming {
    public static <T> List<Pair<Integer, T>> encode(Stream<T> stream) {
        return stream
                .<List<Pair<Integer, T>>>collect(
                        ArrayList::new,
                        (acc, t) -> {
                            if (acc.isEmpty()) {
                                acc.add(new Pair<Integer, T>(1, t));
                                return;
                            }
                            Pair<Integer, T> last = acc.get(acc.size() - 1);
                            if (last.getSnd().equals(t)) {
                                last.setFst(last.getFst() + 1);
                            } else {
                                acc.add(new Pair<>(1, t));
                            }
                        },
                        (pair1, pair2) -> {
                            if (pair1.isEmpty()) {
                                pair1.addAll(pair2);
                                return;
                            }
                            Pair<Integer, T> last = pair1.get(pair1.size() - 1);
                            Pair<Integer, T> first = pair2.get(0);
                            if (last.getSnd().equals(first.getSnd())) {
                                last.setFst(last.getFst() + first.getFst());
                                pair2.remove(0);
                            }
                            pair1.addAll(pair2);
                        });
    }
}