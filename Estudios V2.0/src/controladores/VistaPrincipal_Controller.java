/**
 * M�dulo: Estudios de Obra. Aplicaci�n de escritorio
 * Archivo: VistaPrincipal_Controller.java
 * Objetivo: Archivo Controller de la Vista Principal.
 * Equipo/Persona: Fernando Chac�n Galea  28.614.518 - B
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
 * Controlador encargado de la gesti�n de la ventana principal
 * 
 * @author Fernando Chacon Galea
 * @version 2020.06.22 - V2.0
 */
public class VistaPrincipal_Controller implements Initializable {
	/**
     * Definimos el contenedor de toda la aplicaci�n, de tipo AnchorPane, y refenciado a nuestra
     * interfaz definida en el archivo VistaPrincipal.fxml mediante el atributo @FXML.
     */
	@FXML protected AnchorPane contenido;
	
	/**
     * M�todo que se ejecuta cuando seleccionamos en el men� "Archivo", "Salir", se cierra la 
     * aplicaci�n
     * 
     * @param event Evento generado por el usuario. 
     */
	@FXML private void salirAplicacion(ActionEvent event) {
		System.exit(0);
	}
	
	/**
     * M�todo que se ejecuta cuando seleccionamos en el men� "Ayuda", "Acerca de ...", nos muestra
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
	 * M�todo que se ejecuta cuando pulsamos el bot�n "Inicio", y se muestra la pantalla de inicio 
	 * de la aplicaci�n. Ejecuta el m�todo cargarPanel () que apunta al archivo xml que define la
	 * interfaz del panel.
	 * 
	 * @param event Evento generado por el usuario.
	 */
	@FXML private void cargarPanelInicio(ActionEvent event) {
		cargarPanel("Panel_Inicio.fxml");
	}
	
	/**
	 * M�todo que se ejecuta cuando pulsamos el bot�n "Industriales", y se muestra la pantalla de 
	 * Gesti�n de Industriales de la aplicaci�n. Ejecuta el m�todo cargarPanel () que apunta al 
	 * archivo xml que define la interfaz del panel. 
	 * 
	 * @param event Evento generado por el usuario.
	 */
	@FXML private void cargarPanelIndustriales(ActionEvent event) {
		
			cargarPanel("Panel_Industriales.fxml");
		
	}
	
	/**
	 * M�todo que se ejecuta cuando pulsamos el bot�n "Estudios", y se muestra la pantalla de 
	 * Gesti�n de Estudios de la aplicaci�n. Ejecuta el m�todo cargarPanel () que apunta al 
	 * archivo xml que define la interfaz del panel. 
	 * 
	 * @param event Evento generado por el usuario.
	 */
	@FXML private void cargarPanelEstudios(ActionEvent event) {
		
			cargarPanel("Panel_Estudios.fxml");
		
	}
	
	/**
	 * M�todo que se ejecuta cuando pulsamos el bot�n "Industriales" en la secci�n de configuraci�n,
	 * y se muestra la pantalla de configuraci�n de Industriales de la aplicaci�n. Ejecuta el m�todo
	 * cargarPanel () que apunta al archivo xml que define la interfaz del panel.
	 * 
	 * @param event Evento generado por el usuario.
	 */
	@FXML private void cargarPanelConfigIndustriales(ActionEvent event) {
		cargarPanel("Configuracion_Industriales.fxml");
	}
	
	/**
	 * M�todo que se ejecuta cuando pulsamos el bot�n "Estudios" en la secci�n de configuraci�n,
	 * y se muestra la pantalla de configuraci�n de Estudios de la aplicaci�n. Ejecuta el m�todo
	 * cargarPanel () que apunta al archivo xml que define la interfaz del panel.
	 * 
	 * @param event Evento generado por el usuario.
	 */
	@FXML private void cargarPanelConfigEstudios(ActionEvent event) {
		cargarPanel("Configuracion_Estudios.fxml");
	}
	
	/**
	 * M�todo que se encarga de cargar el panel que se le pasa como par�metro.
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
				alert.setContentText("Si no aparece la opci�n que has elegido, no pierdas la calma."
					+ " Cierra la aplicaci�n y vuelve a entrar en ella.");
				alert.showAndWait();*/
				ex.printStackTrace();
			}
		
		
		
	}
	
	

	/**
     * M�todo de inicializaci�n del controlador, cargamos el panel de Inicio.
     * 
     * @param url La ubicaci�n utilizada para resolver rutas relativas para el objeto ra�z, o
     * null si no se conoce la ubicaci�n.
     * @param rb Los recursos utilizados para localizar el objeto ra�z, o null si el objeto
     * ra�z no se localiz�.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cargarPanel("Panel_Inicio.fxml");
		
		
		
		
		
	}
	
}
