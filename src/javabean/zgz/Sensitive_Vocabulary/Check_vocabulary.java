package javabean.zgz.Sensitive_Vocabulary;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
public class Check_vocabulary {
    public Check_vocabulary(){}
    long startTime = System.currentTimeMillis();
    final int thNum = 5;
    final String filePath1 = "src\\Sensitive_Vocabulary\\暴恐词库.txt"; //266M
    final String filePath2 = "src\\Sensitive_Vocabulary\\反动词库.txt"; //186M
    final String filePath3 = "src\\Sensitive_Vocabulary\\民生词库.txt"; //39KB
    final String filePath4 = "src\\Sensitive_Vocabulary\\色情词库.txt"; //1KB
    final String filePath5 = "src\\Sensitive_Vocabulary\\贪腐词库.txt";
    public String faction(String check_word) {

        String result = "";
        String[] first = new String[5];
        CountDownLatch doneSignal = new CountDownLatch(thNum);
        ReadFileThread2 r1 = new ReadFileThread2(doneSignal, filePath1, check_word);
        ReadFileThread2 r2 = new ReadFileThread2(doneSignal, filePath2, check_word);
        ReadFileThread2 r3 = new ReadFileThread2(doneSignal, filePath3, check_word);
        ReadFileThread2 r4 = new ReadFileThread2(doneSignal, filePath4, check_word);
        ReadFileThread2 r5 = new ReadFileThread2(doneSignal, filePath5, check_word);
        first[0] = r1.ru();
        first[1] = r2.ru();
        first[2] = r3.ru();
        first[3] = r4.ru();
        first[4] = r5.ru();
        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            if (first[i] != "") {
                result = result + first[i];
            }
        }
        System.out.println(result);
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间"+(endTime-startTime));
        return  result;
    }
}
class ReadFileThread2 extends Thread {
    private CountDownLatch doneSignal;
    private String path;
    private String vab;

    public ReadFileThread2(CountDownLatch doneSignal, String path, String vab) {
        this.doneSignal = doneSignal;
        this.path = path;
        this.vab = vab;
    }
    public String ru() {
        String bb = "";
        try {

            BufferedReader in = null;
            in = new BufferedReader(new InputStreamReader(new FileInputStream(path), "gbk"));
            String strTmp = "";
            String first_word = "";
            first_word = in.readLine();
            int i = 0;
            while ((strTmp = in.readLine()) != null) {
                i++;

                if (vab.contains(strTmp)) {

                    bb = first_word;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        doneSignal.countDown();

        return bb;
    }

}
