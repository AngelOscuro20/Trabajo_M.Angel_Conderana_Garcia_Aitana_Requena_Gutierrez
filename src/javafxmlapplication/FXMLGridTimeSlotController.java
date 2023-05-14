/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Booking;
import model.Club;

/**
 *
 * @author DSIC_jsoler
 */
public class FXMLGridTimeSlotController implements Initializable {

    @FXML
    private DatePicker day;
    @FXML
    private GridPane grid;
    @FXML
    private Label slotSelected;
    @FXML
    private Label labelCol;

    private final LocalTime firstSlotStart = LocalTime.of(9, 0);
    private final Duration slotLength = Duration.ofMinutes(60);
    private final LocalTime lastSlotStart = LocalTime.of(22, 0);

    // se puede cambiar por codigo la pseudoclase activa de un nodo    
    private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
    private Club club = null;
    private List<List<TimeSlot>> timeSlots = new ArrayList<>(); //Para varias columnas List<List<TimeSolt>>
    private List<TimeSlot> columna1 = new ArrayList<>();
    private List<TimeSlot> columna2 = new ArrayList<>();
    private List<Booking> pista1 = new ArrayList<>();
    private List<Booking> pista2 = new ArrayList<>();
    private List<TimeSlot> columna3 = new ArrayList<>();
    private List<TimeSlot> columna4 = new ArrayList<>();
    private List<Booking> pista3 = new ArrayList<>();
    private List<Booking> pista4= new ArrayList<>();
    private List<TimeSlot> columna5 = new ArrayList<>();
    private List<TimeSlot> columna6 = new ArrayList<>();
    private List<Booking> pista5 = new ArrayList<>();
    private List<Booking> pista6= new ArrayList<>();
    

    private ObjectProperty<TimeSlot> timeSlotSelected;

    private LocalDate daySelected;
    @FXML
    private Label labelCol2;
    private LocalDate dia;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
            registerHandlers(timeSlot);
            //-----------------------------------------------------------
            // lo anyadimos al grid en la posicion x= 1, y= slotIndex
            grid.add(timeSlot.getView(), 1, slotIndex);
            ObservableList<String> styles = timeSlot.getView().getStyleClass();
            
            for (int j = 0; j < pista1.size(); j++) {

                if (!pista1.isEmpty() && styles.contains("time-slot") && pista1.get(j).getFromTime().atDate(dia).isEqual(timeSlot.getStart())) {
                    styles.remove("time-slot");
                    styles.add("time-slot-libre");
                }

            }
        
            TimeSlot timeSlot2 = new TimeSlot(startTime, slotLength, null);
            timeSlots.add(columna2);
            columna2.add(timeSlot2);
            registerHandlers(timeSlot2);
            grid.add(timeSlot2.getView(), 2, slotIndex );
            ObservableList<String> styles2 = timeSlot2.getView().getStyleClass();
            
            for (int j2 = 0; j2 < pista2.size(); j2++) {

                if (!pista2.isEmpty() && styles2.contains("time-slot") && pista2.get(j2).getFromTime().atDate(dia).isEqual(timeSlot2.getStart())) {
                    styles2.remove("time-slot");
                    styles2.add("time-slot-libre");
                }

            }
            
            TimeSlot timeSlot3 = new TimeSlot(startTime, slotLength, null);
            timeSlots.add(columna3);
            columna3.add(timeSlot3);
            registerHandlers(timeSlot3);
            grid.add(timeSlot3.getView(), 3, slotIndex );
            ObservableList<String> styles3 = timeSlot3.getView().getStyleClass();
            
            for (int j3 = 0; j3 < pista3.size(); j3++) {

                if (!pista3.isEmpty() && styles3.contains("time-slot") && pista3.get(j3).getFromTime().atDate(dia).isEqual(timeSlot3.getStart())) {
                    styles3.remove("time-slot");
                    styles3.add("time-slot-libre");
                }

            }
            
            TimeSlot timeSlot4 = new TimeSlot(startTime, slotLength, null);
            timeSlots.add(columna4);
            columna4.add(timeSlot4);
            registerHandlers(timeSlot4);
            grid.add(timeSlot4.getView(), 4, slotIndex );
            ObservableList<String> styles4 = timeSlot4.getView().getStyleClass();
            
            for (int j4 = 0; j4 < pista4.size(); j4++) {

                if (!pista4.isEmpty() && styles4.contains("time-slot") && pista4.get(j4).getFromTime().atDate(dia).isEqual(timeSlot4.getStart())) {
                    styles4.remove("time-slot");
                    styles4.add("time-slot-libre");
                }

            }
            
