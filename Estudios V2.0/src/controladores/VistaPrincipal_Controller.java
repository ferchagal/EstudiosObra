/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: VistaPrincipal_Controller.java
 * Objetivo: Archivo Controller de la Vista Principal.
 * Equipo/Persona: Fernando Chacón Galea  28.614.518 - B
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

//import controladores.Config_IndustrialesController.txtDriverBD;

/**
 * Controlador encargado de la gestión de la ventana principal
 * 
 * @author Fernando Chacon Galea
 * @version 2020.06.22 - V2.0
 */
public class VistaPrincipal_Controller implements Initializable {
	/**
     * Definimos el contenedor de toda la aplicación, de tipo AnchorPane, y refenciado a nuestra
     * interfaz definida en el archivo VistaPrincipal.fxml mediante el atributo @FXML.
     */
	@FXML protected AnchorPane contenido;
	
	/**
     * Método que se ejecuta cuando seleccionamos en el menú "Archivo", "Salir", se cierra la 
     * aplicación
     * 
     * @param event Evento generado por el usuario. 
     */
	@FXML private void salirAplicacion(ActionEvent event) {
		System.exit(0);
	}
	
	/**
     * Método que se ejecuta cuando seleccionamos en el menú "Ayuda", "Acerca de ...", nos muestra
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
	 * Método que se ejecuta cuando pulsamos el botón "Inicio", y se muestra la pantalla de inicio 
	 * de la aplicación. Ejecuta el método cargarPanel () que apunta al archivo xml que define la
	 * interfaz del panel.
	 * 
	 * @param event Evento generado por el usuario.
	 */
	@FXML private void cargarPanelInicio(ActionEvent event) {
		cargarPanel("Panel_Inicio.fxml");
	}
	
	/**
	 * Método que se ejecuta cuando pulsamos el botón "Industriales", y se muestra la pantalla de 
	 * Gestión de Industriales de la aplicación. Ejecuta el método cargarPanel () que apunta al 
	 * archivo xml que define la interfaz del panel. 
	 * 
	 * @param event Evento generado por el usuario.
	 */
	@FXML private void cargarPanelIndustriales(ActionEvent event) {
		
			cargarPanel("Panel_Industriales.fxml");
		
	}
	
	/**
	 * Método que se ejecuta cuando pulsamos el botón "Estudios", y se muestra la pantalla de 
	 * Gestión de Estudios de la aplicación. Ejecuta el método cargarPanel () que apunta al 
	 * archivo xml que define la interfaz del panel. 
	 * 
	 * @param event Evento generado por el usuario.
	 */
	@FXML private void cargarPanelEstudios(ActionEvent event) {
		
			cargarPanel("Panel_Estudios.fxml");
		
	}
	
	/**
	 * Método que se ejecuta cuando pulsamos el botón "Industriales" en la sección de configuración,
	 * y se muestra la pantalla de configuración de Industriales de la aplicación. Ejecuta el método
	 * cargarPanel () que apunta al archivo xml que define la interfaz del panel.
	 * 
	 * @param event Evento generado por el usuario.
	 */
	@FXML private void cargarPanelConfigIndustriales(ActionEvent event) {
		cargarPanel("Configuracion_Industriales.fxml");
	}
	
	/**
	 * Método que se ejecuta cuando pulsamos el botón "Estudios" en la sección de configuración,
	 * y se muestra la pantalla de configuración de Estudios de la aplicación. Ejecuta el método
	 * cargarPanel () que apunta al archivo xml que define la interfaz del panel.
	 * 
	 * @param event Evento generado por el usuario.
	 */
	@FXML private void cargarPanelConfigEstudios(ActionEvent event) {
		cargarPanel("Configuracion_Estudios.fxml");
	}
	
	/**
	 * Método que se encarga de cargar el panel que se le pasa como parámetro.
	 * 
	 * @param panel cadena de texto que representa el nombre del archivo .xml que se debe cargar.
	 */
	private void cargarPanel(String panel) {
		
		
		
			contenido.getChildren().clear();		
		
			URL url = getClass().getResource("../vistas/"+panel);
		
			try {
				Node nodo = FXMLLoader.load(url);
				contenido.getChildren().add(nodo);
				
			
			}catch(Exception ex) {
				/*Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("INFORMACION RELEVANTE");
				alert.setHeaderText("NO se ha podido cargar el panel");
				alert.setContentText("Si no aparece la opción que has elegido, no pierdas la calma."
					+ " Cierra la aplicación y vuelve a entrar en ella.");
				alert.showAndWait();*/
				ex.printStackTrace();
			}
		
		
		
	}
	
	

	/**
     * Método de inicialización del controlador, cargamos el panel de Inicio.
     * 
     * @param url La ubicación utilizada para resolver rutas relativas para el objeto raíz, o
     * null si no se conoce la ubicación.
     * @param rb Los recursos utilizados para localizar el objeto raíz, o null si el objeto
     * raíz no se localizó.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cargarPanel("Panel_Inicio.fxml");
		
		
		
		
		
	}
	
}
