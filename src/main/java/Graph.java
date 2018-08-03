import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Graph {
    // stores vertex as key and hashset of neighboring verticies as value
    private Map<String, Set<String>> symbolTable;
    // number of edges
    private int E;

    // initializes empty graph
    public Graph() {
        symbolTable = new TreeMap<>();
    }

    /**
     *  initializes a graph from a specified filename and 
     *  uses specified delimiters
     * 
     *  Since the file may have two ways of representing edges, this adds a 
     *  conditional statement if delimiter use delimiterSplitter 
     *  else delimiter2 use delimiter2
     * 
     *  @param fileName name of file
     *  @param delimiter delimiter used for line matcher
     *  @param delimiterSplitter delimiter used for line split if using delimiter
     *  @param delimiter2 delimiter used if line does not match first delimiter
     *  @exception throws IOException if problem with file or not found
     */
    public Graph(String fileName, String delimiter, String delimiterSplitter, 
        String delimiter2) throws IOException {
            symbolTable = new TreeMap<>();
            //read file into stream, try-with-resources
            Stream<String> stream = Files.lines(Paths.get(fileName));
            // loop through each line
            stream.forEach(line -> {
                // declare a variable that will hold which delimiter to use
                String conditionalLimiter;
                // check first delimiter
                if (line.matches(delimiter)) {
                    conditionalLimiter = delimiterSplitter;
                // use delimiter2
                } else {
                    conditionalLimiter = delimiter2;
                }
                // variable to hold the vertex names
                String[] names = line.split(conditionalLimiter);
                for (int i = 1; i < names.length; i++) {
                    addEdge(names[0], names[i]);
                }

            });
    }

    // returns the number of vertices
    public int V() {
        return symbolTable.size();
    }

    // returns the number of edges
    public int E() {
        return E;
    }

    // returns the verticies in graph
    public Iterable<String> vertices() {
        return symbolTable.keySet();
    }

    /**
     * returns the set of vertices adjacent to v
     * 
     * @param v the vertex
     * @return set of verticies adjacent to v
     * @throws IllegalArgumentException if v is not a vertex
     */
    public Iterable<String> adjacentTo(String v) {
        validateVertex(v);
        return symbolTable.get(v);
    }

    /** 
     * returns the degree of vertex v
     * 
     * @param v the vertex
     * @return the degree of v
     * @throws IllegalArgumentException if v is not a vertex
     */
    public int degree(String v) {
        validateVertex(v);
        return symbolTable.get(v).size();
    }

    /**
     * adds vertex to graph (if it does not already exist)
     * 
     * @param v the vertex
     */
    private void addVertex(String v) {
        if (!hasVertex(v)) symbolTable.put(v, new TreeSet<String>());
    }

    /** 
     * adds edge to graph (if it does not already exist)
     * 
     * @param v one vertex of edge
     * @param w other vertex of edge
     */
    public void addEdge(String v, String w) {
        if (!hasVertex(v)) addVertex(v);
        if (!hasVertex(w)) addVertex(w);
        if (!hasEdge(v, w)) E++;
        symbolTable.get(v).add(w);
        symbolTable.get(w).add(v);
    }

	/**
     *  checks if v is a vertex in graph 
     * 
     *  @param v the vertex
     *  @return true if v is vertex in graph else return false
     */
    private boolean hasVertex(String v) {
		return symbolTable.containsKey(v);
    }
    
    /**
     * checks if v-w is an edge in graph
     * 
     * @param v one vertex in edge
     * @param w other vertex in edge
     * @return returns true if v-w is a vertex in graph else false
     * @throws IllegalArgumentException if v or w is not a vertex in graph
     */
    private boolean hasEdge(String v, String w) {
        validateVertex(v);
        validateVertex(w);
        return symbolTable.get(v).contains(w);
	}

    // throws exception if v is not a vertex
	private void validateVertex(String v) {
        if (!hasVertex(v)) throw new IllegalArgumentException(v + " is not a vertex");
    }
    
    /**
     * returns string representation of graph
     * 
     * @return string representation of graph
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String v : symbolTable.keySet()) {
            sb.append(v + ": ");
            for (String w : symbolTable.get(v)) {
                sb.append(w + " ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}