# CityTransport MST Project

## Project Overview

CityTransport MST is a software project designed to optimize urban transport networks by computing Minimum Spanning Trees (MSTs) on weighted graphs, representing city transport points (vertices) and routes (edges). The project aims to find the minimal set of connections covering all points with the least total cost — crucial for infrastructure planning and resource optimization.

---

## Theoretical Background

### Graphs in Transportation Networks

A **graph** \(G = (V, E)\) consists of vertices \(V\) (stations or stops) and edges \(E\) (routes between them), where each edge has a weight representing cost, distance, or travel time. This abstraction models transport connectivity.

---

### Minimum Spanning Tree (MST)

A Minimum Spanning Tree is a subset of edges \(E' \subseteq E\) connecting all vertices \(V\) without any cycles, minimizing the total edge weight:

\[
\text{MST} = \arg\min_{E'~:~\text{tree}} \sum_{e \in E'} w(e)
\]

Applications: reducing infrastructure costs while maintaining full connectivity.

---

### Algorithms Implemented

#### Prim's Algorithm

- Constructs MST by growing from a starting vertex.
- At each step, adds the minimum weight edge extending the MST.
- Uses a priority queue for efficiency.
- **Time complexity:** \(O(E \log V)\)

**Outline:**
1. Start at arbitrary vertex.
2. While MST not spanning all vertices:
   - Add minimum weight edge connecting MST to outside vertex.

#### Kruskal's Algorithm

- Sorts all edges by weight.
- Adds edges in order if they do not create cycles.
- Uses Union-Find data structure for cycle detection.
- **Time complexity:** \(O(E \log E)\)

**Outline:**
1. Sort edges.
2. Iterate edges, adding to MST if no cycle formed.

---

## How to Run

### Build and Run Program

mvn clean compile
mvn exec:java -Dexec.mainClass=com.citytransport.Main

text

- Input JSON files reside in the `input/` folder.
- Output files including MST results are saved in the `output/` folder.

### Run Test Suites

mvn test

text

- Tests verify:
  - JSON serialization/deserialization.
  - Algorithm correctness and performance for various graph sizes.
- Performance and operation counts are recorded in `output/mst_results.csv`.

---

## Key Formulas

- MST weight minimization:  
\[
\min \sum_{(u,v) \in T} w(u,v)
\]

- Complexity of Prim's Algorithm:  
\[
O(E \log V)
\]

- Complexity of Kruskal's Algorithm:  
\[
O(E \log E)
\]

---

## Project Structure
```
citytransport-mst/
├── pom.xml                                   # Maven build configuration file
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── citytransport/
│   │               ├── Main.java             # Application entry point
│   │               ├── algorithms/           # PrimAlgorithm & KruskalAlgorithm implementations
│   │               ├── graph/                # Graph and Edge domain models
│   │               ├── input/                # JSON models: GraphInput, EdgeInput
│   │               ├── model/                # Data model: MSTResult
│   │               ├── output/               # JSON writer: JsonWriter
│   │               └── utils/                # JSON reader: JsonReader
│   └── test/
│       └── java/
│           └── com/
│               └── citytransport/
│                   └── tests/
│                       ├── JsonSerializationTest.java  # Tests JSON serialization/deserialization
│                       └── MSTAlgorithmsTest.java      # Tests MST algorithms & logs CSV results
│
├── input/                                     # Input JSON datasets (small, medium, large)
│   ├── graph_small.json
│   ├── graph_medium.json
│   └── graph_large.json
│
└── output/                                    # Output results & CSV performance summaries
    ├── results.json
    └── performance.csv
```

---

## References

- Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2009). *Introduction to Algorithms* (3rd ed.). MIT Press.
- Prim, R. C. (1957). Shortest connection networks and some generalizations. *Bell System Technical Journal*, 36(6), 1389–1401.
- Kruskal, J. B. (1956). On the shortest spanning subtree of a graph and the traveling salesman problem. *Proceedings of the American Mathematical Society*, 7(1), 48–50.
- GeeksforGeeks: [Prim's MST](https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/), [Kruskal's Algorithm](https://www.geeksforgeeks.org/java/kruskals-algorithm-in-java/)
- Baeldung: [Prim's Algorithm in Java](https://www.baeldung.com/java-prim-algorithm)

---

This README provides a comprehensive understanding of the project’s algorithms, usage, tests, formulas, and references to ensure clarity and ease of maintenance.
