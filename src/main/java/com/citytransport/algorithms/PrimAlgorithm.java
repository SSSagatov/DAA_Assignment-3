package com.citytransport.algorithms;

import com.citytransport.graph.Edge;
import com.citytransport.graph.Graph;
import com.citytransport.model.MSTResult;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PrimAlgorithm {
    private long operationCount = 0;

    public MSTResult findMST(Graph graph) {
        long start = System.currentTimeMillis();

        int V = graph.getVertices();
        boolean[] inMST = new boolean[V];
        int[] key = new int[V];
        Edge[] parent = new Edge[V];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int u = pq.poll()[0];
            if (inMST[u]) continue;
            inMST[u] = true;
            operationCount++;

            for (Edge e : graph.getAdjacencyList().get(u)) {
                int v = e.getDest();
                int w = e.getWeight();
                operationCount++;
                if (!inMST[v] && w < key[v]) {
                    key[v] = w;
                    parent[v] = e;
                    pq.offer(new int[]{v, w});
                    operationCount++;
                }
            }
        }

        int totalCost = 0;
        java.util.List<Edge> mstEdges = new java.util.ArrayList<>();
        for (int i = 1; i < V; i++) {
            if (parent[i] != null) {
                mstEdges.add(parent[i]);
                totalCost += parent[i].getWeight();
            }
        }

        long end = System.currentTimeMillis();

        return new MSTResult(mstEdges, totalCost, V, graph.getEdges().size(), operationCount, end - start);
    }
}
