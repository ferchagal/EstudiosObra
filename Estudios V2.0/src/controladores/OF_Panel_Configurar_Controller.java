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
import javafx.scene.control.CheckBox;
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
	 * ArrayList donde almacenamos los checkBox seleccionados en forma de concatenación de
	 * Strings.
	 */
	private ArrayList <String> chbSeleccionados;
	
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
	 * Método lanzado al pulsar sobre el botón "Previsualizar selección", nos muestra en la tabla
	 * los industriales que hemos filtrado a traves del combobox y los checkbox
	 * 
	 * @param event, evento generado por el usuario
	 */
	@FXML private void cargarIndustriales(ActionEvent event) {
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
		
		//con un bucle for mejorado vamos creando el string actividades
		for(int i=0; i<chbSeleccionados.size(); i++) {
			actividades = actividades +" actividad = " + chbSeleccionados.get(i) + "OR";
			i++;
		}
		
		System.out.println(actividades);
				
		//Conectamos con la BD
		miConexion = new Conexion();
		miConexion.conectarBD();
				
		//Iniciamos el ObservableList de la tabla
		listaIndustriales = FXCollections.observableArrayList();
						
		//Llenamos el tableview
		Industrial.datosTablaIndustriales(miConexion.getConnection(), listaIndustriales);
						
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
