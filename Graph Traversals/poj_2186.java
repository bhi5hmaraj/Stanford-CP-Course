import java.util.*;
import java.io.*;
public class poj_2186 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    

    static class SCC {

        /*
         * Kosaraju-Sharir Algorithm
         * 
         * Identify sinks (reverse post order in inverse graph)
         * Start normal dfs from the above order , the resulting components form SCC
         * 
         * If you want to use 1 based indexing set onBased flag to true
         * 
         */

        private ArrayList<Integer>[] invGraph;
        private ArrayDeque<Integer> stack;
        private Iterable<Integer>[] adj;
        private int group[];
        private boolean marked[];
        private int numOfComponents;
        private int st , V;


        @SuppressWarnings("unchecked")
        SCC(Iterable<Integer>[] adj , boolean oneBased) {
            st = oneBased ? 1 : 0;
            V = adj.length - st; 
            group = new int[V + st];
            this.adj = adj;
            invGraph = new ArrayList[V + st];
            for(int i=st;i<V + st;i++)
                invGraph[i] = new ArrayList<Integer>();
            for(int i=st;i<V + st;i++)
                for(int j : adj[i])
                    invGraph[j].add(i);

            marked = new boolean[V + st];
            stack = new ArrayDeque<Integer>();
            for(int i=st;i<V + st;i++)
                if(!marked[i])
                    reversePostOrder(i);

            marked = new boolean[V + st];
            int grp = 0;
            for(int i : stack)
                if(!marked[i])
                    dfs(i, grp++);

            numOfComponents = grp;
            stack = null;
        }

        private void reversePostOrder(int u) {
            marked[u] = true;
            for(int v : invGraph[u])
                if(!marked[v])
                    reversePostOrder(v);
            stack.push(u);
        }

        private void dfs(int u , int grp) {
            marked[u] = true;
            group[u] = grp;
            for(int v : adj[u])
                if(!marked[v])
                    dfs(v, grp);
        }
        public int[] getSCC() {
            return group;
        }
        public int numberOfComponents() {
            return numOfComponents;
        }


    }
    
    /*
     * If a node is reachable from all the nodes then in the inverse graph from these nodes 
     * we should be able to reach all the nodes .
     * Hence they should be nodes with indegree 0  in the compressed DAG (strongly connected)
     * Also if we have more than one nodes of this type then the answer is 0
     */

    private static void solve() {
        
        
        int V = nextInt();
        int E = nextInt();
        ArrayList<Integer>[] adj = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++)
            adj[i] = new ArrayList<Integer>();
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[v].add(u);  // inverse graph
        }
        
        SCC scc = new SCC(adj, true);
        int inDegree[] = new int[scc.numOfComponents];
        for (int i = scc.st; i < V + scc.st; i++)
            for (int v : adj[i])
                if (scc.group[i] != scc.group[v])
                    inDegree[scc.group[v]]++;
        
        int zeroGrp = -1;
        int zeroGrpCnt = 0;
        for(int i = 0; i < scc.numOfComponents; i++) {
            zeroGrpCnt += inDegree[i] == 0 ? 1 : 0;
            if(inDegree[i] == 0)
                zeroGrp = i;
        }
        
        int ans = 0;
        if(zeroGrpCnt == 1) {
            for(int i = 1; i <= V; i++)
                ans += scc.group[i] == zeroGrp ? 1 : 0;
        }
        
        println(ans);
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                new poj_2186().run();
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