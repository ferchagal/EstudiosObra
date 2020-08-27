/**
 * M�dulo: Estudios de Obra. Aplicaci�n de escritorio
 * Archivo: OF_Panel_Inicio_Controller.java
 * Objetivo: Archivo Controller del Panel Inicio de la seccion de ofertas.
 * Equipo/Persona: Fernando Chac�n Galea  28.614.518 - B
 */
package controladores;

import java.net.URL;
import java.sql.SQLSyntaxErrorException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import modelo.Conexion;
import modelo.Estudio;
import modelo.OfertasJdo;

/**
 * Clase controladora del Panel Inico de la secci�n de ofertas
 * 
 * @author Fernando Chac�n Galea
 * @version 2020.06.22 - V2
 */
public class OF_Panel_Inicio_Controller implements Initializable{
	
	/**
	 * Panel principal de la vista
	 */
	@FXML private AnchorPane panelInicio;
	
	/**
	 * Panel de la leyenda
	 */
	@FXML private AnchorPane panelLeyenda;
		
	/**
	 * Desplegable donde mostramos los estudios que est�n en la tabla estudios de la BD
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
	 * M�todo para mostrar los industriales contenidos en el estudio que seleccionemos en el comboBox
	 * cbEstudios.
	 */
	@FXML private void cargarListaEstudio() throws SQLSyntaxErrorException {
		
			//Creamos un string con el nombre de la tabla de la que queremos mostrar los industriales
			String nombreTabla = cbEstudios.getSelectionModel().getSelectedItem();
		
			//Conectamos con la BD
			miConexion = new Conexion();
			miConexion.conectarBD();
		
			//Inicializamos el ObservableList
			listaOfertas = FXCollections.observableArrayList();
			//Llenamos el TableView
			OfertasJdo.datosTablaOfertasJdo(miConexion.getConnection(), listaOfertas, nombreTabla);
			//A�adimos los objetos a la tabla
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
		
			//Gestionamos el color de las filas seg�n el valor de su estado
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
		
			panelLeyenda.setVisible(true);
			
	}

	/**
     * M�todo de inicializaci�n del controlador, iniciamos la conexion con la BD, cargamos el comboBox,
     * cargamos el tableview de Ofertas, lanzamos el metodo que gestiona la seleccion de los diferentes
     * industriales del tableview, y volvemos a cerrar la conexion con la BD
     * 
     * @param url La ubicaci�n utilizada para resolver rutas relativas para el objeto ra�z, o nul
     * si no se conoce la ubicaci�n.
     * @param rb Los recursos utilizados para localizar el objeto ra�z, o null si el objeto ra�z
     * no se localiz�
     */	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Aplicamos estilos CSS
		panelInicio.getStylesheets().add(getClass().getResource("../estilos/EstilosPorDefecto.css").toExternalForm());
				
		//Conectamos con la BD
		miConexion = new Conexion();
		miConexion.conectarBD();
		
		//Iniciamos los ObservableList 
		listaEstudios = FXCollections.observableArrayList();
		
		//Llenamos el comboBox
		Estudio.datosComboEstudios(miConexion.getConnection(), listaEstudios);
		
		//A�adimos los objetos al comboBox
		cbEstudios.setItems(listaEstudios);
		
		//Cerramos la conexion
		miConexion.cerrarConexionBD();			
	}
}
