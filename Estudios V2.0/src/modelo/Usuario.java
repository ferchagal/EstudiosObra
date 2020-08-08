/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: Usuario.java
 * Objetivo: Clase modelo - usuario.
 * Equipo/Persona: Fernando Chacón Galea  28.614.518 - B
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * 
 * @author Fernando Chacón Galea
 * @version 2020.06.22 - V2
 */
public class Usuario {
	
	/**
	 * Atributo de tipo entero, define un código para cada usuario. Es asignado por la BD.
	 */
	private IntegerProperty codigo_usuario;
	
	/**
	 * Atributo de tipo cadena de texto. Es definido por el administrador de la BD
	 */
	private StringProperty usuario;
	
	/**
	 * Atributo de tipo cadena de texto. Es definido por el administrador de la BD
	 */
	private StringProperty password;
	
	/**
	 * Constructor de clase, sobrecargado, este se utiliza para evaluar el acceso a la aplicación.
	 * Está pendiente la implementación de seguridad de tipo sha-256.
	 * 
	 * @param codigo_usuario, debe ser un número entero
	 * @param usuario, debe ser una cadena de texto
	 * @param password, debe ser una cadena de texto
	 */
	public Usuario(Integer codigo_usuario, String usuario,String password) {
		this.codigo_usuario = new SimpleIntegerProperty(codigo_usuario);
		this.usuario = new SimpleStringProperty (usuario);
		this.password = new SimpleStringProperty (password);
	}
	
	/**
	 * Constructor de clase, sobrecargado, este se utiliza para las tablas estudios donde aparece el usuario
	 * que realiza cada estudio.
	 * 	
	 * @param codigo_usuario, debe ser un número entero
	 * @param usuario, debe ser una cadena de texto
	 */
	public Usuario(Integer codigo_usuario, String usuario) {
		this.codigo_usuario = new SimpleIntegerProperty(codigo_usuario);
		this.usuario = new SimpleStringProperty (usuario);
	}
	
	/**
	 * No se implementa, se deja pendiente para versiones futuras.
	 */
	public void guardarUsuario() {
		
	}
	
	/**
	 * No se implementa, se deja pendiente para versiones futuras.
	 */
	public void actualizarUsuario() {
		
	}
	
	/**
	 * No se implementa, se deja pendiente para versiones futuras.
	 */
	public void eliminarUsuario() {
		
	}
	
	/**
	 * Sobrecargamos el método toString() para que nos devuelva el usuario cuando lo solicitemos.
	 */
	@Override
	public String toString() {
		
		return usuario.get();
	}
	
	/**
	 * Método para poder cargar los combobox desde la tabla usuarios de la BD
	 * 
	 * @param miConexion, objeto de tipo Connection.
	 * @param lista, lista de tipo Usuario, se cargan todos los usuarios en ella
	 */
	public static void datosTablaUsuarios (Connection miConexion, ObservableList<Usuario>lista) {
		try {
			Statement consulta = miConexion.createStatement();
			
			ResultSet rs = consulta.executeQuery(
					"SELECT A.codigo_usuario, " +
					"A.usuario, " +
					"A.password " +
					"FROM usuarios A");
			
			while(rs.next()) {
				lista.add(
						new Usuario(
								rs.getInt("codigo_usuario"),
								rs.getString("usuario"),
								rs.getString("password")
								)
						);
			}
		}catch(Exception ex) {
			Alert alerta = new Alert (Alert.AlertType.INFORMATION,"Base de Datos no encontrada,"
					+ " Pongase en contacto con su Administrador",ButtonType.CLOSE);
			alerta.showAndWait();
		}
	}

	/**
	 * Método para obtener el código del usuario
	 * 
	 * @return devuelve un número entero
	 */
	public final IntegerProperty codigo_usuarioProperty() {
		return this.codigo_usuario;
	}
	
	/**
	 * Método para obtener el código del usuario
	 * 
	 * @return devuelve un número entero
	 */
	public final int getCodigo_usuario() {
		return this.codigo_usuarioProperty().get();
	}
	
	/**
	 * Método para establecer un código de usuario
	 * 
	 * @param codigo_usuario, debe ser un número entero
	 */
	public final void setCodigo_usuario(final int codigo_usuario) {
		this.codigo_usuarioProperty().set(codigo_usuario);
	}
	
	/**
	 * Método para obtener un nombre de usuario
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty usuarioProperty() {
		return this.usuario;
	}
	
	/**
	 * Método para obtener un nombre de usuario
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getUsuario() {
		return this.usuarioProperty().get();
	}
	
	/**
	 * Método para establecer un nombre de usuario
	 * 
	 * @param usuario, debe ser una cadena de texto
	 */
	public final void setUsuario(final String usuario) {
		this.usuarioProperty().set(usuario);
	}
	
	/**
	 * Método para obtener el password de usuario
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty passwordProperty() {
		return this.password;
	}
	
	/**
	 * Método para obtener el password de usuario
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getPassword() {
		return this.passwordProperty().get();
	}
	
	/**
	 * Método para establecer el password de un usuario
	 * 
	 * @param password, debe ser una cadena de texto
	 */
	public final void setPassword(final String password) {
		this.passwordProperty().set(password);
	}
	
}
