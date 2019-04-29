package Client.View;

import java.awt.Point;
import java.awt.Toolkit;

public class WindowXY {

    // 你把窗口的宽和高给我，然后我就可以帮你计算出 你要显示的位置
    public static Point getXY(int w, int h) {

        // 获得了屏幕的 宽和高
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int height = toolkit.getScreenSize().height;
        int width = toolkit.getScreenSize().width;

        int x = (width - w) / 2;
        int y = (height - h) / 2;

        Point p = new Point(x, y);
        return p;
    }

    public static Point getXY(java.awt.Dimension d) {
        return getXY(d.width, d.height);
    }

}
