import java.util.*;
import java.io.*;
public class poj_1426 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int rem[];
    static int N;
    static final int MAX_DIGIT = 100;
    static String memo[][];
    /*
     * Precompute 10^x mod N , for 0 <= x <= 100 (It's given that maximum number of digits is 100)
     * Then perform a subset sum on the remainder array
     * 
     */
    static String getMultiple(int idx , int sum , boolean picked) {
        if(idx == MAX_DIGIT)
            return sum == 0 && picked ? "" : "-1";
        else if(memo[idx][sum] != null)
            return memo[idx][sum];
        else {
            String ret2 = getMultiple(idx + 1, (sum + rem[idx]) % N, true);
            if(!ret2.equals("-1"))
                return memo[idx][sum] = ret2 + "1";

            if(idx < MAX_DIGIT - 1) {   // Shouldn't have leading 0's
                String ret1 = getMultiple(idx + 1, sum, picked);
                if(!ret1.equals("-1"))
                    return memo[idx][sum] = ret1 + "0";
            }
            
            return memo[idx][sum] = "-1";
        }
    }
    
    private static void solve() {
        
        while((N = nextInt()) != 0) {
            rem = new int[MAX_DIGIT];
            rem[0] = 1;
            for(int i=1;i<MAX_DIGIT;i++)
                rem[i] = (rem[i - 1] * 10) % N;
            
            memo = new String[MAX_DIGIT][N];
            println(getMultiple(0, 0, false));
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