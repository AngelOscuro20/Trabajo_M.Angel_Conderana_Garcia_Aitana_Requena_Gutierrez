/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Club;
import model.Member;

/**
 * FXML Controller class
 *
 * @author aitan
 */
public class DetallesController implements Initializable {

    private TextField nombreLabel;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField telf;
    @FXML
    private TextField numTarjeta;
    @FXML
    private TextField cvs;
    @FXML
    private Label rcb;//mensaje de error

    private Member User ;
    private Club club;
    public boolean cambiado = false;
    public String imgP  = new String(""); 

    @FXML
    private ImageView imgCuenta;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       boolean a= numTester(telf.getText());
        
    }    
    
    private boolean numTester(String a){ // comprueba que una cadena de caracteres esta compuesta exclusivamente de números
       try {double b= Double.parseDouble(a);}
      catch (NumberFormatException ex){
            return false;
        }
       return true;   
    }

    @FXML
    private void cambCont(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Diálogo de confirmación");
alert.setHeaderText(null);
alert.setContentText("¿Seguro que quieres continuar?");
Optional<ButtonType> result = alert.showAndWait();
if (result.isPresent() && result.get() == ButtonType.OK){ 

   ((Button) event.getSource()).getScene().getWindow().hide();
} else {
System.out.println("CANCEL");
}
        
        
    }

    @FXML
    private void aceptar(ActionEvent event) {
        
        if(telf.getText().length()!=9||!numTester(telf.getText())){rcb.setText("numero de telefono incompatible");}else{
        if(name.getText().isEmpty()||surname.getText().isEmpty()){rcb.setText("tu nombre o tu apellido se encuentra vacio");}else{
            
        User.setName(name.getText());
        User.setSurname(surname.getText());  
        User.setTelephone(telf.getText());
        rcb.setText("cambios realizados correctamente");
        }}
        
        if(!numTarjeta.getText().isEmpty() || Integer.parseInt(cvs.getText())!=0){
         if(numTarjeta.getText().length()!=16||!(numTester(numTarjeta.getText()))){rcb.setText("numero de tarjeta incompatible");}else{
         if(cvs.getText().length()!=3||!numTester(cvs.getText())){rcb.setText(" cvs incompatible");}else{      
             
             User.setCreditCard(numTarjeta.getText());
             User.setSvc(Integer.parseInt(cvs.getText()));
             
             rcb.setText("cambios realizados correctamente");
             }
        
         }
      }
        
    }

    @FXML
    private void cambImg(ActionEvent event) throws IOException {
        
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/javafxmlapplication/imagenes.fxml"));
Parent root = miCargador.load();

 ImagenesController controladorMiImagen = miCargador.getController();

Scene scene = new Scene(root,500,300);
Stage stage = new Stage();
stage.setScene(scene);
stage.setTitle("Vista imagenes");
stage.initModality(Modality.APPLICATION_MODAL);
//la ventana se muestra modal
stage.showAndWait();
imgP = controladorMiImagen.getImage();
Image imgAux = new Image(imgP);
User.setImage(imgAux);
  imgCuenta.imageProperty().setValue(User.getImage());       
    }
    
     public void InitCuenta(Club b,Member m) {

        club = b;
        User = m;
        name.setText(User.getName());
        surname.setText(User.getSurname());
        telf.setText(User.getTelephone());
        
        numTarjeta.setText(User.getCreditCard());
        cvs.setText(String.valueOf(User.getSvc()));
        imgCuenta.imageProperty().setValue(User.getImage()); 
        
    }

    
   
}

    

