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

/**
 * 
 * @author Fernando Chacón Galea
 * @version 2020.06.22 - V2
 */
public class Actividad {

	/**
	 * Atributo de tipo entero, define un código para cada actividad. Es asignado por
	 * el administrador de la BD.
	 */
	private IntegerProperty codigo_actividad;
	
	/**
	 * Atributo de tipo cadena de texto. Es definido por el administrador de la BD.
	 */
	private StringProperty actividad;
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param codigo_actividad, tiene que ser un número entero, tiene que ser uno de los asignados
	 * por el administrador de la BD.
	 * @param actividad, tiene que ser una cadena de texto, tiene que ser uno de los asignados por
	 * el administrador de la BD.
	 */
	public Actividad(Integer codigo_actividad, String actividad) {
		this.codigo_actividad = new SimpleIntegerProperty (codigo_actividad);
		this.actividad = new SimpleStringProperty (actividad);
	}
	
	/**
	 * No se implementa, se deja pendiente para versiones futuras.
	 */
	public void guardarActividad() {
		
	}
	
	/**
	 * No se implementa, se deja pendiente para versiones futuras.
	 */
	public void actualizarActividad() {
		
	}
	
	/**
	 * No se implementa, se deja pendiente para versiones futuras.
	 */
	public void eliminarActividad() {
		
	}
	
	/**
	 * Sobrecargamos el método toString() para que nos devuelva la actividad cuando la solicitemos.
	 */
	@Override
	public String toString() {
		
		return actividad.get();
		
	}
	
	/**
	 * Método para poder cargar los combobox desde la tabla actividades de la BD
	 * 
	 * @param miConexion, objeto de tipo Connection.
	 * @param lista, lista de tipo Actividad, se cargan todas las actividades en ella
	 */
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
	
	/**
	 * Método para obtener el codigo de la Actividad 
	 * 
	 * @return devuelve el codigo
	 */
	public final IntegerProperty codigo_actividadProperty() {
		return this.codigo_actividad;
	}
	
	/**
	 * Método para obtener el codigo de la Actidad
	 * 
	 * @return devuelve el codigo de la actividad
	 */
	public final int getCodigo_actividad() {
		return this.codigo_actividadProperty().get();
	}
	
	/**
	 * Método para establecer el codigo de la actividad
	 * 
	 * @param codigo_actividad, debe ser un número entero
	 */
	public final void setCodigo_actividad(final int codigo_actividad) {
		this.codigo_actividadProperty().set(codigo_actividad);
	}
	
	/**
	 * Método para obtener el nombre de la Actividad 
	 * 
	 * @return devuelve el codigo
	 */
	public final StringProperty actividadProperty() {
		return this.actividad;
	}
	
	/**
	 * Método para obtener el nombre de la Actividad 
	 * 
	 * @return devuelve el nombre de la Actividad
	 */
	public final String getActividad() {
		return this.actividadProperty().get();
	}
	
	/**
	 * Método para establecer un nuevo nombre de la actividad
	 * 
	 * @param actividad, debe ser una cadena de texto
	 */
	public final void setActividad(final String actividad) {
		this.actividadProperty().set(actividad);
	}
	
}
