package dictionary;

public class Main {
    public static void main(String[] args) {
        SaveableDictionary dictionary = new SaveableDictionary("C:/Users/ronya/Desktop/Java/praktiniai_papildomai_1/dictionary.txt");

        boolean loaded = dictionary.load();
        if (loaded) {
            System.out.println("Dictionary loaded successfully.");
        } else {
            System.out.println("Failed to load the dictionary.");
        }

        dictionary.add("test", "testTranslation1");
        dictionary.add("test2", "testTranslation2");

        System.out.println(dictionary.translate("testTranslation1"));
        System.out.println(dictionary.translate("testTranslation2"));

        dictionary.delete("testTranslation2");

        boolean saved = dictionary.save();
        if (saved) {
            System.out.println("Dictionary saved successfully.");
        } else {
            System.out.println("Failed to save the dictionary.");
        }
    }
}
