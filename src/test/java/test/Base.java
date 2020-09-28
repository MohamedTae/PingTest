package test;

import static org.junit.Assert.fail;

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
                    String mySubString = s.substring(
                        s.lastIndexOf(", ") + 1, 
                        s.lastIndexOf("%")
                    );
                    Integer PacketLoss = Integer.valueOf(mySubString);
                    if(PacketLoss >= 0){
                        fail("Failed");
                        System.out.println("line: " + PacketLoss);

                    }
                    else{
                        System.out.println("line: " + PacketLoss);
                    }
                }
            }
            p.waitFor();
            System.out.println ("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {}
    }
}
