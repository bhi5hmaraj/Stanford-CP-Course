import java.util.*;
import java.io.*;
public class poj_2136 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        int freq[] = new int[26];
        for(int i=0;i<4;i++)
            for(char ch : nextLine().toCharArray())
                if(Character.isLetter(ch))
                    freq[ch - 'A']++;
        
        int max = 0;
        for(int a : freq)
            max = Math.max(max,a);
        
        int end[] = new int[max];   // Endpoint for each alphabet
        
        for(int height = 0;height < max;height++) 
            for(int i=0;i<26;i++)
                if(freq[i] >= height + 1)
                    end[height] = i;
        
        for(int height = max - 1;height >= 0;height--) {
            for(int i=0;i<26 && i <= end[height];i++)
                print((freq[i] >= height + 1 ? "*" : " ") + (i < end[height] ? " " : ""));
            print('\n');
        }
        
        for(int i=0;i<26;i++)
            print((i == 0 ? "" : " ") + (char)('A' + i));
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