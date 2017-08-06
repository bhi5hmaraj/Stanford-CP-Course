import java.util.*;
import java.io.*;
public class poj_1160 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int position[];
    static int V;
    static int cost[][];
    static int memo[][];
    static int findOpt(int idx , int remain) {
        if(idx == V)
            return 0;
        else if(remain == 0)
            return cost[V - 1][idx - 1];
        else if(idx == 0) {
            int min = Integer.MAX_VALUE;
            for(int i = 0; i < V; i++)
                min = Math.min(min , cost[0][i] + findOpt(i + 1, remain - 1));
            return min;
        }
        else if(memo[idx][remain] != -1)
            return memo[idx][remain];
        else {
            int min = Integer.MAX_VALUE;
            for(int i = idx; i < V; i++) {
                int lo = idx - 1 , hi = i;
                int floor = lo;
                int key = (position[lo] + position[hi]) >> 1;
                while(lo <= hi) {
                    int mid = (lo + hi) >> 1;
                    if(key >= position[mid]) {
                        lo = mid + 1;
                        floor = mid;
                    }
                    else
                        hi = mid - 1;
                }
                
                min = Math.min(min , cost[floor][idx - 1] + cost[floor + 1][i] + findOpt(i + 1, remain - 1));
            }
            return memo[idx][remain] = min;
        }
    }
    
    private static void solve() {
        
        
        V = nextInt();
        int P = nextInt();
        position = nextIntArray(V);
        
        // long st = System.nanoTime();
        
        memo = new int[V][P + 1];
        for(int a[] : memo) Arrays.fill(a, -1);
        
        cost = new int[V][V];
        for(int i = 0; i < V; i++)
            for(int j = 0; j <= i; j++)
                for(int k = i; k < V; k++) {
                    cost[j][k] += position[k] - position[i];
                    cost[k][j] += position[i] - position[j];
                }
        
        println(findOpt(0, P));
        // println("Time " + (System.nanoTime() - st) / 1e9);
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
