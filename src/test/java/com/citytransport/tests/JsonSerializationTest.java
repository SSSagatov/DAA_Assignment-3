package com.citytransport.tests;

import com.citytransport.input.EdgeInput;
import com.citytransport.input.GraphInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class JsonSerializationTest {

    @Test
    public void testGraphInputSerializationAndDeserialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        GraphInput graph = new GraphInput();
        graph.id = 1;
        graph.nodes = Arrays.asList("A", "B", "C");

        EdgeInput edge1 = new EdgeInput();
        edge1.from = "A";
        edge1.to = "B";
        edge1.weight = 10;

        EdgeInput edge2 = new EdgeInput();
        edge2.from = "B";
        edge2.to = "C";
        edge2.weight = 20;

        graph.edges = Arrays.asList(edge1, edge2);

        String jsonString = mapper.writeValueAsString(graph);

        assertNotNull(jsonString);
        assertTrue(jsonString.contains("\"id\":1"));
        assertTrue(jsonString.contains("\"from\":\"A\""));
        assertTrue(jsonString.contains("\"weight\":20"));

        GraphInput deserializedGraph = mapper.readValue(jsonString, GraphInput.class);

        assertNotNull(deserializedGraph);
        assertEquals(1, deserializedGraph.id);
        assertEquals(3, deserializedGraph.nodes.size());
        assertEquals("A", deserializedGraph.edges.get(0).from);
        assertEquals(20, deserializedGraph.edges.get(1).weight);
    }
}
