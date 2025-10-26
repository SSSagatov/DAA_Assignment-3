package com.citytransport.utils;

import com.citytransport.graph.Graph;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonReader {
    public static Graph readGraphFromFile(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(path));
        int vertices = root.get("vertices").asInt();
        Graph graph = new Graph(vertices);

        for (JsonNode edgeNode : root.withArray("edges")) {
            int src = edgeNode.get("source").asInt();
            int dest = edgeNode.get("destination").asInt();
            int weight = edgeNode.get("weight").asInt();
            graph.addEdge(src, dest, weight);
        }
        return graph;
    }
}
