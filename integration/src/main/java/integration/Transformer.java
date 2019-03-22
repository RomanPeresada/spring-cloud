package integration;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class Transformer {
    public String modifier(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return "Was modified:  " + content;
    }

    public Transformer() {
    }
}
