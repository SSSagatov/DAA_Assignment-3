package com.citytransport.utils;

import com.citytransport.input.GraphInput;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class JsonReader {
    public static List<GraphInput> readGraphsFromFile(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(path));
        JsonNode graphsNode = root.get("graphs");
        GraphInput[] graphArray = mapper.treeToValue(graphsNode, GraphInput[].class);
        return Arrays.asList(graphArray);
    }
}
