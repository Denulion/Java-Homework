package dictionary;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class SaveableDictionary {
    private Map<String, String> dictionary;
    private String file;

    public SaveableDictionary() {
        this.dictionary = new HashMap<>();
        this.file = null;
    }

    public SaveableDictionary(String file) {
        this.dictionary = new HashMap<>();
        this.file = file;
    }

    public void add(String word, String translation) {
        if (!this.dictionary.containsKey(word) && !this.dictionary.containsValue(word)) {
            this.dictionary.put(word, translation);
            this.dictionary.put(translation, word);
        }
    }

    public String translate(String word) {
        return this.dictionary.getOrDefault(word, null);
    }

    public void delete(String word) {
        String translation = this.dictionary.get(word);

        if (translation != null) {
            this.dictionary.remove(word);
            this.dictionary.remove(translation);
        }
    }

    public boolean load() {
        if (this.file == null) {
            return false;
        }
        try (Scanner fileReader = new Scanner(new File(this.file))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(":");
                if(parts.length == 2) {
                    this.dictionary.put(parts[0], parts[1]);
                    this.dictionary.put(parts[1], parts[0]);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean save() {
        if (this.file == null) {
            return false;
        }

        try (PrintWriter writer = new PrintWriter(this.file)) {
            Set<String> writtenWords = new HashSet<>();
            for (String word : this.dictionary.keySet()) {
                String translation = this.dictionary.get(word);
                if (!writtenWords.contains(word) && !writtenWords.contains(translation)) {
                    writer.println(word + ":" + translation);
                    writtenWords.add(word);
                    writtenWords.add(translation);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
