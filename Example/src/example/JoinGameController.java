
package example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class JoinGameController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
    @FXML
    private TextField ipaddress;
    @FXML
    private TextField portnumber;
    
    public void join(ActionEvent event) throws IOException
    {
        if(ipaddress.getText().equals("1") && portnumber.getText().equals("1234"))
        {
            Parent op=FXMLLoader.load(getClass().getResource("Lobby.fxml"));
            Scene opscene=new Scene(op);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(opscene);
            window.show();
            new Multiplayer().clientLikeEntity();
        }
           
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
