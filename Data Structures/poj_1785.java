import java.util.*;
import java.io.*;
public class poj_1785 {
    
    /*
     * This was a tricky problem to implement . My first approach was to reuse my treap implementation ,
     * but since the priorities aren't randomized the height of the tree isn't O(lg N) . Therefore it was causing 
     * TLE . Then I realised that we don't have to insert the elements online . Since we just need the final 
     * representation of the tree , we can create the tree in an offline fashion . This can be done by first sorting 
     * the nodes based on the keys and then choosing the node with the highest priority as the root and recursively
     * solving the problem on the left subtree and the right subtree. This leads to an O(Nlg N) solution (if we can get 
     * range maximum query in O(lg N)) . This is where things get interesting , I coded the above approach and got a MLE 
     * Initially I assumed that it was due to the segment tree (uses 4N memory) , so I coded a square root decomposition 
     * approach which uses sqrt(N) memory but runs in O(Nsqrt(N)) time . Meanwhile I had a hunch that the recursion 
     * might be the culprit . So I looked up on how to do an inorder traversal using stack . I coded the stack and square root
     * decomposition approach . It caused an Output Overflow (WTF?) . Then after an hour of desperation , I swapped the 
     * sqrt with previously used Segtree . Voila !! ran in ~950MS and ~28MB . Eventhough it was frustrating to have figured
     * out the right approach but still not able to get it accepted , it paid off a lot. 
     * 
     * TL;DR Recursion is evil and SegTree ain't that bad
     */
    
    /************************ SOLUTION STARTS HERE ************************/

    static final int[] NULL = {-1 , -1 , -1};
    static class Pair implements Comparable<Pair> {
        String key;
        int priority;
        Pair(String key , int priority) {
            this.key = key;
            this.priority = priority;
        }
        @Override
        public int compareTo(Pair o) {
            return key.compareTo(o.key);
        }
    }
    
    static Pair arr[];
    static class SegmentTree {
        int tree[];
        int len;   // len of array
        SegmentTree() {
            len = arr.length;
            tree = new int[4 * len];
            build(1, 0, len - 1);
        }

        int build(int index, int start, int end) {
            if (start == end)
                return tree[index] = start;
            else {
                int mid = (start + end) / 2;
                int l = build(2 * index, start, mid);
                int r = build((2 * index) + 1, mid + 1, end);
                return tree[index] = arr[l].priority > arr[r].priority ? l : r;
            }
        }

        int query(int L, int R) {
            return query(1, 0, len - 1, L , R);
        }

        int query(int index, int start, int end, int l, int r) {
            int mid = (start + end) / 2;
            if (start == l && end == r)
                return tree[index];
            else if (end < l || start > r)
                return Integer.MIN_VALUE;
            else if (r <= mid)
                return query(2 * index, start, mid, l, r);
            else if (l > mid)
                return query((2 * index) + 1, mid + 1, end, l, r);
            else {
                int leftMax = query(2 * index, start, mid, l, mid);
                int rightMax = query((2 * index) + 1, mid + 1, end, mid + 1, r); 
                return arr[leftMax].priority > arr[rightMax].priority ? leftMax : rightMax;
            }
        }
    }
    
    static SegmentTree tree;
    
    static void printTreap(int N) {
        ArrayDeque<int[]> stack = new ArrayDeque<int[]>();
        int curr[] = {0 , N - 1 , tree.query(0, N - 1)};
        while(curr[0] <= curr[1]) {
            print('(');
            stack.push(curr);
            curr = new int[]{curr[0] , curr[2] - 1 , tree.query(curr[0], curr[2] - 1)};
        }
        
        while(!stack.isEmpty()) {
            curr = stack.pop();
            if(curr == NULL)
                print(')');
            else {
                print(arr[curr[2]].key + "/" + arr[curr[2]].priority);
                stack.push(NULL);
                
                curr = new int[]{curr[2] + 1 , curr[1] , tree.query(curr[2] + 1, curr[1])}; // right sub tree
                while(curr[0] <= curr[1]) {
                    print('(');
                    stack.push(curr);
                    curr = new int[]{curr[0] , curr[2] - 1 , tree.query(curr[0], curr[2] - 1)};
                }
            }
        }
        
        print('\n');
    }
    
    private static void solve() {
        
        int N;
        while((N = nextInt()) != 0) {
            arr = new Pair[N];
            for(int i=0;i<N;i++) {
                String data = next();
                int slash = data.indexOf('/');
                arr[i] = new Pair(data.substring(0, slash), Integer.parseInt(data.substring(slash + 1)));
            }
            Arrays.sort(arr);
            tree = new SegmentTree();
            printTreap(N);
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