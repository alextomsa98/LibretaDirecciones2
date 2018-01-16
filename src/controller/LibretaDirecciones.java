/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Persona;
import view.VistaPersonaController;

/**
 *
 * @author gminf
 */
public class LibretaDirecciones extends Application {
    
    private ObservableList datosPersona = FXCollections.observableArrayList();
    private Stage escenarioPrincipal;
    private BorderPane layoutPrincipal;
    private AnchorPane vistaPersona;
    
    //Datos de ejemplo
    public LibretaDirecciones(){
        
        datosPersona.add(new Persona("Papi", "Sanchez"));
        datosPersona.add(new Persona("Ratulio", "Murciano"));
        datosPersona.add(new Persona("Pablo", "Picapiedra"));
        datosPersona.add(new Persona("ElCalvo", "Zamarreño"));
        datosPersona.add(new Persona("Tomsa", "Tomsa"));

    }
    
    //Método para devolver los datos como lista observable
    public ObservableList getDatosPersona(){
        
        return datosPersona;
        
    }
    
    @Override
    public void start(Stage escenarioPrincipal) {
        
        this.escenarioPrincipal = escenarioPrincipal;
        
        //Establezco el título
        this.escenarioPrincipal.setTitle("Libreta de direcciones");
        
        //Inicializa el layout principal
        initLayoutPrincipal();
        
        //Muestra la vista persona
        muestraVistaPersona();
        
    }
    
    public void initLayoutPrincipal(){
        
        //Cargo el layout principal a partir de la vista VistaPrincipal.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = LibretaDirecciones.class.getResource("../view/VistaPrincipal.fxml");
        loader.setLocation(location);
        try {
            layoutPrincipal = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LibretaDirecciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Cargo y muestro la escena que contiene ese layout principal
        Scene escena = new Scene(layoutPrincipal);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();
        
        
        
    }
    
    public void muestraVistaPersona(){
        
        //Cargo la vista persona a partir de la vista VistaPersona.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = LibretaDirecciones.class.getResource("../view/VistaPersona.fxml");
        loader.setLocation(location);
        try {
            vistaPersona = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LibretaDirecciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Añado al centro del layoutPrincipal 
        layoutPrincipal.setCenter(vistaPersona);
        
        //Doy acceso al controlador VistaPersonaCOntroller a LibretaDirecciones
        VistaPersonaController controller = loader.getController();
        controller.setLibretaDirecciones(this);
    }
    
    //Invoco el método getPrimaryStage para que devuelva mi escenario principal
    public Stage getPrimaryStage() {
        return escenarioPrincipal;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
}
