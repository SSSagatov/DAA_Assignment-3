package com.citytransport.utils;

import com.citytransport.graph.Edge;
import com.citytransport.model.MSTResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class JsonWriter {
    public static void writeMSTResultToFile(MSTResult result, String outputPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();

        root.put("vertices", result.getOriginalVertices());
        root.put("edges", result.getOriginalEdges());
        root.put("totalCost", result.getTotalCost());
        root.put("operationCount", result.getOperationCount());
        root.put("executionTimeMs", result.getExecutionTimeMs());

        ArrayNode mstEdgesArray = mapper.createArrayNode();
        for (Edge e : result.getMstEdges()) {
            ObjectNode edgeNode = mapper.createObjectNode();
            edgeNode.put("source", e.getSource());
            edgeNode.put("destination", e.getDest());
            edgeNode.put("weight", e.getWeight());
            mstEdgesArray.add(edgeNode);
        }
        root.set("mstEdges", mstEdgesArray);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputPath), root);
    }
}
