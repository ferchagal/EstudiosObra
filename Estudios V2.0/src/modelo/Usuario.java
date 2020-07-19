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

public class Usuario {
	
	private IntegerProperty codigo_usuario;
	private StringProperty usuario;
	private StringProperty password;

	public Usuario(Integer codigo_usuario, String usuario,String password) {
		this.codigo_usuario = new SimpleIntegerProperty(codigo_usuario);
		this.usuario = new SimpleStringProperty (usuario);
		this.password = new SimpleStringProperty (password);
	}
	
	public Usuario(Integer codigo_usuario, String usuario) {
		this.codigo_usuario = new SimpleIntegerProperty(codigo_usuario);
		this.usuario = new SimpleStringProperty (usuario);
	}
	
	public void guardarUsuario() {
		
	}
	
	public void actualizarUsuario() {
		
	}
	
	public void eliminarUsuario() {
		
	}
	
	@Override
	public String toString() {
		
		return usuario.get();
	}
	
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

	public final IntegerProperty codigo_usuarioProperty() {
		return this.codigo_usuario;
	}
	

	public final int getCodigo_usuario() {
		return this.codigo_usuarioProperty().get();
	}
	

	public final void setCodigo_usuario(final int codigo_usuario) {
		this.codigo_usuarioProperty().set(codigo_usuario);
	}
	

	public final StringProperty usuarioProperty() {
		return this.usuario;
	}
	

	public final String getUsuario() {
		return this.usuarioProperty().get();
	}
	

	public final void setUsuario(final String usuario) {
		this.usuarioProperty().set(usuario);
	}
	

	public final StringProperty passwordProperty() {
		return this.password;
	}
	

	public final String getPassword() {
		return this.passwordProperty().get();
	}
	

	public final void setPassword(final String password) {
		this.passwordProperty().set(password);
	}
	
}
