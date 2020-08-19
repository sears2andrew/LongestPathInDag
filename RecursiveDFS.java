//discused with mitchell and emily
package GraphSearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RecursiveDFS
{
    public static Set<Integer> visited;			 // vertices already visited
    
    private static Iterator<Integer> [ ] iter;
    private static List<Integer> [ ] adjList;   
    static int [ ] dist; 				// dist[x] is the number of 
                               				// of vertices on the longest path from x to 
                                 			// to any other vertex in the DAG
    static int [ ] nextHop; 				// nextHop[x] is the 
                                    			// next hop vertex on the longest path from x
                                    			// to any other vertex in the DAG   
    
   
    private static void visit(int v){ visited.add(v);}
    
    static public void init(List<Integer> [] adjList)
    {        
        visited = new HashSet<>();
        iter = new Iterator[adjList.length];
        dist = new int[adjList.length];    
        nextHop = new int[adjList.length];
        Arrays.fill(nextHop, -1);
        Arrays.fill(dist, 1);          
        RecursiveDFS.adjList = adjList;      
        reset();
    }
    
    
    public static void reset()
    {      
        visited.clear();
        for (int k = 0; k < iter.length; k++)
        {
            iter[k] = adjList[k].iterator();           
        }        
    }  
    /**
     * Perform a DFS search from a start vertex v to find the longest path
     * that starts at v     
     * @param v      
     */
    public static void search(int v)
    {
        int longdist = 0;
        while (iter[v].hasNext()){
            int next = iter[v].next();
            if(!visited.contains(next)){
                visit(next);
                search(next);
            }
            if(longdist <= dist[next]+1){
                dist[v]= dist[next]+1;
                longdist = dist[next]+1;
                nextHop[v]=next;
            }
        }
    }
    
    public static void search()
    {
      for(int k =0;k < adjList.length;k++){
            search(k);
       }
    }
    
    public static void printLongestPaths(Graph g)
    {
        for (int v = 0; v < adjList.length; v++)
        {
            printLongestPath(g, v);
            System.out.println();
        }
        System.out.println();
    }
    
    public static void printLongestPath(Graph g, int v)
    {
       int current = v;
       int next;
       System.out.print(g.getVertexName(v) + " ");
       for(int i = 0; i< dist[v]-1; i++){
           next = nextHop[current];
           System.out.print(g.getVertexName(next) + " ");
           current = next;
       }
       System.out.println();
    }
}
