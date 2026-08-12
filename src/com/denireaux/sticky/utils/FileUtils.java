package com.denireaux.sticky.utils;

import java.time.LocalDateTime;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static String buildFileName(String outDirectory, String filePrefix) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String localDateTimeAsString = localDateTime.toString();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(outDirectory);
        stringBuilder.append(filePrefix);
        stringBuilder.append("_");
        stringBuilder.append(localDateTimeAsString);
        stringBuilder.append(".png");

        return stringBuilder.toString();
    }

    public static void saveStateToPng(JLayeredPane layeredPane, String filename) throws IOException {
        int width = layeredPane.getWidth();
        int height = layeredPane.getHeight();

        boolean dimensionsResolved = resolveDimensions(width, height);
        if (!dimensionsResolved) return;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        tryPaintOnPane(g2d, layeredPane);
        String fileName = FileUtils.buildFileName("saved/", "drawing");
        ImageIO.write(bufferedImage, "png", new File(fileName));
    }

    private static boolean resolveDimensions(int width, int height) {
        if (width <= 0 || height <= 0) {
            return true;
        }
        return false;
    }

    private static void tryPaintOnPane(Graphics2D g2d,JLayeredPane layeredPane) {
        try {
            layeredPane.paint(g2d);
        } finally {
            g2d.dispose();
        }
    }
}
