package tech.tresearchgroup.html2j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    private static void main(String[] args) throws IOException {
        System.out.println(new HTMLGenerator().generate(Files.readAllLines(Path.of(new File("test.html").toPath().toUri())).toString()));
    }
}
