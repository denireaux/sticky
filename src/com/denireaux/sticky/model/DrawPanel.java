package com.denireaux.sticky.model;

import com.denireaux.sticky.model.DrawPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel {
    private BufferedImage canvas;
    private int lastX, lastY;

    public DrawPanel() {
        canvas = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

        MouseAdapter drawer = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
            }
            public void mouseDragged(MouseEvent e) {
                Graphics2D g2 = canvas.createGraphics();
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.drawLine(lastX, lastY, e.getX(), e.getY());
                g2.dispose();
                lastX = e.getX();
                lastY = e.getY();
                repaint();
            }
        };
        addMouseListener(drawer);
        addMouseMotionListener(drawer);
    }

    public void resizeCanvas(int w, int h) {
        BufferedImage newCanvas = new BufferedImage(Math.max(w, 1), Math.max(h, 1), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newCanvas.createGraphics();
        g2.drawImage(canvas, 0, 0, null);
        g2.dispose();
        canvas = newCanvas;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvas, 0, 0, null);
    }
}
