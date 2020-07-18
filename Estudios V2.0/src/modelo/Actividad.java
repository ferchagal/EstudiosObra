/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: Actividad.java
 * Objetivo: Clase modelo - actividad.
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

public class Actividad {

	private IntegerProperty codigo_actividad;
	private StringProperty actividad;
	
	public Actividad(Integer codigo_actividad, String actividad) {
		this.codigo_actividad = new SimpleIntegerProperty (codigo_actividad);
		this.actividad = new SimpleStringProperty (actividad);
	}
	
	public void guardarActividad() {
		
	}
	
	public void actualizarActividad() {
		
	}
	
	public void eliminarActividad() {
		
	}
	
	@Override
	public String toString() {
		
		return actividad.get();
		
	}
	
	public static void datosComboActividades (Connection miConexion, ObservableList<Actividad>lista) {
		try {
			Statement consulta = miConexion.createStatement();
			
			ResultSet rs = consulta.executeQuery(
					"SELECT A.codigo_actividad, " +
					"A.actividad " +
					"FROM actividades A");
			
			while(rs.next()) {
				lista.add(
						new Actividad(
								rs.getInt("codigo_actividad"),
								rs.getString("actividad")
								)
						);
			}
		}catch(Exception ex) {
			Alert alerta = new Alert (Alert.AlertType.INFORMATION,"Base de Datos no encontrada,"
					+ " Pongase en contacto con su Administrador",ButtonType.CLOSE);
			alerta.showAndWait();
		}
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
	

	public final StringProperty actividadProperty() {
		return this.actividad;
	}
	

	public final String getActividad() {
		return this.actividadProperty().get();
	}
	

	public final void setActividad(final String actividad) {
		this.actividadProperty().set(actividad);
	}
	
}
