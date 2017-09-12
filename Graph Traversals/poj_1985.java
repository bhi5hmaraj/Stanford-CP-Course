import java.util.*;
import java.io.*;
public class poj_1985 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<int[]>[] adj;
    static int[] getLongest(int u , int par , int len) {  // (vertex , dist) tuple 
        int curr[] = new int[]{u , len};
        for(int edge[] : adj[u])
            if(edge[0] != par) {
                int sub[] = getLongest(edge[0], u, len + edge[1]);
                if(sub[1] > curr[1]) {
                    curr[0] = sub[0];
                    curr[1] = sub[1];
                }
            }
        return curr;
    }
    
    private static void solve() {
        
        int V = nextInt();
        int E = nextInt();
        adj = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++)
            adj[i] = new ArrayList<int[]>();
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            int cost = nextInt();
            next(); // burn
            adj[u].add(new int[]{v , cost});
            adj[v].add(new int[]{u , cost});
        }
        
        println(getLongest(getLongest(1, 0, 0)[0], 0, 0)[1]);
        
        
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                new poj_1985().run();
            }
        }, "Increase Stack", 1 << 25).start();

    }

    void run(){ 
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve();
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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