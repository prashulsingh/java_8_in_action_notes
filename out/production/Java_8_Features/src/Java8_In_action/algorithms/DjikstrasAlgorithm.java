package Java8_In_action.algorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DjikstrasAlgorithm {
    public static void main(String[] args) {
        int graph[][] = new int[][] {
                { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

        int totalVertices = 9;
        Set<Integer> visited = new HashSet<>();
        int[] dist = new int[9];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int sourceVertex = 0;
        dist[sourceVertex] = 0;

        while( visited.size() < 9)
        {
            int nextIndex = 0;
            int minValue = Integer.MAX_VALUE;

            //give me the smallest non visited vertex
            for( int i = 0 ; i < dist.length; i++ )
                if( !visited.contains(i) && minValue > dist[i] )
                {
                    nextIndex = i;
                    minValue = dist[nextIndex];
                }
            visited.add(nextIndex);
            //update the distance of the reachable vertexd from the next vertex
            for( int i = 0 ; i < graph[nextIndex].length; i++ ) {
                int edgeValue = graph[nextIndex][i];
                if (edgeValue != 0 && !visited.contains(i))//edge is present
                {
                    if (edgeValue + dist[nextIndex] < dist[i])
                        dist[i] = edgeValue + dist[nextIndex];
                }
            }
        }

        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < totalVertices; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }
}
