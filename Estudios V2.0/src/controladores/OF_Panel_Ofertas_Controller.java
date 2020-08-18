/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: OF_Panel_Ofertas_Controller.java
 * Objetivo: Archivo Controller del Panel Ofertas de la seccion de ofertas.
 * Equipo/Persona: Fernando Chacón Galea  28.614.518 - B
 */
package controladores;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Conexion;
import modelo.Estudio;
import modelo.OfertasJdo;

public class OF_Panel_Ofertas_Controller implements Initializable{
	
	//------------------- COMPONENTES  y MÉTODOS PARA LA GESTIÓN DE LAS OFERTAS ---------------------
	
	@FXML Label etiEstado;
	
	@FXML Button btnGuardar, btnActualizar, btnEliminar;
	
	@FXML TextField txtCodigoIndustrial, txtNombre, txtApellidos, txtEmail, txtEmpresa, txtActividad,
					txtTelefono, txtComentarios;
	
	@FXML DatePicker dpSolicitada;
	
	@FXML ComboBox <String> cbEstado;
	
	private ObservableList <String> listaEstados;
	
	@FXML public void guardarIndustrial() {
		
	}
	
	@FXML public void actualizarIndustrial() {
		
	}
	
	@FXML public void eliminarIndustrial() {
		
	}
	
	@FXML public void nuevoIndustrial() {
		
	}
	
	
	//--------------------------------------------------------------------------------------------FIN
	
	/**
	 * Desplegable donde mostramos los estudios que están en la tabla estudios de la BD
	 */
	@FXML ComboBox <String> cbEstudios;
	
	/**
	 * Lista de tipo ObservableList para rellenar el comboBox Estudios
	 */
	private ObservableList <String> listaEstudios;
	
	/**
	 * TableView donde mostramos las ofertas pedidas del estudio seleccionado en el combobox
	 */
	@FXML private TableView <OfertasJdo> tablaOfertas;
	
	/**
	 * Lista de tipo ObservableList para rellenar la tabla Ofertas
	 */
	private ObservableList <OfertasJdo> listaOfertas;
	
	@FXML private TableColumn <OfertasJdo,Integer> clCodigo_Industrial;
	@FXML private TableColumn <OfertasJdo, String> clNombre;
	@FXML private TableColumn <OfertasJdo, String> clApellidos;
	@FXML private TableColumn <OfertasJdo, String> clEmail;
	@FXML private TableColumn <OfertasJdo, String> clTelefono;
	@FXML private TableColumn <OfertasJdo, String> clActividad;
	@FXML private TableColumn <OfertasJdo, String> clEmpresa;
	@FXML private TableColumn <OfertasJdo, String> clSolicitada;
	@FXML private TableColumn <OfertasJdo, String> clEstado;
	@FXML private TableColumn <OfertasJdo, String> clComentarios;
	
	/**
	 * Objeto de tipo Connection. Para configurar la conexion a la BBDD
	 */
	private Conexion miConexion;
	
	/**
	 * Método para mostrar los industriales contenidos en el estudio que seleccionemos en el comboBox
	 * cbEstudios.
	 */
	@FXML private void cargarListaEstudio() {
		
		//Creamos un string con el nombre de la tabla de la que queremos mostrar los industriales
		String nombreTabla = cbEstudios.getSelectionModel().getSelectedItem();
		
		//Conectamos con la BD
		miConexion = new Conexion();
		miConexion.conectarBD();
		
		//Inicializamos el ObservableList
		listaOfertas = FXCollections.observableArrayList();
		
		//Llenamos el TableView
		OfertasJdo.datosTablaOfertasJdo(miConexion.getConnection(), listaOfertas, nombreTabla);
		
		//Añadimos los objetos a la tabla
		tablaOfertas.setItems(listaOfertas);
		
		//Enlazamos las columnas con los atributos
		clCodigo_Industrial.setCellValueFactory(new PropertyValueFactory<OfertasJdo, Integer>("codigo_industrial"));
		clNombre.setCellValueFactory(new PropertyValueFactory<OfertasJdo, String>("nombre"));
		clApellidos.setCellValueFactory(new PropertyValueFactory<OfertasJdo,String>("apellidos"));
		clEmail.setCellValueFactory(new PropertyValueFactory<OfertasJdo, String>("email"));
		clTelefono.setCellValueFactory(new PropertyValueFactory<OfertasJdo, String>("telefono"));
		clActividad.setCellValueFactory(new PropertyValueFactory<OfertasJdo, String>("actividad"));
		clEmpresa.setCellValueFactory(new PropertyValueFactory<OfertasJdo, String>("empresa"));
		clSolicitada.setCellValueFactory(new PropertyValueFactory<OfertasJdo, String>("solicitada"));
		clEstado.setCellValueFactory(new PropertyValueFactory<OfertasJdo, String>("estado"));
		clComentarios.setCellValueFactory(new PropertyValueFactory<OfertasJdo, String>("comentarios"));
		
		//Cerramos la conexion
		miConexion.cerrarConexionBD();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Conectamos con la BD
		miConexion = new Conexion();
		miConexion.conectarBD();
				
		//Iniciamos los ObservableList 
		listaEstudios = FXCollections.observableArrayList();
		listaEstados = FXCollections.observableArrayList();	
				
		//Llenamos el comboBox
		Estudio.datosComboEstudios(miConexion.getConnection(), listaEstudios);
		
		//Llenamos la listaEstados
		listaEstados.add("Oferta Solicitada");
		listaEstados.add("Oferta Recibida");
		listaEstados.add("Oferta Rechazada");
				
		//Añadimos los objetos al comboBox
		cbEstudios.setItems(listaEstudios);
		cbEstado.setItems(listaEstados);
				
		//Cerramos la conexion
		miConexion.cerrarConexionBD();
		
	}

}
