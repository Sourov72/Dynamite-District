package example;

import static example.Shooting.objNum;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class LobbyController implements Initializable {
    public static Vector<Player> activePlayer = new Vector<>();
    public static int thisPlayerID = 0;
   // public Profile thisProfile ;
    
    public static Player player;
    private Canvas canvas;
    private GraphicsContext gc;
    public int bulletNumber = 100;

   @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas = new Canvas( GameInfo.ScreenWidth.getValue(), GameInfo.ScreenHeight.getValue() );
        gc = canvas.getGraphicsContext2D();


    }
   
    public void start(ActionEvent event) throws IOException
    {

        Multiplayer.inLobby = false;
        if (thisPlayerID == 0) {
            Multiplayer.ss.close();
        }

        System.out.println(Multiplayer.activeHandlers.size());
        for (int i = 0; i < Multiplayer.activeShadow.size(); i++) {
            Multiplayer.activeShadow.elementAt(i).userOutput.println("0#EXIT");
        }
        if (Multiplayer.playerID != 1) {
            System.out.println("Calling handler: ");
            Multiplayer.activeHandlers.elementAt(0).handle();
        }



        Stage theStage=new Stage();
        DoubleValue degree = new DoubleValue();
        //theStage.setTitle( "Timeline Example" );
        Rectangle rec=new Rectangle(850,20,120,30);
        ArrayList<Node> bullets = new  ArrayList<Node>();
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        rec.setFill(Color.WHITE);
        Rectangle rec1=new Rectangle(850,75,120,30);
        rec1.setFill(Color.WHITE);
        Text name = new Text();
        Text level = new Text();
        name.setText(player.profile.getName());
        name.setX(853);
        name.setY(45);
        name.setFont(Font.font("Sans serif", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 24));
        name.setFill(Color.BLUE);
        name.setStrokeWidth(2); 
        name.setStroke(Color.PURPLE);
        level.setText(Integer.toString(player.profile.getLevel()));
        level.setX(853);
        level.setY(100);
        level.setFont(Font.font("Sans serif", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 24));
        level.setFill(Color.BLUE);
        level.setStrokeWidth(2); 
        level.setStroke(Color.PURPLE);
        root.getChildren().addAll( canvas, rec, name , rec1, level);


        Map1 mainMap = new Map1(activePlayer.elementAt(thisPlayerID).getAbsolutePosX(), activePlayer.elementAt(thisPlayerID).getAbsolutePosY(), 0);
        MiniMap miniMap = new MiniMap();

        theScene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                degree.setValue(Math.toDegrees(Math.atan2( event.getY() - canvas.getHeight()/2, event.getX() - canvas.getWidth()/2)));
                degree.setValue(degree.getValue());
            }
        });
        theScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                degree.setValue(Math.toDegrees(Math.atan2( event.getY() - canvas.getHeight()/2, event.getX() - canvas.getWidth()/2)));
                degree.setValue(degree.getValue());
            }
        });

        ArrayList<String> keyInput = new ArrayList<>();
        theScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            public void handle(KeyEvent e)
            {
                String code = e.getCode().toString();

                // only add once... prevent duplicates
                if ( !keyInput.contains(code) )
                    keyInput.add( code );
            }
        });
        theScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e)
            {
                String code = e.getCode().toString();
                keyInput.remove( code );
            }
        });
       
        Shooting[][] Shoot = new Shooting[activePlayer.size()][bulletNumber];
        for(int j = 0;j<activePlayer.size();j++) {
            for (int i = 0; i < bulletNumber; i++) {
                Shoot[j][i] = new Shooting("image/g.png", activePlayer.elementAt(j).getAbsolutePosX(), activePlayer.elementAt(j).getAbsolutePosY(), mainMap, false);
            }
        }
        final long startNanoTime = System.nanoTime();
        
        AnimationTimer programButtonAnimation;
        programButtonAnimation = new AnimationTimer()
        {
            @Override
            public void handle(long currentNanoTime)
            {
                gc.clearRect(0, 0, GameInfo.ScreenWidth.getValue(), GameInfo.ScreenHeight.getValue());
                double t = (currentNanoTime + startNanoTime) / 1000000000.0;
                //double t = 1000;
                // background image clears canvas
                gc.setFill( new Color(0.498, 1, 0, 0.8) );
                gc.fillRect(0, 0, 980, 980);

                mainMap.setDegree(degree.getValue());
                if (Multiplayer.playerID != 1) {
                    Multiplayer.activeHandlers.elementAt(0).broadcastDegree();
                }

                if (!(keyInput.isEmpty())) {
                    if((keyInput.get(0) == "W") || (keyInput.get(0) == "A")|| (keyInput.get(0) == "S") || (keyInput.get(0) == "D")){
                        mainMap.mapTraverse(degree.getValue(), keyInput.get(0));
                        //player[0].spriteTraverse(degree.getValue(), keyInput.get(0));
                        if (Multiplayer.playerID != 1) {
                            Multiplayer.activeHandlers.elementAt(0).broadcastMovement();
                        }

                    }
                }
                activePlayer.elementAt(thisPlayerID).setDegree(degree.getValue());
                mainMap.render(gc);
                for(int i = 0; i < activePlayer.size(); i++) {
                    if(activePlayer.elementAt(i).isIsAlive()) {
                        if(i == thisPlayerID){
                            activePlayer.elementAt(i).updateAbsolute(mainMap.getAbsolutePosX(), mainMap.getAbsolutePosY());
                        }
                        activePlayer.elementAt(i).render(gc, mainMap.getAbsolutePosX(), mainMap.getAbsolutePosY());
                    }
                }

                theScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        //activePlayer.elementAt(thisPlayerID).setIsShooting(false);
                        System.out.println("mouse released");
//                        if (Multiplayer.playerID != 1) {
//                            Multiplayer.activeHandlers.elementAt(0).broadcastShoot(thisPlayerID + "#" + "SHOOT" + "#" + false);
//                        }
                        //player.setIsShooting(false);
                    }    
                });
                theScene.setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("Mouse clicked");
                        activePlayer.elementAt(thisPlayerID).setIsShooting(true);

                        if (Multiplayer.playerID != 1) {
                            Multiplayer.activeHandlers.elementAt(0).broadcastShoot(thisPlayerID + "#" + "SHOOT" + "#" + true);
                        }
                        //Shoot[thisPlayerID][objNum].check = true;
                    }
                });

                for(int i = 0;i<activePlayer.size();i++) {
                    if (activePlayer.elementAt(i).getIsShooting()) {
                        Shoot[i][objNum].degree1 = activePlayer.elementAt(i).getDegree();
                        Shoot[i][objNum].setabsolutePosX(activePlayer.elementAt(i).getAbsolutePosX());
                        Shoot[i][objNum].setabsolutePosY(activePlayer.elementAt(i).getAbsolutePosY());
                        Shoot[i][objNum].check = true;
                        activePlayer.elementAt(i).setIsShooting(false);
                        if(objNum >= (bulletNumber - 1)){
                            objNum = -1;
                        }
                        objNum++;
                    }
                }

