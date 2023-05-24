/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
        misReservas.remove(selected);
        try {
            club.removeBooking(selected);
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLMisReservasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
