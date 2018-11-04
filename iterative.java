import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class iterative{
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
public static void main(String[] args){
    Map <String, Integer> history = new HashMap<>();
    Map <String, Integer> data = new HashMap<>();
    Deque <String> queue = new LinkedList<String>();
    data = getData();
    int count,max,maxVal;
    String s;
    String[] parts;
    Integer x,y; 
   
    max = 0;
    maxVal = 0;
    for(String k:data.keySet()){
        if (history.containsKey(k)){
         continue;
        }
        count = 1;
        history.put(k,data.get(k));
        queue.addFirst(k);
        parts = k.split(",");
        x = Integer.parseInt(parts[0]);
        y = Integer.parseInt(parts[1]);
        //Put 4 sides to queue
        queue.addFirst((x+1)+","+y);
        queue.addFirst((x-1)+","+y);
        queue.addFirst(x+","+(y+1));
        queue.addFirst(x+","+(y-1));
        while ((s=queue.pollFirst())!=null){
            if (history.containsKey(s)|data.get(s)!=data.get(k)){
                //if visited before or not matching, ignore it
                continue;
            }
            history.put(s, data.get(s));
            count++;
            //put 4 sides to queue
            parts = s.split(",");
            x = Integer.parseInt(parts[0]);
            y = Integer.parseInt(parts[1]);
            queue.addFirst((x+1)+","+y);
            queue.addFirst((x-1)+","+y);
            queue.addFirst(x+","+(y+1));
            queue.addFirst(x+","+(y-1));

        }
        if (count>max){
            max = count;
            maxVal = data.get(k);
        }
    }
    System.out.println("Max is: " + max + " With value:" + maxVal);
    System.out.println("Search took:" + (System.currentTimeMillis()-startTime) + "ms");
}
}