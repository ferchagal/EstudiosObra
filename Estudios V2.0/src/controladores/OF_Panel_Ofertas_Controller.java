/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: OF_Panel_Ofertas_Controller.java
 * Objetivo: Archivo Controller del Panel Ofertas de la seccion de ofertas.
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import modelo.Conexion;
import modelo.Estudio;
import modelo.OfertasJdo;

/**
 * Clase controladora del Panel de Ofertas
 * 
 * @author Fernando Chacón Galea
 * @version 2020.06.22 - V2
 */
public class OF_Panel_Ofertas_Controller implements Initializable{
	/**
	 * Panel principal de la Vista
	 */
	@FXML AnchorPane panelOfertas;
	
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
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clCodigo_Industrial
	 */	
	@FXML private TableColumn <OfertasJdo,Integer> clCodigo_Industrial;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clNombre
	 */
	@FXML private TableColumn <OfertasJdo, String> clNombre;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clApellidos
	 */
	@FXML private TableColumn <OfertasJdo, String> clApellidos;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clEmail
	 */
	@FXML private TableColumn <OfertasJdo, String> clEmail;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clTelefono
	 */
	@FXML private TableColumn <OfertasJdo, String> clTelefono;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clActividad
	 */
	@FXML private TableColumn <OfertasJdo, String> clActividad;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clEmpresa
	 */
	@FXML private TableColumn <OfertasJdo, String> clEmpresa;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clSolicitada
	 */
	@FXML private TableColumn <OfertasJdo, String> clSolicitada;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clEstado
	 */
	@FXML private TableColumn <OfertasJdo, String> clEstado;
	
	/**
	 * TableColum donde indicamos a que atributo-campo del objeto-tabla de tipo Estudio
	 * se refiere la columna clComentarios
	 */
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
		
		tablaOfertas.getColumns().get(0).setVisible(false);
		tablaOfertas.getColumns().get(0).setVisible(true);
				
		//seleccionamos un industrial de la tabla
		gestionarEventos();
		
		//Cerramos la conexion
		miConexion.cerrarConexionBD();
		
