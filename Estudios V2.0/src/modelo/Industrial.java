/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: Industrial.java
 * Objetivo: Clase modelo - industrial.
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

public class Industrial {
	
	private IntegerProperty codigo_industrial;
	private StringProperty nombre;
	private StringProperty apellidos;
	private StringProperty email;
	private StringProperty telefono;
	private StringProperty telefono02;
	private IntegerProperty codigo_actividad;
	private StringProperty empresa;
	private StringProperty localidad;
	private IntegerProperty codigo_zona;
	private StringProperty comentarios;
	
	public Industrial(Integer codigo_industrial, String nombre, String apellidos,String email,
			String telefono, String telefono02, Integer codigo_actividad, String empresa, 
			String localidad, Integer codigo_zona, String comentarios) {
		this.codigo_industrial = new SimpleIntegerProperty(codigo_industrial);
		this.nombre = new SimpleStringProperty(nombre);
		this.apellidos = new SimpleStringProperty(apellidos);
		this.email = new SimpleStringProperty(email);
		this.telefono = new SimpleStringProperty (telefono);
		this.telefono02 = new SimpleStringProperty(telefono02);
		this.codigo_actividad = new SimpleIntegerProperty(codigo_actividad);
		this.empresa = new SimpleStringProperty (empresa);
		this.localidad = new SimpleStringProperty(localidad);
		this.codigo_zona = new SimpleIntegerProperty(codigo_zona);
		this.comentarios = new SimpleStringProperty (comentarios);		
	}
	
	public void guardarIndustrial() {
		
	}
	
	public void actualizarIndustrial() {
		
	}
	
	public void eliminarIndustrial () {
		
	}
	
	public static void datosTablaIndustriales (Connection miConexion, ObservableList<Industrial>lista) {
		try {
			Statement consulta = miConexion.createStatement();
			
			ResultSet rs = consulta.executeQuery(
					"SELECT A.codigo_industrial, " +
					"A.nombre, " +
					"A.apellidos, " +
					"A.email, " +
					"A.telefono, " +
					"A.telefono02, " +
					"A.codigo_actividad, " +
					"A.empresa, " +
					"A.localidad, " +
					"A.codigo_zona, " +
					"A.comentarios " +
					"FROM industriales A");
			
			while(rs.next()) {
				lista.add(
						new Industrial(
								rs.getInt("codigo_industrial"),
								rs.getString("nombre"),
								rs.getString("apellidos"),
								rs.getString("email"),
								rs.getString("telefono"),
								rs.getString("telefono02"),
								rs.getInt("codigo_actividad"),
								rs.getString("empresa"),
								rs.getString("localidad"),
								rs.getInt("codigo_zona"),
								rs.getString("comentarios")
								)
						);
						
			}
			
					
		} catch (Exception e) {
			Alert alerta = new Alert (Alert.AlertType.INFORMATION,"Base de Datos no encontrada,"
					+ " Pongase en contacto con su Administrador",ButtonType.CLOSE);
			alerta.showAndWait();
		}
	}

	public final IntegerProperty codigo_industrialProperty() {
		return this.codigo_industrial;
	}
	

	public final int getCodigo_industrial() {
		return this.codigo_industrialProperty().get();
	}
	

	public final void setCodigo_industrial(final int codigo_industrial) {
		this.codigo_industrialProperty().set(codigo_industrial);
	}
	

	public final StringProperty nombreProperty() {
		return this.nombre;
	}
	

	public final String getNombre() {
		return this.nombreProperty().get();
	}
	

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	

	public final StringProperty apellidosProperty() {
		return this.apellidos;
	}
	

	public final String getApellidos() {
		return this.apellidosProperty().get();
	}
	

	public final void setApellidos(final String apellidos) {
		this.apellidosProperty().set(apellidos);
	}
	

	public final StringProperty emailProperty() {
		return this.email;
	}
	

	public final String getEmail() {
		return this.emailProperty().get();
	}
	

	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}
	

	public final StringProperty telefonoProperty() {
		return this.telefono;
	}
	

	public final String getTelefono() {
		return this.telefonoProperty().get();
	}
	

	public final void setTelefono(final String telefono) {
		this.telefonoProperty().set(telefono);
	}
	

	public final StringProperty telefono02Property() {
		return this.telefono02;
	}
	

	public final String getTelefono02() {
		return this.telefono02Property().get();
	}
	

	public final void setTelefono02(final String telefono02) {
		this.telefono02Property().set(telefono02);
	}
	

	public final IntegerProperty codigo_actividadProperty() {
		return this.codigo_actividad;
	}
	

	public final int getCodigo_actividad() {
		return this.codigo_actividadProperty().get();
	}
	

	public final void setCodigo_actividad(final int codigo_actividad) {
		this.codigo_actividadProperty().set(codigo_actividad);
	}
	

	public final StringProperty empresaProperty() {
		return this.empresa;
	}
	

	public final String getEmpresa() {
		return this.empresaProperty().get();
	}
	

	public final void setEmpresa(final String empresa) {
		this.empresaProperty().set(empresa);
	}
	

	public final StringProperty localidadProperty() {
		return this.localidad;
	}
	

	public final String getLocalidad() {
		return this.localidadProperty().get();
	}
	

	public final void setLocalidad(final String localidad) {
		this.localidadProperty().set(localidad);
	}
	

	public final IntegerProperty codigo_zonaProperty() {
		return this.codigo_zona;
	}
	

	public final int getCodigo_zona() {
		return this.codigo_zonaProperty().get();
	}
	

	public final void setCodigo_zona(final int codigo_zona) {
		this.codigo_zonaProperty().set(codigo_zona);
	}
	

	public final StringProperty comentariosProperty() {
		return this.comentarios;
	}
	

	public final String getComentarios() {
		return this.comentariosProperty().get();
	}
	

	public final void setComentarios(final String comentarios) {
		this.comentariosProperty().set(comentarios);
	}
	
	
}
