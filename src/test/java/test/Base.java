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
                    System.out.println(s);
                    String mySubString = s.substring(
                        s.lastIndexOf(", ") + 1, 
                        s.lastIndexOf("%")
                    );
                    Float PacketLoss = Float.valueOf(mySubString);
                    if(PacketLoss >= 50){
                        fail("Failed");
                        System.out.println("line: " + PacketLoss);
                    }
                    else{
                        System.out.println("line: " + PacketLoss);
                        throw new AssumptionViolatedException("skipping");
                    }
                }
            }
            p.waitFor();
            //System.out.println ("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {}
    }
}
