package com.citytransport.algorithms;

import com.citytransport.graph.Edge;
import com.citytransport.graph.Graph;
import com.citytransport.model.MSTResult;

import java.util.*;

public class PrimAlgorithm {
    private long operationCount = 0;

    public MSTResult findMST(Graph graph) {
        long startTime = System.currentTimeMillis();

        int V = graph.getVertices();
        boolean[] inMST = new boolean[V];
        Edge[] parent = new Edge[V];
        int[] key = new int[V];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{0, 0}); // {vertex, key}

        while (!pq.isEmpty()) {
            int u = pq.poll()[0];
            if (inMST[u]) continue;
            inMST[u] = true;
            operationCount++;

            for (Edge edge : graph.getAdjacencyList().get(u)) {
                int v = edge.getDest();
                int weight = edge.getWeight();
                operationCount++;
                if (!inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    parent[v] = edge;
                    pq.offer(new int[]{v, key[v]});
                    operationCount++;
                }
            }
        }

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        for (int i = 1; i < V; i++) {
            if (parent[i] != null) {
                mstEdges.add(parent[i]);
                totalCost += parent[i].getWeight();
            }
        }

        long endTime = System.currentTimeMillis();

        return new MSTResult(mstEdges, totalCost, graph.getVertices(), graph.getEdges().size(),
                operationCount, endTime - startTime);
    }
}
