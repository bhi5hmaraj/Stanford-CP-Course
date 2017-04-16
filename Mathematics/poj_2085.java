import java.util.*;
import java.io.*;
public class poj_2085 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        int N , M;
        final int MAX_N = (int) 5e4;
        TreeMap<Integer , Integer> map = new TreeMap<Integer , Integer>();
        for(int i=1;i<=MAX_N;i++)
            map.put((i * (i - 1)) / 2, i);
        
        while((N = nextInt()) != -1 && (M = nextInt()) != -1) {
            int floor = map.floorEntry(M).getValue();
            int ans[] = new int[N + 1];
            /*
             * Find the largest consecutive sum less than or equal to M
             * We can greedily fill the remaining inversions
             * we take the next biggest number not in the reversal and include it 
             * suppose N = 6 , M = 13
             * first we fill for M = 10
             * 1 6 5 4 3 2 
             * now we need 3 more , if we remove 4 , shift 3 2 to left and insert 1 to last 
             * we can now increase the inversions by exactly 3 .
             * 4 6 5 3 2 1
             * Hence by construction we can prove that there always exists a permutation for a
             * given inversion.
             */
            for(int i=1;i<=N - floor;i++)   
                ans[i] = i;
            for(int i=N - floor + 1;i <= N;i++)
                ans[i] = 2*N - i - floor + 1;
            
            int sumOfFloor = (floor * (floor - 1)) / 2;
            if(M - sumOfFloor > 0) {
                ans[N - floor] = ans[N - (M - sumOfFloor) + 1];
                for(int i=N - (M - sumOfFloor) + 1, dec = ans[N - floor] - 1;i <= N;i++ , dec--)
                    ans[i] = dec;
            }
            
            for(int i=1;i<=N;i++)
                print((i == 1 ? "" : " ") + ans[i]);
            
            print('\n');
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