
import java.io.IOException;
import java.util.TreeMap;

public class App {

    public static void main(String[] args) {
        String exampleFilePath = "./src/main/resources/example.txt";
        String filePath;
        if (args.length > 0) {
            filePath = args[0];
        } else {
            filePath = exampleFilePath;
        }

        System.out.println("Generating graph...");
        Graph graph;
        // try catch incase of bad file path or problem with file
		try {
			graph = new Graph(filePath, "(.+\\s)\\W(\\s.+)", "\\s\\W\\s", 
                "(?<=\\d)(?=\\w)");
            System.out.println("\nDone.\n\nSymbol Table: \n" + graph.toString());

            System.out.println("\nComputing paths...");
            // compute shortest path from each vertex
            TreeMap<String, PathRunner> allPaths = new TreeMap<>();
            for (String s : graph.vertices()) {
                allPaths.put(s, new PathRunner(graph, s));
            }
            System.out.println("Done.");
            // get the furthest away vertices and get the distance
            System.out.println("Diameter: " + 
                allPaths.get(allPaths.firstKey()).distanceTo(allPaths.lastKey()));

            if (filePath == exampleFilePath) {
                System.out.println("\n\nYou can pass a command line arg file path to a" + 
                    " txt file with format similiar to example.txt found in " + 
                    "/src/main/resources/ or simply edit / replace this one.");
            }
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
}
