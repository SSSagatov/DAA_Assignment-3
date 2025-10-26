package com.citytransport.graph;

import java.util.*;

public class Graph {
    private final int vertices;
    private final List<Edge> edges;
    private final List<List<Edge>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
        this.adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) adjacencyList.add(new ArrayList<>());
    }

    public void addEdge(int source, int dest, int weight) {
        Edge edge = new Edge(source, dest, weight);
        edges.add(edge);
        adjacencyList.get(source).add(edge);
        adjacencyList.get(dest).add(new Edge(dest, source, weight)); // Undirected
    }

    public int getVertices() { return vertices; }
    public List<Edge> getEdges() { return edges; }
    public List<List<Edge>> getAdjacencyList() { return adjacencyList; }
}
