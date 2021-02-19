
package example;

import javafx.scene.canvas.GraphicsContext;


public class Shooting {
   
    private Map1 mp1;
    private MyImage image;
    private double absolutePosX;
    private double absolutePosY;
    private double returnX;
    private double returnY;
    public boolean check;
    private double speed = 20;
    static  int objNum = 0;
    protected double degree1;
    private double height;
    private double width;
    private boolean hit = false;
    public int bulletNumber = 100;
    
    public Shooting(String url,double x,double y, Map1 mp, boolean check)
    {
        image = new MyImage();
        height = 40;
        width = 30;
        this.image.makeMyImage(url, height, width);
        mp1 = mp;
        absolutePosX = x;
        absolutePosY = y;
        this.check = false;

    }

    public void updateAbsolutePosX() {
        this.absolutePosX=this.absolutePosX+ speed * Math.cos(Math.toRadians(this.degree1));
    }

    public void setabsolutePosX(double absolutePosX) {
        
        this.absolutePosX = absolutePosX ;//(20 * Math.cos(Math.toRadians(this.degree1)));

    }

    public void updateAbsolutePosY() {
         this.absolutePosY=this.absolutePosY+ speed * Math.sin(Math.toRadians(this.degree1));
    }

    public void setabsolutePosY(double absolutePosY) {
         this.absolutePosY = absolutePosY;// + (20 * Math.sin(Math.toRadians(this.degree1)));//+ speed * Math.sin(Math.toRadians(this.degree1));
         
    }
    public double getReturnX() {
        returnX = absolutePosX;
        return returnX;
    }

    public double getReturnY() {
        returnY = absolutePosY;
        return returnY;
    }
    public boolean Hcheck(double midPosX, double midPosY)
    {
        if(check){
        double x = absolutePosX - (midPosX - (GameInfo.ScreenWidth.getValue()/2));
        double y = absolutePosY - (midPosY - (GameInfo.ScreenHeight.getValue()/2));
        for (int i = 0; i < LobbyController.activePlayer.size(); i++) {

            if (i == LobbyController.thisPlayerID) {
                continue;
            }
            double playerWidth = LobbyController.activePlayer.elementAt(i).getPlayerWidth();
            double playerHeight = LobbyController.activePlayer.elementAt(i).getPlayerHeight();
            double playerX = LobbyController.activePlayer.elementAt(i).getAbsolutePosX() - (midPosX - (GameInfo.ScreenWidth.getValue()/2))- (playerWidth/2);
            double playerY = LobbyController.activePlayer.elementAt(i).getAbsolutePosY() - (midPosY - (GameInfo.ScreenHeight.getValue()/2))- (playerHeight/2);


            if ((((playerX) <= x) && ((playerY) <= y)) && (((playerX + playerWidth) >= x) && ((playerY + playerHeight) >= y))) {
                System.out.println("check collision point");
                return false;
            }
        }

            if(x >= 0 && x <= GameInfo.ScreenWidth.getValue() && y >= 0 && y <= GameInfo.ScreenHeight.getValue()) {
                hit = mp1.hitcheck(this.degree1, absolutePosX, absolutePosY);
                return hit;
            }
            else {
                return true;
            }
        }
        else {
            return true;
        }
       
    }
    public void updateAbsolutes(double X, double Y)
    {
        absolutePosX = X;
        absolutePosY = Y;
    }

    
    public void render(GraphicsContext gc, double midPosX, double midPosY)
    {

        //(((midPosX - GameInfo.ScreenWidth.getValue()/2) <= absolutePosX) && ((midPosX + GameInfo.ScreenWidth.getValue()/2)>= absolutePosX) && ((midPosY - GameInfo.ScreenHeight.getValue()/2) <= absolutePosY) && ((midPosY + GameInfo.ScreenHeight.getValue()/2)>= absolutePosY))
        if(!(Hcheck(midPosX, midPosY))) {
            gc.drawImage( image.setRotate(this.degree1), (absolutePosX - (midPosX - GameInfo.ScreenWidth.getValue()/2)) - image.getWidth()/2, (absolutePosY - (midPosY - GameInfo.ScreenHeight.getValue()/2)) - image.getHeight()/2 );
            updateAbsolutePosX();
            updateAbsolutePosY();
        }
        else {
            check = false;
        }

    }
    
    
}
