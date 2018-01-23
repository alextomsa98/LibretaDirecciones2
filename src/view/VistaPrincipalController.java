/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.LibretaDirecciones;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

/**
 *
 * @author dam
 */
public class VistaPrincipalController {
    
    //Referencia a la clase principal
    private LibretaDirecciones libretaDirecciones;

    //Es llamada por la clase Principal para tener una referencia de vuelta de si misma
    public void setLibretaDirecciones(LibretaDirecciones libretaDirecciones) {
        this.libretaDirecciones = libretaDirecciones;
    }

    //Creo una nueva libreta de direcciones en XML vacía
    @FXML
    private void nuevo() {
        libretaDirecciones.getDatosPersona().clear();
        libretaDirecciones.setRutaArchivoPersonas(null);
    }

    //Abro un File Chooser para que el usario seleccione una libreta
    @FXML
    private void abrir() {
        FileChooser fileChooser = new FileChooser();

        //Filtro para la extensión
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        //Muestro el diálogo de guardar
        File archivo = fileChooser.showOpenDialog(libretaDirecciones.getPrimaryStage());

        if (archivo != null) {
            libretaDirecciones.cargaPersonas(archivo);
        }
    }

    //Guardar
    @FXML
    private void guardar() {
        File archivo = libretaDirecciones.getRutaArchivoPersonas();
        if (archivo != null) {
            libretaDirecciones.guardaPersonas(archivo);
        } else {
            guardarComo();
        }
    }

    //Abro un File Chooser para guardar como
    @FXML
    private void guardarComo() {
        
        FileChooser fileChooser = new FileChooser();

        //Filtro para la extensión
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        //Muestro el diálogo de guardar
        File archivo = fileChooser.showSaveDialog(libretaDirecciones.getPrimaryStage());

        if (archivo != null) {
            //Me aseguro de que tiene la extensión correcta
            if (!archivo.getPath().endsWith(".xml")) {
                archivo = new File(archivo.getPath() + ".xml");
            }
            libretaDirecciones.guardaPersonas(archivo);
        }
    }

    //Acerca de
    @FXML
    private void acercaDe() {
        //Muestro alerta
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Acerca de");
        alerta.setContentText("Autor: Marco Jakob\nWebsite: http://code.makery.ch\nAdaptación 2018: Jairo García Rincón");
        alerta.showAndWait();
    }

    //Salir
    @FXML
    private void salir() {
        System.exit(0);
    }
    
}
