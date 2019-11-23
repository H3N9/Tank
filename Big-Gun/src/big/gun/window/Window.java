
package big.gun.window;

import javax.imageio.ImageIO;
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
        try{
            jframe.setIconImage(ImageIO.read(getClass().getResource("/res/logo.png")));
        }catch(Exception e){}
        
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(width, height);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.add(new Game(nameTank, amountBout, diff));
        jframe.setVisible(true);
    }
    
}
