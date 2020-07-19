package controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Actividad;
import modelo.Conexion;
import modelo.Industrial;
import modelo.Zona;

public class Panel_Industriales_Controller implements Initializable{
	
	@FXML TextField txtCodigoIndustrial, txtNombre, txtApellidos, txtEmail, txtTelefono, txtTelefono02,
					txtEmpresa,txtLocalidad,txtComentarios;
	
	
	
	
	@FXML private Label etiEstado;
	
	@FXML private ComboBox <Zona> cbZona;
	
	private ObservableList <Zona> listaZonas;
	
	@FXML private ComboBox <Actividad> cbActividad;
	
	private ObservableList <Actividad> listaActividades;
	
	@FXML private TableView <Industrial> tablaIndustriales;
	
	
	
	@FXML private TableColumn <Industrial, Integer> clCodigo_Industrial;
	@FXML private TableColumn <Industrial, String> clNombre;
	@FXML private TableColumn <Industrial, String> clApellidos;
	@FXML private TableColumn <Industrial, String> clEmail;
	@FXML private TableColumn <Industrial, String> clTelefono;
	@FXML private TableColumn <Industrial, String> clTelefono02;
	@FXML private TableColumn <Industrial, Actividad> clActividad;
	@FXML private TableColumn <Industrial, String> clEmpresa;
	@FXML private TableColumn <Industrial, String> clLocalidad;
	@FXML private TableColumn <Industrial, Zona> clZona;
	@FXML private TableColumn <Industrial, String> clComentarios;
	
	private Conexion miConexion;
	
	private ObservableList <Industrial> listaIndustriales;
	
	@FXML public void guardarIndustrial (ActionEvent Event) {
		
	}
	
	@FXML public void actualizarIndustrial (ActionEvent Event) {
		
	}
	
	@FXML public void eliminarIndustrial (ActionEvent Event) {
		
	}
	
	@FXML public void nuevoIndustrial (ActionEvent Event) {
		
	}
	
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Conectamos con la BD
		miConexion = new Conexion();
		miConexion.conectarBD();
		
		//Inicializamos los ObservableList
		listaActividades = FXCollections.observableArrayList();
		listaZonas = FXCollections.observableArrayList();
		listaIndustriales = FXCollections.observableArrayList();
		
		//Llenamos los combobox y la tabla de Industriales
		Actividad.datosComboActividades(miConexion.getConnection(), listaActividades);
		Zona.datosComboZonas(miConexion.getConnection(), listaZonas);
		Industrial.datosTablaIndustriales(miConexion.getConnection(), listaIndustriales);
		
		//Añadimos los objetos a los comboBox y a la tabla
		cbActividad.setItems(listaActividades);
		cbZona.setItems(listaZonas);
		tablaIndustriales.setItems(listaIndustriales);
		
		//Enlazamos las columnas con los atributos
		clCodigo_Industrial.setCellValueFactory(new PropertyValueFactory<Industrial, Integer>("codigo_industrial") );
		clNombre.setCellValueFactory(new PropertyValueFactory<Industrial, String>("nombre"));
		clApellidos.setCellValueFactory(new PropertyValueFactory<Industrial, String>("apellidos"));
		clEmail.setCellValueFactory(new PropertyValueFactory<Industrial, String>("email"));
		clTelefono.setCellValueFactory(new PropertyValueFactory<Industrial, String>("telefono"));
		clTelefono02.setCellValueFactory(new PropertyValueFactory<Industrial, String>("telefono02"));
		clActividad.setCellValueFactory(new PropertyValueFactory<Industrial, Actividad>("actividad"));
		clEmpresa.setCellValueFactory(new PropertyValueFactory<Industrial, String>("empresa"));
		clLocalidad.setCellValueFactory(new PropertyValueFactory<Industrial, String>("localidad"));
		clZona.setCellValueFactory(new PropertyValueFactory<Industrial, Zona>("zona"));
		clComentarios.setCellValueFactory(new PropertyValueFactory<Industrial, String>("comentarios"));
		
		
		
		//cerramos la conexion
		miConexion.cerrarConexionBD();
	}

}
