import java.util.*;
import java.io.*;
public class poj_1703 {
    
    
    
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
            return (parent[p] == p) ? p : (parent[p] = root(parent[p]));    // Path Compression
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

    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            int N = nextInt();
            int M = nextInt();
            DSU dsu = new DSU(N);
            int opposite[] = new int[N + 1];
            while(M-->0) {
                char type = nextChar();
                int u = nextInt();
                int v = nextInt();
                if(type == 'D') {
                    if(opposite[u] == 0 && opposite[v] == 0) {
                        opposite[u] = v;
                        opposite[v] = u;
                    }
                    else if(opposite[u] == 0 && opposite[v] > 0) {
                        opposite[u] = v;
                        dsu.union(opposite[v], u);
                    }
                    else if(opposite[u] > 0 && opposite[v] == 0) {
                        opposite[v] = u;
                        dsu.union(opposite[u], v);
                    }
                    else if(!dsu.connected(u, opposite[v])) {
                        dsu.union(u, opposite[v]);
                        dsu.union(v, opposite[u]);
                    }
                }
                else {
                    if(dsu.connected(u, v))
                        println("In the same gang.");
                    else if(dsu.connected(u, opposite[v]))
                        println("In different gangs.");
                    else
                        println("Not sure yet.");
                }
            }
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