package scanner;

import java.util.Arrays;
import java.util.Scanner;

public class ScannerTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(s);
        char[] c = s.toCharArray();
        int[] dd = new int[2];
        int k=0;
        for(int i=0;i<c.length; i++)
        {
            if(Character.isDigit(c[i]))
            {
                dd[k++] = Integer.valueOf(c[i]+"");
            }
        }

        System.out.println(Arrays.toString(dd));
//        while(scanner.hasNext())
//        {
//            int a = scanner.nextInt();
//            System.out.println(a);
//        }
    }
}
