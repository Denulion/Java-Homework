
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReadingFilesPerLine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "C:/Users/ronya/Desktop/Java/13_praktiniai_stream/13_02.ReadingFilesPerLine/test.txt";
        List<String> lines = read(filePath);
        lines.forEach(System.out::println);
    }
    public static List<String> read(String file){
        try {
            return Files.lines(Paths.get(file)).collect(Collectors.toList());
        }catch (IOException e){
            System.err.println("Error: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
