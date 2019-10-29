
package tank;


public class Tag{
    private Player player;
    private double[] tagFont;
    private int[] tagLeft;
    private int[] tagRight;
    private int[] tagBack;
    private double[] head;

    public Tag(Player player) {
        this.player = player;
        this.tagFont = new double[4];
        this.tagLeft = new int[4];
        this.tagRight = new int[4];
        this.tagBack = new int[4];
        this.head = new double[2];
    }

    public double[] getHead() {
        return head;
    }

    public void setHead(Player player) {
        this.head[0] = Calculate.calculateRotateX(player.getPositionX()+player.getWidthTank()/2, player.getPositionY()+player.getLongTank()/4, player.getCenterX(), player.getCenterY(), (player.getRotateHead()));
        this.head[1] = Calculate.calculateRotateY(player.getPositionX()+player.getWidthTank()/2, player.getPositionY()+player.getLongTank()/4, player.getCenterX(), player.getCenterY(), (player.getRotateHead()));
    }

    public double[] getTagFont() {
        return tagFont;
    }

    public int[] getTagLeft() {
        return tagLeft;
    }

    public int[] getTagRight() {
        return tagRight;
    }

    public int[] getTagBack() {
        return tagBack;
    }
    
    
    

    public void setTagFont(Player player) {
        this.tagFont[0] = Calculate.calculateRotateX(player.getPositionX(), player.getPositionY(), player.getCenterX(), player.getCenterY(), player.getRotate());
        this.tagFont[1] = Calculate.calculateRotateY(player.getPositionX(), player.getPositionY(), player.getCenterX(), player.getCenterY(), player.getRotate());
        this.tagFont[2] = Calculate.calculateRotateX(player.getPositionX()+player.getWidthTank(), player.getPositionY(), player.getCenterX(), player.getCenterY(), player.getRotate());
        this.tagFont[3] = Calculate.calculateRotateY(player.getPositionX()+player.getWidthTank(), player.getPositionY(), player.getCenterX(), player.getCenterY(), player.getRotate());
    }

    public void setTagLeft(int[] tagLeft) {
        this.tagLeft = tagLeft;
    }

    public void setTagRight(int[] tagRight) {
        this.tagRight = tagRight;
    }

    public void setTagBack(int[] tagBack) {
        this.tagBack = tagBack;
    }

    
    
    
    
    
}
