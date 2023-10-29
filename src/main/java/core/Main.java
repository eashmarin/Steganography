package core;

import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("The message must be specified");
        }

        String message = args[0];

        var inputFileName = "minecraft_logo.png";

        var containerPath = Objects.requireNonNull(Main.class.getClassLoader().getResource(inputFileName)).getPath();
        var outputPath = "output." + inputFileName.split("\\.")[1];

        var processor = new SteganographyProcessor(SteganographyType.IMAGE);
        processor.process(containerPath, message, outputPath);

        var extractedText = processor.extract(outputPath, message.length());
        System.out.println(extractedText);
    }
}