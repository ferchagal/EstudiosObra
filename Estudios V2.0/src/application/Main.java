/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: Main.java
 * Objetivo: Clase principal de la Aplicación, contiene el método main().
 * Equipo/Persona: Fernando Chacón Galea  28.614.518 - B
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
 * Clase principal de la aplicación, lanza la aplicación.
 * 
 * @author Fernando Chacón Galea
 * @version 2020.06.22 - V2
 */
public class Main extends Application {
	
	/**
	 * variable booleana para reproducir la animación del principio una sola vez.
	 */
	public static Boolean presentacionCargada = false;
	
	/**
     * Método que inicia la ventana principal de la aplicación
     * 
     * @param stage Es la ventana principal (el escenario) creada por la aplicación en si y se
     * pasa como argumento el método start() de la clase Application.
     * 
     * @throws Exception El método start() puede lanzar una excepción de tipo Exception sino se
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
			primaryStage.setTitle("Identificación de Usuarios - Login");
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Método de entrada a toda la aplicación en Java
     * 
     * @param args Argumentos pasados por la linea de comandos
     */
	public static void main(String[] args) {
		launch(args);
	}
}
