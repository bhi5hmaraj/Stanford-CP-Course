import java.util.*;
import java.io.*;
public class poj_1806 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int x[] = {-1 , 0 , 1 , 0 , 0 , 0}; //up , right , down and left 
    static int y[] = {0 , 1 , 0 , -1 , 0 , 0};
    static int z[] = {0 , 0 , 0 , 0 , 1 , -1};
    static int N;
    static boolean isValid(int i , int j , int k) {
        return i >= 0 && i < N && j >= 0 && j < N && k >= 0 && k < N;
    }
    private static void solve() {
        
        int Q = nextInt();
        for(int scenario = 1;scenario <= Q;scenario++) {
            N = nextInt()*2 + 1;
            int maxFuel = N / 2;
            int grid[][][] = new int[N][N][N];
            for(int a[][] : grid)
                for(int b[] : a)
                    Arrays.fill(b, -1);
            
            ArrayDeque<int[]> queue = new ArrayDeque<int[]>();
            queue.add(new int[]{N / 2 , N / 2 , N / 2});
            grid[N/2][N/2][N/2] = 0;
            
            while(!queue.isEmpty()) {
                int coord[] = queue.remove();
                if(grid[coord[0]][coord[1]][coord[2]] < maxFuel) {
                    for(int i=0;i<6;i++) { 
                        int newX = coord[0] + x[i];
                        int newY = coord[1] + y[i];
                        int newZ = coord[2] + z[i];
                        if(isValid(newX, newY, newZ) && grid[newX][newY][newZ] == -1) {
                            grid[newX][newY][newZ] = grid[coord[0]][coord[1]][coord[2]] + 1;
                            queue.add(new int[]{newX , newY , newZ});
                        }
                    }
                }
            }
            
            println("Scenario #" + (scenario) + ":");
            for(int slice = 0;slice < N;slice++) {
                println("slice #" + (slice + 1) + ":");
                for(int i=0;i<N;i++) {
                    for(int j=0;j<N;j++)
                        print(grid[i][j][slice] == -1 ? "." : grid[i][j][slice]);
                    print('\n');
                }
            }
            
            print(scenario < Q ? '\n' : "");
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