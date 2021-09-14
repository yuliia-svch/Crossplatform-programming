import java.math.BigInteger;
import java.util.Scanner;

public class Lab1 {

    public static long greatestCommonDivider(long a, long b) {
        if (a == 0)
            return b;
        while (b != 0) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }
        return a;
    }

    public static BigInteger greatestCommonDivider(BigInteger a, BigInteger b) {
        if(a.compareTo(BigInteger.ZERO) == 0)
            return b;
        while(b.compareTo(BigInteger.ZERO) != 0) {
            if(a.compareTo(b) > 0) {
                a = a.subtract(b);
            } else {
                b = b.subtract(a);
            }
        }
        return a;
    }

    public static String simplify(long a, long b) {
        long gcd = greatestCommonDivider(a, b);
        return (a / gcd) + "/" + (b / gcd);
    }

    public static String simplify(BigInteger a, BigInteger b) {
        BigInteger gcd = greatestCommonDivider(a, b);
        return (a.divide(gcd)) + "/" + (b.divide(gcd));
    }

    public static long[] summarize(byte n){
        long numerator = 0;
        long denumerator = 1;
        for(int i = 1; i <= n; i++){
            denumerator *= (long) i * (i + 1);
        }
        for(int i = 1; i <= n; i++){
            numerator += denumerator / ((long) i * (i + 1));
        }
        return new long[]{numerator, denumerator};
    }

    public static BigInteger[] summarizeIfMoreThan15(byte n){
        BigInteger numerator = BigInteger.valueOf(0);
        BigInteger denumerator = BigInteger.valueOf(1);
        for(int i = 1; i <= n; i++){
            denumerator = denumerator.multiply(BigInteger.valueOf((long) i * (i + 1)));
        }
        for(int i = 1; i <= n; i++){
            numerator = numerator.add(denumerator.divide(BigInteger.valueOf((long) i * (i+1))));
        }
        return new BigInteger[]{numerator, denumerator};
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a number of members in the series: ");
        byte n = in.nextByte();
        if(n <= 15){
            long[] result = summarize(n);
            System.out.print(String.format("Series with %d members summarized = %d/%d = %s"
                    ,n ,result[0], result[1], simplify(result[0], result[1])));
        } else {
            BigInteger[] result = summarizeIfMoreThan15(n);
            System.out.print(String.format("Series with %d members summarized = %d/%d = %s"
                    ,n ,result[0], result[1], simplify(result[0], result[1])));
        }

    }
}
