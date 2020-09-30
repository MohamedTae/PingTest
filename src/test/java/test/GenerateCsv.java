package test;
import java.io.FileWriter;
import java.io.IOException;
 
public class GenerateCsv
{
   public static void main(String [] args)
   {
    generateCsvFile("test.csv","3.3"); 
   }
 
   public static void generateCsvFile(String sFileName,String max)
   {
 try
 {
     FileWriter writer = new FileWriter(sFileName,true);
     writer.append(max);
     writer.append('\n');
 
     //generate whatever data you want
 
     writer.flush();
     writer.close();
 }
 catch(IOException e)
 {
      e.printStackTrace();
 } 
    }
}
