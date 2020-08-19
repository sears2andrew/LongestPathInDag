package GraphSearch;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner sc = new Scanner(System.in);
        while (true)
        {
            System.out.print("Is there another graph to process? (Y/N)");

            String response = sc.nextLine().toLowerCase();
            if (response.charAt(0) != 'y')
            {
                System.out.println("Goodbye");
                System.exit(0);
            }
            // Pop up a file chooser and select a file containing graph data        
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(null);
            if (result != JFileChooser.APPROVE_OPTION)
            {
                System.out.println("No file selected.");
                continue;
            }
            // Read the file  and put a scanner on it
            Scanner graphScanner = new Scanner(chooser.getSelectedFile());

            Graph graph = new Graph(graphScanner);

            System.out.println();

            // Output the map of sring vertices to integers.
            System.out.println("Vertex name to integer map:");
            System.out.println(graph.vertexNameToNumberMap);
            // Output the map of integer vertices to strings.
            System.out.println("Vertex integer to string names map:");
            System.out.println(Arrays.toString(graph.vertexNames));

            System.out.println();

            // Output the adjacency list in string form
            System.out.println("Adjacency list for the graph in string names form:");
            graph.outputString(System.out);

            System.out.printf("Non-recursive Longest paths from each vertex:\n");
            DFS.init(graph.getAdjacencyList());
            DFS.search();
            DFS.printLongestPaths(graph);

            System.out.println();

            System.out.printf("Recursive Longest paths from each vertex:\n");
            RecursiveDFS.init(graph.getAdjacencyList());
            RecursiveDFS.search();
            RecursiveDFS.printLongestPaths(graph);

            System.out.println();
        }
    }
  
}
