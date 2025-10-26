CityTransport MST Project
🧭 Project Overview

CityTransport MST is a software project designed to optimize urban transportation networks by computing Minimum Spanning Trees (MSTs) on weighted graphs.
Vertices represent city districts (transportation nodes), and edges represent potential roads between them with associated construction costs.

The project identifies the minimum set of connections that links all districts with the lowest possible total cost, a key task in infrastructure planning and resource optimization.

🧠 Theoretical Background
🕸️ Graphs in Transportation Networks

A graph 
𝐺
=
(
𝑉
,
𝐸
)
G=(V,E) consists of a set of vertices 
𝑉
V (districts or intersections) and a set of edges 
𝐸
E (possible roads), where each edge has a weight representing cost, distance, or travel time.

This abstraction allows city connectivity to be modeled mathematically for optimization.

🌳 Minimum Spanning Tree (MST)

A Minimum Spanning Tree (MST) is a subset of edges 
𝐸
′
⊆
𝐸
E
′
⊆E that connects all vertices 
𝑉
V without forming cycles, while minimizing the total edge weight:

MST
=
arg
⁡
min
⁡
𝐸
′
 
:
 tree
∑
𝑒
∈
𝐸
′
𝑤
(
𝑒
)
MST=arg
E
′
 : tree
min
	​

e∈E
′
∑
	​

w(e)

Applications:

Designing cost-efficient road networks

Reducing infrastructure budgets

Ensuring complete city connectivity

⚙️ Algorithms Implemented
🔹 Prim’s Algorithm

Starts from an arbitrary vertex

Repeatedly adds the lowest-weight edge connecting the current MST to a new vertex

Uses a priority queue (min-heap) for selecting edges efficiently

Time Complexity:

𝑂
(
𝐸
log
⁡
𝑉
)
O(ElogV)

Outline:

Choose an arbitrary start vertex

While MST is not complete:

Add the smallest edge connecting MST to an outside vertex

🔸 Kruskal’s Algorithm

Sorts all edges by ascending weight

Iterates through them, adding edges that do not create cycles

Uses a Disjoint Set Union (Union-Find) structure for efficient cycle detection

Time Complexity:

𝑂
(
𝐸
log
⁡
𝐸
)
O(ElogE)

Outline:

Sort edges by weight

Add each edge if it connects two previously disconnected components

🧮 Key Formulas

MST weight minimization:

min
⁡
∑
(
𝑢
,
𝑣
)
∈
𝑇
𝑤
(
𝑢
,
𝑣
)
min
(u,v)∈T
∑
	​

w(u,v)

Prim’s Algorithm complexity:

𝑂
(
𝐸
log
⁡
𝑉
)
O(ElogV)

Kruskal’s Algorithm complexity:

𝑂
(
𝐸
log
⁡
𝐸
)
O(ElogE)

🚀 How to Run
🏗️ Build & Run Program
mvn clean compile
mvn exec:java -Dexec.mainClass=com.citytransport.Main


Input JSON files should be placed in the input/ folder

MST results and performance outputs will be generated in the output/ folder

🧪 Run Test Suites
mvn test


Tests include:

✅ JSON serialization/deserialization validation

✅ MST correctness for multiple datasets

✅ Performance measurement (execution time, operations count)

Results are recorded in:

output/performance.csv

📂 Project Structure
citytransport-mst/
├── pom.xml                                   # 🧩 Maven build configuration
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── citytransport/
│   │               ├── Main.java             # 🚀 Application entry point
│   │               ├── algorithms/           # ⚙️ PrimAlgorithm & KruskalAlgorithm
│   │               ├── graph/                # 🧱 Graph and Edge domain models
│   │               ├── input/                # 📥 JSON input models (GraphInput, EdgeInput)
│   │               ├── model/                # 📊 Data model: MSTResult
│   │               ├── output/               # 📤 JSON writer / CSV logger
│   │               └── utils/                # 🧰 Utility classes (JsonReader, Timer)
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── citytransport/
│                   └── tests/
│                       ├── JsonSerializationTest.java  # ✅ Tests JSON read/write
│                       └── MSTAlgorithmsTest.java      # ✅ Tests MST correctness & performance
│
├── input/                                     # 📂 Input datasets
│   ├── graph_small.json
│   ├── graph_medium.json
│   └── graph_large.json
│
└── output/                                    # 📈 Output results & performance logs
    ├── results.json
    └── performance.csv

📊 Example Output
{
  "graphName": "medium_10_nodes",
  "prim": {
    "totalCost": 158,
    "executionTimeMs": 3,
    "operations": 24
  },
  "kruskal": {
    "totalCost": 158,
    "executionTimeMs": 2,
    "operations": 18
  }
}

📚 References

Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2009). Introduction to Algorithms (3rd ed.). MIT Press.

Prim, R. C. (1957). Shortest connection networks and some generalizations. Bell System Technical Journal, 36(6), 1389–1401.

Kruskal, J. B. (1956). On the shortest spanning subtree of a graph and the traveling salesman problem. Proceedings of the American Mathematical Society, 7(1), 48–50.

GeeksforGeeks: Prim’s MST
, Kruskal’s Algorithm

Baeldung: Prim’s Algorithm in Java
