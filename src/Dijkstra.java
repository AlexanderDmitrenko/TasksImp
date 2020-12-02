import java.util.*;

public class Dijkstra {

    private final LinkedList<Node> unvisitedNode = new LinkedList<>();
    private final Set<Node> visitedNode = new HashSet<>();
    private List<String> fromTo = new ArrayList<>();
    private Graph graph = new Graph();

    public List<String> getFromTo() {
        return fromTo;
    }

    public void setFromTo(List<String> fromTo) {
        this.fromTo = fromTo;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    //Initialization of the graph and the list of way
    public void loadData(){
        Node gdansk = new Node("gdansk");
        Node bydgoszcz = new Node("bydgoszcz");
        Node torun = new Node("torun");
        Node warszawa = new Node("warszawa");

        gdansk.addDestination(bydgoszcz, 1);
        gdansk.addDestination(torun, 3);

        bydgoszcz.addDestination(gdansk, 1);
        bydgoszcz.addDestination(torun, 1);
        bydgoszcz.addDestination(warszawa, 4);

        torun.addDestination(gdansk, 3);
        torun.addDestination(bydgoszcz, 1);
        torun.addDestination(warszawa, 1);

        warszawa.addDestination(bydgoszcz, 4);
        warszawa.addDestination(warszawa, 1);

        graph.addNode(gdansk);
        graph.addNode(bydgoszcz);
        graph.addNode(torun);
        graph.addNode(warszawa);

        fromTo.add("gdansk warszawa");
        fromTo.add("bydgoszcz warszawa");
    }

    //Launches the algorithm for all input data.
    //Shows the answer on the screen.
    public void run(){
        for (String str : fromTo) {
            String[] value = str.split(" ");
            Node start = null;
            Node end = null;
            for (Node node : graph.getNodes() ) {
                if (node.getName().equals(value[0])) start = node;
                if (node.getName().equals(value[1])) end = node;
            }
            calculation(start);
            if (end == null) {
                System.out.println("data is not correct!");
                continue;
            }
            System.out.println(end.distance);
        }
    }

    //Calculates the distance for all nodes from the starting node
    private void calculation(Node start){
        if (start == null) return;
        visitedNode.clear();
        unvisitedNode.clear();
        for (Node node: graph.getNodes()) {
            node.distance = Integer.MAX_VALUE;
        }
        start.distance = 0;
        unvisitedNode.add(start);

        while (unvisitedNode.size() != 0){
            roundNeighbors(unvisitedNode.poll());
        }

    }

    //Extra method.
    //Avoid  adjacent nodes, sets new labels if necessary
    private void roundNeighbors(Node node){
        if (node == null) return;
        List<Map.Entry<Node, Integer>> list = sortEntry(node.neighborNodes);
        for ( Map.Entry<Node, Integer> nodeEntry: list) {
            Node checkNode = nodeEntry.getKey();
            int checkDistance = nodeEntry.getValue();

            if(visitedNode.contains(checkNode)) continue;

            if((node.distance + checkDistance) < checkNode.distance) {
                checkNode.distance = node.distance + checkDistance;
            }

            if (!unvisitedNode.contains(checkNode)) unvisitedNode.add(checkNode);

        }

        visitedNode.add(node);
    }

    // Returns List <Entry> sorted by value.
    private List<Map.Entry<Node, Integer>> sortEntry(Map<Node, Integer> map){
        List<Map.Entry<Node, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<Node, Integer>>() {
            @Override
            public int compare(Map.Entry<Node, Integer> o1, Map.Entry<Node, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        return list;
    }


    public static class Node {

        private final String name;

        private Integer distance = Integer.MAX_VALUE;

        Map<Node, Integer> neighborNodes = new HashMap<>();

        public void addDestination(Node node, int distance) {
            neighborNodes.put(node, distance);
        }


        public Node(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class Graph {

        private final List<Node> nodes = new ArrayList<>();

        public void addNode(Node node) {
            nodes.add(node);
        }

        public List<Node> getNodes() {
            return nodes;
        }

    }

}
