/**
 *
 * format of the contents of the file is
 * number of vertices (N)
 * vertex0 number-of-neighbors  space separated list of neighbors of vertex0
 * vertex1 number-of-neighbors  space separated list of neighbors of vertex1
 *
 * vertex(N-1) number-of-neighbors space separated list of neighbors of vertex(N-1)
 */
package GraphSearch;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Graph
{
    // Map and array translate between integer and string representations
    // of a vertex
    final Map<String, Integer> vertexNameToNumberMap = new HashMap<>();
    final String[] vertexNames;

    private final LinkedList<Integer>[] adjList;
    
    public int getNumberOfVertices() { return adjList.length; }
    public  LinkedList<Integer>[] getAdjacencyList() { return adjList;}
    public String getVertexName(int v) { return vertexNames[v];}
    public int getVertexNumber(String name) { return vertexNameToNumberMap.get(name);}

    public Graph(Scanner sc)
    {
        int numberOfVertices = sc.nextInt();
        vertexNames = new String[numberOfVertices];
        adjList = (LinkedList<Integer>[]) new LinkedList[numberOfVertices];

        int vertexNumber = 0;

        for (int k = 0; k < numberOfVertices; k++)
        {
            String startVertex = sc.next();          
            int startVertexNumber;
            if (!vertexNameToNumberMap.containsKey(startVertex))
            {  
                startVertexNumber = vertexNumber++;
                vertexNameToNumberMap.put(startVertex, startVertexNumber);
                vertexNames[startVertexNumber] = startVertex;
                adjList[startVertexNumber] = new LinkedList<>();
            }
            else
            {
                startVertexNumber = vertexNameToNumberMap.get(startVertex);
            }
            // This start vertex has an entry in the map. Build its list of neighbors
            int numberOfNeighbors = sc.nextInt();
         
            for (int index = 0; index < numberOfNeighbors; index++)
            {
                String neighbor = sc.next();            
                int neighborNumber;
                if (!vertexNameToNumberMap.containsKey(neighbor))
                {
                    neighborNumber = vertexNumber ++;
                    vertexNameToNumberMap.put(neighbor, neighborNumber);
                    vertexNames[neighborNumber] = neighbor;   
                    adjList[neighborNumber] = new LinkedList<>();
                }
                else
                {
                    neighborNumber = vertexNameToNumberMap.get(neighbor);
                }
                adjList[startVertexNumber].add(neighborNumber);
            }
        }
    }
    
    public List<String> translate(List<Integer> path)
    {        
       return path.stream().map(x ->vertexNames[x]).collect(Collectors.toList());
    }

    public void outputInt(PrintStream out)
    {        
        for (int k = 0; k < adjList.length; k++)
        {
            out.printf("%d : %s\n", k, adjList[k]);
        }
    }
    
    public void outputString(PrintStream out)
    {       
        for (int k = 0; k < adjList.length; k++)
        {
            out.printf("%s : %s\n", vertexNames[k],
                    adjList[k].stream().map(x -> vertexNames[x]).collect(Collectors.toList()) );
        }
    }
}