		//Gestionamos el color de las filas según el valor de su estado
		tablaOfertas.setRowFactory(row -> new TableRow<OfertasJdo>() {
			@Override
			public void updateItem(OfertasJdo item, boolean empty) {
				super.updateItem(item, empty);
				if(item == null || item.getEstado() == null) {
					setStyle("");
			
				}else {
					if(item.getEstado().equalsIgnoreCase("Pendiente")) {
						this.setId("Pendiente");
					}
					if(item.getEstado().equalsIgnoreCase("Oferta Solicitada")) {
						this.setId("OfertaSolicitada");
					}
					if (item.getEstado().equalsIgnoreCase("Oferta Recibida")){
						this.setId("OfertaRecibida");
					}
					if(item.getEstado().equalsIgnoreCase("Oferta Rechazada")) {
						this.setId("OfertaRechazada");
					}
				}
			}
		});
		
	}
	
	/**
	 * Método para gestionar la seleccion de un registro en la tableView Ofertas
	 */
	private void gestionarEventos() {
				
		tablaOfertas.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<OfertasJdo>() {
				
					@Override
					public void changed(ObservableValue<? extends OfertasJdo> arg0, OfertasJdo indAnterior,
							OfertasJdo indSeleccionado) {
						if(indSeleccionado!= null) {
							txtCodigoIndustrial.setText(String.valueOf(indSeleccionado.getCodigo_industrial()));
							txtNombre.setText(indSeleccionado.getNombre());
							txtApellidos.setText(indSeleccionado.getApellidos());
							txtEmail.setText(indSeleccionado.getEmail());
							txtTelefono.setText(indSeleccionado.getTelefono());
							txtActividad.setText(indSeleccionado.getActividad());
							txtEmpresa.setText(indSeleccionado.getEmpresa());
							txtSolicitada.setText(indSeleccionado.getSolicitada());
							cbEstado.setValue(indSeleccionado.getEstado());
							txtComentarios.setText(indSeleccionado.getComentarios());
							
							//Controlamos los botones
							btnGuardar.setDisable(true);
							btnActualizar.setDisable(false);
							btnEliminar.setDisable(false);
							
						}
					}
				}
				
		);
		
	}
	
	//------------------- COMPONENTES  y MÉTODOS PARA LA GESTIÓN DE LAS OFERTAS ---------------------
	
	/**
	 * Etiqueta donde vamos mostrando las acciones que realizamos
	 */
	@FXML Label etiEstado;
	
	/**
	 * Botones para gestionar las acciones a realizar con los estudios
	 */
	@FXML Button btnGuardar, btnActualizar, btnEliminar;
	
	/**
	 * Campos donde recogemos los datos de los industriales
	 */
	@FXML TextField txtCodigoIndustrial, txtNombre, txtApellidos, txtEmail, txtEmpresa, txtActividad,
					txtTelefono,txtSolicitada, txtComentarios;
	
	/**
	 * ComboBox donde recogemos los Estados de las ofertas
	 */
	@FXML ComboBox <String> cbEstado;
	
	/**
	 * Lista de tipo ObservableList para rellenar el combobox Estados
	 */
	private ObservableList <String> listaEstados;
	
	/**
	 * Método para crear un nuevo Industrial, se agrega a la BD a la tabla Oferta seleccionada
	 * 
	 * @param Event evento generado por el usuario
	 */
	@FXML public void guardarIndustrial(ActionEvent Event) {
		//Primero evaluamos que todos los campos obligatorios (manda la BBDD y sus especificaciones
		//para cada tabla y sus columnas /atributos)
		if(txtNombre.getText().equals(null) ||
				txtTelefono.getText().equals(null) ||
				txtTelefono.getText().length() != 9 ||
				Character.isDigit(txtTelefono.getText().charAt(0)) == false ||
				Character.isDigit(txtTelefono.getText().charAt(1)) == false ||
				Character.isDigit(txtTelefono.getText().charAt(2)) == false ||
				Character.isDigit(txtTelefono.getText().charAt(3)) == false ||
				Character.isDigit(txtTelefono.getText().charAt(4)) == false ||
				Character.isDigit(txtTelefono.getText().charAt(5)) == false ||
				Character.isDigit(txtTelefono.getText().charAt(6)) == false ||
				Character.isDigit(txtTelefono.getText().charAt(7)) == false ||
				Character.isDigit(txtTelefono.getText().charAt(8)) == false ||
				txtActividad.getText().equals(null) ||
				txtSolicitada.getText().equals(null) ||
				cbEstado.getSelectionModel().getSelectedItem() == null)
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMACIÓN");
			alert.setHeaderText("Para poder guardar un Industrial nuevo:");
			alert.setContentText("No debes dejar ningún campo de texto obligatorio, vacío, "
					+ "recuerda que el teléfono debe tener 9 dígitos, solo 9 (nueve), además"
					+ " debes seleccionar un Estado de la oferta.");
			alert.showAndWait();
		}else {
			//Creamos una nueva instancia de OfertasJdo
			OfertasJdo ind = new OfertasJdo (0,
				txtNombre.getText(),
				txtApellidos.getText(),
				txtEmail.getText(),
				txtTelefono.getText(),
				txtActividad.getText(),
				txtEmpresa.getText(),
				txtSolicitada.getText(),
				cbEstado.getSelectionModel().getSelectedItem(),
				txtComentarios.getText());
		
			//abrimos conexion con la BD
			miConexion.conectarBD();
			//llamamos al metodo guardas de la clase OfertasJdo
			int resultado = ind.guardarIndustrial(miConexion.getConnection(), 
				cbEstudios.getSelectionModel().getSelectedItem());
			//Cerramos la conexion con la BD
			miConexion.cerrarConexionBD();
		
			if(resultado == 1) {
				//añadimos el industrial insertado a la tabla Ofertas
				listaOfertas.add(ind);
			
				etiEstado.setText("Industrial insertado correctamente al Estudio" +
								cbEstudios.getSelectionModel().getSelectedItem() + ".");
			
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMACIÓN");
				alert.setHeaderText("Atención, el industrial se ha añadido al Estudio, pero...");
				alert.setContentText("Si quieres añadirlo a la Base de Datos de la empresa "
						+ "recuerda que deberás añadirlo en el Panel de 'Gestión de Industriales'.");
				alert.showAndWait();
			
			}else {
				etiEstado.setText("Industrial no insertado...");
			}
		}
	}
	
	/**
	 * Método para actualizar un registro de la tabla industriales, se actualiza en la BD y se muestra
	 * en la tablaView Ofertas.
	 * 
	 * @param Event, evento generado por el usuario
	 */
	@FXML public void actualizarIndustrial(ActionEvent Event) {
		//Creamos una nueva instancia de OfertasJdo
				OfertasJdo ind = new OfertasJdo (
						Integer.valueOf(txtCodigoIndustrial.getText()),
						txtNombre.getText(),
						txtApellidos.getText(),
						txtEmail.getText(),
						txtTelefono.getText(),
						txtActividad.getText(),
						txtEmpresa.getText(),
						txtSolicitada.getText(),
						cbEstado.getSelectionModel().getSelectedItem(),
						txtComentarios.getText());
				
				//abrimos conexion con la BD
				miConexion.conectarBD();
				
				//Guardamos el nombre de la tabla en un String
				String nombreTabla = cbEstudios.getSelectionModel().getSelectedItem();
				//llamamos al metodo guardas de la clase OfertasJdo
				int resultado = ind.actualizarIndustrial(miConexion.getConnection(), 
						nombreTabla);
				//Cerramos la conexion con la BD
				miConexion.cerrarConexionBD();
				
				if(resultado == 1) {
					//Añadimos el industrial actualizado en la Tabla Estudio
					listaOfertas.add(tablaOfertas.getSelectionModel().getSelectedIndex(), ind);
					listaOfertas.remove(tablaOfertas.getSelectionModel().getSelectedIndex());
					
					etiEstado.setText("Industrial actualizado correctamente");
				}else {
					
					etiEstado.setText("No se ha podido actualizar el industrial...");
				}
	}
	
	/**
	 * Método para eliminar un industrial de la tabla Oferta de la BD
	 * 
	 * @param Event, evento generado por el usuario
	 */
	@FXML public void eliminarIndustrial(ActionEvent Event) {
		
		//Abrimos la conexion con la BD
		miConexion.conectarBD();
		//Eliminamos de la tabla el industrial seleccionado
		int resultado = tablaOfertas.getSelectionModel().getSelectedItem().eliminarIndustrial(
				miConexion.getConnection(), cbEstudios.getSelectionModel().getSelectedItem()
				);
		//Cerramos la conexión con la BD
		miConexion.cerrarConexionBD();
	
		if(resultado == 1) {
			//Eliminamos el industrial de la tabla
			listaOfertas.remove(tablaOfertas.getSelectionModel().getSelectedIndex());
			
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
	@FXML public void nuevoIndustrial(ActionEvent Event) {
		txtCodigoIndustrial.setText(null);
		txtNombre.setText(null);
		txtApellidos.setText(null);
		txtEmail.setText(null);
		txtEmpresa.setText(null);
		txtActividad.setText(null);
		txtTelefono.setText(null);
		txtComentarios.setText(null);
		txtSolicitada.setText(null);
		cbEstado.setValue(null);
		
		btnGuardar.setDisable(false);
		btnActualizar.setDisable(true);
		btnEliminar.setDisable(true);
		
		etiEstado.setText("Esperando acción...");
		
	}	
	//--------------------------------------------------------------------------------------------FIN

	/**
     * Método de inicialización del controlador, iniciamos la conexion con la BD, cargamos los comboBox,
     * y volvemos a cerrar la conexion con la BD
     * 
     * @param url La ubicación utilizada para resolver rutas relativas para el objeto raíz, o nul
     * si no se conoce la ubicación.
     * @param rb Los recursos utilizados para localizar el objeto raíz, o null si el objeto raíz
     * no se localizó
     */	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Aplicamos estilos CSS
		panelOfertas.getStylesheets().add(getClass().getResource("../estilos/EstilosPorDefecto.css").toExternalForm());
		
		//Conectamos con la BD
		miConexion = new Conexion();
		miConexion.conectarBD();
				
		//Iniciamos los ObservableList 
		listaEstudios = FXCollections.observableArrayList();
		listaEstados = FXCollections.observableArrayList();	
				
		//Llenamos el comboBox
		Estudio.datosComboEstudios(miConexion.getConnection(), listaEstudios);
		
		//Llenamos la listaEstados
		listaEstados.add("Pendiente");
		listaEstados.add("Oferta Solicitada");
		listaEstados.add("Oferta Recibida");
		listaEstados.add("Oferta Rechazada");
				
		//Añadimos los objetos a los comboBox
		cbEstudios.setItems(listaEstudios);
		cbEstado.setItems(listaEstados);
				
		//Cerramos la conexion
		miConexion.cerrarConexionBD();
		
	}
}
