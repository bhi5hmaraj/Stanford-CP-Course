import java.util.*;
import java.io.*;
public class poj_3177 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Integer> tree[];
    static ArrayList<Integer>[] adj;
    static boolean marked[];
    static int biConnected[];
    static int level[];
    static int minLevel[];
    static int biComp;
    static void dfs(int u , int par , int lev) {
        marked[u] = true;
        level[u]  = lev;
        int parallelEdge = 0;
        for(int v : adj[u]) {
            if(v != par) {
                if(marked[v])
                    minLevel[u] = Math.min(minLevel[u] , level[v]);
                else {
                    dfs(v, u, lev + 1);
                    minLevel[u] = Math.min(minLevel[u] , minLevel[v]);
                }
            }
            else 
                parallelEdge++;
        }
        
        if(parallelEdge > 1)
            minLevel[u] = Math.min(minLevel[u] , level[par]);
    }
    static void findBiconnected(int u) {
        marked[u] = true;
        for(int v : adj[u])
            if(!marked[v]) {
                if(minLevel[v] <= level[u]) // not a bridge
                    biConnected[v] = biConnected[u];
                else
                    biConnected[v] = ++biComp;
                findBiconnected(v);
            }
    }
    

    
    static int countLeaves(int u , int par) {
        int subtree = 0;
        int cnt = 0;
        for(int v : tree[u])
            if(v != par) {
                subtree++;
                cnt += countLeaves(v, u);
            }
        return cnt + (subtree == 0 ? 1 : 0);
    }
    
    private static void solve() {
        
        
        int V = nextInt();
        int E = nextInt();
        
        adj = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++)
            adj[i] = new ArrayList<Integer>();
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        
        marked = new boolean[V + 1];
        biConnected = new int[V + 1];
        level = new int[V + 1];
        minLevel = new int[V + 1];
        biComp = 0;
        Arrays.fill(minLevel, Integer.MAX_VALUE);
        dfs(1, 0, 0);
        marked = new boolean[V + 1];
        findBiconnected(1);
        biComp++;
        tree = new ArrayList[biComp];
        for(int i = 0; i < biComp; i++)
            tree[i] = new ArrayList<Integer>();
        for(int i = 1; i <= V; i++)
            for(int j : adj[i]) 
                if(biConnected[i] != biConnected[j]) 
                    tree[biConnected[i]].add(biConnected[j]);
                
        if(biComp == 1) // Single biconnected component 
            println(0);
        else 
            println((countLeaves(0, -1) + 1 + (tree[0].size() == 1 ? 1 : 0)) / 2);
        
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