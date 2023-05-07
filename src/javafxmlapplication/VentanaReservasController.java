/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import model.*;
import model.Club;

/**
 * FXML Controller class
 *
 * @author migue
 */
public class VentanaReservasController implements Initializable {

    private List<Booking> datos = null;
 
    
    private Club club;
    
    private LocalDate dia = null;
            
    @FXML
    private DatePicker fecha;
    @FXML
    private Label prueba;
    @FXML
    private Button botonPrueba;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
      
      
       
    }    

    @FXML
    private void fechaEvento(ActionEvent event) {
        dia = fecha.valueProperty().getValue();
       
    }
    public void initReservas(Club b)
{
    club = b;
    datos = b.getBookings();
    
}

    @FXML
    private void botonEvento(ActionEvent event) {
         datos = club.getBookings();
          prueba.setText(datos.toString());
         
    }

}
