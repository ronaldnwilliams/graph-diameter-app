import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class GraphTest {
    private Graph testGraph;
    private HashSet<String> testNeighbors;
    private List<String> testVerticies;
    
    @Before
    public void setUp() throws Exception {
        testGraph = new Graph("./src/main/resources/example.txt", 
        "(.+\\s)\\W(\\s.+)", "\\s\\W\\s", "(?<=\\d)(?=\\w)");

        testNeighbors = new HashSet<>();
        testNeighbors.add("A");
        testNeighbors.add("C");
        testNeighbors.add("D");

        testVerticies = Arrays.asList(
            "A", "B", "C", "D", "E", "A2", "B4", "C2", "D1", "E2", "F2", "G3");
    }

    @Test
    public void testGraphShouldReturn12Verticies() throws Exception {
        assertEquals(12, testGraph.V());
    }

    @Test
    public void testGraphShouldReturn11Edges() throws Exception {
        assertEquals(11, testGraph.E());
    }

    @Test
    public void testAdjacentToShouldReturnNeighbors() throws Exception {
        assertEquals(testNeighbors, testGraph.adjacentTo("B"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAdjacentToShouldThrowIllegalArumentException() throws Exception {
        testGraph.adjacentTo("COFFEE");
    }

    @Test
    public void testAddEdgeShouldAddEdge() throws Exception {
        int numEdges = testGraph.E();
        testGraph.addEdge("G3", "H");
        assertEquals(numEdges + 1, testGraph.E());
    }

    @Test
    public void testVerticiesShouldReturnVerticies() throws Exception {
        Set<String> vertices = (Set<String>) testGraph.vertices();
        List<String> sortedVerticies = new ArrayList<>(vertices);
        Collections.sort(sortedVerticies);
        Collections.sort(testVerticies);
        assertEquals(testVerticies, sortedVerticies);
    }

    @Test
    public void testDegreeAShouldReturn1() throws Exception {
        assertEquals(1, testGraph.degree("A"));
    }
}