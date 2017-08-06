import java.util.*;
import java.io.*;
public class poj_2292 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final char chars[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 
                                 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '+', '*', '/', '?'};
    
    static final int INF = (int) 1e9;
    static final int CUT = 11;
    static int freq[];
    static int memo[][];
    
    static int findOpt(int idx , int off) {
        if(idx > CUT) 
            return off == chars.length ? 0 : INF;
        else if(off >= chars.length)
            return INF;
        else if(memo[idx][off] != -1)
            return memo[idx][off];
        else {
            int minCost = INF;
            int curr = 0;
            for(int i = 1; i <= chars.length - off; i++) {
                curr += i * freq[off + i - 1];
                minCost = Math.min(minCost , curr + findOpt(idx + 1, off + i));
            }
            
            return memo[idx][off] = minCost;
        }
    }
    
    static int pos(char ch) {
        if(ch >= 'a' && ch <= 'z')
            return ch - 'a';
        else {
            switch(ch) {
            default:
                return -1;
            case '+':
                return 26;
            case '*':
                return 27;
            case '/':
                return 28;
            case '?':
                return 29;
            }
        }
    }
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            int M = nextInt();
            freq = new int[chars.length];
            while(M-->0)
                for(char ch : nextLine().toCharArray())
                    freq[pos(ch)]++;
            
            memo = new int[CUT + 1][chars.length];
            for(int a[] : memo) Arrays.fill(a, -1);
            
//            println("cost = " + findOpt(0, 0));
            findOpt(0, 0);
            StringBuilder ans = new StringBuilder();
            
            int idx = 0 , off = 0;
            while(idx < CUT) {
                int curr = 0;
                for(int i = 1; i <= chars.length - off; i++) {
                    curr += i * freq[off + i - 1];
                    if(memo[idx][off] == curr + memo[idx + 1][off + i]) {
                        ans.append(chars[off + i]);
                        idx++;
                        off += i;
                        break;
                    }
                }
            }
            
            println(ans);
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