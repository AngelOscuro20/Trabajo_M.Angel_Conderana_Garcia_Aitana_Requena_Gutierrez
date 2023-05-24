/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
     private final LocalTime firstSlotStart = LocalTime.of(9, 0);
    private final Duration slotLength = Duration.ofMinutes(60);
    private final LocalTime lastSlotStart = LocalTime.of(21, 0);
      private ObjectProperty<TimeSlot> timeSlotSelected;
    
    
    private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
    private List<List<TimeSlot>> timeSlots = new ArrayList<>(); //Para varias columnas List<List<TimeSolt>>
    private List<TimeSlot> columna1 = new ArrayList<>();
    private List<TimeSlot> columna2 = new ArrayList<>();
    private List<Booking> pista1 = new ArrayList<>();
    private List<Booking> pista2 = new ArrayList<>();
    private List<TimeSlot> columna3 = new ArrayList<>();
    private List<TimeSlot> columna4 = new ArrayList<>();
    private List<Booking> pista3 = new ArrayList<>();
    private List<Booking> pista4 = new ArrayList<>();
    private List<TimeSlot> columna5 = new ArrayList<>();
    private List<TimeSlot> columna6 = new ArrayList<>();
    private List<Booking> pista5 = new ArrayList<>();
    private List<Booking> pista6 = new ArrayList<>();
    private Member user;
    
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
    private DatePicker calendario;
    @FXML
    private DatePicker day;
    @FXML
    private GridPane grid;
    @FXML
    private Label labelCol;
    @FXML
    private Label labelCol2;
    @FXML

    private Button misReservasBoton;

    private ImageView imgCuenta;



    
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
        
        
        String AvatarDef = ("src\\resources\\avatars\\default.PNG");
        //String AAA = ("libraries\\resources\\avatars\\default.PNG");
        Image avatar = null;
        try {
            avatar = new Image(new FileInputStream(AvatarDef));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        imgCuenta.imageProperty().setValue(avatar);   
        
        //inicializa la imagen default, en teoría, si le apetece fufar
        
        try {
           greenBall = greenBall.getInstance();
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        dia = dia.now();
        
        titulo.setText(greenBall.getName());

        pista1 = greenBall.getCourtBookings("Pista 1", dia);
        pista2 = greenBall.getCourtBookings("Pista 2", dia);
        pista3 = greenBall.getCourtBookings("Pista 3", dia);
        pista4 = greenBall.getCourtBookings("Pista 4", dia);
        pista5 = greenBall.getCourtBookings("Pista 5", dia);
        pista6 = greenBall.getCourtBookings("Pista 6", dia);
        timeSlotSelected = new SimpleObjectProperty<>();

        //---------------------------------------------------------------------
        //cambia los SlotTime al cambiar de dia
        //---------------------------------------------------------------------
        //inicializa el DatePicker al dia actual
        day.setValue(LocalDate.now());

        //---------------------------------------------------------------------
        // pinta los SlotTime en el grid
        setTimeSlotsGrid(day.getValue());

        //---------------------------------------------------------------------
        // enlazamos timeSlotSelected con el label para mostrar la seleccion
        reservaBoton.setDisable(true);
        misReservasBoton.setDisable(true);
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
stage.showAndWait();
 if(controladorInicioSesion.cuentaLista == true){
     user = controladorInicioSesion.getMember();
     iniciar.setVisible(false);
     reservaBoton.setDisable(false);
     misReservasBoton.setDisable(false);
     pruebaTexto.setText(user.getNickName());
 }


    }
    



    @FXML
    private void reservaAccion(ActionEvent event) throws IOException, ClubDAOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/javafxmlapplication/FXMLGridTimeSlot.fxml"));
        Parent root = miCargador.load();
        FXMLGridTimeSlotController controladorReservas = miCargador.getController();
        controladorReservas.initReservas(greenBall, user, dia);
        
        Scene scene = new Scene(root, 600, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Vista datos persona");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.sizeToScene();
//la ventana se muestra modal
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
        stage.showAndWait();
        if(controladorReservas.getPulsado() == true){
            setTimeSlotsGrid(controladorReservas.getDia());
            pruebaTexto.setText("true");
        }
                                                                                                                                                    
    }

    private void calendarioAccion(ActionEvent event) {
        
        dia = calendario.valueProperty().getValue();
        pruebaTexto.setText(greenBall.getBookings().toString());
    }



    @FXML
    private void cambioDia(ActionEvent event) {
        dia = day.getValue();
        pista1 = greenBall.getCourtBookings("Pista 1", dia);
        pista2 = greenBall.getCourtBookings("Pista 2", dia);
        pista3 = greenBall.getCourtBookings("Pista 3", dia);
        pista4 = greenBall.getCourtBookings("Pista 4", dia);
        pista5 = greenBall.getCourtBookings("Pista 5", dia);
        pista6 = greenBall.getCourtBookings("Pista 6", dia);
        setTimeSlotsGrid(day.getValue());
    }


     private void setTimeSlotsGrid(LocalDate date) {
        //actualizamos la seleccion

        timeSlotSelected.setValue(null);

        //--------------------------------------------        
        //borramos los SlotTime del grid
        ObservableList<Node> children = grid.getChildren();
        for (List<TimeSlot> lista : timeSlots) {
            for (TimeSlot timeSlot : lista) {
                children.remove(timeSlot.getView());
            }
        }
        timeSlots = new ArrayList<>();

        //----------------------------------------------------------------------------------
        // desde la hora de inicio y hasta la hora de fin creamos slotTime segun la duracion
        int slotIndex = 1;

        for (LocalDateTime startTime = date.atTime(firstSlotStart);
                !startTime.isAfter(date.atTime(lastSlotStart));
                startTime = startTime.plus(slotLength)) {

            //---------------------------------------------------------------------------------------
            // creamos el SlotTime, lo anyadimos a la lista de la columna y asignamos sus manejadores
              TimeSlot timeSlot = new TimeSlot(startTime, slotLength, null);
            timeSlots.add(columna1);
            columna1.add(timeSlot);
            
            //-----------------------------------------------------------
            // lo anyadimos al grid en la posicion x= 1, y= slotIndex
            grid.add(timeSlot.getView(), 1, slotIndex);
            grid.add(timeSlot.getNick(), 1, slotIndex);
            ObservableList<String> styles = timeSlot.getView().getStyleClass();

            for (int j = 0; j < pista1.size(); j++) {

                if (!pista1.isEmpty() && styles.contains("time-slot") && pista1.get(j).getFromTime().atDate(dia).isEqual(timeSlot.getStart())) {
                    timeSlot.getNick().setText(pista1.get(j).getMember().getNickName());
                    styles.remove("time-slot");
                    styles.add("time-slot-libre");
                }

            }

            TimeSlot timeSlot2 = new TimeSlot(startTime, slotLength, null);
            timeSlots.add(columna2);
            columna2.add(timeSlot2);
            
            grid.add(timeSlot2.getView(), 2, slotIndex);
            grid.add(timeSlot2.getNick(), 2, slotIndex);
            ObservableList<String> styles2 = timeSlot2.getView().getStyleClass();

            for (int j2 = 0; j2 < pista2.size(); j2++) {

                if (!pista2.isEmpty() && styles2.contains("time-slot") && pista2.get(j2).getFromTime().atDate(dia).isEqual(timeSlot2.getStart())) {
                    timeSlot2.getNick().setText(pista2.get(j2).getMember().getNickName());
                    styles2.remove("time-slot");
                    styles2.add("time-slot-libre");
                }

            }

            TimeSlot timeSlot3 = new TimeSlot(startTime, slotLength, null);
            timeSlots.add(columna3);
            columna3.add(timeSlot3);
            
            grid.add(timeSlot3.getView(), 3, slotIndex);
            grid.add(timeSlot3.getNick(), 3, slotIndex);
            ObservableList<String> styles3 = timeSlot3.getView().getStyleClass();

            for (int j3 = 0; j3 < pista3.size(); j3++) {

                if (!pista3.isEmpty() && styles3.contains("time-slot") && pista3.get(j3).getFromTime().atDate(dia).isEqual(timeSlot3.getStart())) {
                    timeSlot3.getNick().setText(pista3.get(j3).getMember().getNickName());
                    styles3.remove("time-slot");
                    styles3.add("time-slot-libre");
                }

            }

            TimeSlot timeSlot4 = new TimeSlot(startTime, slotLength, null);
            timeSlots.add(columna4);
            columna4.add(timeSlot4);
            
            grid.add(timeSlot4.getView(), 4, slotIndex);
            grid.add(timeSlot4.getNick(), 4, slotIndex);
            ObservableList<String> styles4 = timeSlot4.getView().getStyleClass();

            for (int j4 = 0; j4 < pista4.size(); j4++) {

                if (!pista4.isEmpty() && styles4.contains("time-slot") && pista4.get(j4).getFromTime().atDate(dia).isEqual(timeSlot4.getStart())) {
                   timeSlot4.getNick().setText(pista4.get(j4).getMember().getNickName());
                    styles4.remove("time-slot");
                    styles4.add("time-slot-libre");
                }

            }

            TimeSlot timeSlot5 = new TimeSlot(startTime, slotLength, null);
            timeSlots.add(columna5);
            columna5.add(timeSlot5);
            
            grid.add(timeSlot5.getView(), 5, slotIndex);
            grid.add(timeSlot5.getNick(), 5, slotIndex);
            ObservableList<String> styles5 = timeSlot5.getView().getStyleClass();

            for (int j5 = 0; j5 < pista5.size(); j5++) {

                if (!pista5.isEmpty() && styles5.contains("time-slot") && pista5.get(j5).getFromTime().atDate(dia).isEqual(timeSlot5.getStart())) {
                    timeSlot5.getNick().setText(pista5.get(j5).getMember().getNickName());
                    styles5.remove("time-slot");
                    styles5.add("time-slot-libre");
                }
            }
            TimeSlot timeSlot6 = new TimeSlot(startTime, slotLength, null);
            timeSlots.add(columna6);
            columna6.add(timeSlot6);
            
            grid.add(timeSlot6.getView(), 6, slotIndex);
            grid.add(timeSlot6.getNick(), 6, slotIndex);
            ObservableList<String> styles6 = timeSlot6.getView().getStyleClass();

            for (int j6 = 0; j6 < pista6.size(); j6++) {

                if (!pista6.isEmpty() && styles6.contains("time-slot") && pista6.get(j6).getFromTime().atDate(dia).isEqual(timeSlot6.getStart())) {
                    timeSlot6.getNick().setText(pista6.get(j6).getMember().getNickName());
                    styles6.remove("time-slot");
                    styles6.add("time-slot-libre");
                }

            }
            slotIndex++;
        }
    }



    @FXML
    private void misReservasAccion(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/javafxmlapplication/FXMLMisReservas.fxml"));
        Parent root = miCargador.load();
        FXMLMisReservasController controladorMisReservas = miCargador.getController();
        controladorMisReservas.initMisReservas(greenBall, user);
        //controladorReservas.initReservas(greenBall,dia);
        Scene scene = new Scene(root, 600, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Vista datos persona");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.sizeToScene();
//la ventana se muestra modal
        stage.showAndWait();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());
        
    }

    
     public void actualizarGrid(LocalDate fecha){
     setTimeSlotsGrid(fecha);
     }



    @FXML
    private void verCuenta(MouseEvent event) throws IOException {
            FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/javafxmlapplication/detalles.fxml"));
        Parent root = miCargador.load();
        
        DetallesController controladorMiCuenta = miCargador.getController();
        controladorMiCuenta.InitCuenta(greenBall,member);
        
        
        
        JavaFXMLApplication.setRoot(root);
    }

     
    public class TimeSlot {

        private final LocalDateTime start;
        private final Duration duration;

        protected final Pane view;
        protected final Label nick = new Label();

        private final BooleanProperty selected = new SimpleBooleanProperty();

        public final BooleanProperty selectedProperty() {
            return selected;
        }

        public final boolean isSelected() {
            return selectedProperty().get();
        }

        public final void setSelected(boolean selected) {
            selectedProperty().set(selected);
        }

        public TimeSlot(LocalDateTime start, Duration duration, Booking booking) {
            this.start = start;
            this.duration = duration;

            view = new Pane();
            
            nick.setText("");
            nick.setAlignment(Pos.CENTER);
            
            view.getStyleClass().add("time-slot");
            // ---------------------------------------------------------------
            // de esta manera cambiamos la apariencia del TimeSlot cuando los seleccionamos
            selectedProperty().addListener((obs, wasSelected, isSelected)
                    -> view.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, isSelected));

        }

        public LocalDateTime getStart() {
            return start;
        }

        public LocalTime getTime() {
            return start.toLocalTime();
        }

        public LocalDate getDate() {
            return start.toLocalDate();
        }

        public DayOfWeek getDayOfWeek() {
            return start.getDayOfWeek();
        }

        public Duration getDuration() {
            return duration;
        }

        public Node getView() {
            return view;
        }
        public Label getNick(){
            return nick;
        }
    }
}
