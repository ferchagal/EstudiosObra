/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: OF_Panel_Configurar_Controller.java
 * Objetivo: Archivo Controller del Panel Configurar de la seccion de ofertas.
 * Equipo/Persona: Fernando Chacón Galea  28.614.518 - B
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Actividad;
import modelo.Conexion;
import modelo.Estudio;
import modelo.Industrial;
import modelo.Zona;

public class OF_Panel_Configurar_Controller implements Initializable {
	
	/**
	 * Desplegable donde mostramos los estudios que están en la tabla estudios de la BD
	 */
	@FXML private ComboBox <String> cbEstudios;
	
	/**
	 * Lista de tipo ObservableList para rellenar el comboBox Estudios
	 */
	private ObservableList <String> listaEstudios;
	
	/**
	 * Desplegable donde mostramos las zonas que estan en la tabla zonas de la BD
	 */
	@FXML private ComboBox <Zona> cbZona;
	
	/**
	 * Lista de tipo ObservableList para rellenar el combobox Zonas
	 */
	private ObservableList <Zona> listaZonas;

	/**
	 * Tabla donde mostramos los Industriales contenidos en la tabla industriales de la BD
	 */
	@FXML private TableView <Industrial> tablaIndustriales;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clCodigo_Industrial
	 */	
	@FXML private TableColumn <Industrial, Integer> clCodigo_Industrial;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clNombre
	 */
	@FXML private TableColumn <Industrial, String> clNombre;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clApellidos
	 */
	@FXML private TableColumn <Industrial, String> clApellidos;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clEmail
	 */
	@FXML private TableColumn <Industrial, String> clEmail;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clTelefono
	 */
	@FXML private TableColumn <Industrial, String> clTelefono;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clTelefono02
	 */
	@FXML private TableColumn <Industrial, String> clTelefono02;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clActividad
	 */
	@FXML private TableColumn <Industrial, Actividad> clActividad;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clEmpresa
	 */
	@FXML private TableColumn <Industrial, String> clEmpresa;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clLocalidad
	 */
	@FXML private TableColumn <Industrial, String> clLocalidad;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clZona
	 */
	@FXML private TableColumn <Industrial, Zona> clZona;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clComentarios
	 */
	@FXML private TableColumn <Industrial, String> clComentarios;
	
	/**
	 * Objeto de tipo Connection. Para configurar la conexion a la BBDD
	 */
	private Conexion miConexion;
	
	/**
	 * Lista de tipo ObservableList para rellenar la tablaView Industriales
	 */
	private ObservableList <Industrial> listaIndustriales;
	
	/**
     * Método de inicialización del controlador, iniciamos la conexion con la BD, cargamos el comboBox,
     * y volvemos a cerrar la conexion con la BD
     * 
     * @param url La ubicación utilizada para resolver rutas relativas para el objeto raíz, o nul
     * si no se conoce la ubicación.
     * @param rb Los recursos utilizados para localizar el objeto raíz, o null si el objeto raíz
     * no se localizó
     */	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Conectamos con la BD
		miConexion = new Conexion();
		miConexion.conectarBD();
		
		//Iniciamos los ObservableList 
		listaEstudios = FXCollections.observableArrayList();
		listaZonas = FXCollections.observableArrayList();
		listaIndustriales = FXCollections.observableArrayList();
				
		//Llenamos el comboBox
		Estudio.datosComboEstudios(miConexion.getConnection(), listaEstudios);
		Zona.datosComboZonas(miConexion.getConnection(), listaZonas);
		Industrial.datosTablaIndustriales(miConexion.getConnection(), listaIndustriales);
				
		//Añadimos los objetos al comboBox
		cbEstudios.setItems(listaEstudios);
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
				
		//Cerramos la conexion
		miConexion.cerrarConexionBD();

		
		
	}

}
