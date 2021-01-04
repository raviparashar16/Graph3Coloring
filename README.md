# Graph3Coloring
The Graph3Coloring.java program implements a solution to the graph 3 coloring problem, in which it must be determined whether an input undirected graph can satisfy the requirement that each node is one of three colors and none of its nodes is the same color as its neighbors. The program prints out colors of the nodes which satisfy the requirement if possible; if not, then it prints out that the requirement could not be satisfied with the input graph.
The user must specify the structure of the graph by modifying the variable "edgearr" in the main method, which is a 2-d boolean array, showing which nodes have connections with which nodes. For example, a graph with 3 nodes which are all connected would be specified as such:
{{false, true, true}, {true, false, true}, {true, true, false}}
Note that no nodes are connected to themselves.
The code should then be compiled and run.
