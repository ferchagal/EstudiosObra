/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: Panel_Inicio_Controller.java
 * Objetivo: Archivo Controller del Panel de Inicio.
 * Equipo/Persona: Fernando Chacón Galea  28.614.518 - B
 */
package controladores;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Conexion;
import modelo.Estudio;
import modelo.Usuario;
import modelo.Zona;

/**
 * Clase controladora del Panel de Inicio
 * 
 * @author Fernando Chacón Galea
 * @version 2020.06.22 - V2
 */
public class Panel_Inicio_Controller implements Initializable  {
	
	/**
	 * Etiqueta donde vamos mostrado la acciones que realizamos
	 */
	@FXML private Label etiEstado;
	
	/**
	 * Tabla donde mostramos los estudios contenidos en la tabla estudios de la BD
	 */
	@FXML private TableView <Estudio> tablaEstudios;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clCodigo_Estudio
	 */
	@FXML private TableColumn<Estudio, Integer> clCodigo_Estudio;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clReferencia
	 */
	@FXML private TableColumn<Estudio, String> clReferencia;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clDesignacion
	 */
	@FXML private TableColumn<Estudio, String> clDesignacion;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clAnyo
	 */
	@FXML private TableColumn<Estudio, Integer> clAnyo;
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clAdjudicada
	 */
	@FXML private TableColumn<Estudio, String> clAdjudicada;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clUsuario
	 */
	@FXML private TableColumn<Estudio, Usuario> clUsuario;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clZona
	 */
	@FXML private TableColumn<Estudio, Zona> clZona;
	
	/**
	 * Objeto de tipo Connection. Para configurar la conexión a la BBDD.
	 */
	private Conexion miConexion;
	
	/**
	 * Lista de tipo ObservableList para rellenar la tabla de estudios
	 */
	private ObservableList<Estudio> estudios;
	
	/**
     * Método que se ejecuta cuando pinchamos en el botón "Sobre CONO, "Acerca de ...", nos muestra
     * una ventana con información sobre la aplicación.
     * 
     * @param event Evento generado por el usuario. 
     */
	@FXML private void acercaDe (ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INFORMACION SOBRE LA APLICACIÓN");
		alert.setHeaderText("Esta información te interesa:");
		alert.setContentText("Esta aplicación ha sido desarrollada para facilitar la gestión"
				+ " de ofertas de industriales/proveedores, para la realización de estudios"
				+ " de obras.\nSi tienes alguna duda o cuestión, ponte en contacto con:\n\nferchagal@gmail.com ");
		alert.showAndWait();	
	}
	
	/**
     * Método que se ejecuta cuando pinchamos sobre el botón "Ver Manual de Usuario", nos muestra
     * una ventana con el manual de usuario.
     * 
     * @param event Evento generado por el usuario. 
     */
	@FXML private void verManualUsuario (ActionEvent event) {
		String ruta = "src/manuales/ManualUsuarioV1.pdf";
		abrirFichero(ruta);
	}
	
	/**
	 * Método para abrir un fichero, en este caso el Manual de Usuario
	 * 
	 * @param ruta, Es una cadena de texto con la URL donde se encuentra el Manual de Usuario.
	 */
	public void abrirFichero(String ruta) {
		
		try {
			Runtime.getRuntime().exec("cmd /c start "+ruta);
		}catch(Exception ex) {
			ex.printStackTrace();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMACIÓN");
			alert.setHeaderText("Esta información te interesa:");
			alert.setContentText("Manual de usuario no encontrado. Ponte en contacto con el administrador."
					+ "\n\nferchagal@gmail.com ");
			alert.showAndWait();
		}
	}
	
	/**
     * Método de inicialización del controlador, iniciamos la conexion con la BD, cargamos el tableview
     * de Estudios, lanzamos el metodo que gestiona la seleccion de los diferentes estudios del tableview,
     * y volvemos a cerrar la conexion con la BD
     * 
     * @param url La ubicación utilizada para resolver rutas relativas para el objeto raíz, o nul
     * si no se conoce la ubicación.
     * @param rb Los recursos utilizados para localizar el objeto raíz, o null si el objeto raíz
     * no se localizó
     */
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
