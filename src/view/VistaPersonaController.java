package view;

import controller.LibretaDirecciones;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Persona;
import util.UtilidadDeFechas;

public class VistaPersonaController {

    @FXML
    private TableView tablaPersonas;
    @FXML
    private TableColumn nombreColumn;
    @FXML
    private TableColumn apellidosColumn;

    @FXML
    private Label nombreLabel;
    @FXML
    private Label apellidosLabel;
    @FXML
    private Label direccionLabel;
    @FXML
    private Label codigoPostalLabel;
    @FXML
    private Label ciudadLabel;
    @FXML
    private Label fechaDeNacimientoLabel;

    // Referencia a la clase principal
    private LibretaDirecciones libretaDirecciones;

    //El constructor es llamado ANTES del método initialize
    public VistaPersonaController() {
    }

    //Inicializa la clase controller y es llamado justo después de cargar el archivo FXML
    @FXML
    private void initialize() {

        //Inicializo la tabla con las dos primera columnas
        String nombre = "nombre";
        String apellidos = "apellidos";
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>(nombre));
        apellidosColumn.setCellValueFactory(new PropertyValueFactory<>(apellidos));
        //Borro los detalles de la persona
        mostrarDetallesPersona(null);

        //Escucho cambios en la selección de la tabla y muestro los detalles en caso de cambio
        tablaPersonas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetallesPersona((Persona) newValue));

    }

    //Es llamado por la apliación principal para tener una referencia de vuelta de si mismo
    public void setLibretaDirecciones(LibretaDirecciones libretaDirecciones) {

        this.libretaDirecciones = libretaDirecciones;

        //Añado la lista obervable a la tabla
        tablaPersonas.setItems(libretaDirecciones.getDatosPersona());
    }

    private void mostrarDetallesPersona(Persona persona) {

        if (persona != null) {
            //Relleno los labels desde el objeto persona
            nombreLabel.setText(persona.getNombre());
            apellidosLabel.setText(persona.getApellidos());
            direccionLabel.setText(persona.getDireccion());
            codigoPostalLabel.setText(Integer.toString(persona.getCodigoPostal()));
            ciudadLabel.setText(persona.getCiudad());
            fechaDeNacimientoLabel.setText(UtilidadDeFechas.formato(persona.getFechaDeNacimiento()));
        } else {
            //Persona es null, vacío todos los labels.
            nombreLabel.setText("");
            apellidosLabel.setText("");
            direccionLabel.setText("");
            codigoPostalLabel.setText("");
            ciudadLabel.setText("");
            fechaDeNacimientoLabel.setText("");
        }
    }
    
    //Borro la persona seleccionada cuando el usuario hace clic en el botón de Borrar
    //Borro la persona seleccionada cuando el usuario hace clic en el botón de Borrar
    @FXML
    private void borrarPersona() {
        //Capturo el indice seleccionado y borro su item asociado de la tabla
        int indiceSeleccionado = tablaPersonas.getSelectionModel().getSelectedIndex();
        if (indiceSeleccionado >= 0){
            //Borro item
            tablaPersonas.getItems().remove(indiceSeleccionado);
            
        } else {
            //Muestro alerta
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Atención");
            alerta.setHeaderText("Persona no seleccionada");
            alerta.setContentText("Por favor, selecciona una persona de la tabla");
            alerta.showAndWait();
                        
        }    
    }

}
