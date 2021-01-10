package Java8_In_action.algorithms;

public class Heap {
        int arr[];
        int index = 0;
        public Heap( int size )
        {
            arr =  new int[ size ];
        }

        public int insert( int element )
        {
            arr[ index ] = element;
            int current = index;
            while( arr[current] < arr[parentIndex(current)] )
            {
                swap(current, parentIndex(current));
                current= parentIndex(current);
            }
            return 0;
        }

    private void swap(int current, int parentIndex) {
            int temp = arr[current];
            arr[current]=arr[parentIndex];
            arr[parentIndex]=temp;
    }

    public int getMin( )
        {
            int min = arr[ 0 ];
            arr[0]=arr[index];
            index--;
            heapify(0);
            return 0;
        }

    private void heapify(int pos)
    {
        if( !isLeaf( pos ) )
        {
            if( arr[leftChildIndex(pos)] < arr[pos] || arr[rightChildIndex(pos)] < arr[pos] )
            {
                if(  arr[leftChildIndex(pos)] < arr[pos] )
                {
                    swap(leftChildIndex(pos),pos);
                    heapify(leftChildIndex(pos));
                }
                else if( arr[rightChildIndex(pos)] < arr[pos] )
                {
                    swap(rightChildIndex(pos),pos);
                    heapify(rightChildIndex(pos));
                }
            }
        }
    }

    public boolean isLeaf(int pos )
    {
        if( pos > parentIndex( index) && pos <= index )
            return true;
        return false;
    }

    public int parentIndex( int index )
        {
            return (index-1)/2;
        }

        public int rightChildIndex( int parentIndex )
        {
            return parentIndex*2+2;
        }

        public int leftChildIndex( int parentIndex )
        {
            return parentIndex*2+1;
        }
}
