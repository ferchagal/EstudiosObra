/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: OF_Panel_Configurar_Controller.java
 * Objetivo: Archivo Controller del Panel Configurar de la seccion de ofertas.
 * Equipo/Persona: Fernando Chacón Galea  28.614.518 - B
 */
package controladores;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import modelo.Actividad;
import modelo.Conexion;
import modelo.Estudio;
import modelo.Industrial;
import modelo.OfertasJdo;
import modelo.Zona;

/**
 * Clase Controladora del la vista "Configurar Estudio"
 * 
 * @author Fernando Chacón Galea
 * @version 2020.06.22 - V2
 */
public class OF_Panel_Configurar_Controller implements Initializable {
	
	/**
	 * ArrayList donde almacenamos los checkBox seleccionados en forma de concatenación de
	 * Strings.
	 */
	private ArrayList <String> chbSeleccionados;
	
	@FXML Label etiEstado;
	
	/**
	 * CheckBox donde recogemos las actividades que existen en el Estudio
	 */
	@FXML private CheckBox chb_acerosCorrugados, chb_aislamientos, chb_alicatadoresSoladores, 
				chb_alquilerMaquinaria, chb_ascensores, chb_asfaltos, chb_automatismos,
				chb_bombasHormigon, chb_carpFenolica, chb_carpMadera, chb_carpMetalica,
				chb_carpRF, chb_cimentacEspeciales, chb_contenedEscombros, chb_cubiertas,
				chb_demoliciones, chb_desatascos, chb_direccionFacultativa, chb_ensayos,
				chb_especiales, chb_estrucHormigon, chb_estrucMetalicas, chb_ferreteria,
				chb_gruasMoviles, chb_gruasTorre, chb_hormigonPoroso, chb_hormigonPulido,
				chb_hormigones, chb_ignifugados,chb_impermeabilizaciones, chb_instAireComprimido,
				chb_instClimatizacion, chb_instElectricidad, chb_instFontaneria, chb_instGas,
				chb_instGasesEscape, chb_limpieza, chb_mamparas, chb_manoObra, chb_montPlacasAlveolares,
				chb_morteros, chb_movimientoTierras, chb_panelesArquitectonicos, chb_pavimentosEspeciales,
				chb_perfCorteHormigon, chb_piedrasNaturales, chb_pintores, chb_piscinas, chb_polveros,
				chb_prefHormigon, chb_prl, chb_revCeramicos, chb_revestimientos, chb_rotulosSenyalitica,
				chb_sanitarios, chb_senyalizacionVial, chb_sueloTecnico, chb_tabGranFormato,
				chb_terrazosContinuos, chb_toldos, chb_trabajosVerticales, chb_urbanizadoras,
				chb_vidrios, chb_vinilos, chb_yesoLaminado;
	
