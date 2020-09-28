package test;
import static org.junit.Assert.*;
import org.junit.internal.AssumptionViolatedException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Base {
    public static void main(String args[]) {
        String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec("ping -c10 google.com");
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
            }
            p.waitFor();
            //System.out.println ("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {}
    }
}
