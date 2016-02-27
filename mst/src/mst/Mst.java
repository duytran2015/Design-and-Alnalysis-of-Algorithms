package mst;

import java.io.IOException;
import java.util.ArrayList;


//Main class for performing all the input and calling the objects
class Mst {
    static boolean connected;
    // list of all the vertexes
    static Vertex[] vertexList;
    static Vertex[] primMst;
    static Vertex[] kruskalMst;
    // to select mst from graph using Cost scheme
    //static int[][] Cost = new int[1000][1000];
    //Array A for Find2()
    static int[] height;
    //
    // calculating the cost of the mst
    int primCost = 0;
    int kruskalCost = 0;
    //Kruskal
    int den;

    public static void main(String[] args) throws IOException {
        long start = 0;
        long stop;
        long primTime = 0;
        long kruskalTime = 0;
        int nodes = Integer.parseInt(args[1]);
        int density = Integer.parseInt(args[2]);
        // creating g object for generating the random graph
        //GraphRandom g = new GraphRandom(nodes, density);
        int[][] Cost = new int[nodes][nodes];
        int[] A = new int[nodes];
        
        // create vertices for printing Prim's MST & Kruskal's MST
        primMst = new Vertex[nodes];
        for (int v = 0; v < primMst.length; v++) {
            primMst[v] = new Vertex(v, null);
        }
        kruskalMst = new Vertex[nodes];
        for (int v = 0; v < kruskalMst.length; v++) {
            kruskalMst[v] = new Vertex(v, null);
        }
        
        System.out.println("Number of vertices = "+nodes);
        System.out.println("Density = "+density);
        
        /* ******Test program to collect data
        boolean st;
        for(int i = 0; i < 100; i++){
            GraphRandom g = new GraphRandom(nodes, density);
            //create 2 dimesion array Cost
            g.createCostArray(Cost);
            start = System.currentTimeMillis();
            for(int j = 0; j < 100; j++){
            // running prim's algorithm for the graph
                g.Prim(Cost, nodes, 0);
            }
            stop = System.currentTimeMillis();
            // execution time for your algorithm.
            primTime += stop - start;
            
            
            Edge[] edgeList = new Edge[g.den];
            g.createEdgeList(Cost, g, nodes, edgeList);
            //g.HeapSort(edgeList, edgeList.length);
            start = System.currentTimeMillis();
            for (int j = 0; j < 100;j++){
            // running prim's algorithm for the graph
            st = g.Kruskal(edgeList, Cost, nodes, A);
            }
            stop = System.currentTimeMillis();
            // execution time for your algorithm.
            kruskalTime += stop - start;
        }
        System.out.println("Time for Prim Algorithm= " + primTime);
        System.out.println("\n");
        System.out.println("Time for Kruskal Algorithm: " + kruskalTime);
        System.out.println("\n");
        ********************* */
        
        System.out.println("******Prim Alogorithm******");
        
        GraphRandom g = new GraphRandom(nodes, density);
        //create 2 dimesion array Cost
        g.createCostArray(Cost);
        //print out Cost[][] 
        for (int i = 0; i < vertexList.length; i++) {
            System.out.print(i);
            for (int j = 0; j < vertexList.length; j++) {
                if(Cost[i][j]!=Integer.MAX_VALUE)
                    System.out.print ("\t"+Cost[i][j]);
                else System.out.print ("\tinfi");
            }
            System.out.print("\n");
        }
        start = System.currentTimeMillis();
        // running prim's algorithm for the graph
        g.Prim(Cost, nodes, 0);
        stop = System.currentTimeMillis();
        // execution time for your algorithm.
        primTime += stop - start;
        // Print MST
        g.print(primMst);
        System.out.println("Total cost: " + g.primCost);
        System.out.println("Time for Prim Algorithm= " + primTime);
        System.out.println("\n");
        
        System.out.println("******Kruskal Alogorithm******");
        //create a list of edges with no repeating,e.g. 0 -> 1 but no 1 -> 0
        Edge[] edgeList = new Edge[g.den];
        g.createEdgeList(Cost, g, nodes, edgeList);

        //Construct array A that contain label of each vertex
        //g.constructArrayA(edgeList, A, nodes);
        g.HeapSort(edgeList, edgeList.length);
        boolean st;
        start = System.currentTimeMillis();
        // running prim's algorithm for the graph
        st = g.Kruskal(edgeList, Cost, nodes, A);

        if(st=false) System.out.print("No Spanning Tree");
        stop = System.currentTimeMillis();
        // execution time for your algorithm.
        kruskalTime += stop - start;
        
        g.print(kruskalMst);
        System.out.println("Total cost: " + g.kruskalCost);
        System.out.println("Time for Kruskal Algorithm: " + kruskalTime);
        System.out.println("\n");
        
    }

    public void Prim(int[][] Cost, int n, int index){
        // create Near[]
        int[] Near = new int[n];
        Near[index] = -1;
        for(int i = 0; i < n; i++){
            if (i != index && Cost[i][i]==0){
                Near[i] = index;
            }
            else{
                Near[i] = -1;
            }
        }

        for(int i = 0; i < n-1; i++){
            int min = Integer.MAX_VALUE;
            for(int current = 0; current < n; current++){
                if(Near[current] != -1){
                    if(Cost[current][Near[current]] < min) 
                    {
                        min = Cost[current][Near[current]];

                        index = current;
                    }
                }
            }
            if(min == Integer.MAX_VALUE) break;
            
            primMst[index].adjList = new Neighbor(Near[index], min,
                                primMst[index].adjList);
            primMst[Near[index]].adjList = new Neighbor(index, min,
                                primMst[Near[index]].adjList);
            
            primCost += Cost[index][Near[index]];
            //System.out.println("index = "+index+"; min = "+min);
            Near[index] = -1;
            for(int k = 0; k < n; k++){
                if((Near[k]!=-1) && (Cost[k][Near[k]]>Cost[k][index])){
                    Near[k] = index;
                }
                //System.out.println(k+" "+Near[k]);
            }
            
        }
    }	

