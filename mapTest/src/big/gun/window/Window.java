
package big.gun.window;

import javax.swing.JFrame;

public class Window {
    private JFrame jframe;
    public static int width;
    public static int height;
    
    public Window(int w, int h){
        width = w;
        height = h;
        createWindow();
    }
    
    private void createWindow(){
        jframe = new JFrame();
        jframe.setTitle("Big-Gun");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(width, height);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.add(new Game());
        jframe.setVisible(true);
    }
}
