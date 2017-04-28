import java.util.*;
import java.io.*;
public class poj_3321 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class SegmentTree  { 
        int tree[];
        int len;
        int size;
        SegmentTree(int len) { // arr should be a 1 based array
            this.len = len;
            size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
            tree = new int[size];
            build(1, 1, len);
        }
        void update(int node,int idx,int val,int nl,int nr) {
            if(nl == nr && nl == idx)
                tree[node] = val;
            else {
                int mid = (nl + nr) / 2;
                if(idx <= mid)
                    update(2*node, idx , val ,nl , mid);
                else
                    update((2*node) + 1, idx ,val , mid + 1, nr);

                tree[node] = tree[2*node] + tree[(2 * node) + 1];
            }
        }
        void update(int idx , int val){
            update(1, idx, val, 1, len);
        }
        int query(int L , int R){
            return query(1, L, R, 1, len);
        }
        int query(int node , int L , int R, int nl, int nr) {
            int mid = (nl + nr) / 2;
            if(nl == L && nr == R)
                return tree[node];
            else if(R <= mid)
                return query(2 * node, L, R, nl, mid);
            else if(L > mid)
                return query((2*node)+1, L, R, mid + 1 , nr);
            else
                return query(2*node, L, mid , nl , mid) + query((2*node)+1, mid+1, R , mid+1,nr);
        }
        void build(int node,int L,int R) {
            if(L == R)
                tree[node] = 1;
            else
            {
                int mid = L + ((R-L)/2);
                build(2 * node, L, mid);
                build((2 * node) + 1, mid + 1, R);
                tree[node] = tree[2*node] + tree[(2 * node) + 1];
            }
        }
    }

    static class FenwickTree { 
        /**************** DONT USE BIT IF YOUR INDEX STARTS FROM ZERO (causes infinite loop) ******************/
        int tree[];
        int len;
        FenwickTree(int len) {
            this.len = len;
            tree = new int[len + 10];
        }
        void update(int idx , int val) {
            if(idx == 0) throw new IndexOutOfBoundsException("BIT IS NOT ZERO INDEXED");
            for(;idx <= len;idx += (idx & -idx))
                tree[idx] += val;
        }
        int query(int idx) {
            int sum = 0;
            for(;idx > 0;idx -= (idx & -idx))
                sum += tree[idx];

            return sum;
        }
        int query(int L , int R) {
            return R < L ? 0 : query(R) - query(L - 1);
        }
    }

    static int open[] , close[];
    static ArrayList<Integer>[] adj;
    static int time;
    static void dfs(int u , int par) {
        open[u] = time++;
        for(int v : adj[u])
            if(v != par)
                dfs(v, u);
        close[u] = time - 1;
    }
    
    /*
     * Fenwick Tree
     * Memory: 25624K       Time: 2375MS 
     */
    @SuppressWarnings("unchecked")
    private static void solve2() {  
        
        int N = nextInt();
        time = 1;
        adj = new ArrayList[N + 1];
        open = new int[N + 1];
        close = new int[N + 1];
        for(int i=1;i<=N;i++)
            adj[i] = new ArrayList<Integer>();
        
        int E = N - 1;
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        dfs(1, 0);
        
        BitSet bitSet = new BitSet(N + 1);
        bitSet.set(1, N + 1);
        
        int Q = nextInt();
        FenwickTree fenwickTree = new FenwickTree(N);
        for(int i=1;i<=N;i++)
            fenwickTree.update(i, 1);

        while(Q-->0) {
            if (nextChar() == 'Q') {
                int x = nextInt();
                println(fenwickTree.query(open[x], close[x]));
            }
            else {
                int x = nextInt();
                fenwickTree.update(open[x], bitSet.get(x) ? -1 : 1);
                bitSet.flip(x);
            }
        }
    }
    /*
     * Segment Tree
     * Memory: 26268K       Time: 2719MS
     */
    @SuppressWarnings("unchecked")
    private static void solve1() {  
        
        int N = nextInt();
        time = 1;
        adj = new ArrayList[N + 1];
        open = new int[N + 1];
        close = new int[N + 1];
        for(int i=1;i<=N;i++)
            adj[i] = new ArrayList<Integer>();
        
        int E = N - 1;
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        dfs(1, 0);
        
        BitSet bitSet = new BitSet(N + 1);
        bitSet.set(1, N + 1);
        
        int Q = nextInt();
        SegmentTree segTree = new SegmentTree(N);
        while(Q-->0) {
            if (nextChar() == 'Q') {
                int x = nextInt();
                println(segTree.query(open[x], close[x]));
            }
            else {
                int x = nextInt();
                segTree.update(open[x], bitSet.get(x) ? 0 : 1);
                bitSet.flip(x);
            }
        }
    }
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve2();
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