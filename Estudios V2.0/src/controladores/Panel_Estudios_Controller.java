/**
 * M�dulo: Estudios de Obra. Aplicaci�n de escritorio
 * Archivo: Panel_Estudios_Controller.java
 * Objetivo: Archivo Controller del Panel de Estudios.
 * Equipo/Persona: Fernando Chac�n Galea  28.614.518 - B
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class Panel_Estudios_Controller implements Initializable {
	
	/**
	 * Campos donde recogemos los datos de los estudios
	 */
	@FXML private TextField txtCodigo_Estudio, txtReferencia, txtDesignacion, txtAnyo, txtAdjudicada;
	
	/**
	 * Botones para gestionar las acciones a realizar con los estudios
	 */
	@FXML private Button btnGuardar, btnEliminar, btnActualizar;
	
	/**
	 * Etiqueta donde vamos mostrado la acciones que realizamos
	 */
	@FXML private Label etiEstado;
	
	/**
	 * Desplegable donde mostramos los usuarios que estan en la tabla usuarios de la BD
	 */
	@FXML private ComboBox <Usuario> cbUsuario;
	
	/**
	 * Lista de tipo ObservableList para rellenar el combobox Usuarios
	 */
	private ObservableList <Usuario> listaUsuarios;
	
	/**
	 * Desplegable donde mostramos las zonas que estan en la tabla zonas de la BD
	 */
	@FXML private ComboBox <Zona> cbZona;
	
	/**
	 * Lista de tipo ObservableList para rellenar el combobox Zonas
	 */
	private ObservableList <Zona> listaZonas;	
	
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
	 * Objeto de tipo Connection. Para configurar la conexion a la BBDD
	 */
	private Conexion miConexion;
	
	/**
	 * Lista de tipo ObservableList para rellenar la tablaView Estudios
	 */
	private ObservableList <Estudio> listaEstudios;
	
	/**
	 * M�todo para crear un nuevo Estudio, se agrega a la BD a la tabla estudios
	 * 
	 * @param Event evento generado por el usuario
	 */
	@FXML public void guardarEstudio (ActionEvent Event) {
		//Creamos una nueva instancia de estudio
		Estudio est = new Estudio(0,
				Integer.valueOf(txtReferencia.getText()),
				txtDesignacion.getText(),
				Integer.valueOf(txtAnyo.getText()),
				txtAdjudicada.getText(),
				cbUsuario.getSelectionModel().getSelectedItem(),
				cbZona.getSelectionModel().getSelectedItem()
				);
		
		//abrimos la conexion
		miConexion.conectarBD();
		//llamamos al metodo guardar de la clase estudio
		int resultado = est.guardarEstudio(miConexion.getConnection());
		//Cerramos la conexion
		miConexion.cerrarConexionBD();
		
		if(resultado == 1) {
			//A�adimos el industrial insertado en la BBDD al Tableview Industriales
			listaEstudios.add(est);
			
			etiEstado.setText("Estudio insertado correctamente");
		}else {
			etiEstado.setText("Estudio no insertado...");
		}
	}
	
	/**
	 * M�todo para actualizar un registro de la tabla estudios, se actualiza en la BD y se muestra
	 * en la tablaView Estudios.
	 * 
	 * @param Event, evento generado por el usuario
	 */
	@FXML public void actualizarEstudio (ActionEvent Event) {
		//Creamos una nueva instancia de estudio
		Estudio est = new Estudio(
				Integer.valueOf(txtCodigo_Estudio.getText()),
				Integer.valueOf(txtReferencia.getText()),
				txtDesignacion.getText(),
				Integer.valueOf(txtAnyo.getText()),
				txtAdjudicada.getText(),
				cbUsuario.getSelectionModel().getSelectedItem(),
				cbZona.getSelectionModel().getSelectedItem()
				);
				
		//abrimos la conexion
		miConexion.conectarBD();
		//llamamos al metodo guardar de la clase estudio
		int resultado = est.actualizarEstudio(miConexion.getConnection());
		//Cerramos la conexion
		miConexion.cerrarConexionBD();
				
		if(resultado == 1) {
			//A�adimos el industrial actualizado en la BBDD al Tableview Industriales
			listaEstudios.add(tablaEstudios.getSelectionModel().getSelectedIndex(),est);
			listaEstudios.remove(tablaEstudios.getSelectionModel().getSelectedIndex());
					
			etiEstado.setText("Estudio actualizado correctamente");
		}else {
			etiEstado.setText("No se ha podido actualizar el estudio...");
		}
	}
	
	/**
	 * M�todo para eliminar un registro de la tabla estudios de la BD
	 * 
	 * @param Event, evento generado por el usuario
	 */
	@FXML public void eliminarEstudio(ActionEvent Event) {
		
		//Abrimos la conexion con la BBDD
		miConexion.conectarBD();
		//Eliminamos el estudio seleccionado en la tabla
		int resultado = tablaEstudios.getSelectionModel().getSelectedItem().eliminarEstudio(miConexion.getConnection());		
		//Cerramos la conexion con la BBDD
		miConexion.cerrarConexionBD();
		
		if(resultado == 1) {
			//Eliminamos el estudio de la Tableview Estudios
			listaEstudios.remove(tablaEstudios.getSelectionModel().getSelectedIndex());
			
			etiEstado.setText("Estudio eliminado correctamente");
		}else {
			etiEstado.setText("No se ha podido eliminar el estudio...");
		}		
	}
	
	/**
	 * M�todo que limpia los textfield para poder insertar un nuevo estudio en la tabla estudios de la BD
	 * 
	 * @param Event, evento generado por el usuario
	 */
	@FXML public void nuevoEstudio (ActionEvent Event) {
		txtCodigo_Estudio.setText(null);
		txtReferencia.setText(null);
		txtDesignacion.setText(null);
		txtAnyo.setText(null);
		txtAdjudicada.setText(null);
		cbUsuario.setValue(null);
		cbZona.setValue(null);
		
		btnGuardar.setDisable(false);
		btnActualizar.setDisable(true);
		btnEliminar.setDisable(true);
		
	}
	
	/**
	 * M�todo para gestionar la selecci�n de un registro en el tableview de Estudios
	 */
	public void gestionarEventos() {
		tablaEstudios.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Estudio>() {
					
					@Override
					public void changed(ObservableValue<? extends Estudio>arg0, 
							Estudio estudioAnterior, Estudio estudioSeleccionado) {
						if(estudioSeleccionado!= null) {
							txtCodigo_Estudio.setText(String.valueOf(estudioSeleccionado.getCodigo_estudio()));
							txtReferencia.setText(String.valueOf(estudioSeleccionado.getReferencia()));
							txtDesignacion.setText(estudioSeleccionado.getDesignacion());
							txtAnyo.setText(String.valueOf(estudioSeleccionado.getAnyo()));
							txtAdjudicada.setText(estudioSeleccionado.getAdjudicada());
							cbUsuario.setValue(estudioSeleccionado.getUsuario());
							cbZona.setValue(estudioSeleccionado.getZona());
							
							//Controlamos los botones
							btnGuardar.setDisable(true);
							btnActualizar.setDisable(false);
							btnEliminar.setDisable(false);
						}
					}
				}
				
				
		);
	}
	
	/**
	 * M�todo por el que mostramos el estudio en su Panel de control, lo lanzamos cuando pulsamos el
	 * bot�n cargar.
	 * 
	 * @param Event, evento generado por el usuario
	 * @throws InterruptedException 
	 */
	@FXML public void cargarEstudio (ActionEvent Event) throws InterruptedException {
		
		
		etiEstado.setText("Cargando Estudio seleccionado...");
		Thread.sleep(2000);
		
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
     * M�todo de inicializaci�n del controlador, iniciamos la conexion con la BD, cargamos los comboBox,
     * cargamos el tableview de Estudios, lanzamos el metodo que gestiona la seleccion de los diferentes
     * estudios del tableview, y volvemos a cerrar la conexion con la BD
     * 
     * @param url La ubicaci�n utilizada para resolver rutas relativas para el objeto ra�z, o nul
     * si no se conoce la ubicaci�n.
     * @param rb Los recursos utilizados para localizar el objeto ra�z, o null si el objeto ra�z
     * no se localiz�
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//conectamos con la BBDD
		miConexion = new Conexion();
		miConexion.conectarBD();
		
		//Inicializamos los ObservableList
		listaUsuarios = FXCollections.observableArrayList();
		listaZonas = FXCollections.observableArrayList();
		listaEstudios = FXCollections.observableArrayList();
		
		//llenamos los combobox y la tabla de Estudios
		Usuario.datosTablaUsuarios(miConexion.getConnection(), listaUsuarios);
		Zona.datosComboZonas(miConexion.getConnection(), listaZonas);
		Estudio.datosTablaEstudios(miConexion.getConnection(), listaEstudios);
		
		//A�adimos los objetos a los comboBox y a la tabla
		cbUsuario.setItems(listaUsuarios);
		cbZona.setItems(listaZonas);
		tablaEstudios.setItems(listaEstudios);
		
		//Enlazamos las columnas con los atributos
		clCodigo_Estudio.setCellValueFactory(new PropertyValueFactory<Estudio, Integer>("codigo_estudio"));
		clReferencia.setCellValueFactory(new PropertyValueFactory<Estudio, Integer>("referencia"));
		clDesignacion.setCellValueFactory(new PropertyValueFactory<Estudio, String>("Designacion"));
		clAnyo.setCellValueFactory(new PropertyValueFactory<Estudio, Integer>("anyo"));
		clAdjudicada.setCellValueFactory(new PropertyValueFactory<Estudio, String>("adjudicada"));
		clUsuario.setCellValueFactory(new PropertyValueFactory<Estudio, Usuario>("usuario"));
		clZona.setCellValueFactory(new PropertyValueFactory<Estudio,Zona>("zona"));
		
		//seleccionamos un estudio de la tabla
		gestionarEventos();
		
		//cerramos la conexion con la BD
		miConexion.cerrarConexionBD();		
	}

}