            TimeSlot timeSlot5 = new TimeSlot(startTime, slotLength, null);
            timeSlots.add(columna5);
            columna5.add(timeSlot5);
            registerHandlers(timeSlot5);
            grid.add(timeSlot5.getView(), 5, slotIndex );
            ObservableList<String> styles5 = timeSlot5.getView().getStyleClass();
            
            for (int j5 = 0; j5 < pista5.size(); j5++) {

                if (!pista5.isEmpty() && styles5.contains("time-slot") && pista5.get(j5).getFromTime().atDate(dia).isEqual(timeSlot5.getStart())) {
                    styles5.remove("time-slot");
                    styles5.add("time-slot-libre");
                }
            }
                TimeSlot timeSlot6 = new TimeSlot(startTime, slotLength, null);
            timeSlots.add(columna6);
            columna6.add(timeSlot6);
            registerHandlers(timeSlot6);
            grid.add(timeSlot6.getView(), 6, slotIndex );
            ObservableList<String> styles6 = timeSlot6.getView().getStyleClass();
            
            for (int j6 = 0; j6 < pista6.size(); j6++) {

                if (!pista6.isEmpty() && styles6.contains("time-slot") && pista6.get(j6).getFromTime().atDate(dia).isEqual(timeSlot6.getStart())) {
                    styles6.remove("time-slot");
                    styles6.add("time-slot-libre");
                }

            
            }
            slotIndex++;
        }
    }
    

    private void registerHandlers(TimeSlot timeSlot) {

        timeSlot.getView().setOnMousePressed((MouseEvent event) -> {
            //---------------------------------------------slot----------------------------
            //solamente puede estar seleccionado un slot dentro de la lista de slot
            columna1.forEach(slot -> {
                slot.setSelected(slot == timeSlot);
            });

            columna2.forEach(slot -> {
                slot.setSelected(slot == timeSlot);
            });
            //----------------------------------------------------------------
            //actualizamos el label Dia-Hora, esto es ad hoc,  para mi diseño
            timeSlotSelected.setValue(timeSlot);
            //----------------------------------------------------------------
            // si es un doubleClik  vamos a mostrar una alerta y cambiar el estilo de la celda

            if (event.getClickCount() > 1) {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("SlotTime");
                alerta.setHeaderText("Confirma la selecció");
                alerta.setContentText("Has seleccionat: "
                        + timeSlot.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) + ", "
                        + timeSlot.getTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
                Optional<ButtonType> result = alerta.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    ObservableList<String> styles = timeSlot.getView().getStyleClass();
                    if (styles.contains("time-slot")) {
                        styles.remove("time-slot");
                        styles.add("time-slot-libre");
                    }
                }
            }
        });

    }

    public void initReservas(Club clubTranspaso, LocalDate date) {
        club = clubTranspaso;
        dia = date;
        pista1 = club.getCourtBookings("Pista 1", dia);
        pista2 = club.getCourtBookings("Pista 2", dia);
        pista3 = club.getCourtBookings("Pista 3", dia);
        pista4 = club.getCourtBookings("Pista 4", dia);
        pista5 = club.getCourtBookings("Pista 5", dia);
        pista6 = club.getCourtBookings("Pista 6", dia);
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
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("E MMM d");
        timeSlotSelected.addListener((a, b, c) -> {
            if (c == null) {
                slotSelected.setText("");
            } else {
                slotSelected.setText(c.getDate().format(dayFormatter)
                        + "-"
                        + c.getStart().format(timeFormatter));
            }
        });
    }

    private void boton(ActionEvent event) {
        labelCol.setText(pista1.get(0).getFromTime().toString());
        labelCol2.setText(club.getBookings().get(0).getCourt().getName());

    }

    @FXML
    private void cambioDia(ActionEvent event) {
        dia = day.getValue();
        pista1 = club.getCourtBookings("Pista 1", dia);
         pista2 = club.getCourtBookings("Pista 2", dia);
         pista3 = club.getCourtBookings("Pista 3", dia);
         pista4 = club.getCourtBookings("Pista 4", dia);
         pista5 = club.getCourtBookings("Pista 5", dia);
         pista6 = club.getCourtBookings("Pista 6", dia);
        setTimeSlotsGrid(day.getValue());
    }

    public class TimeSlot {

        private final LocalDateTime start;
        private final Duration duration;
        
        protected final Pane view;

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

    }

}
