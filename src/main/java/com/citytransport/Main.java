package com.citytransport;

import com.citytransport.algorithms.KruskalAlgorithm;
import com.citytransport.algorithms.PrimAlgorithm;
import com.citytransport.graph.Graph;
import com.citytransport.input.GraphInput;
import com.citytransport.input.EdgeInput;
import com.citytransport.model.MSTResult;
import com.citytransport.output.JsonWriter;
import com.citytransport.utils.JsonReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static Graph buildGraphFromInput(GraphInput input) {
        Map<String, Integer> mapping = new HashMap<>();
        int index = 0;
        for (String node : input.nodes) {
            mapping.put(node, index++);
        }
        Graph graph = new Graph(input.nodes.size());
        for (EdgeInput e : input.edges) {
            int src = mapping.get(e.from);
            int dst = mapping.get(e.to);
            graph.addEdge(src, dst, e.weight);
            graph.addEdge(dst, src, e.weight); // неориентированный
        }
        return graph;
    }

    public static void main(String[] args) throws Exception {
        String inputPath = "input/assign_3_input.json";
        String outputPath = "output/results.json";

        List<GraphInput> graphInputs = JsonReader.readGraphsFromFile(inputPath);
        ObjectMapper mapper = new ObjectMapper();

        PrimAlgorithm prim = new PrimAlgorithm();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();

        ArrayNode resultsArray = mapper.createArrayNode();

        for (GraphInput inputGraph : graphInputs) {
            Graph graph = buildGraphFromInput(inputGraph);

            MSTResult primResult = prim.findMST(graph);
            MSTResult kruskalResult = kruskal.findMST(graph);

            ObjectNode graphNode = mapper.createObjectNode();
            graphNode.put("graph_id", inputGraph.id);

            ObjectNode inputStats = mapper.createObjectNode();
            inputStats.put("vertices", inputGraph.nodes.size());
            inputStats.put("edges", inputGraph.edges.size());
            graphNode.set("input_stats", inputStats);

            graphNode.set("prim", JsonWriter.createMSTJson(primResult, inputGraph.nodes, mapper));
            graphNode.set("kruskal", JsonWriter.createMSTJson(kruskalResult, inputGraph.nodes, mapper));

            resultsArray.add(graphNode);
        }

        ObjectNode root = mapper.createObjectNode();
        root.set("results", resultsArray);

        JsonWriter.writeAllResults(root, new File(outputPath));

        System.out.println("Готово! Результаты записаны в " + outputPath);
    }
}
