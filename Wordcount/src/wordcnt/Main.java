package wordcnt;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here

        File file = new File("/home/l/Desktop/Book/testIJ/storybook.txt");
        Scanner sc = new Scanner(file);

        long word = 0;
        while(sc.hasNextLine())
        {
            String ss= sc.nextLine();
            word += ss.split(" ").length;
        }
        System.out.println(word);



    }
}
