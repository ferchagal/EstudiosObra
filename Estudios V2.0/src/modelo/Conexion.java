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

public class Conexion {
	private Connection miConexion;
	private String url = "jdbc:mysql://localhost:3306/estudiosobra";
	private String usuario = "Fer";
	private String contrasena = "F3rn4nd0Ch@c0n##";
	
	public Connection getConnection() {
		return miConexion;
	}
	
	public void setConnection (Connection miConexion) {
		this.miConexion = miConexion;
	}
	
	public void conectarBD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			miConexion = DriverManager.getConnection(url, usuario, contrasena);
		}catch(Exception ex) {
			Alert alerta = new Alert (Alert.AlertType.INFORMATION,"Base de Datos no encontrada,"
					+ " Pongase en contacto con su Administrador ",ButtonType.CLOSE);
			alerta.showAndWait();
			}
	}
	
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
