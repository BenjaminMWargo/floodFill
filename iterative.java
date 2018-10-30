import java.util.*;
public class iterative{
public static void main(String[] args){
    Map <String, Boolean> history = new HashMap<>();
    Map <String, Boolean> data = new HashMap<>();
    Deque <String> queue = new LinkedList<String>();
    map = getData();
    int count,max;
    String s;
    String[] parts;
    Integer x,y; 
    Integer.parseInt(parts[1]);
    max = 0;
    for(String k:data.keySet){
        if (history.containsKey(k)){
         continue;
        }
        count = 0;
        history.put(k,data.get(k));
        queue.put(k);
        parts = k.split(",");
        x = Integer.parseInt(parts[0]);
        y = Integer.parseInt(parts[1]);
        //Put 4 sides to queue
        queue.push((x+1)+","+y);
        queue.push((x-1)+","+y);
        queue.push(x+","+(y+1));
        queue.push(x+","+(y-1));
        while ((s=queue.poll())!=null){
            if (history.containsKey(s)|data.get(s)!=data.get(k)){
                //if visited before or not matching, ignore it
                continue;
            }
            history.put(s, data.get(s));
            count++;
            //put 4 sides to queue
        }
        if (count>max){
            max = count;
        }
    }
    System.out.print(max);
}
}