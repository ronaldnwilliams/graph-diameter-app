import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Runs breadth first search algorithm from source s on graph G.
 * After preprocessing can return shortest paths from given verticies.
 */
public class PathRunner {
    // previoues vertex on shortest path from s to v
    private Map<String, String> previous = new TreeMap<>();
    // length of shortest path from s to v
    private Map<String, Integer> distance = new TreeMap<>();

    // s is the source vertex of graph
    public PathRunner(Graph G, String s) {
        // put source on the queue
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        distance.put(s, 0);

        // keep moving to next verteex as long as it is not in previous
        while (!queue.isEmpty()) {
            String v = queue.remove();
            for (String w : G.adjacentTo(v)) {
                if (!distance.containsKey(w)) {
                    queue.add(w);
                    distance.put(w, 1 + distance.get(v));
                    previous.put(w, v);
                }
            }
        } 
    }

    // checks if v can be reached from source
    public boolean hasPathTo(String v) {
        return distance.containsKey(v);
    }

    // return length of shortest path from v to s else return Integer.Max_Value
    public int distanceTo(String v) {
        if(!hasPathTo(v)) return Integer.MAX_VALUE;
        return distance.get(v);
    }

    // reutrn shortest path from v to source as Iterable
    public Iterable<String> pathTo(String v) {
        Stack<String> path = new Stack<String>();
        while (v != null && distance.containsKey(v)) {
            path.push(v);
            v = previous.get(v);
        }
        return path;
    }
}