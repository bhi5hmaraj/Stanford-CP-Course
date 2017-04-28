import java.util.*;
import java.io.*;
public class poj_1988 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/

    static class CustomDSU {
        private int parent[];
        private int size[];
        private int cnt;
        private int cubes[];
        CustomDSU(int length) {
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

    

    static class Treap {
        /*
         * Based on https://sites.google.com/site/indy256/algo/treap_set
         * It is a max priority heap based treap
         */
        
        // static final long SEED = 226366815112524829L;
        
        static class TreapNode {
            int key , size , value;
            int priority;
            TreapNode left , right;
            
            TreapNode(int key , int value , int priority) {
                left = right = null;
                this.key = key;
                this.priority = priority;
                this.value = value;
                size = 1;
            }
            @Override
            public String toString() {
                return String.format("[key = %d sz = %d val = %d]", key ,size , value);
            }
        }
        static class TreapNodePair {
            TreapNode left , right;
            TreapNodePair(TreapNode left , TreapNode right) {
                this.left = left;
                this.right = right;
            }
        }
        private static int size(TreapNode treap) {
            return treap == null ? 0 : treap.size;
        }
        
        private int last() {
            TreapNode curr = root;
            while(curr.right != null)
                curr = curr.right;
            return curr.key;
        }
        private int first() {
            TreapNode curr = root;
            while(curr.left != null)
                curr = curr.left;
            return curr.key;
        }
        private static void update(TreapNode treap) {
            treap.size = 1 + size(treap.left) + size(treap.right);
        }
        
        private Random rand;
        private TreapNode root;
        
        Treap() {
            rand = new Random();
            root = null;
        }
        /*
         * All the elements in the left tree are <= x 
         */
        private TreapNodePair split(TreapNode treap , int x) {
            if(treap == null)
                return new TreapNodePair(null, null);
            else if(treap.key <= x) {   // No need to take care of left subtree now
                TreapNodePair rightSplit = split(treap.right, x);
                treap.right = rightSplit.left;
                update(treap);
                rightSplit.left = treap;
                return rightSplit;
            }
            else {
                TreapNodePair leftSplit = split(treap.left, x);
                treap.left = leftSplit.right;
                update(treap);
                leftSplit.right = treap;
                return leftSplit;
            }
        }
        
        private TreapNode merge(TreapNode leftTreap , TreapNode rightTreap) {
            if(leftTreap == null && rightTreap == null)
                return null;
            else if(leftTreap == null)
                return rightTreap;
            else if(rightTreap == null)
                return leftTreap;
            else {
                if(leftTreap.priority > rightTreap.priority) {
                    leftTreap.right = merge(leftTreap.right, rightTreap);
                    update(leftTreap);
                    return leftTreap;
                }
                else {
                    rightTreap.left = merge(leftTreap , rightTreap.left);
                    update(rightTreap);
                    return rightTreap;
                }
            }
        }
        
        private TreapNode insert(TreapNode treap , int x , int val) {
            if(treap == null)
                return new TreapNode(x, val , rand.nextInt());
            else {
                TreapNodePair split = split(treap, x);
                TreapNode newNode = merge(merge(split.left, new TreapNode(x, val , rand.nextInt())), split.right);
                return newNode;
            }
        }
        public void insert(int x , int val) {
            root = insert(root, x , val);
        }
        
        private int countLess(TreapNode treap , int x) {
            return treap == null ? 0 :
                   x < treap.key ? countLess(treap.left, x) :
                   x > treap.key ? size(treap.left) + 1 + countLess(treap.right, x) :
                                   size(treap.left);
        }
        
        public int countLess(int x) {
            return countLess(root , x);
        }
  
        private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb, TreapNode treap) {
            if (treap == null) {
                sb.append("Tree Empty\n");
                return sb;
            }
            if (treap.right != null) {
                toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb, treap.right);
            }
            sb.append(prefix).append(isTail ? "└── " : "┌── ").append(treap).append("\n");
            if (treap.left != null) {
                toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb, treap.left);
            }
            return sb;
        }

        @Override
        public String toString() {
            return this.toString(new StringBuilder(), true, new StringBuilder(), root).toString();
        }
    }
    

    static class DSUTreap {
        private int parent[];
        private int size[];
        private int cnt;
        private int map[];
        private Treap treap[];
        DSUTreap(int length) {
            this.cnt = length;
            parent = new int[length + 10];
            size = new int[length + 10];
            map = new int[length + 10];
            treap = new Treap[length + 10];
            Arrays.fill(size, 1);
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
                treap[i] = new Treap();
                treap[i].insert(0, i);
            }
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
        
        void copy(Treap.TreapNode from , Treap to , int offset) {
            if(from != null) {
                copy(from.left, to, offset);
                map[from.value] = offset + Treap.size(from.left);
                to.insert(map[from.value], from.value);
                copy(from.right, to, offset + 1 + Treap.size(from.left));
            }
        }
        
        void union(int u, int v) {
            if (!connected(u, v)) {
                cnt--;
                int rootU = root(u);
                int rootV = root(v);
                if (size[rootU] <= size[rootV]) {
                    parent[rootU] = rootV;
                    size[rootV] += size[rootU];
                    copy(treap[rootU].root, treap[rootV], treap[rootV].last() + 1);
                    treap[rootU].root = null;
                    treap[rootU] = null;
                } else {
                    parent[rootV] = rootU;
                    size[rootU] += size[rootV];
                    copy(treap[rootV].root, treap[rootU], treap[rootU].first() - treap[rootV].root.size);
                    treap[rootV].root = null;
                    treap[rootV] = null;
                }
            }
        }
    }

    /*
     * Time   : 1922MS
     * Memory : 5368K
     */
    private static void solve() {
        
        int Q = nextInt();
        final int MAX_N = 30000;
        CustomDSU dsu = new CustomDSU(MAX_N);
        while(Q-->0) {
            if(nextChar() == 'M')
                dsu.union(nextInt(), nextInt());
            else
                println(dsu.countCubes(nextInt()));
            
        }
        
    }
    
    /*
     * Time   : 2516MS
     * Memory : 9724K
     */
    private static void solve2() {
        
        int Q = nextInt();
        final int MAX_N = 30000;
        DSUTreap dsu = new DSUTreap(MAX_N);
        while(Q-->0) {
            if(nextChar() == 'M')
                dsu.union(nextInt(), nextInt());
            else {
                int x = nextInt();
                println(dsu.treap[dsu.root(x)].countLess(dsu.map[x]));
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