    public boolean Kruskal(Edge[] eList, int[][] Cost, int n, int[] A){
        int i = 0;
        int list = 0;
        HeapSort(eList,eList.length);
        
        A = new int[n+1];
        for(int m = 0; m < n+1; m++){
            A[m] = m;
        }
        //constructArrayA(eList, A, n);
        while (i < n-1 && list < eList.length){
            int j = Find2(eList[list].v1, A);
            int k = Find2(eList[list].v2, A);
            if (j!=k){
                
                kruskalMst[eList[i].v1].adjList = 
                        new Neighbor(eList[i].v2, eList[i].cost,
                                kruskalMst[eList[i].v1].adjList);
                kruskalMst[eList[i].v2].adjList = 
                        new Neighbor(eList[i].v1, eList[i].cost,
                                primMst[eList[i].v2].adjList);
                
                kruskalCost += eList[i].cost;
                //Merge3(j,k,height,A);
                i++;                
                Merge2(j,k,A);
            }
            list++;
        }
        if(i!=n-1)
            return false;
        else return true;
    }

    public int Find2(int x, int[]A){
        int i = x;
        while(A[i] != i){
            i = A[i];
        }
        return i;
    }

    public void Merge3(int a, int b,int[] height, int[] A){
        if(height[a] == height[b]){
            A[b] = a;
            height[a] = height[a]+1;
        }
        else if(height[a] > height[b]){
            A[b] = a;
        }
        else A[a] = b;
    }
    
    public void Merge2(int a, int b, int[] A){
        if(a<b){
            A[b] = a;
        }
        else{
            A[a] = b;
        }
    }

    public void HeapSort(Edge[] eList, int n){
        for(int i = (int)Math.floor(eList.length/2); i >= 0; i--){
            Max_Heapify(eList, i, n-1);
        }
        for(int i = n-1; i >= 1; i--){
            swap(eList, i, 0);
            Max_Heapify(eList, 0,i-1 );
        }
    }

    public void Max_Heapify(Edge[] eList, int i, int n){
        int l = 2*(i+1) - 1;
        int r = 2*(i+1);
        int largest;
        if(l < n && eList[l].cost > eList[i].cost){
            largest = l;
        }
        else{
            largest = i;
        }
 
        if(r < n && eList[r].cost > eList[largest].cost){
            largest = r;
        }
        
        if(largest != i){
            swap(eList, i, largest);
            Max_Heapify(eList, largest, n);
        }
    }

    public static void swap(Edge[] e, int x, int y){
        Edge temp = new Edge (e[x].v1, e[x].v2, e[x].cost);
        e[x]= new Edge(e[y].v1, e[y].v2, e[y].cost);
        e[y] = new Edge(temp.v1, temp.v2, temp.cost);
    }

    // Printing the graph for debugging
    public void print(Vertex[] v) {
        //int l = 0;
        System.out.println();
        for (int i = 0; i < v.length; i++) {
            System.out.print(v[i].id);
            for (Neighbor nbr = v[i].adjList; nbr != null; 
                    nbr = nbr.next) 
            {
                System.out.print(" -> " + 
                    nbr.vertexNumber + "("
                    + nbr.weight + ")");
                //l++;
            }
            System.out.println("\n");
        }
    }

    public void createEdgeList(int[][] Cost, GraphRandom g, 
                                int n, Edge[] e)
    {
        int z = 0;
        for(int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if(Cost[i][j]!=0 && Cost[i][j]!=Integer.MAX_VALUE){
                    if(i<j)
                        e[z++] = new Edge(i, j, Cost[i][j]);
                    
                }
            }
        }
    }
    
    public void createCostArray(int[][] Cost){
        // create Cost adjacency matrix
        for (int i = 0; i < vertexList.length; i++) {
            for (int j = 0; j < vertexList.length; j++) {
                if (i == j){
                    if(vertexList[i].adjList == null)
                        Cost[i][i] = Integer.MAX_VALUE;
                    else Cost[i][j] = 0;
                }
                else{
                    Cost[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        // initialize array with the costs
        for (int i = 0; i < vertexList.length; i++) {
            for (Neighbor nbr = vertexList[i].adjList; nbr != null; 
                    nbr = nbr.next) 
            {
                Cost[i][nbr.vertexNumber] = nbr.weight;
            }
        }
       
    }
    public void constructArrayA(Edge[] edgeList, int[] A, int n){
        for(int i = 0; i < n; i++)
            A[i] = -1;
        
        for(int i = 0; i < edgeList.length; i++){
            if (A[edgeList[i].v2] == -1 && A[edgeList[i].v1] == -1){
                A[edgeList[i].v1] = edgeList[i].v1;
                A[edgeList[i].v2] = edgeList[i].v1;
            }
            else if (A[edgeList[i].v2] != -1 && A[edgeList[i].v1] == -1){
                A[edgeList[i].v1] = edgeList[i].v2;
            }
            else if (A[edgeList[i].v2] == -1 && A[edgeList[i].v1] != -1){
                A[edgeList[i].v2] = edgeList[i].v1;
            }
            else{
                continue;
            }
        }
    }
        
}
