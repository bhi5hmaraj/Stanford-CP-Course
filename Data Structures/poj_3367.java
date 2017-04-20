import java.util.*;
import java.io.*;
public class poj_3367 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Node> nodes;
    
    static class Node implements Comparable<Node> {
        Node left , right;
        char ch;
        int level;
        Node(char ch , Node l ,Node r) {
            this.ch = ch;
            left = l;
            right = r;
        }
        @Override
        public int compareTo(Node o) {
            return o.level - this.level;
        }
    }
    
    static void dfs(Node node , int lev) {
        if(node != null) {
            dfs(node.right , lev + 1);
            node.level = lev;
            nodes.add(node);
            dfs(node.left , lev + 1);
        }
    }
    
    private static void solve() {
        int T = nextInt();
        while(T-->0) {
            
            String str = nextLine();
            nodes = new ArrayList<Node>();
            ArrayDeque<Node> stack = new ArrayDeque<Node>();
            
            for(int i = 0 , len = str.length();i < len;i++) {
                char ch =  str.charAt(i);
                if(Character.isLowerCase(ch))
                    stack.push(new Node(ch, null, null));
                else {
                    Node a = stack.pop();
                    Node b = stack.pop();
                    stack.push(new Node(ch, b, a));
                }
            }
            
            dfs(stack.pop() , 0);
            Collections.sort(nodes);
            StringBuilder builder = new StringBuilder();
            for(Node node : nodes)
                builder.append(node.ch);
            
            println(builder);
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