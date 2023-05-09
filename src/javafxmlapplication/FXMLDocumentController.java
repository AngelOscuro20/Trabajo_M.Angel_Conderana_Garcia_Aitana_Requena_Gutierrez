/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import model.Club;

import model.Club;
import model.ClubDAOException;


/**
 *
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {
    private Club greenBall ;
    
    
    @FXML
    private Button reservaBoton;
    private Label labelMessage;
    @FXML
    private Button iniciar;


    @FXML
    private Label titulo;
    private LocalDate dia = null;
    @FXML
    private Label pruebaTexto;
    @FXML
    private DatePicker calendario;


    
    //=========================================================
    // event handler, fired when button is clicked or 
    //                      when the button has the focus and enter is pressed
    private void handleButtonAction(ActionEvent event) {
        labelMessage.setText("C");
    }
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
           greenBall = greenBall.getInstance();
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        dia = dia.now();
        
        titulo.setText(greenBall.getName());

    }    


    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new
FXMLLoader(getClass().getResource("FXMLiniciarSesion.fxml"));
Parent root = miCargador.load();
FXMLiniciarSesionController controladorInicioSesion = miCargador.getController();
controladorInicioSesion.InitSesion(greenBall);
Scene scene = new Scene(root,500,300);
Stage stage = new Stage();
stage.setScene(scene);
stage.setTitle("Vista datos persona");
stage.initModality(Modality.APPLICATION_MODAL);
//la ventana se muestra modal
stage.show();



    }
    



    @FXML
    private void reservaAccion(ActionEvent event) throws IOException, ClubDAOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/javafxmlapplication/ventanaReservas.fxml"));
        Parent root = miCargador.load();
        VentanaReservasController controladorReservas = miCargador.getController();
        controladorReservas.initReservas(greenBall,dia);
        Scene scene = new Scene(root, 500, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Vista datos persona");
        stage.initModality(Modality.APPLICATION_MODAL);
        
//la ventana se muestra modal
        stage.showAndWait();
                                                                                                                                                    
    }

    @FXML
    private void calendarioAccion(ActionEvent event) {
        
        dia = calendario.valueProperty().getValue();
        pruebaTexto.setText(greenBall.getBookings().toString());
    }

    
}
