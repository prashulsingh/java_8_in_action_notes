package Java8_In_action.algorithms;

import java.util.Arrays;

public class Union_Disjoint {
    int[] nodes;
    int[] rank;
    int[] parent;
    public Union_Disjoint(int nodes[])
    {
        this.nodes = nodes.clone();
        this.parent = new int[ nodes.length ];
        rank = new int[ nodes.length ];
        Arrays.fill( rank, -1 );
    }
    public int findSet(int index )
    {
        if( parent[ index ] != index )
            parent[index] = findSet( parent[index] );

    return parent[index];
    }
    public void union( int index1, int index2 )
    {
        int parent1Index = findSet( index1 );
        int parent2Index = findSet( index2 );

        //sort out how to return if already they are in one group
        if( parent1Index == parent2Index )//already belong to the same group
            return;
        if( rank[parent1Index] >= rank[parent2Index] )
        {
            parent[index2] = parent1Index;
            if( rank[parent1Index] == rank[parent2Index] )
                rank[parent1Index]++;
        }
        else{
            parent[index1] = parent2Index;
        }
    }
}