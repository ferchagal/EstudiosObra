/**
 * M�dulo: Estudios de Obra. Aplicaci�n de escritorio
 * Archivo: LoginController.java
 * Objetivo: Archivo Controller de la Vista Principal.
 * Equipo/Persona: Fernando Chac�n Galea  28.614.518 - B
 */

package controladores;

import java.net.URL;
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
import javafx.stage.Stage;
import modelo.Conexion;
import javafx.scene.control.Alert.AlertType;



/**
 * 
 * @author Fernando Chac�n Galea
 * @version 2020.06.22 - V2
 */
public class Login_Controller implements Initializable {
	
	/**
	 * Campos de donde recogemos los datos de inicio de sesion
	 */
	@FXML TextField txtUsuario;
	
	@FXML PasswordField txtContrasena;
	
	/**
	 * Objeto de tipo Connection. Para configurar la conexi�n a la BBDD.
	 */
	private Conexion miConexion;
	
	/**
	 * M�todo que lanzamos haciendo click en el bot�n "Iniciar Sesi�n" comprueba que el usuario y 
	 * contrase�a introducios son correctos.
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
					Alert alerta = new Alert(AlertType.INFORMATION,"Ning�n Usuario encontrado con los "
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
						alert.setContentText("Si no aparece la opci�n que has elegido, no pierdas la calma."
							+ " Cierra la aplicaci�n y vuelve a entrar en ella.");
						alert.showAndWait();
					}
				}				
				
			}catch(Exception ex) {
				Alert alerta = new Alert(AlertType.ERROR,"Error al interactuar con la Base de "
						+ "Datos, comprueba que tus datos son correctos", ButtonType.CLOSE);
				alerta.showAndWait();
			}
		}else {
			Alert alerta = new Alert(AlertType.ERROR,"No debes dejar ning�n campo vac�o...",
					ButtonType.CLOSE);
			alerta.showAndWait();
		}
		
	}
	
	/**
	 * M�todo para salir de la aplicaci�n
	 * 
	 * @param Event, evento generado por el usario
	 */
	@FXML public void salirAplicacion (ActionEvent Event){
		System.exit(0);
	}

	/**
     * M�todo de inicializaci�n del controlador, iniciamos la conexion con la BD
     * 
     * @param url La ubicaci�n utilizada para resolver rutas relativas para el objeto ra�z, o nul
     * si no se conoce la ubicaci�n.
     * @param rb Los recursos utilizados para localizar el objeto ra�z, o null si el objeto ra�z
     * no se localiz�
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		miConexion = new Conexion();
		miConexion.conectarBD();
		
		//Hacemos que el b�ton adquiera el foco...
		txtUsuario.setFocusTraversable(false);
		txtContrasena.setFocusTraversable(false);
	}

}