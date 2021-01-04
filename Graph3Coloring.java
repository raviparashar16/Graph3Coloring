/**
 * The Graph3Coloring.java program implements a solution to the graph 3 coloring problem, in which it must be determined
 * whether an input undirected graph can satisfy the requirement that each node is one of three colors and none of its
 * nodes is the same color as its neighbors.
 * The program prints out colors of the nodes which satisfy the requirement if possible; if not, then it prints out that
 * the requirement could not be satisfied with the input graph.
 * In the main method, the user must specify the structure of the graph via a 2-d boolean array, showing which nodes
 * have connections with which nodes.
 * For example, a graph with 3 nodes which are all connected would be specified as such:
 * {{false, true, true}, {true, false, true}, {true, true, false}}
 * Note that no nodes are connected to themselves.
 *
 * @author  Ravi Parashar
 * @version 1.0
 */

// import statements
import java.lang.*;
import java.util.*;

public class Graph3Coloring {

    /**
     * Returns a list containing nodes which are neighbors of the specified input node
     *
     * @param  loc  an integer value which refers to a location in the edges array (essentially specifies a node)
     * @param  edges the boolean array which specifies nodes and their connections
     * @return      a list of nodes which are neighbors of the specified node
     */
    public static List<Integer> neighbors(int loc, List<List<Boolean>> edges){
        List<Integer> neighbors = new ArrayList<>();
        // scrolls through the specified nodes' connections
        for(int j = 0; j < edges.get(loc).size(); j++){
            // if a location is true, it is added to the neighbors list
            if(edges.get(loc).get(j)) neighbors.add(j);
        }
        return neighbors;
    }

    /**
     * Returns true/false depending on whether or not the graph 3 coloring property can be satisfied for the input
     *
     * @param  edges the boolean array which specifies nodes and their connections
     * @return      whether or not the graph 3 coloring property can be satisfied for the input graph
     */
    public static boolean graph3Color(List<List<Boolean>> edges){
        List<Integer> d = new ArrayList<>();
        List<List<String>> c = new ArrayList<>();
        // adds an empty darr and carr list as parameters for the coloring method
        return coloring(edges, d, c);
    }

    /**
     * Recursive method which returns true/false depending on whether or not the graph 3 coloring property can be
     * satisfied for the input
     *
     * @param  edges the boolean array which specifies nodes and their connections
     * @param  darr  a list which holds the nodes which have already been encountered
     * @param  carr  a list which holds the nodes which have been encountered and their respective colors
     * @return      whether or not the graph 3 coloring property can be satisfied for the input graph
     */
    public static boolean coloring(List<List<Boolean>> edges, List<Integer> darr, List<List<String>> carr){
        // if the size of the list of nodes which have been encountered grows to equal the number of nodes, the program
        // returns true and prints the nodes and their respective colors
        if(darr.size() == edges.size()){
            System.out.println("node colors which ensure graph 3 coloring property: " + carr);
            return true;
        }
        // picks an arbitrary node which has not already been encountered
        int v;
        do{
            Random random = new Random();
            v = random.nextInt(edges.size());
        }
        while(darr.contains(v));
        // list of neighbors of the arbitrary node
        List<Integer> neighborsn = neighbors(v, edges);
        boolean yellow = true, blue = true, red = true;
        // scrolls through list of nodes which have been encountered
        for(int k = 0; k < darr.size(); k++) {
            // enter if the node which has been encountered is a neighbor of the chosen node
            if(neighborsn.contains(darr.get(k))) {
                int d = darr.get(k);
                int i = 0;
                while (i < carr.size()) {
                    if (Integer.parseInt(carr.get(i).get(0)) == d) {
                        // get the color of this encountered neighbor node
                        String color = carr.get(i).get(1);
                        if (color.equals("Y")) yellow = false;
                        else if (color.equals("B")) blue = false;
                        else if (color.equals("R")) red = false;
                    }
                    i = i + 1;
                }
            }
        }
        // add chosen node to the list of encountered nodes
        darr.add(v);
        // if there was no yellow neighbor of the chosen node
        if(yellow){
            List<String> cadd = new ArrayList<>();
            cadd.add("" + v);
            cadd.add("Y");
            // color the chosen node yellow and recursively call this method again
            carr.add(cadd);
            return coloring(edges, darr, carr);
        }
        // if there was no blue neighbor of the chosen node
        if(blue){
            List<String> cadd = new ArrayList<>();
            cadd.add("" + v);
            cadd.add("B");
            carr.add(cadd);
            return coloring(edges, darr, carr);
        }
        // if there was no red neighbor of the chosen node
        if(red){
            List<String> cadd = new ArrayList<>();
            cadd.add("" + v);
            cadd.add("R");
            carr.add(cadd);
            return coloring(edges, darr, carr);
        }
        // if there were red, blue, and yellow neighbors of the chosen node, return false
        else if(!yellow && !blue && !red){
            return false;
        }
        else{
            return true;
        }
    }

    public static void main(String[] args){
        // please modify this array to specify a graph as described in the top comment
        Boolean[][] edgearr = {{false, true, true, true}, {true, false, true, true}, {true, true, false, false},
                {true, true, false, false}};
        List<List<Boolean>> edges = new ArrayList<>();
        // converts the array into a list
        for (Boolean[] arr: edgearr) {
            List<Boolean> eadd = Arrays.asList(arr);
            edges.add(eadd);
        }
        boolean outcome = graph3Color(edges);
        // if the graph3Color method returns false, this message is printed
        if(!outcome){
            System.out.println("The graph 3 coloring property could not be satisfied with this graph.");
        }
    }
}
