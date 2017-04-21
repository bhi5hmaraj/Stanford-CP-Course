import java.util.*;
import java.io.*;
public class poj_1984 {
    
    
    
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
            return (parent[p] == p) ? p : (parent[p] = root(parent[p]));
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
    
    static int dx[] = {-1 , 0 , 1 , 0}; //up , right , down and left 
    static int dy[] = {0 , 1 , 0 , -1};
    
    static class Edge {
        int v , dir , len;
        Edge(int v , int dir , int len) {
            this.v = v;
            this.dir = dir;
            this.len = len;
        }
    }
    
    static int getDir(char ch) {
        switch(ch) {
            case 'N': return 0;
            case 'E': return 1;
            case 'S': return 2;
            case 'W': return 3;
        }
        return -1;
    }
    
    static int getOppDir(int dir) {
        return (dir + 2) % 4;   // It happened that the opposites are 2 apart !!
    }
    
    static ArrayList<Edge> adj[];
    static int coord[][];
    
    static void dfs(int u , int par, int x , int y) {
        coord[u][0] = x;
        coord[u][1] = y;
        for(Edge e : adj[u])
            if(e.v != par) 
                dfs(e.v, u, x + (dx[e.dir] * e.len), y + (dy[e.dir] * e.len));
    }
    
    @SuppressWarnings("unchecked")
    private static void solve() {
        
        int N = nextInt();
        int M = nextInt();
        int edges[][] = new int[M][2];
        coord = new int[N + 1][2];
        adj = new ArrayList[N + 1];
        for(int i=1;i<=N;i++)
            adj[i] = new ArrayList<Edge>();
        
        for(int i=0;i<M;i++) {
            edges[i] = nextIntArray(2);
            int len = nextInt();
            int dir = getDir(nextChar());
            adj[edges[i][0]].add(new Edge(edges[i][1], dir, len));
            adj[edges[i][1]].add(new Edge(edges[i][0], getOppDir(dir), len));
        }
        
        dfs(1, 0, 0, 0);
        /*
        for(int i=1;i<=N;i++)
            println(i + " ==> " + Arrays.toString(coord[i]));
        */
        int Q = nextInt();
        int ans[] = new int[Q];
        DSU dsu = new DSU(N);
        int query[][] = new int[Q][4];
        for(int i=0;i<Q;i++) {
            query[i][0] = nextInt();    // from vertex
            query[i][1] = nextInt();    // to vertex
            query[i][2] = nextInt();    // index in the data after which Bob asks the query
            query[i][3] = i;            // position of query
        }
        
        Arrays.sort(query, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        
        int sync = 0;
        for(int perQuery[] : query) {
            for(;sync < perQuery[2];sync++)
                dsu.union(edges[sync][0] , edges[sync][1]);
            
            if(dsu.connected(perQuery[0], perQuery[1]))
                ans[perQuery[3]] = Math.abs(coord[perQuery[0]][0] - coord[perQuery[1]][0]) +
                                   Math.abs(coord[perQuery[0]][1] - coord[perQuery[1]][1]);
            else
                ans[perQuery[3]] = -1;
        }
        
        for(int a : ans)
            println(a);
        
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