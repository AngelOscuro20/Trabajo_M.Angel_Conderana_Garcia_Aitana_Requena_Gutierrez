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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aitan
 */
public class FXMLiniciarSesionController implements Initializable {

    @FXML
    private Button botonIniciar;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonCrear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void cancel(ActionEvent event) {
        ((Button)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void crearCuenta(ActionEvent event) throws IOException {
              ((Button)event.getSource()).getScene().getWindow().hide();
         FXMLLoader miCargador = new
FXMLLoader(getClass().getResource("FXMLcrearCuenta.fxml"));
Parent root = miCargador.load();
Scene scene = new Scene(root,500,300);
Stage stage = new Stage();
stage.setScene(scene);
stage.setTitle("Vista datos persona");
stage.initModality(Modality.APPLICATION_MODAL);
//la ventana se muestra modal
stage.show();
    }
    
}
