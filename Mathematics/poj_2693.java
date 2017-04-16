import java.util.*;
import java.io.*;
public class poj_2693 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class Vector {
        double         x, y;
        private double norm;
        

        public String toString() {
            return String.format("(%.3f , %.3f)", x , y);
        }
        
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
        
        Vector add(Vector that) {
            return new Vector(this.x + that.x, this.y + that.y);
        }
        
        static Vector[] getPerpendicularUnit(Vector v) {
            Vector[] vecs = new Vector[2];
            double constant;
            if(v.x == 0) { 
                constant = 1.0 / Math.sqrt(1.0 + ((v.x * v.x) / (v.y * v.y)));
                vecs[0] = new Vector(constant, -constant * (v.x / v.y));
            }
            else {
                constant = 1.0 / Math.sqrt(1.0 + ((v.y * v.y) / (v.x * v.x)));
                vecs[0] = new Vector(-constant * (v.y / v.x), constant);
            }
            vecs[1] = new Vector(-vecs[0].x, -vecs[0].y);
            
            return vecs;
        }
        
        void multiply(double scalar) {
            x *= scalar;
            y *= scalar;
            norm *= scalar; 
        }
        
    }
    
    static double distSq(double x1 , double y1 , double x2 , double y2) {
        double diff1 = x1 - x2;
        double diff2 = y1 - y2;
        return (diff1 * diff1) + (diff2 * diff2);
    }
    
    
    private static void solve() {
        
        ArrayList<double[]> points = new ArrayList<double[]>();
        String line;
        final double DIAMETER = 5.0;
        final double RADIUS = 2.5;
        final double RADIUS_SQ = 6.25;
        int maxCnt = 1;
        
        while((line = nextLine()) != null) {
            String sp[] = line.split(" ");
            points.add(new double[]{Double.parseDouble(sp[0]) , Double.parseDouble(sp[1])});
        }
        
        for(int i=0;i<points.size();i++)
            for(int j=i + 1;j<points.size();j++) {
                double p1[] = points.get(i);
                double p2[] = points.get(j);
                Vector vec = new Vector(p1[0] - p2[0], p1[1] - p2[1]);
                if(vec.magnitude() <= DIAMETER) {
                    Vector M = new Vector((p1[0] + p2[0]) / 2.0, (p1[1] + p2[1]) / 2.0);
                    Vector perps[] = Vector.getPerpendicularUnit(vec);
                    // System.out.println("perps " + perps[0] + " , " + perps[1]);
                    double magnitude = Math.sqrt((RADIUS * RADIUS) - ((vec.norm * vec.norm) / 4.0));
                    
                    perps[0].multiply(magnitude);
                    perps[1].multiply(magnitude);
                    Vector C1 = M.add(perps[0]);
                    Vector C2 = M.add(perps[1]);
                    // println("C1 " + C1 + " C2 " + C2);
                    int cnt1 = 2 , cnt2 = 2;
                    for(int k=0;k<points.size();k++) {
                        if(k != i && k != j) {
                            double pt[] = points.get(k);
                            if(distSq(C1.x, C1.y, pt[0], pt[1]) <= RADIUS_SQ)
                                cnt1++;
                            if(distSq(C2.x, C2.y, pt[0], pt[1]) <= RADIUS_SQ)
                                cnt2++;
                        }
                    }
                    
                    maxCnt = Math.max(maxCnt,Math.max(cnt1,cnt2));
               }
            }
        
        println(points.size() == 0 ? 0 : maxCnt);
        
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