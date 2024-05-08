package view;

import javax.swing.*;
import java.awt.*;

public class SquareIcon implements Icon {
    private int size;

    public SquareIcon(int size) {
        this.size = size;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.fillRect(x, y, size, size);
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    @Override
    public int getIconHeight() {
        return size;
    }
}
