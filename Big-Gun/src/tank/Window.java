
package tank;

import javax.swing.JFrame;


public class Window {
    private static int height;
    private static int width;
    private JFrame frame;
    
    public Window(int width, int height){
        this.width = width;
        this.height = height;
        createWindow();
    }
    private void createWindow(){
        frame = new JFrame("Big Gun");
        frame.setSize(getWidth(), getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(new Draw());
        frame.setVisible(true);
    }
    public static int getHeight() {
        return height;
    }
    public static int getWidth() {
        return width;
    }

    
    
}
