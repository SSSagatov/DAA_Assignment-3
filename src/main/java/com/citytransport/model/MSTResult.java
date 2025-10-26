package com.citytransport.model;

import com.citytransport.graph.Edge;

import java.util.List;

public class MSTResult {
    private final List<Edge> mstEdges;
    private final int totalCost;
    private final int originalVertices;
    private final int originalEdges;
    private final long operationCount;
    private final long executionTimeMs;

    public MSTResult(List<Edge> mstEdges, int totalCost, int originalVertices,
                     int originalEdges, long operationCount, long executionTimeMs) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.originalVertices = originalVertices;
        this.originalEdges = originalEdges;
        this.operationCount = operationCount;
        this.executionTimeMs = executionTimeMs;
    }

    public List<Edge> getMstEdges() { return mstEdges; }
    public int getTotalCost() { return totalCost; }
    public int getOriginalVertices() { return originalVertices; }
    public int getOriginalEdges() { return originalEdges; }
    public long getOperationCount() { return operationCount; }
    public long getExecutionTimeMs() { return executionTimeMs; }
}
