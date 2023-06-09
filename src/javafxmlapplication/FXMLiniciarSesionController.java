/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import model.Club;
/**
 * FXML Controller class
 *
 * @author aitan
 */
public class FXMLiniciarSesionController implements Initializable {
    
    public Member user = null;
    private Club club;
    public boolean cuentaLista = false;
    
    @FXML
    private Button botonIniciar;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonCrear;
    @FXML
    private TextField usuario;
    
    
    @FXML
    private PasswordField contrasena;
    @FXML
    private Label error;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException, ClubDAOException {
        
       
      
      try{ user = club.getMemberByCredentials(usuario.getText(),contrasena.getText());
      if(user == null){error.setText("Error , comprueba el nombre y la contraseña");}
      else{cuentaLista = true;
       ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();}
      }
      catch(Exception e ){error.setText("Error,comprueba el nombre y la contraseña");}
       
      
    }

    @FXML
    private void cancel(ActionEvent event) {
        ((Button)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void crearCuenta(ActionEvent event) throws IOException {
        ((Button) event.getSource()).getScene().getWindow().hide();
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("FXMLcrearCuenta.fxml"));
        Parent root = miCargador.load();
        FXMLcrearCuentaController controladorCrearCuenta = miCargador.getController();
        controladorCrearCuenta.InitSesion(club);
        Scene scene = new Scene(root, 500, 550);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Registrarse");
        stage.initModality(Modality.APPLICATION_MODAL);
//la ventana se muestra modal
        stage.show();
    }
    
    public void InitSesion(Club b){
    
    club = b;
    
    
    }
    
    public Member getMember(){
    
    return user;
    }
            }
