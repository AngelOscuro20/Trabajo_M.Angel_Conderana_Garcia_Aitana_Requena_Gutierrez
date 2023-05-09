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
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import model.Club;

/**
 * FXML Controller class
 *
 * @author migue
 */
public class VentanaReservasController implements Initializable {

    private List<Booking> datos = null;
 
    ObservableList<Booking> conversion = null;
    
    private Club club;
    
    private LocalDate dia = null;
    
    @FXML
    private DatePicker fecha;
    @FXML
    private Label prueba;
    @FXML
    private Button botonPrueba;
    @FXML
    private TableView<Booking> tablaHorarios;
    @FXML
    private TableColumn<Booking, String> horarios;
    @FXML
    private TableColumn<Booking, String> pista1;
    @FXML
    private TableColumn<?, ?> pista2;
    @FXML
    private TableColumn<?, ?> pista3;
    @FXML
    private TableColumn<?, ?> pista4;
    @FXML
    private TableColumn<?, ?> pista5;
    @FXML
    private TableColumn<?, ?> pista6;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
      
      
       
    }    

    @FXML
    private void fechaEvento(ActionEvent event) {
        dia = fecha.valueProperty().getValue();
        datos = club.getForDayBookings(dia);
    conversion = FXCollections.observableArrayList(datos);
      tablaHorarios.setItems(conversion);
    horarios.setCellValueFactory(
      horariosFila -> new ReadOnlyStringWrapper(horariosFila.getValue().getFromTime().toString()));
    pista1.setCellValueFactory(
     pista1Fila -> new ReadOnlyStringWrapper(pista1Fila.getValue().getCourt().getName()));
    }
    public void initReservas(Club b, LocalDate date)
{
    club = b;
    dia = date;
    datos = b.getForDayBookings(dia);
    conversion = FXCollections.observableArrayList(datos);
    tablaHorarios.setItems(conversion);
    horarios.setCellValueFactory(
      horariosFila -> new ReadOnlyStringWrapper(horariosFila.getValue().getFromTime().toString()));
    pista1.setCellValueFactory(
     pista1Fila -> new ReadOnlyStringWrapper(pista1Fila.getValue().getCourt().getName()));
}

    @FXML
    private void botonEvento(ActionEvent event) {
         datos = club.getBookings();
          prueba.setText(conversion.get(0).getFromTime().toString());
         
    }
    
}
