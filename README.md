CityTransport MST Project README
Project Overview
CityTransport MST is a software project designed to optimize urban transport networks by computing Minimum Spanning Trees (MSTs) on weighted graphs, representing city transport points (vertices) and routes (edges). The goal is to find the minimal set of connections covering all points with the least total cost, essential for infrastructure planning.

Theoretical Background
Graphs in Transportation Networks
A graph 
G
=
(
V
,
E
)
G=(V,E) consists of a set of vertices 
V
V (nodes/stations) and edges 
E
E (connections/routes). Each edge has a weight representing cost, distance, or travel time. This abstraction allows analyzing the connectivity and optimization of transport systems.

Minimum Spanning Tree (MST)
An MST is a subset of edges 
E
′
⊆
E
E 
′
 ⊆E that connects all vertices 
V
V without cycles and with minimum possible total edge weight:

MST
=
arg
⁡
min
⁡
E
′
∑
e
∈
E
′
w
(
e
)
MST=arg 
E 
′
 
min
  
e∈E 
′
 
∑
 w(e)
subject to 
E
′
E 
′
  connecting all vertices and forming a tree.

Applications include minimizing road construction costs and resource allocation.

Algorithms Implemented
Prim's Algorithm
Starts from a selected vertex 
v
0
v 
0
 .

Repeatedly adds the smallest weight edge that connects a vertex in the growing MST to a vertex outside it.

Uses a priority queue for efficient edge selection.

Time complexity: 
O
(
E
log
⁡
V
)
O(ElogV).

Pseudo-code:

Initialize MST with vertex 
v
0
v 
0
 .

While MST does not include all vertices:

Select edge with minimum weight connecting MST to 
V
∖
M
S
T
V∖MST.

Add that edge and the vertex to MST.

Kruskal's Algorithm
Sorts all edges by increasing weight.

Adds edges one-by-one if they do not form a cycle.

Uses Union-Find (Disjoint Set) for cycle detection.

Time complexity: 
O
(
E
log
⁡
E
)
O(ElogE).

Pseudo-code:

Sort edges in ascending order.

Initialize MST as empty.

For each edge in sorted order:

If it connects two different components (no cycle), add it to MST.

Stop when MST connects all vertices.

Running the Project
Build and Run Application
bash
mvn clean compile
mvn exec:java -Dexec.mainClass=com.citytransport.Main
Inputs are read from JSON files in input/.

Results and MST outputs are written to output/.

Running Tests
bash
mvn test
Executes two main test suites:

JSON serialization/deserialization correctness.

Algorithm correctness and performance on different sized graphs.

Detailed test results with operational counts and execution times are saved to output/mst_results.csv.

Mathematical Formulas Used
MST weight minimization:

min
⁡
∑
(
u
,
v
)
∈
T
w
(
u
,
v
)
min 
(u,v)∈T
∑
 w(u,v)
Complexity of Prim’s:

O
(
E
log
⁡
V
)
O(ElogV)
Complexity of Kruskal’s:

O
(
E
log
⁡
E
)
O(ElogE)
Project Architecture
text
citytransport-mst/
├── pom.xml                      # Maven config
├── src/
│   ├── main/java/com/citytransport/
│   │   ├── Main.java            # Program entry
│   │   ├── algorithms/          # PrimAlgorithm, KruskalAlgorithm
│   │   ├── graph/               # Graph, Edge
│   │   ├── input/               # GraphInput, EdgeInput (JSON models)
│   │   ├── model/               # MSTResult
│   │   ├── output/              # JsonWriter
│   │   └── utils/               # JsonReader
│   └── test/java/com/citytransport/tests/
│       ├── JsonSerializationTest.java
│       └── MSTAlgorithmsTest.java
├── input/                      # Input JSON files
└── output/                     # Output JSON & mst_results.csv
References
Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2009). Introduction to Algorithms (3rd ed.). MIT Press.

Prim, R. C. (1957). Shortest connection networks and some generalizations. Bell System Technical Journal, 36(6):1389–1401.

Kruskal, J. B. (1956). On the shortest spanning subtree of a graph and the traveling salesman problem. Proceedings of the American Mathematical Society, 7(1), 48–50.

GeeksforGeeks. (2023). Prim's Minimum Spanning Tree (MST). Retrieved from https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/

GeeksforGeeks. (2023). Kruskal's Algorithm in Java. Retrieved from https://www.geeksforgeeks.org/java/kruskals-algorithm-in-java/

Baeldung. (2024). Prim's Algorithm with a Java Implementation. Retrieved from https://www.baeldung.com/java-prim-algorithm
