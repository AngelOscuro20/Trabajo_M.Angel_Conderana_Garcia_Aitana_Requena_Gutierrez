/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.*;

/**
 * FXML Controller class
 *
 * @author migue
 */
public class FXMLMisReservasController implements Initializable {
    private Club club;
    private Member user;
    private ObservableList<Booking> misReservas;
    @FXML
    private TableView<Booking> reservasUsuario;
    @FXML
    private TableColumn<Booking, String> fecha;
    @FXML
    private TableColumn<Booking, String> pista;
    @FXML
    private TableColumn<Booking, String> fechaReserva;
    @FXML
    private TableColumn<Booking, String> pago;
    @FXML
    private Button borrar;
    @FXML
    private Label nombre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       
    }    
    
    
    public void initMisReservas(Club c, Member member){
    club = c;
    user = member;
    nombre.setText("Reservas de " + user.getNickName());
    List<Booking> bookings = club.getUserBookings(member.getNickName());
    misReservas = FXCollections.observableArrayList(bookings);
    reservasUsuario.setItems(misReservas);
    fecha.setCellValueFactory(fechaPropiedad -> new ReadOnlyStringWrapper(fechaPropiedad.getValue().getMadeForDay().format(DateTimeFormatter.ISO_DATE) + " : " + fechaPropiedad.getValue().getFromTime().format(DateTimeFormatter.ISO_TIME) ));

    pista.setCellValueFactory(pistaPropiedad -> new ReadOnlyStringWrapper(pistaPropiedad.getValue().getCourt().getName()));

   fechaReserva.setCellValueFactory(fechaReservaPropiedad -> new ReadOnlyStringWrapper(fechaReservaPropiedad.getValue().getBookingDate().format(DateTimeFormatter.ISO_DATE) + " : " + fechaReservaPropiedad.getValue().getBookingDate().format(DateTimeFormatter.ISO_TIME)  ));

   pago.setCellValueFactory(pagoPropiedad -> new ReadOnlyStringWrapper(pagoPropiedad.getValue().getPaid().toString()));
    }

    @FXML
    private void borrarAccion(ActionEvent event) {
        Booking selected = reservasUsuario.getSelectionModel().getSelectedItem();
        
         if(selected.getMadeForDay().atTime(selected.getFromTime()).minusHours(24).isAfter(LocalDate.now().atTime(LocalTime.now())) && selected.getMadeForDay().atTime(selected.getFromTime()).isAfter(LocalDate.now().atTime(LocalTime.now())) ){
           Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Borrar Reserva");
                alerta.setHeaderText("¿Seguro que quieres borrar esta reserva?");
                Optional<ButtonType> result = alerta.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
        misReservas.remove(selected);
        try {
           
            club.removeBooking(selected);
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLMisReservasController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
    }else if(selected.getMadeForDay().atTime(selected.getFromTime()).isBefore(LocalDate.now().atTime(LocalTime.now()))){
    Alert alerta2 = new Alert(Alert.AlertType.ERROR);
                            alerta2.setTitle("Error");
                            alerta2.setHeaderText("No puedes cancelar una reserva del pasado");
                            Optional<ButtonType> result2 = alerta2.showAndWait();}
    
         else{Alert alerta3 = new Alert(Alert.AlertType.ERROR);
                            alerta3.setTitle("Error");
                            alerta3.setHeaderText("No puedes cancelar una reserva 24h horas antes de su fecha.");
                            Optional<ButtonType> result2 = alerta3.showAndWait();}
    }

    @FXML
    private void salir(ActionEvent event) {
        ((Button) event.getSource()).getScene().getWindow().hide();
    }
}
