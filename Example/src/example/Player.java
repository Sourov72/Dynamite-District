/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

public class Player extends Sprite{
    

    private PlayerNetworkEntity shadow;
    private boolean isShooting;
    private boolean isAlive;
    private boolean isActive;
    private int playerID;
    Profile profile;
    private double absolutePosX;
    private double absolutePosY;

    public Player(int ID,Profile profile, double absolutePosX, double absolutePosY, double velocity, boolean isAlive) {
        super(profile.getImage(), absolutePosX, absolutePosY, velocity);
        this.playerID = ID;
        this.isAlive = isAlive;
        this.isActive = true;
        this.profile = profile;
    }

    public PlayerNetworkEntity getShadow() {
        return shadow;
    }

    public void setShadow(PlayerNetworkEntity shadow) {
        this.shadow = shadow;
    }

    public double getPlayerX() {
        return super.getPositionX();
    }

    public double getPlayerY() {
        return super.getPositionY();
    }

    public double getPlayerWidth () {
        return super.getWidth();
    }

    public double getPlayerHeight () {
        return super.getHeight();
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
    

    public void setXY (double x, double y) {
        super.setPositionX(x);
        super.setPositionY(y);
    }



    public void setIsShooting(boolean isShooting) {
        this.isShooting = isShooting;
    }

    public boolean getIsShooting() {
        return isShooting;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive( boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    public double getDegree() {
        return super.getDegree();
    }

}
