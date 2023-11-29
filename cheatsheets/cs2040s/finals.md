## Sorting
| Algorithm | Best Time Complexity     | Average Time Complexity    | Worst Time Complexity | InPlace | Stable |
| --------- | ------------------------ | -------------------------- | --------------------- | ------- | ------ |
| Bubble    | $O(n)$ comp, $O(1)$ swap | $O(n^2)$ comp, swap        | $O(n^2)$ comp, swap   | Yes     | Yes    |
| Selection | $O(n^2)$, $O(1)$ swap    | $O(n^2)$ comp, $O(n)$ swap | $O(n^2)$, O(n) swap   | Yes     |
| Insertion | $O(n)$, $O(1)$ swap      | $O(n^2)$ comp, swap        | $O(n^2)$ comp, swap   | Yes     | Yes    |
| Merge     | $O(n \log(n))$           | $O(n \log(n))$             | $O(n \log(n))$        | No      | Yes    |
| Quick     | $O(n \log(n))$           | $O(n \log(n))$             | $O(n^2)$              | Yes     | No     |
| Counting  | $O(n + k)$               | $O(n + k)$                 | $O(n + k)$            |
| Radix     | $O(nk)$                  | $O(nk)$                    | $O(nk)$               | No      | Yes    |
### Bubble
Invariant: After `i` iterations, largest `i` elements are sorted at the back of the array
```java
private static void swap(int[] arr, int a, int b) {
    int tmp = arr[a]; arr[a] = arr[b]; arr[b] = tmp; }

static void sort(int[] input) {
    int N = input.length;
    for(; N > 0; N--) {
        for (int i = 0; i < N-1; i++) {
            if (input[i] > input[i+1]) swap(input, i, i+1);
        } } } 
```

### Selection
Invariant: After `i` iterations, smallest `i` elements are sorted to the front of the array
```java
static void sort(int[] input) {
    for (int i = 0; i < input.length; i++) {
        int smallest = i;
        for (int j = i; j < input.length; j++) {
            if (input[smallest] > input[j]) smallest = j;
        }
        swap(input, i, smallest); } }
```
### Insertion
Invariant: At the end of kth iteration, leftmost `k` elements are sorted (but not in final correct position)

1. Outer loop executes N-1 times
2. inner loop runs O(n) if arrayis reverse sorted

```java
static void sort(int[] arr) {
    // iterate through each element in the array
    for (int i = 1; i < arr.length; i++) {
        int tmp = arr[i];
        if (tmp > arr[i - 1]) continue;
        int j = i;
        for(; j > 0; j--) {
            arr[j] = arr[j-1];
            if (tmp > arr[j-1]) break;
        }
        arr[j] = tmp; } }
```
### Merge
1. O(N log N) performance guarentee, no matter the original ordering of input
2. Not inplace
3. Needs N space
### Quick Sort
```java
int partition(array A, int I, int J) {
    int p = a[i];
    int m = i; // S1 and S2 are empty
    for (int k = i+1; k <=j k++) { // explore unknown
        if ((A[k] < p) || ((A[k] == p) && (rand()%2==0))) {
            ++m;
            swap(A[k], A[m]); // exchange 2 values
        }
    }
    swap(A[i], A[m]); // put pivot in center
    return m; // return index of pivot
}

void quickSort(array A, int low, int high) {
    if (low >= high) return;
    int m = partition(a, low, high);
    quickSort(A, low, m-1);
    quickSort(A, m+1, high);
}
```
## Linked List

## Binary Heap
## Union Find
## Hash Table
## Binary Search Trees
## Graph Structures
## Graph Traversal
### Topo Sorting
#### Lexographic Kahn's Algorithm $O(V\log(V)+E)$
Non Lexographic Variant is O(V+E)
```python
from heapq import heappush, heappop
def topoSort(AL):
    V = len(AL)
    in_deg = [0] * V # Build the indegree of each vertex
    for u in range(V):
        for w in AL[u]:
            in_deg[w] += 1
    q = [] # Push all elements with indegree 0 to be processed
    for i in range(V):
        if in_deg[i] == 0: heappush(q, i)
    result = []
    count = 0 # to check for cycles
    while q:
        u = heappop(q) # normal push / pop for non lexographic variants
        result.append(u)
        count +=1
        for w in AL[u]:
            in_deg[w] -= 1
            if in_deg[w] == 0: heappush(q, w)
    if count != V: 
        return []
    return res
```
## Single Source Shortest Path
### Bellman Ford Algorithm $O(V\times E)$
```python
def bellman_ford(AL, numV, start):
    dist = [INF for _ in range(V)]
    dist[start] = 0
    for _ in range(0, V-1):
        modified = False
        for u in range(0, V):
            if (dist[u] != INF):
                for v, w in AL[u]:
                    if(dist[u]+w >= dist[v]): continue
                    dist[v] = dist[u]+w
                    modified = True
        if (not mofified): break
    hasNegativeCycle = False
    for _ in range(0, V):
        if (dist[u] != INF):
            for v, w in AL[u]:
                if (dist[v] > dist[u]+w): hasNegativeCycle = True
    if hasNegativeCycles: print("Negative cycles")
    for u in range(V):
        print(start, u, dist[u])
```
### Breadth First Search
### Dijkstra's Algorithm $O((V+E)\log Vk)$
#### Modified Dijkstra's Algorithm
### Dynamic Programming
## Minimum Spanning Tree
### Kruskal's Algorithm $O(E\log(V))$
If the weight of the edges is bounded, then you can use counting sort to bring the complexity down to O(E)
```python
def kruskalMST(EL, numV, numE): # edge list has (weight, from, to)
    EL.sort()
    UF = UnionFind(numV)
    count = 0
    cost = 0
    for i in range(E):
        if count == V-1: break
        w, u, v = EL[i]
        if (not UF.isSameSet(u, v)):
            num_taken += 1
            mst_cost += w
            UF.unionSet(u, v)
    print(cost)
```
### Prim's Algorithm
```python
def prims(AL, numV):
    pq = []
    taken = [0 for i in range(numV)]
    cost = 0
    count =0 
    taken[0] = 1
    for v,w in AL[0]: heappush(pq, (w, v)) # starting elements into the heap
    while pq:
        if count = numV-1: break
        w, u = heappop(pq)
        if not taken[u]:
            taken[u] = 1
            cost += w
            count += 1
            for v, w in AL[u]: 
                if not taken[v]: heappush(pq, (w, v))
    print(cost)
```
