/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: Panel_Inicio_Controller.java
 * Objetivo: Archivo Controller del Panel de Inicio.
 * Equipo/Persona: Fernando Chacón Galea  28.614.518 - B
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Conexion;
import modelo.Estudio;
import modelo.Usuario;
import modelo.Zona;


public class Panel_Inicio_Controller implements Initializable  {
	
	@FXML private Label etiEstado;
	
	@FXML private TableView <Estudio> tablaEstudios;
	
	@FXML private TableColumn<Estudio, Integer> clCodigo_Estudio;
	@FXML private TableColumn<Estudio, String> clReferencia;
	@FXML private TableColumn<Estudio, String> clDesignacion;
	@FXML private TableColumn<Estudio, Integer> clAnyo;
	@FXML private TableColumn<Estudio, String> clAdjudicada;
	@FXML private TableColumn<Estudio, Usuario> clUsuario;
	@FXML private TableColumn<Estudio, Zona> clZona;
	
	/**
	 * Objeto de tipo Connection. Para configurar la conexión a la BBDD.
	 */
	private Conexion miConexion;
	
	/**
	 * Lista de tipo ObservableList para rellenar la tabla de estudios
	 */
	private ObservableList<Estudio> estudios;
	
	@FXML public void cargarEstudio (ActionEvent Event) {
		
		
		etiEstado.setText("Cargando Estudio seleccionado...");
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		miConexion = new Conexion();
		miConexion.conectarBD();
		
		//inicializamos  lista de estudios
		estudios = FXCollections.observableArrayList();
		
		//Llenamos lista estudios
		Estudio.datosTablaEstudios(miConexion.getConnection(), estudios);
		
		//Enlazamos con la Tableview
		tablaEstudios.setItems(estudios);
		
		//Enlazar columnas con atributos
		clCodigo_Estudio.setCellValueFactory(new PropertyValueFactory<Estudio, Integer>("codigo_estudio"));
		clReferencia.setCellValueFactory(new PropertyValueFactory<Estudio, String>("referencia"));
		clDesignacion.setCellValueFactory(new PropertyValueFactory<Estudio, String>("designacion"));
		clAnyo.setCellValueFactory(new PropertyValueFactory<Estudio, Integer>("anyo"));
		clAdjudicada.setCellValueFactory(new PropertyValueFactory<Estudio, String>("adjudicada"));
		clUsuario.setCellValueFactory(new PropertyValueFactory<Estudio, Usuario>("usuario"));
		clZona.setCellValueFactory(new PropertyValueFactory<Estudio, Zona>("zona"));
		
		//Cerramos la conexion
		miConexion.cerrarConexionBD();
		
	}

}
