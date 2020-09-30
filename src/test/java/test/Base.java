package test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Base {
    
    public static void main(String args[]) {
        String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec("ping -c10 109.224.14.2");
            BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null){
                System.out.println(s);
                if(s.contains("packet loss")){
                    s = s.substring(s.indexOf("received, ") + 10);
                    s = s.substring(0, s.indexOf("%"));
                    System.out.println(s);
                    
                    Float PacketLoss = Float.valueOf(s);
                    if(PacketLoss >= 50){
                        fail("Failed");
                        System.out.println("line: " + PacketLoss);
                    }
                }
                if(s.contains("avg/max/")){
                    s = s.substring(s.indexOf(" = ") + 3);
                    //System.out.println(s);
                    s = s.substring(s.indexOf("/") + 1);
                    //System.out.println(s);
                    s = s.substring(s.indexOf("/") + 1);
                    //System.out.println(s);
                    s = s.split("/")[0];
                    //System.out.println(s);
                    String P = String.valueOf(s);
                    GenerateCsv.generateCsvFile("s.csv", P);
                }
            }
            p.waitFor();
            //System.out.println ("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {}
    }
}