//               for(int j =0; j < activePlayer.size(); j++) {
//
//                   for (int i = 0; i < 20; i++) {
//                       if (activePlayer.elementAt(j).getIsShooting()) {
//
//
////                           Shoot[j][i].updateAbsolutes(activePlayer.elementAt(j).getAbsolutePosX(), activePlayer.elementAt(j).getAbsolutePosY());
//                       }
////                            if (Shoot[j][i].Hcheck())
////                               Shoot[j][i].check = false;
//                   }
//               }
                for (int j = 0; j < activePlayer.size(); j++) {
                    for (int i = 0; i < bulletNumber; i++) {
                        if (Shoot[j][i].check == true) {
                            Shoot[j][i].render(gc,  mainMap.getAbsolutePosX(), mainMap.getAbsolutePosY());
                            //System.out.println("Drawing bullet" + j + " " + i);
                        }
                    }
                }
                miniMap.render(gc);
            }
        };

        
        programButtonAnimation.start();
        theStage=(Stage)((Node)event.getSource()).getScene().getWindow();
        theStage.setScene(theScene);
        theStage.show();
    }

    public void back(ActionEvent event) throws IOException
    {
            Parent op=FXMLLoader.load(getClass().getResource("GameStart.fxml"));
            Scene opscene=new Scene(op);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(opscene);
            window.show();
    }
    
}
