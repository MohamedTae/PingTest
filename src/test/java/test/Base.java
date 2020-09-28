package test;

import java.io.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.internal.AssumptionViolatedException;


public class Base
{

  public static void run(String args[]) 
  throws IOException
  {
    // create the ping command as a list of strings
    Base ping = new Base();
    List<String> commands = new ArrayList<String>();
    commands.add("ping");
    commands.add("-c");
    commands.add("5");
    commands.add("127.0.0.1");
    commands.add("grep");
    commands.add("-e");
    commands.add("'packet loss'");
    commands.add("-e");
    commands.add("'max'");

    ping.doCommand(commands);
  }

  public void doCommand(List<String> command) 
  throws IOException
  {
    String s = null;

    ProcessBuilder pb = new ProcessBuilder(command);
    Process process = pb.start();

    BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
    BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

    // read the output from the command
    System.out.println("Here is the standard output of the command:\n");
    while ((s = stdInput.readLine()) != null)
    {
      System.out.println(s);
    }

    // read any errors from the attempted command
    System.out.println("Here is the standard error of the command (if any):\n");
    while ((s = stdError.readLine()) != null)
    {
      System.out.println(s);
      fail("ooops");
    }
  }

}
