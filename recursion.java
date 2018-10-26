import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class recursion{
    public static  Map <String, Boolean> getData(){
        Map <String, Boolean> x = new HashMap<>();
        
        try{  
        FileReader fileReader = new FileReader("data.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
       
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
           x.put(line,true);
         }
        bufferedReader.close();
       } catch(Exception e){System.out.print("read error");}

        return x;
    }
    public static Integer countAround(String k,Integer count, Map <String, Boolean> data, Map <String, Boolean> history){
        //Exit if the node is not on the list of filled or has been added to history
        if (!(data.containsKey(k))|history.containsKey(k)){
            return count;
        }
        //Divide up string into int coords
        String[] parts = k.split(",");
        Integer x = Integer.parseInt(parts[0]);
        Integer y = Integer.parseInt(parts[1]);
        //Add current node to history
        history.put(k, true);
        //Count the current node
        count++;
        //Check up
        String nKey = x+","+(y-1);
        count = countAround(nKey, count, data, history);
        //Check down
        nKey = x+","+(y+1);
        count = countAround(nKey, count, data, history);
        //Check left
        nKey = (x-1)+","+y;
        count = countAround(nKey, count, data, history);
        //check right
        nKey = (x+1)+","+y;
        count = countAround(nKey, count, data, history);
        return count;
    }
    public static void main(String[] args) {
       Map <String, Boolean> history = new HashMap<>();
       Map <String, Boolean> data = new HashMap<>();
       Integer max,count;
       max = 0;
       data = getData();
       for (String k:data.keySet()){
           count = 0;
           count = countAround(k,count,data,history);
           if (count >max){
               max = count;
           }
       }
       System.out.println("Max is: " + max);
    }
}