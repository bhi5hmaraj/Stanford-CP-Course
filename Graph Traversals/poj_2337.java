import java.util.*;
import java.io.*;
public class poj_2337  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class Edge implements Comparable<Edge> {
        int v , key;
        Edge(int v , int k) {
            this.v = v;
            this.key = k;
        }
        @Override
        public int compareTo(Edge o) {
            return words[key].compareTo(words[o.key]);
        }
    }
    
    static String words[];
    static ArrayList<Edge> adj[];
    static ArrayList<Integer> stack;
    static Iterator<Edge>[] iter;
    
    static void eulerTour(int u , Edge e) {
//        if(e != null)
//            System.out.println((char)(u + 'a') + " ed " + words[e.key]);
        while(iter[u] != null && iter[u].hasNext()) {
            Edge next = iter[u].next();
            eulerTour(next.v , next);
        }
        if(e != null)
            stack.add(e.key);
    }
    
    
    private static void solve() {
        
        
        int T = nextInt();
        while(T-->0) {
            int n = nextInt();
            words = new String[n];
            adj = new ArrayList[26];
            iter = new Iterator[26];
            
            int inDegree[] = new int[26];
            
            for(int i = 0; i < 26; i++)
                adj[i] = new ArrayList<Edge>();
            
            for(int i = 0; i < n; i++)
                words[i] = nextLine();
            
            for(int i = 0; i < n; i++) { 
                adj[words[i].charAt(0) - 'a'].add(new Edge(words[i].charAt(words[i].length() - 1) - 'a', i));
                inDegree[words[i].charAt(words[i].length() - 1) - 'a']++;
            }
            for(int i = 0; i < 26; i++) {
                Collections.sort(adj[i]);
                if(adj[i].size() > 0)
                    iter[i] = adj[i].iterator();
            }
            
            boolean equal = true;
            int cntOut = 0;
            int cntIn  = 0;
            int start = 0;
            for(int i = 0; i < 26; i++) {
                if(adj[i].size() > 0)
                    start = i;
                equal &= inDegree[i] == adj[i].size();
                cntOut += adj[i].size() == inDegree[i] + 1 ? 1 : 0;
                cntIn  += inDegree[i] == adj[i].size() + 1 ? 1 : 0;
            }
            
            if(equal) {
                for(int i = 0; i < 26; i++)
                    if(adj[i].size() > 0)
                        start = adj[i].get(0).compareTo(adj[start].get(0)) < 0 ? i : start;
            }
            else if(cntIn == cntOut && cntIn == 1) {
                for(int i = 0; i < 26; i++)
                    if(adj[i].size() == inDegree[i] + 1)
                        start = i;
            }
            else {
                println("***");
                continue;
            }
            
            //println("start " + (char)(start + 'a'));
            
            stack = new ArrayList<Integer>();
            eulerTour(start, null);
            
            if(stack.size() != n)
                println("***");
            else {
                for(int i = n - 1; i >= 0; i--)
                    print(words[stack.get(i)] + (i > 0 ? "." : ""));
                print('\n');
            }
        }
        
        
        
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
//        writer = new PrintWriter("out.txt");
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