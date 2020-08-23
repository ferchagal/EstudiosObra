/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: LoginController.java
 * Objetivo: Archivo Controller de la Vista Principal.
 * Equipo/Persona: Fernando Chacón Galea  28.614.518 - B
 */

package controladores;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Main;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelo.Conexion;
import javafx.scene.control.Alert.AlertType;

/**
 * 
 * @author Fernando Chacón Galea
 * @version 2020.06.22 - V2
 */
public class Login_Controller implements Initializable {
	/**
	 * Panel principal de la pantalla de login
	 */
	@FXML AnchorPane root;
	
	/**
	 * Campos de donde recogemos los datos de inicio de sesion
	 */
	@FXML TextField txtUsuario;
	@FXML PasswordField txtContrasena;
		
	/**
	 * Objeto de tipo Connection. Para configurar la conexión a la BBDD.
	 */
	private Conexion miConexion;
	
	/**
	 * Método que lanzamos haciendo click en el botón "Iniciar Sesión" comprueba que el usuario y 
	 * contraseña introducios son correctos.
	 *  
	 * @param Event evento generado por el usuario
	 */
	@FXML public void iniciarAplicacion (ActionEvent Event) {
		
		
		
		String usuario = txtUsuario.getText();
		String contrasena = txtContrasena.getText();
		
		if(usuario.length()>0 && contrasena.length()>0 ) {
			try {
				Statement sql = miConexion.getConnection().createStatement();
				String consulta = "SELECT * FROM usuarios WHERE usuario = '" + usuario
						+ "' AND password = '" + contrasena+"'";
							
				ResultSet rs = sql.executeQuery(consulta);
			
				if(!rs.next()) {
					Alert alerta = new Alert(AlertType.INFORMATION,"Ningún Usuario encontrado con los "
							+ "Datos introducidos", ButtonType.CLOSE);
					alerta.showAndWait();
				}else {
					URL url = getClass().getResource("../vistas/VistaPrincipal.fxml");
					
					try {
						Node node = FXMLLoader.load(url);
						Scene scene = new Scene((Parent) node);
						Stage appStage = (Stage) ((Node) Event.getSource()).getScene().getWindow();
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
				
			}catch(Exception ex) {
				Alert alerta = new Alert(AlertType.ERROR,"Error al interactuar con la Base de "
						+ "Datos, comprueba que tus datos son correctos", ButtonType.CLOSE);
				alerta.showAndWait();
			}
		}else {
			Alert alerta = new Alert(AlertType.ERROR,"No debes dejar ningún campo vacío...",
					ButtonType.CLOSE);
			alerta.showAndWait();
		}
		
	}
	
	
	/**
	 * Método para salir de la aplicación
	 * 
	 * @param Event, evento generado por el usario
	 */
	@FXML public void salirAplicacion (ActionEvent Event){
		System.exit(0);
	}
	
	/**
	 * Método que gestiona la animación del inicio de la aplicación
	 */
	private void cargarPresentacion() {
		try {
			Main.presentacionCargada = true;
			
			AnchorPane panel = FXMLLoader.load(getClass().getResource("../vistas/Panel_Presentacion.fxml"));
			AnchorPane panel01 = FXMLLoader.load(getClass().getResource("../vistas/Login.fxml"));
			
			root.getChildren().setAll(panel);
			
			FadeTransition transicion = new FadeTransition(Duration.seconds(3), panel);
			transicion.setFromValue(0);
			transicion.setToValue(1);
			transicion.setCycleCount(1);
			
			FadeTransition transicion01 = new FadeTransition(Duration.seconds(3), panel);
			transicion01.setFromValue(1);
			transicion01.setToValue(0);
			transicion01.setCycleCount(1);
			
			FadeTransition transicion02 = new FadeTransition(Duration.millis(100), panel01);
			transicion02.setFromValue(0);
			transicion02.setToValue(1);
			transicion02.setCycleCount(1);
			
			transicion.play();
			
			transicion.setOnFinished((e) -> {
				transicion01.play();
			});
			
			transicion01.setOnFinished((e) -> {
				transicion02.play();
			});
			
			transicion02.setOnFinished((e)->{
				try {
					AnchorPane parentContent = FXMLLoader.load(getClass().getResource("../vistas/Login.fxml"));
					root.getChildren().setAll(parentContent);
				}catch(Exception ex) {
					
				}
			});
			
			
		}catch(Exception ex) {
			
		}
		
	}

	/**
     * Método de inicialización del controlador, iniciamos con la animación, y hacemos la conexion con la BD.
     * 
     * @param url La ubicación utilizada para resolver rutas relativas para el objeto raíz, o nul
     * si no se conoce la ubicación.
     * @param rb Los recursos utilizados para localizar el objeto raíz, o null si el objeto raíz
     * no se localizó
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if(!Main.presentacionCargada) {
			cargarPresentacion();
		}
		
		miConexion = new Conexion();
		miConexion.conectarBD();
		
		//Hacemos que el bóton adquiera el foco...
		txtUsuario.setFocusTraversable(false);
		txtContrasena.setFocusTraversable(false);
	}
}