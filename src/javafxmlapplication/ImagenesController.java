/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aitan
 */
public class ImagenesController implements Initializable {

    @FXML
    private ImageView i1;
    @FXML
    private ImageView i2;
    @FXML
    private ImageView i3;
    @FXML
    private ImageView i4;
    @FXML
    private ImageView i5;
    @FXML
    private ImageView i6;
    @FXML
    private ImageView i7;
    @FXML
    private ImageView i8;
    @FXML
    private ImageView i9;
    @FXML
    private ImageView i10;
    @FXML
    private ImageView i11;
    @FXML
    private ImageView i12;
    
    
    public String url = new String ("");
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    } 
    
    

    
    
    public String getImage(){
    return url;
    }

    @FXML
    private void imagenPick1(MouseEvent event) {
        url=i1.getImage().getUrl();
        Stage stage = (Stage) i1.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void imagenPick2(MouseEvent event) {
        url=i2.getImage().getUrl();
        Stage stage = (Stage) i1.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void imagenPick3(MouseEvent event) {
        url=i3.getImage().getUrl();
        Stage stage = (Stage) i1.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void imagenPick4(MouseEvent event) {
        url=i4.getImage().getUrl();
        Stage stage = (Stage) i1.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void imagenPick5(MouseEvent event) {
        url=i5.getImage().getUrl();
        Stage stage = (Stage) i1.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void imagenPick6(MouseEvent event) {
        url=i6.getImage().getUrl();
        Stage stage = (Stage) i1.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void imagenPick7(MouseEvent event) {
        url=i7.getImage().getUrl();
        Stage stage = (Stage) i1.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void imagenPick8(MouseEvent event) {
        url=i8.getImage().getUrl();
        Stage stage = (Stage) i1.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void imagenPick9(MouseEvent event) {
        url=i9.getImage().getUrl();
        Stage stage = (Stage) i1.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void imagenPick10(MouseEvent event) {
        url=i10.getImage().getUrl();
        Stage stage = (Stage) i1.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void imagenPick11(MouseEvent event) {
        url=i11.getImage().getUrl();
        Stage stage = (Stage) i1.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void imagenPick12(MouseEvent event) {
        url=i12.getImage().getUrl();
        Stage stage = (Stage) i1.getScene().getWindow();
        stage.close(); 
    }
}
