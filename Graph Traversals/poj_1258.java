import java.util.*;
import java.io.*;
public class poj_1258 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class DSU {
        private int parent[];
        private int size[];
        private int cnt;

        DSU(int length) {
            this.cnt = length;
            parent = new int[length + 10];
            size = new int[length + 10];
            Arrays.fill(size, 1);
            for (int i = 0; i < parent.length; i++)
                parent[i] = i;
        }

        int root(int p) {
            while(p != parent[p]) p = parent[p];
            return p;
        }

        int sizeOf(int p) {
            return size[root(p)];
        }

        boolean connected(int u, int v) {
            return root(u) == root(v);
        }

        int components() {
            return cnt;
        }

        void union(int u, int v) {
            if (!connected(u, v)) {
                cnt--;
                int rootU = root(u);
                int rootV = root(v);
                if (size[rootU] < size[rootV]) {
                    parent[rootU] = rootV;
                    size[rootV] += size[rootU];
                } else {
                    parent[rootV] = rootU;
                    size[rootU] += size[rootV];
                }
            }
        }
    }


    static class Edge_MST implements Comparable<Edge_MST> // For kruskal MST
    {
        int u, v;
        int cost;

        Edge_MST(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge_MST o) {
            return cost - o.cost;
        }
    }

    private static long KruskalMST(ArrayList<Edge_MST> arr, int V) {
        Collections.sort(arr);
        DSU dsu = new DSU(V);
        long minCost = 0;
        for (int i = 0; i < arr.size() && dsu.components() != 1; i++) {
            if (!dsu.connected(arr.get(i).u, arr.get(i).v)) {
                dsu.union(arr.get(i).u, arr.get(i).v);
                minCost += arr.get(i).cost;
            }
        }
        return minCost;
    }


    
    private static void solve() {
        
        String line;
        while((line = nextLine()) != null) {
            int V = Integer.parseInt(line);
            ArrayList<Edge_MST> edges = new ArrayList<Edge_MST>();
            
            for(int i = 0; i < V; i++) {
                int cost[] = nextIntArray(V);
                for(int j = 0; j < V; j++)
                    if(cost[j] > 0)
                        edges.add(new Edge_MST(i, j, cost[j]));
            }
            println(KruskalMST(edges, V));
        }
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve();
        reader.close();
        writer.close();
    }
    
    static BufferedReader reader;
    static PrintWriter    writer;
    static StringTokenizer st;
    
    static String next()
    {while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}            
    st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
    static String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}             
    static int    nextInt()   {return Integer.parseInt(next());}
    static long   nextLong()  {return Long.parseLong(next());}     
    static double nextDouble(){return Double.parseDouble(next());}
    static char   nextChar()  {return next().charAt(0);}
    static int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
    static long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}    
    static int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}            
    static long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}            
    static void   print(Object o)  { writer.print(o);  }
    static void   println(Object o){ writer.println(o);}
    
    /************************ TEMPLATE ENDS HERE ************************/
    
}