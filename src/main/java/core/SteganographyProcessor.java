package core;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SteganographyProcessor {

    private final SteganographyType type;

    public SteganographyProcessor(SteganographyType type) {
        this.type = type;
    }

    public void process(String containerPath, String message, String outputPath) throws IOException {
        switch (type) {
            case IMAGE -> {
                var newImage = ImageEncrypter.encrypt(
                        ImageIO.read(new File(containerPath)),
                        message
                );
                ImageIO.write(newImage, outputPath.split("\\.")[1], new File(outputPath));
            }
            default -> throw new IllegalArgumentException();
        }
    }

    public String extract(String containerPath, int messageLength) throws IOException {
        return switch (type) {
            case IMAGE -> ImageDecrypter.decrypt(
                    ImageIO.read(new File(containerPath)),
                    messageLength
            );
            default -> throw new IllegalArgumentException();
        };
    }
}
