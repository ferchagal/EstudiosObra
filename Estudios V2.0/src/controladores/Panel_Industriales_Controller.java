/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: Panel_Industriales_Controller.java
 * Objetivo: Archivo Controller del Panel de Industriales.
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
import javafx.scene.control.Button;
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

/**
 * 
 * @author Fernando Chacón Galea
 * @version 2020.06.22 - V2
 */
public class Panel_Industriales_Controller implements Initializable{
	
	/**
	 * Campos donde recogemos los datos de los industriales
	 */
	@FXML private TextField txtCodigoIndustrial, txtNombre, txtApellidos, txtEmail, txtTelefono, txtTelefono02,
					txtEmpresa,txtLocalidad,txtComentarios;
	
	/**
	 * Botones para gestionar las acciones a realizar con los estudios
	 */
	@FXML private Button btnGuardar, btnEliminar, btnActualizar;
	
	/**
	 * Etiqueta donde vamos mostrando las acciones que realizamos
	 */
	@FXML private Label etiEstado;
	
	/**
	 * Desplegable donde mostramos las zonas que estan en la tabla zonas de la BD
	 */
	@FXML private ComboBox <Zona> cbZona;
	
	/**
	 * Lista de tipo ObservableList para rellenar el combobox Zonas
	 */
	private ObservableList <Zona> listaZonas;
	
	/**
	 * Desplegable donde mostramos las Actividades que estan en la tabla actividades de la BD
	 */
	@FXML private ComboBox <Actividad> cbActividad;
	
	/**
	 * Lista de tipo ObservableList para rellenar el combobox Actividades
	 */
	private ObservableList <Actividad> listaActividades;
	
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
	 * Método para crear un nuevo Industrial, se agrega a la BD a la tabla industriales
	 * 
	 * @param Event evento generado por el usuario
	 */
	@FXML public void guardarIndustrial (ActionEvent Event) {
		//creamos una nueva instancia de industrial
		Industrial ind = new Industrial(0,
				txtNombre.getText(),
				txtApellidos.getText(),
				txtEmail.getText(),
				txtTelefono.getText(),
				txtTelefono02.getText(),
				cbActividad.getSelectionModel().getSelectedItem(),
				txtEmpresa.getText(),
				txtLocalidad.getText(),
				cbZona.getSelectionModel().getSelectedItem(),
				txtComentarios.getText());
		
		//abrimos conexion con la BBDD
		miConexion.conectarBD();
		//llamamos al metodo guardar de la clase Industrial
		int resultado = ind.guardarIndustrial(miConexion.getConnection());
		//Cerramos la conexión con la BBDD
		miConexion.cerrarConexionBD();
		
		if(resultado == 1) {
			//Añadimos el industrial insertado en la BBDD al Tableview Industriales
			listaIndustriales.add(ind);
			
			etiEstado.setText("Industrial insertado correctamente");
		}else {
			etiEstado.setText("Industrial no insertado...");
		}
	}
	
	/**
	 * Método para actualizar un registro de la tabla industriales, se actualiza en la BD y se muestra
	 * en la tablaView Industriales.
	 * 
	 * @param Event, evento generado por el usuario
	 */
	@FXML public void actualizarIndustrial (ActionEvent Event) {
		
		//creamos una nueva instancia de industrial
		Industrial ind = new Industrial(
				Integer.valueOf(txtCodigoIndustrial.getText()),
				txtNombre.getText(),
				txtApellidos.getText(),
				txtEmail.getText(),
				txtTelefono.getText(),
				txtTelefono02.getText(),
				cbActividad.getSelectionModel().getSelectedItem(),
				txtEmpresa.getText(),
				txtLocalidad.getText(),
				cbZona.getSelectionModel().getSelectedItem(),
				txtComentarios.getText());
		
		//Abrimos la conexion con la BBDD
		miConexion.conectarBD();
		//llamamos al metodo actualizar de la clase Industrial		
		int resultado = ind.actualizarIndustrial(miConexion.getConnection());
		//Cerramos la conexion con la BBDD
		miConexion.cerrarConexionBD();
		
		if(resultado == 1) {
			//Añadimos el industrial actualizado en la BBDD al Tableview Industriales
			listaIndustriales.add(tablaIndustriales.getSelectionModel().getSelectedIndex(),ind);
			listaIndustriales.remove(tablaIndustriales.getSelectionModel().getSelectedIndex());
			
			etiEstado.setText("Industrial actualizado correctamente");
		}else {
			etiEstado.setText("No se ha podido actualizar el industrial...");
		}
		
	}
	
