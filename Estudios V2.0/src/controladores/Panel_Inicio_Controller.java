/**
 * M�dulo: Estudios de Obra. Aplicaci�n de escritorio
 * Archivo: Panel_Inicio_Controller.java
 * Objetivo: Archivo Controller del Panel de Inicio.
 * Equipo/Persona: Fernando Chac�n Galea  28.614.518 - B
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
 * @author Fernando Chac�n Galea
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
	 * Objeto de tipo Connection. Para configurar la conexi�n a la BBDD.
	 */
	private Conexion miConexion;
	
	/**
	 * Lista de tipo ObservableList para rellenar la tabla de estudios
	 */
	private ObservableList<Estudio> estudios;
	
	/**
     * M�todo que se ejecuta cuando pinchamos en el bot�n "Sobre CONO, "Acerca de ...", nos muestra
     * una ventana con informaci�n sobre la aplicaci�n.
     * 
     * @param event Evento generado por el usuario. 
     */
	@FXML private void acercaDe (ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INFORMACION SOBRE LA APLICACI�N");
		alert.setHeaderText("Esta informaci�n te interesa:");
		alert.setContentText("Esta aplicaci�n ha sido desarrollada para facilitar la gesti�n"
				+ " de ofertas de industriales/proveedores, para la realizaci�n de estudios"
				+ " de obras.\nSi tienes alguna duda o cuesti�n, ponte en contacto con:\n\nferchagal@gmail.com ");
		alert.showAndWait();	
	}
	
	/**
     * M�todo que se ejecuta cuando pinchamos sobre el bot�n "Ver Manual de Usuario", nos muestra
     * una ventana con el manual de usuario.
     * 
     * @param event Evento generado por el usuario. 
     */
	@FXML private void verManualUsuario (ActionEvent event) {
		String ruta = "src/manuales/ManualUsuarioV1.pdf";
		abrirFichero(ruta);
	}
	
	/**
	 * M�todo para abrir un fichero, en este caso el Manual de Usuario
	 * 
	 * @param ruta, Es una cadena de texto con la URL donde se encuentra el Manual de Usuario.
	 */
	public void abrirFichero(String ruta) {
		
		try {
			Runtime.getRuntime().exec("cmd /c start "+ruta);
		}catch(Exception ex) {
			ex.printStackTrace();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMACI�N");
			alert.setHeaderText("Esta informaci�n te interesa:");
			alert.setContentText("Manual de usuario no encontrado. Ponte en contacto con el administrador."
					+ "\n\nferchagal@gmail.com ");
			alert.showAndWait();
		}
	}
	
	/**
     * M�todo de inicializaci�n del controlador, iniciamos la conexion con la BD, cargamos el tableview
     * de Estudios, lanzamos el metodo que gestiona la seleccion de los diferentes estudios del tableview,
     * y volvemos a cerrar la conexion con la BD
     * 
     * @param url La ubicaci�n utilizada para resolver rutas relativas para el objeto ra�z, o nul
     * si no se conoce la ubicaci�n.
     * @param rb Los recursos utilizados para localizar el objeto ra�z, o null si el objeto ra�z
     * no se localiz�
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
