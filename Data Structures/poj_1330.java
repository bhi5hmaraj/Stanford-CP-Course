import java.util.*;
import java.io.*;
public class poj_1330 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int V;
    
    /* LCA <NlogN , logN> dependency : level , log , V , DP = new int[log(V) + 1][V + 1]; */
    static int DP[][]; // One based vertices
    static int level[];
    static int adj[][];
    
    // Courtesy : UWI ( adjacency list using Jagged Arrays )
    static int[][] packD(int n, int[] from, int[] to , int isOneBased) {   
        int[][] g = new int[n + isOneBased][];
        int[] p = new int[n + isOneBased];
        for (int f : from)
            p[f]++;
        for (int i = 0 + isOneBased; i < n + isOneBased; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < from.length; i++) 
            g[from[i]][--p[from[i]]] = to[i];
        
        return g;
    }
    static int log(int N){
        return 31 - Integer.numberOfLeadingZeros(N);
    }
    static void binaryLift() {        
        for (int i = 1; i < DP.length; i++) 
            for (int j = 1; j <= V; j++) 
                DP[i][j] = DP[i - 1][DP[i - 1][j]];

    }
    static void dfs(int u , int lev) {
        level[u] = lev;
        for(int v : adj[u])
            dfs(v, lev + 1);
    }
    static int LCA(int u , int v){

        if(level[v] < level[u]){
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = level[v] - level[u];
        while(diff > 0){        // Bring v to the same level as u
            int log = log(diff);
            v = DP[log][v];
            diff -= (1 << log);
        }

        while(u != v){
            int i = log(level[u]);
            for(;i > 0 && DP[i][u] == DP[i][v];)
                i--;

            u = DP[i][u];
            v = DP[i][v];
        }

        return u;
    }

    
    private static void solve() {
        /*
         * Using ArrayList causes MLE , use LinkedLists or 2D arrays
         */
        int T = nextInt();
        while(T-->0) {
        
            V = nextInt();
            level = new int[V + 1];
            int E = V - 1;

            int from[] = new int[E];
            int to[] = new int[E];
            int root = ((V + 1) * V) / 2;
            DP = new int[log(V) + 1][V + 1];
            while(E-->0) {
                int par = nextInt();
                int child = nextInt();
                from[E] = par;
                to[E] = child;
                root -= child;
                DP[0][child] = par;
            }

            adj = packD(V, from, to, 1);
            dfs(root, 0);
            binaryLift();
            println(LCA(nextInt(), nextInt()));
        
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
