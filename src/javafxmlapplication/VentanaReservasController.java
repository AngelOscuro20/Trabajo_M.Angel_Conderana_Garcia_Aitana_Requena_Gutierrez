/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.*;
import model.Club;

/**
 * FXML Controller class
 *
 * @author migue
 */
public class VentanaReservasController implements Initializable {

    private List<Booking> datos = null;
    
 private final LocalTime firstSlotStart = LocalTime.of(9, 0);
    private final Duration slotLength = Duration.ofMinutes(60);
    private final LocalTime lastSlotStart = LocalTime.of(22, 0);
   
    
    private Club club;
    
    private LocalDate dia = null;
    
    @FXML
    private DatePicker fecha;
    @FXML
    private Label prueba;
    @FXML
    private Button botonPrueba;
    
    @FXML
    private GridPane grid;
    private SimpleObjectProperty<Object> bookingSelected;
    private List<List<Booking>> Bookings;
    private List<Booking> columna1;
    private List<Booking> columna2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       fecha.setValue(LocalDate.now());  
      
    }    

    @FXML
    private void fechaEvento(ActionEvent event) {
        dia = fecha.valueProperty().getValue();
        
    
    }
    public void initReservas(Club b, LocalDate date)
{
    club = b;
    dia = date;
    
    
}

    @FXML
    private void botonEvento(ActionEvent event) {
         datos = club.getBookings();
         
         
    }
    
   
        
        
        
        
    
    }
     

