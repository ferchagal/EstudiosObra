/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: Zona.java
 * Objetivo: Clase modelo - zona.
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

public class Zona {

	private IntegerProperty codigo_zona;
	private StringProperty zona;
	
	public Zona(Integer codigo_zona, String zona) {
		this.codigo_zona = new SimpleIntegerProperty (codigo_zona);
		this.zona = new SimpleStringProperty (zona);
		
	}
	
	public void guardarZona() {
		
	}
	
	public void actualizarZona() {
		
	}
	
	public void eliminarZona() {
		
	}
	
	public static void datosTablaZonas (Connection miConexion, ObservableList<Zona>lista) {
		try {
			Statement consulta = miConexion.createStatement();
			
			ResultSet rs = consulta.executeQuery(
					"SELECT A.codigo_zona, " +
					"A.zona " +
					"FROM zonas A");
			
			while(rs.next()) {
				lista.add(
						new Zona(
								rs.getInt("codigo_zona"),
								rs.getString("zona")
								)
						);
			}
					
			
		}catch(Exception ex) {
			Alert alerta = new Alert (Alert.AlertType.INFORMATION,"Base de Datos no encontrada,"
					+ " Pongase en contacto con su Administrador",ButtonType.CLOSE);
			alerta.showAndWait();
		}
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
	

	public final StringProperty zonaProperty() {
		return this.zona;
	}
	

	public final String getZona() {
		return this.zonaProperty().get();
	}
	

	public final void setZona(final String zona) {
		this.zonaProperty().set(zona);
	}
	
}
