package Java8_In_action.com.company;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        System.out.println(  minKnightMoves(1,1) );
    }

    public static  int minKnightMoves(int x, int y) {

        if( x == 0 && y == 0 )
            return 0;
        x = Math.abs(x);
        y = Math.abs(y);
        Queue<Pair<Integer,Integer>> bfs = new LinkedList<>();
        Set<String> set = new HashSet<>();
        Pair<Integer,Integer> currentCoordinates = new Pair<>(0,0);
        bfs.offer(currentCoordinates);
        set.add( currentCoordinates.getKey() + "#" + currentCoordinates.getValue() );
        int minMoves = 0;
        while( !bfs.isEmpty() )
        {
            minMoves++;
            int size = bfs.size();
            while( size > 0 )
            {
                currentCoordinates = bfs.poll();
                int coordinates[][] = { { -2,-1 },{ -2,1 },{ 2,1 },{ 2,-1 },{ -1,-2 },{1,-2},{ 1,2 },{-1,2} };
                for( int i = 0 ; i < coordinates.length; i++ )
                {
                    int cX = coordinates[i][0] + currentCoordinates.getKey();
                    int cY = coordinates[i][1] + currentCoordinates.getValue();

                    if( cX == x && cY == y )
                        return minMoves;

                    if (Math.abs(cX) + Math.abs(cY) > 300)
                        continue;

                    if( set.contains( cX + "#" + cY ) )
                        continue;

                    if( cX >= 0 && cY >= 0 )
                        bfs.offer(new Pair<>(cX,cY));
                }
                size--;
            }

        }
        return minMoves;

    }
}
