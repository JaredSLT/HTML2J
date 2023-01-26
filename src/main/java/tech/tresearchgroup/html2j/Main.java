package tech.tresearchgroup.html2j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> data = Files.readAllLines(new File("test.html").toPath());
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : data) {
            stringBuilder.append(line);
        }
        String input = stringBuilder.toString();
        System.out.println(input);
        System.out.println(new J2HTMLGenerator().generate(input));
    }
}