	/**
	 * Método para eliminar un registro de la tabla industriales de la BD
	 * 
	 * @param Event, evento generado por el usuario
	 */
	@FXML public void eliminarIndustrial (ActionEvent Event) {
		
		//Abrimos la conexion con la BBDD
		miConexion.conectarBD();
		//Eliminamos el industrial seleccionado en la tabla
		int resultado = tablaIndustriales.getSelectionModel().getSelectedItem().eliminarIndustrial(miConexion.getConnection());
		//Cerramos la conexion con la BBDD
		miConexion.cerrarConexionBD();
		
		if(resultado == 1) {
			//Eliminamos el industrial de la Tableview Industriales
			listaIndustriales.remove(tablaIndustriales.getSelectionModel().getSelectedIndex());
			
			etiEstado.setText("Industrial eliminado correctamente");
		}else {
			etiEstado.setText("No se ha podido eliminar el industrial...");
		}
	}
	
	/**
	 * Método que limpia los textfield para poder insertar un nuevo Industrial en la tabla industriales
	 * de la BD
	 * 
	 * @param Event, evento generado por el usuario
	 */
	@FXML public void nuevoIndustrial (ActionEvent Event) {
		txtCodigoIndustrial.setText(null);
		txtNombre.setText(null);
		txtApellidos.setText(null);
		txtEmail.setText(null);
		txtTelefono.setText(null);
		txtTelefono02.setText(null);
		txtEmpresa.setText(null);
		txtLocalidad.setText(null);
		txtComentarios.setText(null);
		cbActividad.setValue(null);
		cbZona.setValue(null);
		
		btnGuardar.setDisable(false);
		btnActualizar.setDisable(true);
		btnEliminar.setDisable(true);
	}
	
	/**
	 * Método para gestionar la selección de un registro en el tableview de Industriales
	 */
	public void gestionarEventos () {
		tablaIndustriales.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Industrial>() {

					@Override
					public void changed(ObservableValue<? extends Industrial> arg0, 
							Industrial industrialAnterior, Industrial industrialSeleccionado) {
						if(industrialSeleccionado!= null) {
							txtCodigoIndustrial.setText(String.valueOf(industrialSeleccionado.getCodigo_industrial()));
							txtNombre.setText(industrialSeleccionado.getNombre());
							txtApellidos.setText(industrialSeleccionado.getApellidos());
							txtEmail.setText(industrialSeleccionado.getEmail());
							txtTelefono.setText(industrialSeleccionado.getTelefono());
							txtTelefono02.setText(industrialSeleccionado.getTelefono02());
							txtEmpresa.setText(industrialSeleccionado.getEmpresa());
							txtLocalidad.setText(industrialSeleccionado.getLocalidad());
							txtComentarios.setText(industrialSeleccionado.getComentarios());
							cbActividad.setValue(industrialSeleccionado.getActividad());
							cbZona.setValue(industrialSeleccionado.getZona());
						
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
     * cargamos el tableview de Industriales, lanzamos el metodo que gestiona la seleccion de los diferentes
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
		
		//sleccionamos un industrial de la tabla
		gestionarEventos();
		
		//cerramos la conexion
		miConexion.cerrarConexionBD();
	}
}
