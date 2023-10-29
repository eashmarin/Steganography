package utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageTool {
    public static BufferedImage copyImage(BufferedImage source) {
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());

        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();

        return b;
    }

    public static RGB getRGB(int pixel) {
        return new RGB(
                (pixel >> 24) & 0xFF,
                (pixel >> 16) & 0xFF,
                (pixel >> 8) & 0xFF,
                pixel & 0xFF
        );
    }

    public static int toPixel(RGB rgb) {
        return (rgb.alpha() << 24) | (rgb.red() << 16) | (rgb.green() << 8) | rgb.blue();
    }

}
