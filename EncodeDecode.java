import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
        
public class EncodeDecode { 
    

    public static List<Integer> encode(String text) {
        int dictSize = 128;
        Map<String, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < dictSize; i++) {
            dictionary.put(String.valueOf((char) i), i);
        }

        String oldChar = "";
        List<Integer> result = new ArrayList<>();
        for (char character : text.toCharArray()) {
            String newChar = oldChar + character;
            if (dictionary.containsKey(newChar)) {
                oldChar = newChar;
            } else {
                result.add(dictionary.get(oldChar));
                dictionary.put(newChar, dictSize++);
                oldChar = String.valueOf(character);
            }
        }
        if (!oldChar.isEmpty()) {
            result.add(dictionary.get(oldChar));
        }
        return result;
    }

    public static String decode(List<Integer> encodedText) {
        int dictSize = 128;
        Map<Integer, String> dictionary = new HashMap<>();
        for (int i = 0; i < dictSize; i++) {
            dictionary.put(i, String.valueOf((char) i));
        }

        String characters = String.valueOf((char) encodedText.remove(0).intValue());
        StringBuilder result = new StringBuilder(characters);
        for (int code : encodedText) {
            String entry = dictionary.containsKey(code)
                    ? dictionary.get(code)
                    : characters + characters.charAt(0);
            result.append(entry);
            dictionary.put(dictSize++, characters + entry.charAt(0));
            characters = entry;
        }
        return result.toString();
    }
    
    
    public static void main(String[] args) {
        System.out.println("Test Case: ABAABABBAABAABAAAABABBBBBBBB");

        List<Integer> compressed = encode("ABAABABBAABAABAAAABABBBBBBBB");
        System.out.println("Encode LZW: ");
        System.out.println(compressed);

        String decompressed = decode(compressed);
        System.out.println("Decode LZW: ");
        System.out.println(decompressed);

    }

}
    

