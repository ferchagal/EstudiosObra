/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: Panel_OferEst_Controller.java
 * Objetivo: Archivo Controller del Panel OferEst.
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controlador encargado de la gestión de la ventana principal del Panel de Ofertas
 * 
 * @author Fernando Chacón Galea
 * @version 2020.06.22 - V2
 *
 */
public class Panel_OferEst_Controller implements Initializable {
	
	/**
	 * Definimos el contenedor de los paneles de las Ofertas, se iran mostrando las diferentes opciones
	 * de la gestión de ofertas
	 */
	@FXML private AnchorPane contenidoOferta;
	
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
	 * Método que se ejecuta cuando pulsamos el botón "Inicio", y se muestra la pantalla de inicio 
	 * de la aplicación. Ejecuta el método cargarPanelInicio () que apunta al archivo xml que define la
	 * interfaz del panel.
	 * 
	 * @param event Evento generado por el usuario.
	 */
	@FXML private void cargarPanelInicio(ActionEvent event) {
		URL url = getClass().getResource("../vistas/VistaPrincipal.fxml");
		
		try {
			Node node = FXMLLoader.load(url);
			Scene scene = new Scene((Parent) node);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			appStage.setScene(scene);
			appStage.toFront();
			appStage.centerOnScreen();
			appStage.show();
								
		}catch(Exception ex) {
			ex.printStackTrace();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("INFORMACION RELEVANTE");
			alert.setHeaderText("NO se ha podido cargar el panel");
			alert.setContentText("Si no aparece la opción que has elegido, no pierdas la calma."
				+ " Cierra la aplicación y vuelve a entrar en ella.");
			alert.showAndWait();
		}
	}
	
	/**
	 * Método que se ejecuta cuando pulsamos el botón "Industriales", y se muestra la pantalla de 
	 * Gestión de Industriales de la aplicación. Ejecuta el método cargarPanel () que apunta al 
	 * archivo xml que define la interfaz del panel. 
	 * 
	 * @param event Evento generado por el usuario.
	 */
	@FXML private void cargarPanelConfigurar(ActionEvent event) {
		
			cargarPanel("Panel_Configurar.fxml");
		
	}
	
	/**
	 * Método que se ejecuta cuando pulsamos el botón "Estudios", y se muestra la pantalla de 
	 * Gestión de Estudios de la aplicación. Ejecuta el método cargarPanel () que apunta al 
	 * archivo xml que define la interfaz del panel. 
	 * 
	 * @param event Evento generado por el usuario.
	 */
	@FXML private void cargarPanelGestion(ActionEvent event) {
		
			cargarPanel("Panel_Gestion.fxml");
		
	}
	
	/**
	 * Método que se encarga de cargar el panel que se le pasa como parámetro.
	 * 
	 * @param panel cadena de texto que representa el nombre del archivo .xml que se debe cargar.
	 */
	private void cargarPanel(String panel) {
		
		
		
			contenidoOferta.getChildren().clear();		
		
			URL url = getClass().getResource("../vistas/"+panel);
		
			try {
				Node nodo = FXMLLoader.load(url);
				contenidoOferta.getChildren().add(nodo);
				
			
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
     * Método de inicialización del controlador, cargamos el panel de Gestión de ofertas del estudio
     * seleccionado.
     * 
     * @param url La ubicación utilizada para resolver rutas relativas para el objeto raíz, o
     * null si no se conoce la ubicación.
     * @param rb Los recursos utilizados para localizar el objeto raíz, o null si el objeto
     * raíz no se localizó.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//cargarPanel("Panel_Gestion.fxml");
		
	}

}
