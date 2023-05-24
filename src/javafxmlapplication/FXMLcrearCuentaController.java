/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.util.converter.IntegerStringConverter;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author aitan
 */
public class FXMLcrearCuentaController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField telf;
    @FXML
    private TextField nickname;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField password2;
    @FXML
    private TextField numcred;
    @FXML
    private TextField svc;
    @FXML
    private Button botonRegistrarse;
    @FXML
    private Button cancelarBoton;
    @FXML
    private Label rcb; //tag que da el mensaje de error
    
    private int svcValue;
    private Member newUser = null;
    private Club club;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    } 
    
    

    @FXML
    private void registrarse(ActionEvent event) throws ClubDAOException {

        if (!password.getText().equals(password2.getText())) {
            rcb.setText("las contraseñas no coinciden");
        } else {
            if (surname.getText().length() == 0 || name.getText().length() == 0) {
                rcb.setText("nombre o apellido vacio");
            } else {
                if (nickname.getText().length() == 0) {
                    rcb.setText("username vacio");
                } else {
                    if (svc.getText().length() != 0 && svc.getText().length() > 3) {
                        rcb.setText("error al introducir el svc");
                    } else {
                        if (password.getText().length() == 0) {
                            rcb.setText("contraseña vacia");
                        } else {
                            try {
                                svcValue = Integer.parseInt(svc.getText());
                            } catch (NumberFormatException e) {
                                rcb.setText("letras introducidas en el svc");
                            }

                            if (club.existsLogin(nickname.getText()) == true) {
                                rcb.setText("nickname que ya esta en uso");
                            } else {

                                if (telf.getText().length() != 9) {
                                    rcb.setText("error al introducir tu número de telefono");
                                } else {
                                    if (numcred.getText().length() > 16) {
                                        rcb.setText("error al introducir tu número de la tarjeta de credito");
                                    } else {

                                        try {
                                            newUser = club.registerMember(name.getText(), surname.getText(), telf.getText(), nickname.getText(), password.getText(), numcred.getText(), svcValue, null);
                                        } catch (ClubDAOException e) {
                                            rcb.setText("error al crear la cuenta,revisa todos los parametros");
                                        }

                                        ((Button) event.getSource()).getScene().getWindow().hide();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    } // aqui se cierran todos los mensajes de error
    
    @FXML
    private void cancel(ActionEvent event) {
        ((Button) event.getSource()).getScene().getWindow().hide();
    }

    public void InitSesion(Club b) {

        club = b;

    }

}
