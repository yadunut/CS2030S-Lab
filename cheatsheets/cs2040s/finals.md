## Sorting
| Algorithm | Best Time Complexity     | Average Time Complexity    | Worst Time Complexity | InPlace | Stable |
| --------- | ------------------------ | -------------------------- | --------------------- | ------- | ------ |
| Bubble    | $O(n)$ comp, $O(1)$ swap | $O(n^2)$ comp, swap        | $O(n^2)$ comp, swap   | Yes     | Yes    |
| Selection | $O(n^2)$, $O(1)$ swap    | $O(n^2)$ comp, $O(n)$ swap | $O(n^2)$, O(n) swap   | Yes     | No     |
| Insertion | $O(n)$, $O(1)$ swap      | $O(n^2)$ comp, swap        | $O(n^2)$ comp, swap   | Yes     | Yes    |
| Merge     | $O(n \log(n))$           | $O(n \log(n))$             | $O(n \log(n))$        | No      | Yes    |
| Quick     | $O(n \log(n))$           | $O(n \log(n))$             | $O(n^2)$              | Yes     | No     |
| Counting  | $O(n + k)$               | $O(n + k)$                 | $O(n + k)$            |
| Radix     | $O(nk)$                  | $O(nk)$                    | $O(nk)$               | No      | Yes    |

Counting: O(N+k), 

Radix: $O(w\times (N+k))$: Each item to sort as a string of w digits, sort by rightmost digit (using stable counting sort), k is radix
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
- Stack: Last In First Out, Push and Pop from Head
- Queue - First In First Out, Push to tail, pop from head
## Binary Heap
- Height: floor$(\log N)$, Left Node: $2n$, Right Node: $2n+1$, parent node = $\frac{n}{2}$
- Min Comparisions: n-1, min swaps: 0
- Max comparisons: 2(n) - 2(no of 1s) - 2(trailing 0 bits)
- Max swaps: $\frac{n}{2}$ until `x=1` then sum

```pseudocode
insert(node) {
    arr.append(node)
    bubbleUp(arr)
}
bubbleUp(node) {
    while(A[i] > A[parent(i)]) {
        swap(A[i], A[parent(v)]);
        v = parent(i);
    }
}
bubbleDown(node) {
    A[1] = A[A.length-1]
    i = 1; A.length--;
    while(i < A.length)
        if A[i] < (L = larger of i's children) {
            swap(A[i], L); i = L
        }
}
optimised heapify() {
    for (i = A.length/2; i >= 1; --i) shiftDown(i);
}
```
- Change value at index
  - Update value
  - If new value > old node, then bubbleUp, else bubbleDown
- Root element is the largest, 2nd largest element is child of root, 3rd largest doesn't have to be
- Max swaps: n / 2 integer divide until x = 1 and then sum
- Max comparisons: 2n - 2(num of 1s) - 2(trailing 0s)

### Invariants
- Every vertex is greater than every value of its children
- Must be a complete binary tree
- Height is always log(N), as every level has to be filled
## Union Find $O(\alpha N)$
- MIN height needed  = $2^h$ nodes
- Keep track of rank[i], uppperbound height of a subtree rooted at vertex i
- `findSet(i)`: From vertex i, recursively go up the tree, until root of tree. Path compression used after each call of findSet
- `IsSameSet(i,j)`: Check if `FindSet(i) == FindSet(j)`
- `unionSet(i,j)`: Shorter tree joined to the taller tree (rank comes into play)
```python
def union(a,b):
    ap = findSet(a)
    bp = findSet(b)
    if rank[ap] < rank[bp]: parents[ap] = bp
    elif rank[bp] < rank[ap]: parents[bp] = ap
    else: parent[bp] = ap; rank[ap] += 1
```
## Hash Table
The hash table size (modulo) sould be a large prime about 2x size of expected number of keys
```python
def hash_string(s): 
    sum = 0
    for c in s: sum = (sum*26)%M + ord(c)%M
    return sum
```
- Open Addressing: All keys in single array, collision resolved by probing alternative address
- Closed Addressing: Adjacency List, append collided keys to auxiliary data structure
### Linear
`i = (h(v) + k*1)%M`
- On deletion set the value to removed, it knows that the values after it are valid during probing
- If the values tend to cluster, linear probing is bad
### Quadratic
`i = (h(v) + k*k)%M` 
- Issues is that we might have infinite cycles with this scheme. 
- If $\alpha < 0.5$ and M is prime, then we can always find empty slot
### Double Hashing
`i = (h(v) + k*h2(v))`, h2 is usually a smaller prime than M.
## Binary Search Trees
- Height: $O (\log_2(N)) < h < N$
- BST Property: For vertex X, all vertices on the left subtree of X are strictly smaller than X and all vertices on the right subtree are strictly greater than X
- `minHeight=ceil(log(n))-1`
- `maxHeight=n-1`

