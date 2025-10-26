package com.citytransport.tests;

import com.citytransport.algorithms.KruskalAlgorithm;
import com.citytransport.algorithms.PrimAlgorithm;
import com.citytransport.graph.Edge;
import com.citytransport.graph.Graph;
import com.citytransport.model.MSTResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MSTAlgorithmsTest {

    private Graph generateRandomConnectedGraph(int numVertices, int maxEdges) {
        Graph graph = new Graph(numVertices);
        Random rnd = new Random();

        for (int i = 0; i < numVertices - 1; i++) {
            int weight = rnd.nextInt(100) + 1;
            graph.addEdge(i, i + 1, weight);
            graph.addEdge(i + 1, i, weight);
        }

        int edgesAdded = numVertices - 1;
        while (edgesAdded < maxEdges) {
            int u = rnd.nextInt(numVertices);
            int v = rnd.nextInt(numVertices);
            if (u != v) {
                int weight = rnd.nextInt(100) + 1;
                graph.addEdge(u, v, weight);
                graph.addEdge(v, u, weight);
                edgesAdded++;
            }
        }

        return graph;
    }

    @Test
    public void testSmallGraphs() {
        testGraphSet(5, 30, 5);
    }

    @Test
    public void testMediumGraphs() {
        testGraphSet(300, 4500, 10);
    }

    @Test
    public void testLargeGraphs() {
        testGraphSet(1000, 15000, 10);
    }

    @Test
    public void testExtraLargeGraphs() {
        testGraphSet(3000, 45000, 5);
    }

    private void testGraphSet(int maxVertices, int maxEdges, int numberOfGraphs) {
        PrimAlgorithm prim = new PrimAlgorithm();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();

        for (int i = 0; i < numberOfGraphs; i++) {
            int vertices = new Random().nextInt(maxVertices - 1) + 1;
            int edges = Math.min(maxEdges, vertices * (vertices - 1) / 2);
            Graph graph = generateRandomConnectedGraph(vertices, edges);

            MSTResult primResult = prim.findMST(graph);
            MSTResult kruskalResult = kruskal.findMST(graph);

            assert primResult.getTotalCost() == kruskalResult.getTotalCost();

            System.out.printf("Graph %d [V=%d] - Prim cost: %d, Kruskal cost: %d%n", i + 1, vertices,
                    primResult.getTotalCost(), kruskalResult.getTotalCost());
        }
    }
}
