/open Pair.java
/open Streaming.java

List<Integer> intList1 = List.of(
  1, 1, 1, 2, 2, 1, 1, 1, 1, 4, 3, 3
)
List<Integer> intList2 = List.of(
  1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
)
List<String> strList1 = List.of(
  "A", "A", "A", "B", "B", "A",
  "A", "A", "A", "R", "Z", "Z"
)
List<String> strList2 = List.of(
  "A", "A", "A", "A", "A", "A",
  "A", "A", "A", "A", "A", "A"
)
List<Pair<Integer,Integer>> pairLst1 = List.of(
  new Pair<Integer, Integer>(3, 1),
  new Pair<Integer, Integer>(2, 2),
  new Pair<Integer, Integer>(4, 1),
  new Pair<Integer, Integer>(1, 4),
  new Pair<Integer, Integer>(2, 3)
)
List<Pair<Integer,Integer>> pairLst2 = List.of(
  new Pair<Integer, Integer>(12, 1)
)
List<Pair<Integer,String>> pairLst3 = List.of(
  new Pair<Integer, String>(3, "A"),
  new Pair<Integer, String>(2, "B"),
  new Pair<Integer, String>(4, "A"),
  new Pair<Integer, String>(1, "R"),
  new Pair<Integer, String>(2, "Z")
)
List<Pair<Integer,String>> pairLst4 = List.of(
  new Pair<Integer, String>(12, "A")
)

Streaming.decode(pairLst1.stream())
Streaming.decode(pairLst2.stream())
Streaming.decode(pairLst3.stream())
Streaming.decode(pairLst4.stream())

Streaming.decode(pairLst1.stream()).equals(intList1)
Streaming.decode(pairLst2.stream()).equals(intList2)
Streaming.decode(pairLst3.stream()).equals(strList1)
Streaming.decode(pairLst4.stream()).equals(strList2)

Streaming.decode(pairLst1.stream()).equals(intList2)
Streaming.decode(pairLst2.stream()).equals(intList1)
Streaming.decode(pairLst3.stream()).equals(strList2)
Streaming.decode(pairLst4.stream()).equals(strList1)
