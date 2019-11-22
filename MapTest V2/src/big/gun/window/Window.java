
package big.gun.window;

import javax.swing.JFrame;

public class Window {
    public static JFrame jframe;
    public static int width;
    public static int height;
    
    public Window(int w, int h, String nameTank, int amountBout, String diff){
        width = w;
        height = h;
        createWindow(nameTank, amountBout, diff);
    }
    private void createWindow(String nameTank, int amountBout, String diff){
        jframe = new JFrame();
        jframe.setTitle("Big-Gun");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(width, height);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.add(new Game(nameTank, amountBout, diff));
        jframe.setVisible(true);
    }
    
}
