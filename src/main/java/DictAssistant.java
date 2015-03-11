import java.util.*;

public class DictAssistant {
    private Set<String> wordDict = new HashSet<>();

    public void insertWord(String word) {
        wordDict.add(word);
    }

    public List<String> lookupDict(String lookup) {
        List<String> results = new ArrayList<>();
        int minInsert = Integer.MAX_VALUE;
        for (String dict : wordDict) {
            if (isSubString(lookup, dict)) {
                int needInsert = dict.length() - lookup.length();
                if (needInsert < minInsert) {
                    minInsert = needInsert;
                    results.clear();
                    results.add(dict);
                } else if (needInsert == minInsert) {
                    results.add(dict);
                }
            }
        }
        return results;
    }

    private boolean isSubString(String lookup, String word) {
        int lenLookup = lookup.length();
        int lenWord = word.length();
        if (lenWord == 0) {
            return lenLookup == 0;
        } else if (lenLookup == 0) {
            return true;
        } else {
            if (word.charAt(lenWord - 1) == lookup.charAt(lenLookup - 1)) {
                return isSubString(lookup.substring(0, lenLookup - 1),
                        word.substring(0, lenWord - 1));
            } else {
                return isSubString(lookup,
                        word.substring(0, lenWord - 1));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String lookup = scanner.nextLine();
        final DictAssistant dictAssistant = new DictAssistant();
        while (scanner.hasNext()) {
            final String word = scanner.nextLine();
            if (word == null) {
                break;
            }
            dictAssistant.insertWord(word);
        }
        final List<String> list = dictAssistant.lookupDict(lookup);
        if (list.size() == 0) {
            System.out.println("None");
        } else {
            for (String s : list) {
                System.out.println(s);
            }
        }
    }
}
