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

/**
 * 
 * @author Fernando Chacón Galea
 * @version 2020.06.22 - V2
 */
public class Zona {

	/**
	 * Atributo de tipo entero, define un código para cada zona. Es asignado por el 
	 * administrador de la BD.
	 */
	private IntegerProperty codigo_zona;
	
	/**
	 * Atributo de tipo cadena de texto. Es definido por el administrador de la BD.
	 */
	private StringProperty zona;
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param codigo_zona, tiene que ser un número entero, tiene que ser uno de los asignados
	 * por el administrador de la BD
	 * @param zona, tiene que ser una cadena de texto, tiene que ser uno de los asignados por 
	 * el administrador de la BD.
	 */
	public Zona(Integer codigo_zona, String zona) {
		this.codigo_zona = new SimpleIntegerProperty (codigo_zona);
		this.zona = new SimpleStringProperty (zona);
		
	}
	
	/**
	 * No se implementa, se deja pendiente para versiones futuras.
	 */
	public void guardarZona() {
		
	}
	
	/**
	 * No se implementa, se deja pendiente para versiones futuras.
	 */
	public void actualizarZona() {
		
	}
	
	/**
	 * No se implementa, se deja pendiente para versiones futuras.
	 */
	public void eliminarZona() {
		
	}
	
	/**
	 * Sobrecargamos el método toString() para que nos devuelva la zona cuando la solicitemos.
	 */
	@Override
	public String toString() {
		
		return zona.get();
	}
		
	/**
	 * Método para poder cargar los combobox desde la tabla zonas de la BD
	 * 
	 * @param miConexion, objeto de tipo Connection.
	 * @param lista, lista de tipo Zona, se cargan todas las zonas en ella
	 */
	public static void datosComboZonas (Connection miConexion, ObservableList<Zona>lista) {
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

	/**
	 * Método para obtener el código de la zona
	 * 
	 * @return devuelve un número entero
	 */
	public final IntegerProperty codigo_zonaProperty() {
		return this.codigo_zona;
	}
	
	/**
	 * Método para obtener el código de la zona
	 * 
	 * @return devuelve un número entero
	 */
	public final int getCodigo_zona() {
		return this.codigo_zonaProperty().get();
	}
	
	/**
	 * Método para establecer un código de zona
	 * 
	 * @param codigo_zona, debe ser un número entero
	 */
	public final void setCodigo_zona(final int codigo_zona) {
		this.codigo_zonaProperty().set(codigo_zona);
	}
	
	/**
	 * Método para obtener la zona del estudio
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty zonaProperty() {
		return this.zona;
	}
	
	/**
	 * Método para obtener la zona del estudio
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getZona() {
		return this.zonaProperty().get();
	}
	
	/**
	 * Método para establecer una zona
	 * 
	 * @param zona, debe ser una cadena de texto
	 */
	public final void setZona(final String zona) {
		this.zonaProperty().set(zona);
	}
	
}
