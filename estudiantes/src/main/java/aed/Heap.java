package aed;

public class Heap {
    private Nodo[] Heap;
    private int size;
    private int maxsize;
    public class Nodo {
        int votos;
        int indice;

        public Nodo(int value, int i){
            votos = value;
            indice = i;
        }
    }

    // Constructor to initialize an
    // empty max heap with given maximum
    // capacity
    public Heap(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new Nodo[this.maxsize];

    }

    // Method 1
    // Returning position of parent
    private int padre(int pos) {
        return (pos - 1) / 2;
    }

    // Method 2
    // Returning left children
    private int hijoIzquierdo(int pos) {
        return (2 * pos) + 1;
    }

    // Method 3
    // Returning right children
    private int hijoDerecho(int pos) {
        return (2 * pos) + 2;
    }

    // Method 4
    // Returning true if given node is leaf
    private boolean esHoja(int pos)
    {
        if (pos > (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    // Method 5
    // Swapping nodes
    private void intercambiar(int fpos, int spos)
    {
        Nodo tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    // Method 6
    // Recursive function to max heapify given subtree
    public void maxHeapify(int pos)
    {
        if (esHoja(pos))
            return;

        if (Heap[pos].votos < Heap[hijoIzquierdo(pos)].votos || Heap[pos].votos < Heap[hijoDerecho(pos)].votos) {

            if (Heap[hijoIzquierdo(pos)].votos > Heap[hijoDerecho(pos)].votos) {
                intercambiar(pos, hijoIzquierdo(pos));
                maxHeapify(hijoIzquierdo(pos));
            }
            else {
                intercambiar(pos, hijoIzquierdo(pos));
                maxHeapify(hijoDerecho(pos));
            }
        }
    }

    // Method 7
    // Inserts a new element to max heap
    public void insertar(int element, int i)
    {
        Heap[size].votos = element;
        Heap[size].indice = i;

        // Traverse up and fix violated property
        int actual = size;
        while (Heap[actual].votos > Heap[padre(actual)].votos) {
            intercambiar(actual, padre(actual));
            actual = padre(actual);
        }
        size++;
    }
    public void deleteRoot(int[] arr, int n)
    {
        // Get the last element
        int lastElement = arr[n - 1];

        // Replace root with first element
        arr[0] = lastElement;

        // Decrease size of heap by 1
        n = n - 1;

        // heapify the root node
        maxHeapify(0);
        
    }


    public Nodo obtenerMaximo()
    {
        Nodo popped = Heap[0];
        Heap[0] = Heap[--size];
        maxHeapify(0);
        return popped;
    }
}
