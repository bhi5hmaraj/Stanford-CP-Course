import java.util.*;
import java.io.*;
public class poj_2127 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static void debug(int arr[][]) {
        for(int a[] : arr) {
            for(int b : a)
                print(String.format("%2d ", b));
            print('\n');
        }
    }
    
    private static void solve() {
        
        int n = nextInt();
        int A[] = nextIntArray(n);  
        int m = nextInt();
        int B[] = nextIntArray(m);  
        
        HashSet<Integer> set = new HashSet<Integer>();
        for(int a : A) set.add(a);
        
        TreeSet<Integer> intersection = new TreeSet<Integer>();
        for(int b : B) if(set.contains(b)) intersection.add(b);

        int DP[][] = new int[n][m];
        int RMQ[][] = new int[n][m];
        

        for (int asc : intersection) {
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    if (A[i] == B[j] && A[i] == asc) {
                        if (i > 0 && j > 0)
                            DP[i][j] = RMQ[i - 1][j - 1];
                        DP[i][j]++;
                    }

            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++) {
                    RMQ[i][j] = Math.max(RMQ[i][j], DP[i][j]);
                    if (i > 0)
                        RMQ[i][j] = Math.max(RMQ[i][j], RMQ[i - 1][j]);
                    if (j > 0)
                        RMQ[i][j] = Math.max(RMQ[i][j], RMQ[i][j - 1]);
                }
        }
        
        int LCISLen = 0;
        int curr_i = -1 , curr_j = -1;
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                if(DP[i][j] > LCISLen) {
                    LCISLen = DP[i][j];
                    curr_i = i;
                    curr_j = j;
                }
        
        int new_i = -1, new_j = -1;
        int ans[] = new int[LCISLen];
        int ptr = LCISLen - 1;
        while(!(new_i == curr_i && new_j == curr_j)) {
            // System.out.println("curr x " + curr_i + " curr_y " + curr_j);
            new_i = curr_i;
            new_j = curr_j;
            ans[ptr--] = A[curr_i];
            outer:
            for(int i = 0; i < curr_i; i++)
                for(int j = 0; j < curr_j; j++)
                    if(A[i] == B[j] && A[i] < A[curr_i] && DP[i][j] == DP[curr_i][curr_j] - 1) {
                        curr_i = i;
                        curr_j = j;
                        break outer;
                    }
            
        }
        
        println(LCISLen);
        for(int num : ans)
            print(num + " ");
        print('\n');
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
//         reader = new BufferedReader(new InputStreamReader(System.in));
//         writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        reader = new BufferedReader(new FileReader("gcis.in"));
        writer = new PrintWriter(new BufferedWriter(new FileWriter("gcis.out")));
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