import java.util.*;
import java.io.*;
public class poj_2140 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        int N = nextInt();
        int cnt = 0;
        for(int i=1;i<=N;i++) {
            int sum = 0;
            int j = i;
            while(sum < N) 
                sum += j++;
            
            cnt += sum == N ? 1 : 0;
        }
        println(cnt);
    }
    
    static long isPerfectSquare(long n) {
        long sqrt = (long) Math.sqrt(n);
        return sqrt * sqrt == n ? (long) sqrt : -1;
    }
    
    private static void solve2() {
        int N = nextInt();
        int cnt = 0;
        for(long i=1;i<=N;i++) {
            long sqrt = isPerfectSquare(4L*i*i - 4L*i + 8L*N + 1);
            long consecutive = (sqrt - 2L*i + 1);
            if(sqrt > 0 && consecutive > 0 && consecutive % 2L == 0) 
                cnt++;
        }
        println(cnt);
    }
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve(); // Time = 1328ms
        // solve2();   // Time = ~3000ms
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