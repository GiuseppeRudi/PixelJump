package application.view;

import application.model.Settings;
import application.model.World;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class PauseScreen {
    private ArrayList<Object> functions = new ArrayList<>();
    private Function[] pauseScreenFunctions;

    public Function[] getScreenFunctions() {
        return pauseScreenFunctions;
    }

    public PauseScreen() {
        getFunctions();
        this.pauseScreenFunctions = createItems(this.functions);
    }

    private void getFunctions() {
        functions.add("Menu Principale");
    }

    private Function[] createItems(ArrayList<Object> func) {
        if (func == null)
            return null;
        Function[] items = new Function[func.size()];
        items[0] = new Function(func.getFirst(), new Point((Settings.WINDOW_SIZE_X-183)/2, 438));
        return items;
    }

    public Object select(Point mouseLocation) {
        for (Function item : pauseScreenFunctions) {
            Dimension dimension = item.getDimension();
            Point location = item.getLocation();
            boolean inX = location.x <= mouseLocation.x && location.x + dimension.width >= mouseLocation.x;
            boolean inY = location.y >= mouseLocation.y && location.y - dimension.height <= mouseLocation.y;
            if (inX && inY) {
                return item.getObject();
            }
        }
        return null;
    }
}

