package com.denireaux.sticky.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class FileUtils {
    static final String FileUtilsName = FileUtils.class.getName();
    static final Logger log = Logger.getLogger(FileUtilsName);

    public static String buildFileName(String outDirectory, String filePrefix) {
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return outDirectory + filePrefix + "_" + ts + ".png";
    }

    public static void saveStateToPng(JLayeredPane layeredPane) throws IOException {
        int width = layeredPane.getWidth();
        int height = layeredPane.getHeight();
        if (width <= 0 || height <= 0) return;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        try {
            layeredPane.paint(g2d);
        } finally {
            g2d.dispose();
        }

        File out = new File(buildFileName("saved/", "drawing"));
        out.getParentFile().mkdirs();
        ImageIO.write(image, "png", out);

        log.info("Saved to " + out.getAbsolutePath());
    }
}
