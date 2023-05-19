/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Club;
import model.Member;

/**
 * FXML Controller class
 *
 * @author aitan
 */
public class DetallesController implements Initializable {

    @FXML
    private Button cerrarSesion;
    private TextField nombreLabel;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField telf;
    @FXML
    private ImageView imgPerf;
    @FXML
    private TextField numTarjeta;
    @FXML
    private TextField cvs;
    @FXML
    private Label rcb;//mensaje de error

    private Member User ;
    private Club club;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setText(User.getName());
    }    


    @FXML
    private void cambCont(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void aceptar(ActionEvent event) {
    }

    @FXML
    private void cambImg(ActionEvent event) {
    }
    
     public void InitCuenta(Club b,Member m) {

        club = b;
        User = m;

    }

    
}