```python
def search(target):
  if curr == None: return None
  if curr == target: return curr
  if curr < target: return search(curr.right)
  return search(curr.left)
def left(node): return node if node.left == None else return left(node.left)
def successor(node):
  if node.right != null: return left(node.right)
  p = node.parent, T = node
  while(p != null and T == p.right): T = p, p = T.parent
  if p == null: return None
  return p
def remove(node, key):
  if node == None: return None
  if node.val < key: node.right = remove(node.right, key)
  if node.val > key: node.left = remove(node.left, key)
  if node.left == None and node.right == None:
    node == None
  elif node.left == None and node.right != None:
    node.right.parent = node.parent
    node = node.right
  elif node.right == None and node.left != None:
    node.left.parent = node.parent
    node = node.left
  else: # both nodes exist, find successor
    successorV = successor(key)
    node.val = successorV # replace this with successor
    delete(T.right, successor) # delete successor
```
```python
def validBst(node, minV, maxV):
  if not node: return True
  if node.val <= minV or node.val >= maxV: 
    return False
  left = validBst(node.left, minV, node.value)
  right = validBst(node.right, node.value, maxV)
  return left and right
```
## BBST AVL Tree
- each node augmented with height
- height: $h < 2\log(N)$
- `height=-1` (if empty tree), `height=max(left.h, right.h)+1`, computed at end of `insert(v)` or `remove(v)` operation
- `BF(n)=left.h-right.h`
- height balanced IF $|$left.h $-$right.h$|\leq 1$
```python
def h(node): node.height if node else -1
def rotateLeft(node):
  if node.right == None: return node
  w = node.right
  w.parent = node.parent
  node.parent = w
  if (w.left != None): w.left.parent = node
  w.left = node
  node.height = max(h(node.left), h(node.right))+1
  w.height = max(h(w.left), h(w.right))+1
  return w

def balance(node):
  bal = h(node.left) - h(node.right)
  if bal == 2: # left heavy
    bal2 = h(node.left.left)-h(node.left.right)
    if bal2 != 1: node.left = rotateLeft(node.left)
    node = rotateRight(node)
  elif bal == -2:
    bal2 = h(node.right.left)-h(node.right.right)
    if bal2 != -1: node.right = rotateRight(node.right)
    node = rotateLeft(node)
def insert(node, val):
  if node == None: return Node(val)
  if node.key < val:
    node.right = insert(node.right, v)
    node.right.parent = node
  if node.key > val:
    node.left = insert(node.left, v)
    node.left.parent = node
  node = balance(node)
  node.height = max(h(node.left), h(node.right)) +1
  return node
```
### Invariant
## Graph Structures
### Trees
- V vertices, E = V-1 edges, acyclic, 1 unique path between any pair of vertices
- Root a tree by running BFS/DFS from the root
```python
def isTree(AL, directed=True):
  def dfs(node, visited, parent):
    visited[node] = True
    for neighbour in AL[node]:
      if not visited[neighbour]:
        if dfs(neighbour, visited, node): return True
      elif neighbour != parent: return True
    return False
  n = len(AL)
  visited = [False]*n
  if dfs(0, visited, -1):
    return False #cycle detected.
  if False in visited:
    return False # unconnected
  edges = sum(len(n) for n in AL.values())
  if directed:
    if edges != n-1: return False
  else:
    if edges != 2*(n-1): return False
  return True
```
### Complete Graph
- $V$ vertices, $E=\frac{V\times(V-1)}{2}$ edges
```python
def isComplete(AL):
  n = len(AL)
  for i in range(n):
    for j in range(i+1, n):
      if j not in AL[i]: return False
```
### Bipartite
- V vertices that can be split to 2 sets, where no edge between members of same set
- no odd length cycle
- Complete Bipartite: all V from one set connected to all V from other set. 
```python
def isBipartite(AL):
  n = len(AL)
  colors = [-1]*n
  for start in range(n): # incase it isn't connected
    if colors[start] == -1:
      q = deque([(start,0)]) # (vertex,color)
      colors[start] = 0
      while q:
        curr, color = q.popleft()
        for neighbour in AL[curr]:
          if colors[neighbour] == -1:
            colors[neighbour] = 1-color # flip color
            q.append((neighbour, 1-color))
          elif colors[neighbour] == color:
            return False
  return True
```
### DAG
- Directed, no cycle
```python
def isDag(AL):
  def dfs(node, visited, stack):
    visited[node] = True
    stack[node] = True
    for n in AL[node]:
      if not visited[n]:
        if dfs(n, visited, stack):
          return True # pass it down
      elif stack[n]:
        return True # back edge found 
    stack[node] = False
    return False
  n = len(AL)
  visited = [False]*n
  stack = [False]*n
  for i in range(n):
    if not visited[i]:
      if dfs(i, visited,stack):
        return False
  return True
```
```python
def isDagBfs(AL):
  n = len(AL)
  inDeg = [0]*n
  for nbs in AL:
    for nb in nbs:
      inDeg[nb] += 1
  q = deque([v for v in range(n) if inDeg[v] == 0]) # queue of 0 indeg
  while q:
    curr = q.popleft()
    for nb in AL[curr]:
      inDeg[nb] -= 1
      if inDeg[nb] == 0: q.append(nb)
  return all(i == 0 for i in inDeg) # all inDeg == 0
```
### Storage
- Adjacency Matrix - Check existance of edge in $O(1)$. Storage $O(V^2)$
- Adjacency List - Storage $O(V+E)$
- Edge List - Storage $O(E)$
## Graph Traversal
- Reachability Test: DFS/BFS and check if visited
- ID CCs: run DFS on a vertex. all visited=true is in same CC
- count CCs: for all u in V, if unvisited, incr CCCount, then DFS
### Lexographic Kahn's Algorithm (Topo Sort) $O(V\log(V)+E)$
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
```python
def relax(from,to, weight):
  if dist[v] > dist[u]+weight: # can be shortened
    dist[v] = d[u]+weight
    path[v] = u
    return True
  return False
```
- On unweighted graphs: BFS
- On graphs without negative weight: Dijkstra
- On graphs without negative weight cycles: Modified Dijkstra
- On Tree: BFS/DFS
- On DAG: DP
### Bellman Ford Algorithm $O(V\times E)$
- Can detect negative weight cycles
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
Instead of standard visited array, replace with dist[] array, where initial distances are infinite. Then set source to 0. if D[v] = inf, then set it to d[u]+1
### Dijkstra's Algorithm $O((V+E)\log Vk)$
- BFS with a priority queue
```python
def dijkstra(AL, start):
  V = len(AL)
  dist = [float('inf') for u in range(V)]
  dist[start] = 0
  pq = []
  heappush(pq, (0, start))
  while pq:
    dist, u = heappop(pq)
    if (dist > dist[u]): continue
    for v, w in AL[u]:
      if dist[u]+w >= dist[v]: continue
      dist[v] = dist[u]+w
      heappush(pq, (dist[v], v))
```
### DFS O(V)
DFS can be used to solve SSSP on a weighted tree. Since there is only 1 unique path that connects source to another vertex, that path is teh shortest path
### Dynamic Programming O(V+E)
SSSP on a DAG. Find the topo sort and relax in the order of the topo sort.
```python
order = khan_toposort() # O(V+E)
while not order.empty():
  u = order.popleft()
  for v, w in AL[u]: relax(u, v, w)
```
## Minimum Spanning Tree
- Tree that connects to all vertices of G
### Kruskal's Algorithm $O(E\log(V))$
- sort edges, then loop over these edges, and greedily take the next edge that does not cause cycles
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
### Prim's Algorithm $O(E \log(V))$
- Starts from a vertex, and queues all edges to PQ
- if head vertex has not been visited, take that edge and repeat from 1
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
## Miscellaneous
- make a graph where the vertices are a pair <rectangle, horizontal/vertical>
- define the edges as the following
    - for each <rectangle, vertical>, make a directed edge to <neighboring rectangle in vertical direction, horizontal>
    - for each <rectangle, horizontal>, make a directed edge to <neighboring rectangle in horizontal direction, vertical>
    - (still undefined: what is the weight?)
- run multi-source shortest path, where the sources are <starting rectangle, vertical> and <starting rectangle, horizontal>
  - (still undefined: what shortest path algorithm to be used here?)
- take the shortest path to <target rectangle, vertical> as the answer
  - (is this correct? any other vertex to consider?)

### Jetpack solution (DFS)
- add initial position to queue
- while queue
  - add up if it has not been visited
  - add down if it has not been visited
- backtrack on the results to generate path

### Conquest (dijkstra)
- create pqueue of all places i can currently visit
- while pq
  - if army at pq > current army, break
  - if i've been here before, continue
  - add current to my army
  - visit current
  - add current adjacent to heap
- return armysize