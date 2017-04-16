import java.io.IOException;
import java.text.DecimalFormat;

public class poj_1654 {

    /*
     * Source : http://www.mathopenref.com/coordpolygonarea2.html
     */

    static int dx[] = { 0, -1, 0, 1, -1, 0, 1, -1, 0, 1 };
    static int dy[] = { 0, -1, -1, -1, 0, 0, 0, 1, 1, 1 };
    
    /*
     * Storing the data in a string causes an MLE !! , also don't print trailing zeros after decimal point 
     * ie. if number is 5.0 print 5
     */
    public static void main(String[] args) throws IOException {

        int T = 0;
        int digit;
        while ((digit = System.in.read()) != '\n')
            T = (T * 10) + (digit - '0');

        while (T-- > 0) {
            int prevX = 0;
            int prevY = 0;
            long area = 0;
            int ch;
            while ((ch = System.in.read()) != '5') {
                int k = ch - '0';
                int newX = prevX + dx[k];
                int newY = prevY + dy[k];
                area += 1L * (newX + prevX) * (newY - prevY);
                prevX = newX;
                prevY = newY;
            }
            System.in.read(); // burn the \n

            area += -1L * prevX * prevY;
            System.out.println(new DecimalFormat("#.#").format(Math.abs(area) / 2.0));
        }
        
    }
}