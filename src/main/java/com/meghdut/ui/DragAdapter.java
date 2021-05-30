package com.meghdut.ui;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class DragAdapter extends javax.swing.event.MouseInputAdapter
{
    Point location;
    MouseEvent pressed;

    public void mousePressed(MouseEvent me)
    {
        pressed = me;
    }

    public void mouseDragged(MouseEvent me)
    {
        Component component = me.getComponent();
        location = component.getLocation(location);
        int x = location.x - pressed.getX() + me.getX();
        int y = location.y - pressed.getY() + me.getY();
        component.setLocation(x, y);
    }
}