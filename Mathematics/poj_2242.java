import java.util.*;
import java.io.*;
public class poj_2242 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/

    static class Vector {
        double         x, y;
        private double norm;

        Vector(double x, double y) {
            this.x = x;
            this.y = y;
            this.norm = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }

        Vector() {
            this(0, 0);
        }

        double magnitude() {
            return norm;
        }

        double angleTo(Vector that) {
            double cosTheta = ((this.x * that.x) + (this.y * that.y)) / (this.magnitude() * that.magnitude());
            return Math.acos(cosTheta);
        }
    }
    
    private static void solve() {
        
        String line;
        final double PI = 3.141592653589793;
        while((line = nextLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            
            double x1 = Double.parseDouble(st.nextToken());
            double y1 = Double.parseDouble(st.nextToken());
            double x2 = Double.parseDouble(st.nextToken());
            double y2 = Double.parseDouble(st.nextToken());
            double x3 = Double.parseDouble(st.nextToken());
            double y3 = Double.parseDouble(st.nextToken());
/*            
            double C1 = x3*x3 - x2*x2 + y2*y2 - y1*y1;
            double C2 = x3*x3 - x2*x2 + y3*y3 - y2*y2;
            
            double y = (((C2 * (x1 - x2)) / (x2 - x3)) - C1) / (2.0 * ((y1 - y2) - (((y2 - y3) * (x1 - x2)) / (x2 - x3))));
            double x = ((-2.0 * (y1 - y2) * y) - C1) / (2.0 * (x1 - x2));
            
            println("radius = " + Point2D.distance(x, y, x1, y1));
            
            double circumference = 2.0 * PI * Point2D.distance(x, y, x1, y1);
            
            println(String.format("%.2f", circumference));
*/        
            /*
             * Use law of sine 
             * a / sin(A) = b / sin(B) = c / sin(C) = 2R
             * 
             */
            
            double A = new Vector(x2 - x1, y2 - y1).angleTo(new Vector(x3 - x1, y3 - y1));
            double a = new Vector(x3 - x2, y3 - y2).magnitude();
            double circumference = PI * (a / Math.sin(A));
            println(String.format("%.2f", circumference));
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