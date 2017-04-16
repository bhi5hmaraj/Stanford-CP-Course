import java.util.*;
import java.io.*;
public class poj_2356 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        /*
         * If any prefixSum % N is 0 we are done , or else
         * we have N - 1 positive remainder i.e. 1...N-1
         * But we have N prefix sums , hence due to pigeonhole principle
         * there will be atleast 2 prefix sum with the same remainder
         */
        int N = nextInt();
        int arr[] = nextIntArray(N);
        int prefixSum[] = new int[N + 1];
        for(int i=1;i<=N;i++)
            prefixSum[i] = (prefixSum[i - 1] + arr[i - 1]) % N;
        
        int prev[] = new int[N];
        Arrays.fill(prev, -1);
        
        for(int i=0;i<=N;i++) {
            if(prev[prefixSum[i]] == -1)
                prev[prefixSum[i]] = i;
            else {
                println(i - prev[prefixSum[i]]);
                for(int j = prev[prefixSum[i]];j < i;j++)
                    println(arr[j]);
                return;
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