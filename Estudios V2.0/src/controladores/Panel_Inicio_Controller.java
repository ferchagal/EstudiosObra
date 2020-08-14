/**
 * M�dulo: Estudios de Obra. Aplicaci�n de escritorio
 * Archivo: Panel_Inicio_Controller.java
 * Objetivo: Archivo Controller del Panel de Inicio.
 * Equipo/Persona: Fernando Chac�n Galea  28.614.518 - B
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Conexion;
import modelo.Estudio;
import modelo.Usuario;
import modelo.Zona;

/**
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
	@FXML private TableColumn<Estudio, Integer> clReferencia;
	
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
	 * M�todo por el que cambiamos al Panel de estudios/ofertas, lo lanzamos cuando pulsamos el
	 * bot�n "Ir al panel de Ofertas".
	 * 
	 * @param Event, evento generado por el usuario
	 * @throws InterruptedException 
	 */
	@FXML public void cargarPanelOfertas (ActionEvent Event) throws InterruptedException {
		
		URL url = getClass().getResource("../vistas/Panel_OferEst.fxml");
		
		try {
			Node node = FXMLLoader.load(url);
			Scene scene = new Scene((Parent) node);
			Stage appStage = (Stage) ((Node) Event.getSource()).getScene().getWindow();
			appStage.setScene(scene);
			appStage.toFront();
			appStage.centerOnScreen();
			appStage.show();
			
		}catch(Exception ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMACION RELEVANTE");
			alert.setHeaderText("NO se ha podido cargar el panel");
			alert.setContentText("Si no aparece la opci�n que has elegido, no pierdas la calma."
				+ " Cierra la aplicaci�n y vuelve a entrar en ella.");
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
		clReferencia.setCellValueFactory(new PropertyValueFactory<Estudio, Integer>("referencia"));
		clDesignacion.setCellValueFactory(new PropertyValueFactory<Estudio, String>("designacion"));
		clAnyo.setCellValueFactory(new PropertyValueFactory<Estudio, Integer>("anyo"));
		clAdjudicada.setCellValueFactory(new PropertyValueFactory<Estudio, String>("adjudicada"));
		clUsuario.setCellValueFactory(new PropertyValueFactory<Estudio, Usuario>("usuario"));
		clZona.setCellValueFactory(new PropertyValueFactory<Estudio, Zona>("zona"));
		
		//Cerramos la conexion
		miConexion.cerrarConexionBD();
		
	}

}
