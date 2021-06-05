package com.meghdut.ui.test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class ColorToBlackAndWhite {

    /**
     * Returns the supplied src image brightened by a float value from 0 to 10.
     * Float values below 1.0f actually darken the source image.
     */
    public static BufferedImage brighten(BufferedImage src, float level) {
        BufferedImage dst = new BufferedImage(
                src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        float[] scales = {level, level, level};
        float[] offsets = new float[4];
        RescaleOp rop = new RescaleOp(scales, offsets, null);

        Graphics2D g = dst.createGraphics();
        g.drawImage(src, rop, 0, 0);
        g.dispose();

        return dst;
    }

    public static void main(String[] args) throws Exception {
        URL colorURL = new URL("http://i.stack.imgur.com/AuY9o.png");
        final BufferedImage colorImage = ImageIO.read(colorURL);

        float[] scales = {2f, 2f, 2f};
        float[] offsets = new float[4];
        RescaleOp rop = new RescaleOp(scales, offsets, null);

        final BufferedImage scaledImage = new BufferedImage(
                colorImage.getWidth(),
                colorImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaledImage.createGraphics();
        g.drawImage(colorImage, rop, 0, 0);

        final BufferedImage grayImage = new BufferedImage(
                colorImage.getWidth(),
                colorImage.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY);
        g = grayImage.createGraphics();
        g.drawImage(colorImage, 0, 0, null);

        final BufferedImage blackAndWhiteImage = new BufferedImage(
                colorImage.getWidth(),
                colorImage.getHeight(),
                BufferedImage.TYPE_BYTE_BINARY);
        g = blackAndWhiteImage.createGraphics();
        g.drawImage(colorImage, 0, 0, null);

        g.dispose();

        Runnable r = new Runnable() {

            @Override
            public void run() {
                JPanel gui = new JPanel(new BorderLayout(2, 2));
                JPanel images = new JPanel(new GridLayout(0, 2, 2, 2));
                gui.add(images, BorderLayout.CENTER);

                final JLabel scaled = new JLabel(new ImageIcon(scaledImage));
                final JSlider brighten = new JSlider(0, 1000, 100);
                gui.add(brighten, BorderLayout.PAGE_START);
                ChangeListener cl = new ChangeListener() {

                    @Override
                    public void stateChanged(ChangeEvent e) {
                        int val = brighten.getValue();
                        float valFloat = val / 1000f;
                        BufferedImage bi = brighten(colorImage, valFloat);
                        BufferedImage bw = new BufferedImage(
                                colorImage.getWidth(),
                                colorImage.getHeight(),
                                BufferedImage.TYPE_BYTE_BINARY);
                        Graphics g = bw.createGraphics();
                        g.drawImage(bi, 0, 0, null);
                        g.dispose();

                        scaled.setIcon(new ImageIcon(bw));
                    }
                };
                brighten.addChangeListener(cl);

                images.add(new JLabel(new ImageIcon(colorImage)));
                images.add(scaled);
                images.add(new JLabel(new ImageIcon(grayImage)));
                images.add(new JLabel(new ImageIcon(blackAndWhiteImage)));

                JOptionPane.showMessageDialog(null, gui);
            }
        };
        // Swing GUIs should be created and updated on the EDT
        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
        SwingUtilities.invokeLater(r);
    }
}