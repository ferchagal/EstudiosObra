/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: Estudio.java
 * Objetivo: Clase modelo - estudio.
 * Equipo/Persona: Fernando Chacón Galea  28.614.518 - B
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	private IntegerProperty referencia;
	private StringProperty designacion;
	private IntegerProperty anyo;
	private StringProperty adjudicada;
	private Usuario usuario;
	private Zona zona;
	
	public Estudio (Integer codigo_estudio, Integer referencia, String designacion, Integer anyo, String adjudicada,
			Usuario usuario, Zona zona) {
		this.codigo_estudio = new SimpleIntegerProperty(codigo_estudio);
		this.referencia = new SimpleIntegerProperty(referencia);
		this.designacion = new SimpleStringProperty(designacion);
		this.anyo = new SimpleIntegerProperty(anyo);
		this.adjudicada = new SimpleStringProperty(adjudicada);
		this.usuario = usuario;
		this.zona = zona;
	}
	
	
	
	public int guardarEstudio(Connection miConexion) {
		try {
			PreparedStatement consulta = miConexion.prepareStatement("INSERT INTO estudios" +
					"(referencia, designacion, anyo, adjudicada, codigo_usuario, codigo_zona) " +
					"VALUES (?,?,?,?,?,?)");
			
			consulta.setInt(1, referencia.get());
			consulta.setString(2, designacion.get());
			consulta.setInt(3, anyo.get());
			consulta.setString(4, adjudicada.get());
			consulta.setInt(5, usuario.getCodigo_usuario());
			consulta.setInt(6, zona.getCodigo_zona());
			
			return consulta.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		
	}
	
	public int actualizarEstudio(Connection miConexion) {
		try {
			PreparedStatement consulta = miConexion.prepareStatement("UPDATE estudios SET "
					+ "referencia = ?, "
					+ "designacion = ?, "
					+ "anyo = ?, "
					+ "adjudicada = ?, "
					+ "codigo_usuario = ?, "
					+ "codigo_zona = ? "
					+ "WHERE codigo_estudio = ?");
			
			consulta.setInt(1, referencia.get());
			consulta.setString(2,  designacion.get());
			consulta.setInt(3, anyo.get());
			consulta.setString(4, adjudicada.get());
			consulta.setInt(5, usuario.getCodigo_usuario());
			consulta.setInt(6, zona.getCodigo_zona());
			consulta.setInt(7, codigo_estudio.get());
			
			return consulta.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		
	}
	
	public int eliminarEstudio(Connection miConexion) {
		
		try {
			PreparedStatement consulta = miConexion.prepareStatement("DELETE FROM estudios "
				+ "WHERE codigo_estudio = ?");
		
			consulta.setInt(1, codigo_estudio.get());
		
			return consulta.executeUpdate();
		
		}catch(Exception ex) {
			
			return 0;
		}
		
	}
	
	public static void datosTablaEstudios(Connection miConexion,ObservableList<Estudio>lista) {
		try {
			Statement consulta = miConexion.createStatement();
			
			ResultSet rs = consulta.executeQuery(
					"SELECT A.codigo_estudio, " +
					"A.referencia, " +
					"A.designacion, " +
					"A.anyo, " +
					"A.adjudicada, " +
					"A.codigo_usuario, " +
					"A.codigo_zona, " +
					"B.usuario, " +
					"C.zona " +
					"FROM estudios A " +
					"INNER JOIN usuarios B " + 
					"ON (A.codigo_usuario = B.codigo_usuario) " +
					"INNER JOIN zonas C " +
					"ON (A.codigo_zona = C.codigo_zona)"
					);
			
			while (rs.next()){
				lista.add(
						new Estudio(
								rs.getInt("codigo_estudio"),
								rs.getInt("referencia"),
								rs.getString("designacion"),
								rs.getInt("anyo"),
								rs.getString("adjudicada"),
								new Usuario(rs.getInt("codigo_usuario"), rs.getString("usuario")),
								new Zona(rs.getInt("codigo_zona"), rs.getString("zona"))
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
	
	
	public final IntegerProperty referenciaProperty() {
		return this.referencia;
	}
	



	public final int getReferencia() {
		return this.referenciaProperty().get();
	}
	



	public final void setReferencia(final int referencia) {
		this.referenciaProperty().set(referencia);
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

	
	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Zona getZona() {
		return zona;
	}



	public void setZona(Zona zona) {
		this.zona = zona;
	}



	
	
	
	

}
