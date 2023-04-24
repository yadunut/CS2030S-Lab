/open Pair.java
/open Streaming.java

Streaming.evenTriangular(5).count();
Streaming.evenTriangular(5).forEach(System.out::println);
Streaming.evenTriangular(5).toArray();
Streaming.evenTriangular(10).forEach(System.out::println);
Streaming.evenTriangular(5).collect(Collectors.toList());
