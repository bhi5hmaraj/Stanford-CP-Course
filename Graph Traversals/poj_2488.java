import java.util.*;
import java.io.*;
public class poj_2488 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int n , m;
    static int dx[] = {-1 , 1 , -2 , 2 , -2 , 2 , -1 , 1};
    static int dy[] = {-2 , -2 , -1 , -1 , 1 , 1 , 2 , 2};
    static boolean marked[][];
    static ArrayDeque<String> ans;
    static String convert(int i , int j) {
        return String.valueOf((char)('A' + j)) + (i + 1);
    }
    static boolean isValid(int i , int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }
    static boolean dfs(int x , int y , int remain) {
        boolean flag = false;
        if(remain == 1) 
            flag = true;
        else {
            marked[x][y] = true;
            for(int choice = 0 ; choice < 8; choice++) {
                int nx = x + dx[choice];
                int ny = y + dy[choice];
                if(isValid(nx, ny) && !marked[nx][ny]) {
                    if((flag = dfs(nx, ny, remain - 1)))
                        break;
                }
            }
            marked[x][y] = false;
        }
        if(flag)
            ans.push(convert(x, y));
        return flag;
    }
    private static void solve() {
        
        
        int T = nextInt();
        for(int tc = 1; tc <= T; tc++) {
            
            // row - n - number , col - m - alphabet
            n = nextInt();
            m = nextInt();
            marked = new boolean[n][m];
            ans = new ArrayDeque<String>();
            outer:
            for(int i = 0; i < n; i++)
                for(int j = 0; j < m; j++)
                    if(dfs(i, j, n * m))
                        break outer;
            
            println("Scenario #" + tc + ":");
            if(ans.isEmpty())
                print("impossible");
            else
                for(String s : ans)
                    print(s);
            println('\n');
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