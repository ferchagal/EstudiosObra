/**
 * M�dulo: Estudios de Obra. Aplicaci�n de escritorio
 * Archivo: Main.java
 * Objetivo: Clase principal de la Aplicaci�n, contiene el m�todo main().
 * Equipo/Persona: Fernando Chac�n Galea  28.614.518 - B
 */
package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

/**
 * Clase principal de la aplicaci�n, lanza la aplicaci�n.
 * 
 * @author Fernando Chac�n Galea
 * @version 2020.06.22 - V2
 */
public class Main extends Application {
	
	/**
	 * variable booleana para reproducir la animaci�n del principio una sola vez.
	 */
	public static Boolean presentacionCargada = false;
	
	/**
     * M�todo que inicia la ventana principal de la aplicaci�n
     * 
     * @param stage Es la ventana principal (el escenario) creada por la aplicaci�n en si y se
     * pasa como argumento el m�todo start() de la clase Application.
     * 
     * @throws Exception El m�todo start() puede lanzar una excepci�n de tipo Exception sino se
     * crea correctamente la ventana. 
     */
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../vistas/Login.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("/imagenes/logoCono.png"));
			primaryStage.setTitle("Identificaci�n de Usuarios - Login");
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * M�todo de entrada a toda la aplicaci�n en Java
     * 
     * @param args Argumentos pasados por la linea de comandos
     */
	public static void main(String[] args) {
		launch(args);
	}
}
