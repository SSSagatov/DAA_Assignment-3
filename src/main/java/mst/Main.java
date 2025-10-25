package mst;

import mst.algorithms.*;
import mst.io.*;
import mst.model.Graph;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String inputFile = "src/main/resources/input.json";
        String outputFile = "src/main/resources/output.json";

        List<Graph> graphs = InputReader.readGraphs(inputFile);
        List<Map<String, Object>> results = new ArrayList<>();

        int id = 1;
        for (Graph g : graphs) {
            MSTResult prim = PrimAlgorithm.run(g);
            MSTResult kruskal = KruskalAlgorithm.run(g);
            GraphStats stats = new GraphStats(g.getVertexCount(), g.getEdgeCount());
            results.add(OutputWriter.buildResult(id++, stats, prim, kruskal));
        }

        OutputWriter.writeResults(outputFile, results);
        System.out.println("Results saved to " + outputFile);
    }
}
