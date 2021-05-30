/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meghdut.ui;

import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 *
 * @author user
 */
public class UIUtils {

    static void setLocationToTopRight(JFrame frame) {
        GraphicsConfiguration config = frame.getGraphicsConfiguration();
        Rectangle bounds = config.getBounds();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);

        int x = bounds.x + bounds.width - insets.right - frame.getWidth();
        int y = bounds.y + insets.top;
        y= y /2;
        frame.setLocation(x, y);
    }

    public static void setUpLookFeel()
    {
        try{

            String className = javax.swing.UIManager.getSystemLookAndFeelClassName();
            javax.swing.UIManager.setLookAndFeel(className);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(LoginUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
