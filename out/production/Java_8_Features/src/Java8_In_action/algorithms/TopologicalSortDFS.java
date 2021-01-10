package Java8_In_action.algorithms;
import java.util.*;
public class TopologicalSortDFS {
    public static void main(String[] args) {
        //this algorithm gives only one topological sort, for all possible topological sort we use Kahn's algorithm
        int[][] edges = {{1,2},{2,3}};
        List<List<Integer>> graph = new ArrayList();
        Set<Integer> visited = new HashSet();
        Stack<Integer> stack = new Stack<>();
        dfs( graph, visited,0, stack);
        int N = 10;
        for( int i = 0; i < N; i++ )
        {
            if( !visited.contains(i ))
                dfs( graph, visited, 0, stack );
        }

        stack.toString();

    }

    private static void dfs(List<List<Integer>> graph, Set<Integer> visited, int cI, Stack<Integer> stack ) {

        visited.add( cI );

        List<Integer> neighbouringVertices = graph.get( cI );

        for( Integer node : neighbouringVertices )
        {
            if(!visited.contains(node) )
            {
                dfs( graph, visited, cI,stack );
            }
        }
    stack.add(cI);
    }

}
