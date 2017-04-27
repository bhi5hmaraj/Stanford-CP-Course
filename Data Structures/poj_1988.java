import java.util.*;
import java.io.*;
public class poj_1988 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/

    static class DSU {
        private int parent[];
        private int size[];
        private int cnt;
        private int cubes[];
        DSU(int length) {
            this.cnt = length;
            parent = new int[length + 10];
            size   = new int[length + 10];
            cubes  = new int[length + 10];
            Arrays.fill(size, 1);
            for (int i = 0; i < parent.length; i++)
                parent[i] = i;
        }

        int root(int u) {
            while(parent[u] != u)
                u = parent[u];
            return u;
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
        int countCubes(int u) {
            int ct = cubes[u];
            while(u != parent[u]) 
                ct += cubes[u = parent[u]];
            return ct;
        }
        void union(int u, int v) {
            if (!connected(u, v)) {
                cnt--;
                int rootU = root(u);
                int rootV = root(v);
                if (size[rootU] <= size[rootV]) {
                    cubes[rootU] += size[rootV];
                    cubes[rootU] -= cubes[rootV];
                    parent[rootU] = rootV;
                    size[rootV] += size[rootU];
                } else {
                    cubes[rootV] -= cubes[rootU];
                    cubes[rootV] -= size[rootV];
                    cubes[rootU] += size[rootV];
                    parent[rootV] = rootU;
                    size[rootU] += size[rootV];
                }
            }
        }
    }

    
    private static void solve() {
        
        int Q = nextInt();
        final int MAX_N = 30000;
        DSU dsu = new DSU(MAX_N);
        while(Q-->0) {
            if(nextChar() == 'M')
                dsu.union(nextInt(), nextInt());
            else
                println(dsu.countCubes(nextInt()));
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