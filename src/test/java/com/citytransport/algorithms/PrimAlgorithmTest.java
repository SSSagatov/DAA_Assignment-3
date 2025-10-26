package com.citytransport.algorithms;

import com.citytransport.graph.Edge;
import com.citytransport.graph.Graph;
import com.citytransport.model.MSTResult;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PrimAlgorithmTest {

    private Graph createSampleGraph() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 6);
        graph.addEdge(2, 3, 3);
        return graph;
    }

    @Test
    void testMSTCorrectness() {
        Graph graph = createSampleGraph();
        PrimAlgorithm prim = new PrimAlgorithm();
        MSTResult result = prim.findMST(graph);

        // MST must have V-1 edges
        assertEquals(graph.getVertices() - 1, result.getMstEdges().size());

        // MST must be connected and acyclic, check edges count and no duplicate edges
        Set<String> edgeSet = new HashSet<>();
        for (Edge e : result.getMstEdges()) {
            String edgeStr = e.getSource() < e.getDest()
                    ? e.getSource() + "-" + e.getDest()
                    : e.getDest() + "-" + e.getSource();
            assertFalse(edgeSet.contains(edgeStr));
            edgeSet.add(edgeStr);
        }

        // Check total cost correctness (expected cost = 6)
        assertEquals(6, result.getTotalCost());

        // Operation count and time should be non-negative
        assertTrue(result.getOperationCount() >= 0);
        assertTrue(result.getExecutionTimeMs() >= 0);
    }
}
