package com.gupao.baiyu.nio;

import java.io.*;

public class Test {

    public static void main(String[] args) {
        try {
            File file = new File("E:\\Test.txt");
            InputStream in = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            String line2 = br.readLine();
            String line3 = br.readLine();
            String line4 = br.readLine();
            String line5 = br.readLine();

            System.out.println(line);
            System.out.println(line2);
            System.out.println(line3);
            System.out.println(line4);
            System.out.println(line5);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
