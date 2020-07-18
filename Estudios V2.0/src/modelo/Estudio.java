/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: Estudio.java
 * Objetivo: Clase modelo - estudio.
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

public class Estudio {
	
	private IntegerProperty codigo_estudio;
	private StringProperty designacion;
	private IntegerProperty anyo;
	private StringProperty adjudicada;
	private IntegerProperty codigo_usuario;
	private IntegerProperty codigo_zona;
	
	public Estudio (Integer codigo_estudio, String designacion, Integer anyo, String adjudicada,
			Integer codigo_usuario, Integer codigo_zona) {
		this.codigo_estudio = new SimpleIntegerProperty(codigo_estudio);
		this.designacion = new SimpleStringProperty(designacion);
		this.anyo = new SimpleIntegerProperty(anyo);
		this.adjudicada = new SimpleStringProperty(adjudicada);
		this.codigo_usuario = new SimpleIntegerProperty(codigo_usuario);
		this.codigo_zona = new SimpleIntegerProperty(codigo_zona);
	}
	
	
	
	public void guardarEstudio() {
		
	}
	
	public void actualizarEstudio() {
		
	}
	
	public void eliminarEstudio() {
		
	}
	
	public static void datosTablaEstudios(Connection miConexion,ObservableList<Estudio>lista) {
		try {
			Statement consulta = miConexion.createStatement();
			
			ResultSet rs = consulta.executeQuery(
					"SELECT A.codigo_estudio, " +
					"A.designacion, " +
					"A.anyo, " +
					"A.adjudicada, " +
					"A.codigo_usuario, "+
					"A.codigo_zona "+
					"FROM estudios A");
			
			while (rs.next()){
				lista.add(
						new Estudio(
								rs.getInt("codigo_estudio"),
								rs.getString("designacion"),
								rs.getInt("anyo"),
								rs.getString("adjudicada"),
								rs.getInt("codigo_usuario"),
								rs.getInt("codigo_zona")
								)
						);
			}
		}catch(Exception ex ) {
			Alert alerta = new Alert (Alert.AlertType.INFORMATION,"Base de Datos no encontrada,"
					+ " Pongase en contacto con su Administrador",ButtonType.CLOSE);
			alerta.showAndWait();
		}
	}



	public final IntegerProperty codigo_estudioProperty() {
		return this.codigo_estudio;
	}
	



	public final int getCodigo_estudio() {
		return this.codigo_estudioProperty().get();
	}
	



	public final void setCodigo_estudio(final int codigo_estudio) {
		this.codigo_estudioProperty().set(codigo_estudio);
	}
	



	public final StringProperty designacionProperty() {
		return this.designacion;
	}
	



	public final String getDesignacion() {
		return this.designacionProperty().get();
	}
	



	public final void setDesignacion(final String designacion) {
		this.designacionProperty().set(designacion);
	}
	



	public final IntegerProperty anyoProperty() {
		return this.anyo;
	}
	



	public final int getAnyo() {
		return this.anyoProperty().get();
	}
	



	public final void setAnyo(final int anyo) {
		this.anyoProperty().set(anyo);
	}
	



	public final StringProperty adjudicadaProperty() {
		return this.adjudicada;
	}
	



	public final String getAdjudicada() {
		return this.adjudicadaProperty().get();
	}
	



	public final void setAdjudicada(final String adjudicada) {
		this.adjudicadaProperty().set(adjudicada);
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
	



	public final IntegerProperty codigo_zonaProperty() {
		return this.codigo_zona;
	}
	



	public final int getCodigo_zona() {
		return this.codigo_zonaProperty().get();
	}
	



	public final void setCodigo_zona(final int codigo_zona) {
		this.codigo_zonaProperty().set(codigo_zona);
	}
	
	

}
