import java.io.*;
import java.util.*;

public class Annagram {

    public static void main(String[] args){
        try {
            List<String> wordList = readFile();
            Map<String, String> anagramGroups = groupAnagrams(wordList);
            anagramGroups.values().stream().filter(f -> f.contains(" ")).forEach(v -> System.out.println(v));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public static List<String> readFile() throws IOException {
        List<String> wordList = new ArrayList<>();
        String path = "src/main/resources/ordbok-utf8.txt";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
        String line;
        while ( (line = reader.readLine()) != null){
            if (!line.equals("")) {
                wordList.add(line);
            }
        }
        return wordList;
    }

    public static Map<String,String> groupAnagrams(List<String> words) {
        HashMap<String,String> anagramGroups = new HashMap<>();
        for (String word: words) {
            char[] wordArray = word.toLowerCase().toCharArray();
            Arrays.sort(wordArray);
            String sortedKey = String.valueOf(wordArray);
            if (anagramGroups.get(sortedKey) == null){
                anagramGroups.put(sortedKey,word);
            }
            else {
                String anagramGroup = anagramGroups.get(sortedKey).concat(" " + word);
                anagramGroups.put(sortedKey,anagramGroup);
            }
        }
        return anagramGroups;
    }
}
