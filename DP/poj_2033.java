import java.util.*;
import java.io.*;
public class poj_2033  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static String line;
    static long memo[];
    static long rec(int idx) {
        if(idx == line.length())
            return 1;
        else if(line.charAt(idx) == '0')
            return 0;
        else if(memo[idx] != -1)
            return memo[idx];
        else {
            long ret = rec(idx + 1);
            int num = line.charAt(idx) - '0';
            if(idx < line.length() - 1 && (num * 10 + (line.charAt(idx + 1) - '0') <= 26))
                ret += rec(idx + 2);
            
            return memo[idx] = ret;
        }
    }
    
    private static void solve() {
        
        
        while(!(line = nextLine()).equals("0")) {
            memo = new long[line.length()];
            Arrays.fill(memo, -1);
            println(rec(0));
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