	/**
	 * Button para añadir industriales al estudio seleccionado en el comboBox Estudios, lo
	 * iniciamos para poder desHabilitarlo en el inicio del panel, y habilitarlo cuando
	 * mostramos Industriales filtrados para añadir en el estudio.
	 */
	@FXML private Button btnAddIndustriales;
	
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
	 * Método lanzado al pulsar sobre el botón "Aplicar Filtros", nos muestra en la tabla
	 * los industriales que hemos filtrado a traves del combobox y los checkbox
	 * 
	 * @param event, evento generado por el usuario
	 */
	@FXML public void cargarIndustriales(ActionEvent event) {
		if(cbEstudios.getSelectionModel().getSelectedItem() == null ||
				cbZona.getSelectionModel().getSelectedItem() == null) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMACIÓN");
			alert.setHeaderText("Para poder guardar industriales en un Estudio:");
			alert.setContentText("Debes seleccionar el Estudio, la zona geográfica de la obra y"
					+ " las Actividades de los Industriales a incluir en el Estudio.");
			alert.showAndWait();
			
		}else {
			//creamos el string actividades donde recogemos todos los checkbox seleccionados
			String actividades = crearStringComboBoxSelec ();
			//creamos el string donde recogemos la zona seleccionada en el combobox
			String zona = cbZona.getSelectionModel().getSelectedItem().getZona()+"'";
				
			//Conectamos con la BD
			miConexion = new Conexion();
			miConexion.conectarBD();
				
			//Iniciamos el ObservableList de la tabla
			listaIndustriales = FXCollections.observableArrayList();
						
			//Llenamos el tableview con los Industriales filtrados
			Industrial.datosTablaIndustrialesEstudio(miConexion.getConnection(), listaIndustriales, actividades,
				zona);
						
			//Añadimos los objetos al tableview
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
		
			//Habilitamos el boton btnAddIndustriales
			btnAddIndustriales.setDisable(false);
		
		}
	}
	
	/**
	 * Método para limpiar los filtros apliclados, limpiamos la zona de estudio y los checkBox seleccionados,
	 * Volvemos a mostrar todos los industriales en la tabla
	 * 
	 * @param event, evento generado por el usuario
	 */
	@FXML public void limpiarFiltros (ActionEvent event) {
		
		//Reseteamos el ComboBox
		cbZona.getSelectionModel().clearSelection();
		
		//Reseteamos los checkBox
		chb_acerosCorrugados.setSelected(false);
		chb_aislamientos.setSelected(false);
		chb_alicatadoresSoladores.setSelected(false); 
		chb_alquilerMaquinaria.setSelected(false);
		chb_ascensores.setSelected(false);
		chb_asfaltos.setSelected(false);
		chb_automatismos.setSelected(false);
		chb_bombasHormigon.setSelected(false);
		chb_carpFenolica.setSelected(false);
		chb_carpMadera.setSelected(false);
		chb_carpMetalica.setSelected(false);
		chb_carpRF.setSelected(false);
		chb_cimentacEspeciales.setSelected(false);
		chb_contenedEscombros.setSelected(false);
		chb_cubiertas.setSelected(false);
		chb_demoliciones.setSelected(false);
		chb_desatascos.setSelected(false);
		chb_direccionFacultativa.setSelected(false);
		chb_ensayos.setSelected(false);
		chb_especiales.setSelected(false);
		chb_estrucHormigon.setSelected(false);
		chb_estrucMetalicas.setSelected(false);
		chb_ferreteria.setSelected(false);
		chb_gruasMoviles.setSelected(false);
		chb_gruasTorre.setSelected(false);
		chb_hormigonPoroso.setSelected(false);
		chb_hormigonPulido.setSelected(false);
		chb_hormigones.setSelected(false);
		chb_ignifugados.setSelected(false);
		chb_impermeabilizaciones.setSelected(false);
		chb_instAireComprimido.setSelected(false);
		chb_instClimatizacion.setSelected(false);
		chb_instElectricidad.setSelected(false);
		chb_instFontaneria.setSelected(false);
		chb_instGas.setSelected(false);
		chb_instGasesEscape.setSelected(false);
		chb_limpieza.setSelected(false);
		chb_mamparas.setSelected(false);
		chb_manoObra.setSelected(false);
		chb_montPlacasAlveolares.setSelected(false);
		chb_morteros.setSelected(false);
		chb_movimientoTierras.setSelected(false);
		chb_panelesArquitectonicos.setSelected(false);
		chb_pavimentosEspeciales.setSelected(false);
		chb_perfCorteHormigon.setSelected(false);
		chb_piedrasNaturales.setSelected(false);
		chb_pintores.setSelected(false);
		chb_piscinas.setSelected(false);
		chb_polveros.setSelected(false);
		chb_prefHormigon.setSelected(false);
		chb_prl.setSelected(false);
		chb_revCeramicos.setSelected(false);
		chb_revestimientos.setSelected(false);
		chb_rotulosSenyalitica.setSelected(false);
		chb_sanitarios.setSelected(false);
		chb_senyalizacionVial.setSelected(false);
		chb_sueloTecnico.setSelected(false);
		chb_tabGranFormato.setSelected(false);
		chb_terrazosContinuos.setSelected(false);
		chb_toldos.setSelected(false);
		chb_trabajosVerticales.setSelected(false);
		chb_urbanizadoras.setSelected(false);
		chb_vidrios.setSelected(false);
		chb_vinilos.setSelected(false);
		chb_yesoLaminado.setSelected(false);
		
		//Reiniciamos el tableview
		//Conectamos con la BD
		miConexion = new Conexion();
		miConexion.conectarBD();
				
		//Iniciamos los ObservableList 
		listaIndustriales = FXCollections.observableArrayList();
						
		//Llenamos el tableView
		Industrial.datosTablaIndustriales(miConexion.getConnection(), listaIndustriales);
						
		//Añadimos los objetos al tableView
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
		
		//deshabilitamos el boton btnAddIndustriales
		btnAddIndustriales.setDisable(true);
		
		//limpiamos la etiqueta de estado
		etiEstado.setText("");
		
	}
	
	/**
	 * Método para añadir los industriales al estudio seleccionado en el comboBox cbEstudios
	 * 
	 * @param Event, evento generado por el usuario
	 */
	@FXML public void addIndustriales (ActionEvent Event) {
		
		//creamos el string actividades donde recogemos todos los checkbox seleccionados
		String actividades = crearStringComboBoxSelec ();
		//creamos el string donde recogemos la zona seleccionada en el combobox
		String zona = cbZona.getSelectionModel().getSelectedItem().getZona()+"'";
		//creamos un string con el nombre de la tabla a crear
		String nombreTabla = cbEstudios.getSelectionModel().getSelectedItem();
		//creamos un int donde recogemos los industriales que se han insertado en la tabla
		int industrialesInsertados;
		
		//Conectamos con la BD
		miConexion = new Conexion();
		miConexion.conectarBD();
		
		//Creamos la tabla donde vamos a insertar los industriales filtrados
		OfertasJdo.crearTabla(nombreTabla, miConexion.getConnection());		
		
		/* Llamamos al método estático datosTablaIndustrialesEstudio() de la clase OfertasJdo para guardar
		 *  los industriales filtrados en la tabla creada anteriormente. Le pasamos como parametros
		 *  un objeto conexion de tipo Connection, un string con el nombre de la tabla, un string
		 *  actividades donde recogemos todos los checkbox seleccionados, un string zona donde recogemos
		 *  la zona seleccionada en el comboBox  */
		industrialesInsertados = OfertasJdo.datosTablaIndustrialesEstudio(miConexion.getConnection(),
				nombreTabla, actividades, zona);
				
		//Cerramos la conexion con la BD
		miConexion.cerrarConexionBD();
		
		//Creamos un mensanje con las acciones realizadas
		if (industrialesInsertados == 0) {
			etiEstado.setTextFill(Color.RED);
			etiEstado.setText("No se ha insertado ningún industrial en el Estudio");
		}else {
			etiEstado.setText("Se han insertado " + industrialesInsertados + " Industriales en el Estudio " +
		nombreTabla);
		}
	}
	
	/**
	 * Método que recoge los comboBox que estan seleccionados y crea un String que lo añadiremos a
	 * la consulta a la BD para que nos muestre los industriales solictados en los filtros.
	 * 
	 * @return una cadena de texto.
	 */
	private String crearStringComboBoxSelec () {
		//Iniciamos el Arraylist donde guardaremos los checkBox Seleccionados
		chbSeleccionados = new ArrayList<String>();
				
		//Llenamos el ArrayList segun tengamos seleccionados los checkBox
		if(chb_acerosCorrugados.isSelected()) chbSeleccionados.add("Aceros Corrugados");
		if(chb_aislamientos.isSelected()) chbSeleccionados.add("Aislamientos");
		if(chb_alicatadoresSoladores.isSelected()) chbSeleccionados.add("Alicatadores - Soladores"); 
		if(chb_alquilerMaquinaria.isSelected()) chbSeleccionados.add("Alquiler de Maquinaria");
		if(chb_ascensores.isSelected()) chbSeleccionados.add("Ascensores");
		if(chb_asfaltos.isSelected()) chbSeleccionados.add("Asfaltos");
		if(chb_automatismos.isSelected()) chbSeleccionados.add("Automatismos");
		if(chb_bombasHormigon.isSelected()) chbSeleccionados.add("Bombas de Hormigón");
		if(chb_carpFenolica.isSelected()) chbSeleccionados.add("Carp. Fenólica");
		if(chb_carpMadera.isSelected()) chbSeleccionados.add("Carp. Madera");
		if(chb_carpMetalica.isSelected()) chbSeleccionados.add("Carp. Metálica");
		if(chb_carpRF.isSelected()) chbSeleccionados.add("Carp. RF");
		if(chb_cimentacEspeciales.isSelected()) chbSeleccionados.add("Cimentaciones Especiales");
		if(chb_contenedEscombros.isSelected()) chbSeleccionados.add("Contenedores Escombros");
		if(chb_cubiertas.isSelected()) chbSeleccionados.add("Cubiertas");
		if(chb_demoliciones.isSelected()) chbSeleccionados.add("Demoliciones");
		if(chb_desatascos.isSelected()) chbSeleccionados.add("Desatascos");
		if(chb_direccionFacultativa.isSelected()) chbSeleccionados.add("Dirección Facultativa");
		if(chb_ensayos.isSelected()) chbSeleccionados.add("Ensayos");
		if(chb_especiales.isSelected()) chbSeleccionados.add("Especiales");
		if(chb_estrucHormigon.isSelected()) chbSeleccionados.add("Estructuras de Hormigón");
		if(chb_estrucMetalicas.isSelected()) chbSeleccionados.add("Estructuras Metálicas");
		if(chb_ferreteria.isSelected()) chbSeleccionados.add("Ferretería");
		if(chb_gruasMoviles.isSelected()) chbSeleccionados.add("Grúas Móviles");
		if(chb_gruasTorre.isSelected()) chbSeleccionados.add("Grúas Torre");
		if(chb_hormigonPoroso.isSelected()) chbSeleccionados.add("Hormigón Poroso");
		if(chb_hormigonPulido.isSelected()) chbSeleccionados.add("Hormigón Pulido");
		if(chb_hormigones.isSelected()) chbSeleccionados.add("Hormigones");
		if(chb_ignifugados.isSelected()) chbSeleccionados.add("Ignifugados");
		if(chb_impermeabilizaciones.isSelected()) chbSeleccionados.add("Impermeabilizaciones");
		if(chb_instAireComprimido.isSelected()) chbSeleccionados.add("Inst. Aire Comprimido");
		if(chb_instClimatizacion.isSelected()) chbSeleccionados.add("Inst. Climatización");
		if(chb_instElectricidad.isSelected()) chbSeleccionados.add("Inst. Electricidad");
		if(chb_instFontaneria.isSelected()) chbSeleccionados.add("Inst. Fontanería");
		if(chb_instGas.isSelected()) chbSeleccionados.add("Inst. Gas");
		if(chb_instGasesEscape.isSelected()) chbSeleccionados.add("Inst. Gases de Escape");
		if(chb_limpieza.isSelected()) chbSeleccionados.add("Limpieza");
		if(chb_mamparas.isSelected()) chbSeleccionados.add("Mamparas");
		if(chb_manoObra.isSelected()) chbSeleccionados.add("Mano de Obra");
		if(chb_montPlacasAlveolares.isSelected()) chbSeleccionados.add("Montadores Placas Alveolares");
		if(chb_morteros.isSelected()) chbSeleccionados.add("Morteros");
		if(chb_movimientoTierras.isSelected()) chbSeleccionados.add("Movimientos de Tierras");
		if(chb_panelesArquitectonicos.isSelected()) chbSeleccionados.add("Paneles Arquitectóonicos");
		if(chb_pavimentosEspeciales.isSelected()) chbSeleccionados.add("Pavimentos Especiales");
		if(chb_perfCorteHormigon.isSelected()) chbSeleccionados.add("Perforaciones y Corte de Hormigón");
		if(chb_piedrasNaturales.isSelected()) chbSeleccionados.add("Piedras Naturales");
		if(chb_pintores.isSelected()) chbSeleccionados.add("Pintores");
		if(chb_piscinas.isSelected()) chbSeleccionados.add("Piscinas");
		if(chb_polveros.isSelected()) chbSeleccionados.add("Polveros");
		if(chb_prefHormigon.isSelected()) chbSeleccionados.add("Prefabricados de Hormigón");
		if(chb_prl.isSelected()) chbSeleccionados.add("PRL");
		if(chb_revCeramicos.isSelected()) chbSeleccionados.add("Rev. Cerámicos");
		if(chb_revestimientos.isSelected()) chbSeleccionados.add("Revestimientos");
		if(chb_rotulosSenyalitica.isSelected()) chbSeleccionados.add("Rótulos y Señalitica");
		if(chb_sanitarios.isSelected()) chbSeleccionados.add("Sanitarios");
		if(chb_senyalizacionVial.isSelected()) chbSeleccionados.add("Señalización Vial");
		if(chb_sueloTecnico.isSelected()) chbSeleccionados.add("Suelo Técnico");
		if(chb_tabGranFormato.isSelected()) chbSeleccionados.add("Tab. Gran Formato");
		if(chb_terrazosContinuos.isSelected()) chbSeleccionados.add("Terrazos Continuos");
		if(chb_toldos.isSelected()) chbSeleccionados.add("Toldos");
		if(chb_trabajosVerticales.isSelected()) chbSeleccionados.add("Trabajos Verticales");
		if(chb_urbanizadoras.isSelected()) chbSeleccionados.add("Urbanizadoras");
		if(chb_vidrios.isSelected()) chbSeleccionados.add("Vidrios");
		if(chb_vinilos.isSelected()) chbSeleccionados.add("Vinilos");
		if(chb_yesoLaminado.isSelected()) chbSeleccionados.add("Yeso Laminado");
				
		//Creamos un String para guardar la concatenación de todos los CheckBox seleccionados
		String actividades ="";
				
		//con un bucle for vamos creando el string actividades
		for(int i=0; i<chbSeleccionados.size(); i++) {
			actividades = actividades +" actividad = '" + chbSeleccionados.get(i) +  "' OR";
			i++;
		}
		try {		
			//debemos quitar los dos últimos caracteres del string para que la consulta funcione
			actividades = actividades.substring(0, actividades.length()-2);	
		}catch(Exception ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMACIÓN");
			alert.setHeaderText("Para poder guardar industriales en un Estudio:");
			alert.setContentText("Debes seleccionar el Estudio, la zona geográfica de la obra y"
					+ " las Actividades de los Industriales a incluir en el Estudio.");
			alert.showAndWait();
		}
		return actividades;
	}
		
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
		
		//Deshabilitamos el boton btnAddIndustriales
		btnAddIndustriales.setDisable(true);
		
		//Iniciamos el Arraylist donde guardaremos los checkBox Seleccionados
		chbSeleccionados = new ArrayList<String>();
		
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
