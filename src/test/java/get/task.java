package get;

import java.util.HashMap;
import java.util.Map;

public class task {
    public static void main(String[] args) {

        String name = "abca";

        Map<Character, Integer> counMap = new HashMap<>();

        for (int i = 0; i < name.length(); i++) {

            if (!counMap.containsKey(name.charAt(i))){
                counMap.put(name.charAt(i), 1);
            }else {
                int value = counMap.get(name.charAt(i));
                counMap.put(name.charAt(i), value+1);
            }

        }
        System.out.println(counMap);


    }
}
