package com.denireaux.sticky;

import com.denireaux.sticky.model.DrawPanel;
import com.denireaux.sticky.utils.Settings;
import com.denireaux.sticky.utils.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.time.LocalDateTime;

public class Sticky {

    static Map<String, String> config = Settings.loadConfig("application.properties");
    final static int WINDOW_WIDTH = Integer.parseInt(config.get("window.width"));
    final static int WINDOW_HEIGHT = Integer.parseInt(config.get("window.height"));
    final static int FONT_SIZE = Integer.parseInt(config.get("font.size"));
    final static int TAB_SIZE_SPACES = Integer.parseInt(config.get("window.tab-size"));
    final static String WINDOW_TITLE = config.get("window.title");
    final static String FONT_FAMILY = config.get("font.family");
    final static boolean LINE_IS_WRAPPED = Boolean.parseBoolean(config.get("line.is-wrapped"));

    static DrawPanel drawPanel;
    static boolean drawMode = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame(WINDOW_TITLE);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));
        textArea.setBackground(new Color(255, 255, 240));
        textArea.setTabSize(TAB_SIZE_SPACES);
        textArea.setLineWrap(LINE_IS_WRAPPED);

        JScrollPane scrollPane = new JScrollPane(textArea);
        drawPanel = new DrawPanel();
        drawPanel.setOpaque(false);
        drawPanel.setVisible(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(scrollPane, Integer.valueOf(0));
        layeredPane.add(drawPanel, Integer.valueOf(1));

        frame.setContentPane(layeredPane);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                int w = layeredPane.getWidth();
                int h = layeredPane.getHeight();
                scrollPane.setBounds(0, 0, w, h);
                drawPanel.setBounds(0, 0, w, h);
                drawPanel.resizeCanvas(w, h);
            }
        });

        InputMap im = layeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = layeredPane.getActionMap();

        im.put(KeyStroke.getKeyStroke("control D"), "toggleDraw");
        am.put("toggleDraw", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                drawMode = !drawMode;
                drawPanel.setVisible(drawMode);
            }
        });

        im.put(KeyStroke.getKeyStroke("control S"), "toggleSave");

        am.put("toggleSave", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    saveStateToPng(layeredPane, "out.png");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void saveStateToPng(JLayeredPane layeredPane, String filename) throws IOException {
        int width = layeredPane.getWidth();
        int height = layeredPane.getHeight();

        if (width <= 0 || height <= 0) {
            return;
        }

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        try {
            layeredPane.paint(g2d);
        } finally {
            g2d.dispose();
        }

        String fileName = FileUtils.buildFileName("saved/", "drawing");

        ImageIO.write(bufferedImage, "png", new File(fileName));
    }
}
