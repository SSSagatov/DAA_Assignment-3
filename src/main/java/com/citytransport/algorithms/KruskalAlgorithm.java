package com.citytransport.algorithms;

import com.citytransport.graph.Edge;
import com.citytransport.graph.Graph;
import com.citytransport.model.MSTResult;

import java.util.*;

public class KruskalAlgorithm {
    private long operationCount = 0;

    static class DisjointSet {
        private final int[] parent, rank;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int u) {
            if (parent[u] != u) parent[u] = find(parent[u]);
            return parent[u];
        }

        public boolean union(int u, int v) {
            int pu = find(u), pv = find(v);
            if (pu == pv) return false;
            if (rank[pu] < rank[pv]) parent[pu] = pv;
            else if (rank[pv] < rank[pu]) parent[pv] = pu;
            else {
                parent[pv] = pu;
                rank[pu]++;
            }
            return true;
        }
    }

    public MSTResult findMST(Graph graph) {
        long startTime = System.currentTimeMillis();
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        Collections.sort(edges);
        operationCount += edges.size(); // counting sorts roughly

        DisjointSet ds = new DisjointSet(graph.getVertices());
        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;

        for (Edge edge : edges) {
            operationCount++;
            if (ds.union(edge.getSource(), edge.getDest())) {
                mstEdges.add(edge);
                totalCost += edge.getWeight();
            }
        }
        long endTime = System.currentTimeMillis();

        return new MSTResult(mstEdges, totalCost, graph.getVertices(), graph.getEdges().size(),
                operationCount, endTime - startTime);
    }
}
