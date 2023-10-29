package core;

import utils.ImageTool;

import java.awt.image.BufferedImage;

public class ImageDecrypter {

    public static String decrypt(BufferedImage input, int messageLength) {
        int modifiedImageWidth = input.getWidth();
        int modifiedImageHeight = input.getHeight();

        StringBuilder extractedMessage = new StringBuilder();
        int pixelsCounter = 0;

        for (int y = 0; y < modifiedImageHeight; y++) {
            for (int x = 0; x < modifiedImageWidth - 1; x += 2) {
                var rgb1 = ImageTool.getRGB(input.getRGB(x, y));
                var rgb2 = ImageTool.getRGB(input.getRGB(x + 1, y));

                char extractedChar = (char) (
                        ((rgb1.alpha() & 0x1) << 7) |
                                ((rgb1.red() & 0x1) << 6) |
                                ((rgb1.green() & 0x1) << 5) |
                                ((rgb1.blue() & 0x1) << 4) |
                                ((rgb2.alpha() & 0x1) << 3) |
                                ((rgb2.red() & 0x1) << 2) |
                                ((rgb2.green() & 0x1) << 1) |
                                (rgb2.blue() & 0x1)
                );

                extractedMessage.append(extractedChar);
                pixelsCounter++;

                if (pixelsCounter >= messageLength) {
                    return extractedMessage.toString();
                }
            }
        }

        return extractedMessage.toString();
    }
}
