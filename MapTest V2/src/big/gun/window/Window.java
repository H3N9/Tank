
package big.gun.window;

import javax.swing.JFrame;

public class Window {
    private JFrame jframe;
    public static int width;
    public static int height;
    
    public Window(int w, int h, String nameTank, int alli, int axis, String diff){
        width = w;
        height = h;
        createWindow(nameTank, alli, axis, diff);
    }
    private void createWindow(String nameTank, int alli, int axis, String diff){
        jframe = new JFrame();
        jframe.setTitle("Big-Gun");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(width, height);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.add(new Game(nameTank, alli, axis, diff));
        jframe.setVisible(true);
    }
}
