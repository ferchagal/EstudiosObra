/**
 * M�dulo: Estudios de Obra. Aplicaci�n de escritorio
 * Archivo: conexion.java
 * Objetivo: Clase modelo - conexion.
 * Equipo/Persona: Fernando Chac�n Galea  28.614.518 - B
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Creamos la clase conexi�n para gestionar las conexiones a la BD
 * 
 * @author Fernando Chac�n Galea
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
	 * el usuario de la Aplicaci�n.
	 */
	private String usuario = "Fer";
	
	/**
	 * Atributo de clase, cadena de texto que define la contrasena del usuario del a BD
	 */
	private String contrasena = "F3rn4nd0Ch@c0n##";
	
	/**
	 * M�todo para gestionar la conexi�n con la BD
	 * 
	 * @return un objeto de tipo Connection
	 */
	public Connection getConnection() {
		return miConexion;
	}
	
	/**
	 * M�todo para establecer una nueva conexi�n
	 * 
	 * @param miConexion, se le debe pasar un objeto de tipo conexi�n
	 */
	public void setConnection (Connection miConexion) {
		this.miConexion = miConexion;
	}
	
	/**
	 * Metodo que llamamos para establecer la conexi�n
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
	 * m�todo que llamamos para cerrar la conexi�n
	 */
	public void cerrarConexionBD() {
		try {
			miConexion.close();
		}catch(Exception ex) {
			Alert alerta = new Alert (Alert.AlertType.INFORMATION,"Algo no funcion� correctamente,"
					+ " Pongase en contacto con su Administrador",ButtonType.CLOSE);
			alerta.showAndWait();
		}
	}
}
