package com.citytransport.algorithms;

import com.citytransport.graph.Edge;
import com.citytransport.graph.Graph;
import com.citytransport.model.MSTResult;

import java.util.Collections;
import java.util.List;

public class KruskalAlgorithm {
    private long operationCount = 0;

    private static class DisjointSet {
        int[] parent, rank;

        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int u) {
            if (parent[u] != u) parent[u] = find(parent[u]);
            return parent[u];
        }

        boolean union(int u, int v) {
            int pu = find(u);
            int pv = find(v);
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
        long start = System.currentTimeMillis();

        List<Edge> edges = graph.getEdges();
        Collections.sort(edges);
        operationCount += edges.size();

        DisjointSet ds = new DisjointSet(graph.getVertices());
        java.util.List<Edge> mstEdges = new java.util.ArrayList<>();
        int totalCost = 0;

        for (Edge e : edges) {
            operationCount++;
            if (ds.union(e.getSource(), e.getDest())) {
                mstEdges.add(e);
                totalCost += e.getWeight();
            }
        }

        long end = System.currentTimeMillis();

        return new MSTResult(mstEdges, totalCost, graph.getVertices(), graph.getEdges().size(), operationCount, end - start);
    }
}
