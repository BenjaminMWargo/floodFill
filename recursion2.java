import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class recursion2{
    public static long startTime;
    public static  Map <String, Integer> getData(){
        Map <String, Integer> x = new HashMap<>();
        
        try{  
        FileReader fileReader = new FileReader("data.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
       
        String line = null;
        String[] inputStrings;
        while ((line = bufferedReader.readLine()) != null) {
           inputStrings = line.split(" ");
           x.put(inputStrings[0],Integer.parseInt(inputStrings[1]));
         }
        bufferedReader.close();
       } catch(Exception e){System.out.print("read error");}
        startTime = System.currentTimeMillis();
        return x;
    }
    public static Integer countAround(String k,Integer target,Integer count, Map <String, Integer> data, Map <String, Integer> history){
        //Exit if the node is not on the list of filled or has been added to history
        if (!(data.containsKey(k))|history.containsKey(k)|(data.get(k)!=target)){
            return count;
        }
        //Divide up string into int coords
        String[] parts = k.split(",");
        Integer x = Integer.parseInt(parts[0]);
        Integer y = Integer.parseInt(parts[1]);
        //Add current node to history
        history.put(k, data.get(k));
        //Count the current node
        count++;
        //Check up
        String nKey = x+","+(y-1);
        count = countAround(nKey,target, count, data, history);
        //Check down
        nKey = x+","+(y+1);
        count = countAround(nKey,target, count, data, history);
        //Check left
        nKey = (x-1)+","+y;
        count = countAround(nKey,target, count, data, history);
        //check right
        nKey = (x+1)+","+y;
        count = countAround(nKey,target, count, data, history);
        return count;
    }
    public static void main(String[] args) {
       Map <String, Integer> history = new HashMap<>();
       Map <String, Integer> data = new HashMap<>();
       Integer max,count,maxColor;
       max = 0;
       maxColor = 0;
       data = getData();
       for (String k:data.keySet()){
           count = 0;
           count = countAround(k,data.get(k),count,data,history);
           if (count >max){
               max = count;
               maxColor = data.get(k);
           }
       }
       System.out.println("Max is: " + max + " With value:" + maxColor);
       System.out.println("Search took:" + (System.currentTimeMillis()-startTime) + "ms");
    }
}