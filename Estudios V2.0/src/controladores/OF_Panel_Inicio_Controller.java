package controladores;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Conexion;
import modelo.Estudio;
import modelo.OfertasJdo;

public class OF_Panel_Inicio_Controller implements Initializable{
		
	/**
	 * Desplegable donde mostramos los estudios que están en la tabla estudios de la BD
	 */
	@FXML private ComboBox <String> cbEstudios;
	
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
	@FXML private TableColumn <OfertasJdo, Date> clSolicitada;
	@FXML private TableColumn <OfertasJdo, String> clEstado;
	@FXML private TableColumn <OfertasJdo, String> clComentarios;
	
	/**
	 * Objeto de tipo Connection. Para configurar la conexion a la BBDD
	 */
	private Conexion miConexion;
	
	
	
	
	@FXML private void cargarListaEstudio() {
		
		//Conectamos con la BD
		miConexion = new Conexion();
		miConexion.conectarBD();
		
		//Inicializamos el ObservableList
		listaOfertas = FXCollections.observableArrayList();
		
		//Llenamos el TableView
		OfertasJdo.datosTablaOfertasJdo(miConexion.getConnection(), listaOfertas);
		
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
		clSolicitada.setCellValueFactory(new PropertyValueFactory<OfertasJdo, Date>("solicitada"));
		clEstado.setCellValueFactory(new PropertyValueFactory<OfertasJdo, String>("estado"));
		clComentarios.setCellValueFactory(new PropertyValueFactory<OfertasJdo, String>("comentarios"));
		
		//Seleccionamos un estudio en el comboBox y lo mostramos en el TableView
		cbEstudios.valueProperty().addListener(
				new ChangeListener<String>() {
					@Override 
					public void changed(ObservableValue<? extends String>arg0,
							String estudioAnterior, String estudioSeleccionado) {
						if(estudioSeleccionado!= null) {
							
						}
						
					}
			
		});
		
	}

	/**
     * Método de inicialización del controlador, iniciamos la conexion con la BD, cargamos el comboBox,
     * cargamos el tableview de Ofertas, lanzamos el metodo que gestiona la seleccion de los diferentes
     * industriales del tableview, y volvemos a cerrar la conexion con la BD
     * 
     * @param url La ubicación utilizada para resolver rutas relativas para el objeto raíz, o nul
     * si no se conoce la ubicación.
     * @param rb Los recursos utilizados para localizar el objeto raíz, o null si el objeto raíz
     * no se localizó
     */	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		//Conectamos con la BD
		miConexion = new Conexion();
		miConexion.conectarBD();
		
		//Iniciamos los ObservableList 
		listaEstudios = FXCollections.observableArrayList();
		
		//Llenamos el comboBox
		Estudio.datosComboEstudios(miConexion.getConnection(), listaEstudios);
		
		//Añadimos los objetos al comboBox
		cbEstudios.setItems(listaEstudios);
		
		//Cerramos la conexion
		miConexion.cerrarConexionBD();		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
