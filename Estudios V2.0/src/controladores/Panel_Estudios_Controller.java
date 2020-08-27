/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: Panel_Estudios_Controller.java
 * Objetivo: Archivo Controller del Panel de Estudios.
 * Equipo/Persona: Fernando Chacón Galea  28.614.518 - B
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Conexion;
import modelo.Estudio;
import modelo.Usuario;
import modelo.Zona;

/**
 * Clase controladora del panel "Gestión de Estudios"
 * 
 * @author Fernando Chacón Galea
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
	 * Objeto de tipo Connection. Para configurar la conexion a la BBDD
	 */
	private Conexion miConexion;
	
	/**
	 * Lista de tipo ObservableList para rellenar la tablaView Estudios
	 */
	private ObservableList <Estudio> listaEstudios;
	
	/**
	 * Método para crear un nuevo Estudio, se agrega a la BD a la tabla estudios
	 * 
	 * @param Event evento generado por el usuario
	 */
	@FXML public void guardarEstudio (ActionEvent Event) {
		//Primero evaluamos que todos los campos obligatorios (manda la BBDD y sus especificaciones
		//para cada tabla y sus columnas/atributos		
		if(txtReferencia.getText().equals(null) ||
				txtDesignacion.getText().equals(null) ||
				txtAnyo.getText().equals(null) ||
				txtAdjudicada.getText().equals(null) ||
				cbUsuario.getSelectionModel().getSelectedItem() == null ||
				cbZona.getSelectionModel().getSelectedItem() == null) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMACIÓN");
			alert.setHeaderText("Para poder guardar un Estudio nuevo:");
			alert.setContentText("No debes dejar ningún campo de texto vacío, y debes seleccionar"
					+ " un Técnico y una Zona geográfica para el Estudio.");
			alert.showAndWait();
		}
		else {
			//Creamos una nueva instancia de estudio
			Estudio est = new Estudio(0,
				txtReferencia.getText(),
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
				//Añadimos el industrial insertado en la BBDD al Tableview Industriales
				listaEstudios.add(est);
			
				etiEstado.setText("Estudio insertado correctamente");
			}else {
				etiEstado.setText("Estudio no insertado...");
			}
		}
	}
	
	/**
	 * Método para actualizar un registro de la tabla estudios, se actualiza en la BD y se muestra
	 * en la tablaView Estudios.
	 * 
	 * @param Event, evento generado por el usuario
	 */
	@FXML public void actualizarEstudio (ActionEvent Event) {
		//Primero evaluamos que todos los campos obligatorios (manda la BBDD y sus especificaciones
		//para cada tabla y sus columnas/atributos)
		if(txtReferencia.getText().equals(null) ||
				txtDesignacion.getText().equals(null) ||
				txtAnyo.getText().equals(null) ||
				txtAdjudicada.getText().equals(null) ||
				cbUsuario.getSelectionModel().getSelectedItem() == null ||
				cbZona.getSelectionModel().getSelectedItem() == null) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMACIÓN");
			alert.setHeaderText("Para actualizar un Estudio:");
			alert.setContentText("No debes dejar ningún campo de texto vacío, y debes seleccionar"
					+ " un Técnico y una Zona geográfica para el Estudio.");
			alert.showAndWait();
		}
		else {
			//Creamos una nueva instancia de estudio
			Estudio est = new Estudio(
				Integer.valueOf(txtCodigo_Estudio.getText()),
				txtReferencia.getText(),
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
				//Añadimos el industrial actualizado en la BBDD al Tableview Industriales
				listaEstudios.add(tablaEstudios.getSelectionModel().getSelectedIndex(),est);
				listaEstudios.remove(tablaEstudios.getSelectionModel().getSelectedIndex());
					
				etiEstado.setText("Estudio actualizado correctamente");
			}else {
				etiEstado.setText("No se ha podido actualizar el estudio...");
			}
		}
	}
	
	/**
	 * Método para eliminar un registro de la tabla estudios de la BD
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
	 * Método que limpia los textfield para poder insertar un nuevo estudio en la tabla estudios de la BD
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
	 * Método para gestionar la selección de un registro en el tableview de Estudios
	 */
	public void gestionarEventos() {
		tablaEstudios.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Estudio>() {
					
					@Override
					public void changed(ObservableValue<? extends Estudio>arg0, 
							Estudio estudioAnterior, Estudio estudioSeleccionado) {
						if(estudioSeleccionado!= null) {
							txtCodigo_Estudio.setText(String.valueOf(estudioSeleccionado.getCodigo_estudio()));
							txtReferencia.setText(estudioSeleccionado.getReferencia());
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
     * Método de inicialización del controlador, iniciamos la conexion con la BD, cargamos los comboBox,
     * cargamos el tableview de Estudios, lanzamos el metodo que gestiona la seleccion de los diferentes
     * estudios del tableview, y volvemos a cerrar la conexion con la BD
     * 
     * @param url La ubicación utilizada para resolver rutas relativas para el objeto raíz, o nul
     * si no se conoce la ubicación.
     * @param rb Los recursos utilizados para localizar el objeto raíz, o null si el objeto raíz
     * no se localizó
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
		
		//Añadimos los objetos a los comboBox y a la tabla
		cbUsuario.setItems(listaUsuarios);
		cbZona.setItems(listaZonas);
		tablaEstudios.setItems(listaEstudios);
		
		//Enlazamos las columnas con los atributos
		clCodigo_Estudio.setCellValueFactory(new PropertyValueFactory<Estudio, Integer>("codigo_estudio"));
		clReferencia.setCellValueFactory(new PropertyValueFactory<Estudio, String>("referencia"));
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
