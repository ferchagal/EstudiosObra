/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: LoginController.java
 * Objetivo: Archivo Controller de la Vista Principal.
 * Equipo/Persona: Fernando Chacón Galea  28.614.518 - B
 */

package controladores;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;


/**
 * 
 * @author Fernando Chacón Galea
 * @version 2020.06.22 - V2
 */
public class Login_Controller implements Initializable {
	
	/**
	 * Campos de donde recogemos los datos de inicio de sesion
	 */
	@FXML TextField txtUsuario;
	
	@FXML PasswordField txtContrasena;
	
	/**
	 * Objeto de tipo Connection. Para configurar la conexión a la BBDD.
	 */
	private Connection miConexion;
	
	/**
	 * Método que lanzamos haciendo click en el botón "Iniciar Sesión", lanza el método conectarBD
	 * (nos conecta con la BBDD), y compruba que el usuario y contraseña introducios son correctos.
	 *  
	 * @param Event evento generado por el usuario
	 */
	@FXML public void iniciarAplicacion (ActionEvent Event) {
		
		conectarBD();
		
		String usuario = txtUsuario.getText();
		String contrasena = txtContrasena.getText();
		
		if(usuario.length()>0 && contrasena.length()>0 ) {
			try {
				Statement sql = miConexion.createStatement();
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
	
	@FXML public void salirAplicacion (ActionEvent Event){
		System.exit(0);
	}
	
	private void conectarBD () {
		miConexion = null;
		//Cargamos el driver de la BBDD
		try {
			Class.forName("com.mysql.jdbc.Driver");
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/estudiosobra",
					"Fer","F3rn4nd0Ch@c0n##");
		} catch (Exception ex) {
			Alert alerta = new Alert (Alert.AlertType.INFORMATION,"Base de Datos no encontrada,"
					+ " Pongase en contacto con su Administrador",ButtonType.CLOSE);
			alerta.showAndWait();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Hacemos que el bóton adquiera el foco...
		txtUsuario.setFocusTraversable(false);
		txtContrasena.setFocusTraversable(false);
	}

}