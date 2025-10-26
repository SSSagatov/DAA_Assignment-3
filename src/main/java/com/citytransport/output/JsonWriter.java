package com.citytransport.output;

import com.citytransport.graph.Edge;
import com.citytransport.model.MSTResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.util.List;

public class JsonWriter {
    public static ObjectNode createMSTJson(MSTResult result, List<String> nodes, ObjectMapper mapper) {
        ObjectNode node = mapper.createObjectNode();
        ArrayNode edgesArray = mapper.createArrayNode();
        for (Edge e : result.getMstEdges()) {
            ObjectNode edgeNode = mapper.createObjectNode();
            edgeNode.put("from", nodes.get(e.getSource()));
            edgeNode.put("to", nodes.get(e.getDest()));
            edgeNode.put("weight", e.getWeight());
            edgesArray.add(edgeNode);
        }
        node.set("mst_edges", edgesArray);
        node.put("total_cost", result.getTotalCost());
        node.put("operations_count", result.getOperationCount());
        node.put("execution_time_ms", result.getExecutionTimeMs());
        return node;
    }

    public static void writeAllResults(ObjectNode root, File outputFile) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, root);
    }
}
