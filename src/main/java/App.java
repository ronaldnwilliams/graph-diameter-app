public class App {

    public static void main(String[] args) {
        Graph graph = new Graph("./src/main/resources/example.txt", 
            "(.+\\s)\\W(\\s.+)", "\\s\\W\\s", "(?<=\\d)(?=\\w)");
        System.out.println(graph.toString());
    }
}
