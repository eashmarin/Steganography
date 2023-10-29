package core;

import utils.ImageTool;
import utils.RGB;

import java.awt.image.BufferedImage;

public class ImageSteganographier implements Steganographier<BufferedImage> {

    @Override
    public BufferedImage encode(BufferedImage container, String message) {
        int containerWidth = container.getWidth();
        int containerHeight = container.getHeight();

        var modifiedImage = ImageTool.copyImage(container);

        for (char currentChar : message.toCharArray()) {
            for (int y = 0; y < containerHeight; y++) {
                for (int x = 0; x < containerWidth - 1; x += 2) {
                    var rgb1 = ImageTool.getRGB(container.getRGB(x, y));
                    var rgb2 = ImageTool.getRGB(container.getRGB(x + 1, y));

                    var modifiedRGB1 = new RGB(
                            (rgb1.alpha() & 0xFE) | (((int) currentChar >> 7) & 0x1),
                            (rgb1.red() & 0xFE) | (((int) currentChar >> 6) & 0x1),
                            (rgb1.green() & 0xFE) | (((int) currentChar >> 5) & 0x1),
                            (rgb1.blue() & 0xFE) | (((int) currentChar >> 4) & 0x1)
                    );
                    var modifiedRGB2 = new RGB(
                            (rgb2.alpha() & 0xFE) | (((int) currentChar >> 3) & 0x1),
                            (rgb2.red() & 0xFE) | (((int) currentChar >> 2) & 0x1),
                            (rgb2.green() & 0xFE) | (((int) currentChar >> 1) & 0x1),
                            (rgb2.blue() & 0xFE) | ((int) currentChar) & 0x1
                    );

                    modifiedImage.setRGB(x, y, ImageTool.toPixel(modifiedRGB1));
                    modifiedImage.setRGB(x + 1, y, ImageTool.toPixel(modifiedRGB2));
                }
            }
        }

        return modifiedImage;
    }
}
