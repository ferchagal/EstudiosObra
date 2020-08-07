/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: conexion.java
 * Objetivo: Clase modelo - conexion.
 * Equipo/Persona: Fernando Chacón Galea  28.614.518 - B
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Creamos la clase conexión para gestionar las conexiones a la BD
 * 
 * @author Fernando Chacón Galea
 * @version 2020.06.22 - V2
 */
public class Conexion {
	
	/**
	 * Atributo de clase, objeto de tipo Connection, lo utilizamos para gestionar todas las conexiones a la BD
	 */
	private Connection miConexion;
	
	/**
	 * Atributo de clase, cadena de texto que define la direccion de la BD
	 */
	private String url = "jdbc:mysql://localhost:3306/estudiosobra?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	/**
	 * Atributo de clase, cadena de texto que define el usuario de la BD, no tiene nada que ver con
	 * el usuario de la Aplicación.
	 */
	private String usuario = "Fer";
	
	/**
	 * Atributo de clase, cadena de texto que define la contrasena del usuario del a BD
	 */
	private String contrasena = "F3rn4nd0Ch@c0n##";
	
	/**
	 * Método para gestionar la conexión con la BD
	 * 
	 * @return un objeto de tipo Connection
	 */
	public Connection getConnection() {
		return miConexion;
	}
	
	/**
	 * Método para establecer una nueva conexión
	 * 
	 * @param miConexion, se le debe pasar un objeto de tipo conexión
	 */
	public void setConnection (Connection miConexion) {
		this.miConexion = miConexion;
	}
	
	/**
	 * Metodo que llamamos para establecer la conexión
	 */
	public void conectarBD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			miConexion = DriverManager.getConnection(url, usuario, contrasena);
		}catch(Exception ex) {
			Alert alerta = new Alert (Alert.AlertType.INFORMATION,"Base de Datos no encontrada,"
					+ " Pongase en contacto con su Administrador ",ButtonType.CLOSE);
			alerta.showAndWait();
			
			ex.printStackTrace();
			}
	}
	
	/**
	 * método que llamamos para cerrar la conexión
	 */
	public void cerrarConexionBD() {
		try {
			miConexion.close();
		}catch(Exception ex) {
			Alert alerta = new Alert (Alert.AlertType.INFORMATION,"Algo no funcionó correctamente,"
					+ " Pongase en contacto con su Administrador",ButtonType.CLOSE);
			alerta.showAndWait();
		}
	}
}
