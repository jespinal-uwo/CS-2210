import java.io.IOException;
import java.util.*;

public class Test {
    public static void main (String args[]){


    

        try {
           
            Graph graph = new Graph(5);

            MyMap map = new MyMap(args[0]);

            Graph mapGraph = map.getGraph();

            Iterator <Edge> it2 = mapGraph.incidentEdges(mapGraph.getNode(5));
            Iterator <Edge> it3 = mapGraph.incidentEdges(mapGraph.getNode(5));

            Iterator <Node> it4 = map.findPath(0, 10, 1, 1);

            while(it4.hasNext()) {
              System.out.println("Nodes to Destination: " + it4.next().getId() );
            }

            while(it2.hasNext()) {
              System.out.println("Nodes: " + it2.next().secondNode().getId() );
            }

            while(it3.hasNext()) {
              System.out.println("Road Type: " + it3.next().getType() );
            }

            System.out.println();

            Node node0 = new Node(0);
            Node node1 = new Node(1);
            Node node2 = new Node(2);
            Node node3 = new Node(3);
            Node node4 = new Node(4);
            Node node5 = new Node(5);

          //  Node node = graph.getNode(3);

            graph.addEdge(node1, node2, "Private");
           // graph.addEdge(node1, node2, "Private");
            graph.addEdge(node0, node2, "Construction");
            graph.addEdge(node1, node3, "Public");
            graph.addEdge(node4, node1, "Public");
          //  graph.addEdge(node0, node1, "Public");
         //   graph.addEdge(node1, node4, "Construction");
           // graph.addEdge(node4, node2, "Private");

           Iterator <Edge> it = graph.incidentEdges(node1);

           System.out.println("Are adjacent: " + graph.areAdjacent(node0, node4));
           System.out.println("Get Node: " + graph.getNode(4).getId() );

           while(it.hasNext()) {
            System.out.println("Nodes: " + it.next().secondNode().getId());
          }


           System.out.println("Road Type (get Edge): " + graph.getEdge(node1, node3).getType() );
         //  System.out.println("Is adjacent: " + graph.areAdjacent(node1, node0) );

        //    System.out.println("Node ID: " + node.getId());


        } catch (GraphException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        catch (MapException e){
           System.out.println(e.getMessage());
       }

        

    }
}
