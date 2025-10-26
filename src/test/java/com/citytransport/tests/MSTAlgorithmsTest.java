package com.citytransport.tests;

import com.citytransport.algorithms.KruskalAlgorithm;
import com.citytransport.algorithms.PrimAlgorithm;
import com.citytransport.graph.Graph;
import com.citytransport.model.MSTResult;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MSTAlgorithmsTest {

    private Graph generateRandomConnectedGraph(int numVertices, int maxEdges) {
        Graph graph = new Graph(numVertices);
        Random rnd = new Random();

        // Строим связное дерево цепочкой
        for (int i = 0; i < numVertices - 1; i++) {
            int w = rnd.nextInt(100) + 1;
            graph.addEdge(i, i + 1, w);
            graph.addEdge(i + 1, i, w);
        }

        int edgesAdded = numVertices - 1;
        while (edgesAdded < maxEdges) {
            int u = rnd.nextInt(numVertices);
            int v = rnd.nextInt(numVertices);
            if (u != v) {
                int w = rnd.nextInt(100) + 1;
                graph.addEdge(u, v, w);
                graph.addEdge(v, u, w);
                edgesAdded++;
            }
        }
        return graph;
    }

    private void writeResultsToCsv(String filePath, List<ResultRecord> records) throws Exception {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("graph_size,algorithm,total_cost,operations_count,execution_time_ms");
            for (ResultRecord r : records) {
                pw.printf("%d,%s,%d,%d,%d%n",
                        r.graphSize, r.algorithm, r.totalCost, r.operationsCount, r.executionTimeMs);
            }
        }
    }

    private static class ResultRecord {
        int graphSize;
        String algorithm;
        int totalCost;
        long operationsCount;
        long executionTimeMs;

        ResultRecord(int graphSize, String algorithm, int totalCost, long operationsCount, long executionTimeMs) {
            this.graphSize = graphSize;
            this.algorithm = algorithm;
            this.totalCost = totalCost;
            this.operationsCount = operationsCount;
            this.executionTimeMs = executionTimeMs;
        }
    }

    @Test
    public void runTestsAndSaveCsv() throws Exception {
        PrimAlgorithm prim = new PrimAlgorithm();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();

        List<ResultRecord> allResults = new ArrayList<>();
        Random rnd = new Random();

        int[][] testConfigs = new int[][] {
                {5, 30},      // small: 5 графов до 30 вершин
                {10, 300},    // medium: 10 графов до 300 вершин
                {10, 1000},   // large: 10 графов до 1000 вершин
                {5, 3000}     // extra: 5 графов до 3000 вершин
        };

        for (int i = 0; i < testConfigs.length; i++) {
            int graphsCount = testConfigs[i][0];
            int maxVertices = testConfigs[i][1];

            for (int g = 0; g < graphsCount; g++) {
                int vertices = rnd.nextInt(maxVertices - 10) + 10; // минимум 10 вершин
                int maxEdges = Math.min(vertices * (vertices -1) / 2, vertices * 10); // ограничение сверху

                Graph graph = generateRandomConnectedGraph(vertices, maxEdges);

                MSTResult primResult = prim.findMST(graph);
                MSTResult kruskalResult = kruskal.findMST(graph);

                // Проверяем совпадение результата
                assertEquals(primResult.getTotalCost(), kruskalResult.getTotalCost());

                allResults.add(new ResultRecord(vertices, "Prim", primResult.getTotalCost(),
                        primResult.getOperationCount(), primResult.getExecutionTimeMs()));

                allResults.add(new ResultRecord(vertices, "Kruskal", kruskalResult.getTotalCost(),
                        kruskalResult.getOperationCount(), kruskalResult.getExecutionTimeMs()));
            }
        }

        // Запись результатов в CSV
        writeResultsToCsv("output/mst_results.csv", allResults);
        System.out.println("Тесты выполнены, результаты записаны в output/mst_results.csv");
    }
}
