/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.util.converter.IntegerStringConverter;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author aitan
 */
public class FXMLcrearCuentaController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField telf;
    @FXML
    private TextField nickname;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField password2;
    @FXML
    private TextField numcred;
    @FXML
    private TextField svc;
    @FXML
    private Button botonRegistrarse;
    @FXML
    private Button cancelarBoton;
    @FXML
    private Label rcb;
    
    private int svcValue;
    private Member newUser = null;
    private Club club;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    } 
    
    

    @FXML
    private void registrarse(ActionEvent event) throws ClubDAOException {
        
        if (password.getText() != password2.getText()){
            
            rcb.setText("las contraseñas no coinciden");}
        
       try{svcValue= Integer.parseInt(svc.getText());}
       catch(NumberFormatException e){
           rcb.setText("test");
       }
      
     
    
       try{newUser = club.registerMember(name.getText(), surname.getText(), telf.getText(),nickname.getText(), password.getText(), numcred.getText(),svcValue, null);}
       catch(ClubDAOException e){rcb.setText("error al crear la cuenta,revisa todos los parametros");}
       
           ((Button)event.getSource()).getScene().getWindow().hide();
    }
    
    @FXML
    private void cancel(ActionEvent event) {
        ((Button)event.getSource()).getScene().getWindow().hide();
    }
    
    public void InitSesion(Club b){
    
    club = b;
    
    
    }
    
}
