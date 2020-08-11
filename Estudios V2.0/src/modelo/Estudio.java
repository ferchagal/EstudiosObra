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

/**
 * 
 * @author fernando Chacón Galea
 * @version 2020.06.22 - V2
 */
public class Estudio {
	
	/**
	 * Atributo de tipo entero, define un codigo para cada estudio. es asignado automaticamente por la BD
	 */
	private IntegerProperty codigo_estudio;
	
	/**
	 * Atributo de tipo entero, define una referencia para cada estudio, es asignado por el usuario
	 */
	private IntegerProperty referencia;
	
	/**
	 * Atributo de tipo cadena de texto, define la denominación de la obra
	 */
	private StringProperty designacion;
	
	/**
	 * Atributo de tipo entero, define el año en el que se realiza el estudio, asignado por el usuario
	 */
	private IntegerProperty anyo;
	
	/**
	 * Atributo de tipo cadena de texto, define el estado del estudio.
	 */
	private StringProperty adjudicada;
	
	/**
	 * Atributo de tipo Usuario, define el usuario que ha realizado/está realizando el estudio
	 */
	private Usuario usuario;
	
	/**
	 * Atributo de tipo zona, define la zona donde está ubicada la obra
	 */
	private Zona zona;
	
	/**
	 * Constructor de clase
	 * 
	 * @param codigo_estudio, debe ser un numero entero, es asignado automaticamente por la BD, asi que lo
	 * mejor es que pongamos un cero, y en la inserción la BD le asigna el código automaticamente.
	 * @param referencia, debe ser un numero entero.
	 * @param designacion, debe ser una cadena de texto.
	 * @param anyo, debe ser un número entero, debe ser el año de estudio.
	 * @param adjudicada,debe ser una cadena de texto.
	 * @param usuario, debe ser un objeto de tipo Usuario.
	 * @param zona, debe ser un objeto de tipo Zona.
	 */
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
		
	/**
	 * Método para guardar un estudio en la BD, asi evitamos que nada que no sea el Modelo
	 * interactue con la BD. Será llamado desde el controller pertinente.
	 * 
	 * @param miConexion, objeto de tipo Connection
	 * @return devuelve un numero entero, 1 ó 0, 1 cuando se ha insertado el registro correctamente,
	 * 0 cuando el registro no se ha podido insertar.
	 */
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
	
	/**
	 * Método para actualizar un estudio en la BD, asi evitamos que nada que no sea el Modelo
	 * interactue con la BD. Será llamado desde el controller pertinente.
	 * 
	 * @param miConexion, objeto de tipo Connection
	 * @return devuelve un numero entero, 1 ó 0, 1 cuando se ha actualizado el registro correctamente,
	 * 0 cuando el registro no se ha podido actualizar.
	 */
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
	
	/**
	 * Método para eliminar un estudio en la BD, asi evitamos que nada que no sea el Modelo
	 * interactue con la BD. Será llamado desde el controller pertinente.
	 * 
	 * @param miConexion, objeto de tipo Connection
	 * @return devuelve un numero entero, 1 ó 0, 1 cuando se ha eliminado el registro correctamente,
	 * 0 cuando el registro no se ha podido eliminar.
	 */
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
	
	/**
	 * Método para poder cargar el tableView desde la tabla estudios de la BD
	 * 
	 * @param miConexion, objeto de tipo Connection
	 * @param lista, lista de tipo Estudio, se cargan todos los estudios en ella
	 */
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
	
	/**
	 * Sobrecargamos el método toString() para que nos devuelva la zona cuando la solicitemos.
	 */
	@Override
	public String toString() {
		
		return String.valueOf(referencia.get());
	}
	
	/**
	 * Método para poder cargar los combobox desde la tabla estudios de la BD
	 * 
	 * @param miConexion
	 * @param lista
	 */
	public static void datosComboEstudios (Connection miConexion, ObservableList<String>lista) {
		try {
			Statement consulta = miConexion.createStatement();
			
			ResultSet rs = consulta.executeQuery(
					"SELECT A.referencia " +
					"FROM estudios A"
					);
			
			while(rs.next()) {
				lista.add(
						String.valueOf(rs.getInt("referencia"))
						);
			}
			
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
			Alert alerta = new Alert (Alert.AlertType.INFORMATION,"Base de Datos no encontrada,"
					+ " Pongase en contacto con su Administrador",ButtonType.CLOSE);
			alerta.showAndWait();
		}
	}

	/**
	 * Metodo para obtener el codigo del estudio
	 * 
	 * @return devuelve el codigo
	 */
	public final IntegerProperty codigo_estudioProperty() {
		return this.codigo_estudio;
	}
	
	/**
	 * Metodo para obtener el codigo del estudio
	 * 
	 * @return devuelve el codigo
	 */
	public final int getCodigo_estudio() {
		return this.codigo_estudioProperty().get();
	}
	
	/**
	 * Método para establecer el codigo del estudio
	 * 
	 * @param codigo_estudio, debe ser un número entero
	 */
	public final void setCodigo_estudio(final int codigo_estudio) {
		this.codigo_estudioProperty().set(codigo_estudio);
	}
	
	/**
	 * Método para obtener la referencia del estudio.
	 * 
	 * @return devuelve un número entero
	 */
	public final IntegerProperty referenciaProperty() {
		return this.referencia;
	}
	
	/**
	 * Método para obtener la referencia del estudio.
	 * 
	 * @return devuelve un número entero
	 */
	public final int getReferencia() {
		return this.referenciaProperty().get();
	}
	
	/**
	 * Método para establecer la referencia del estudio.
	 * 
	 * @param referencia, debe ser un número entero
	 */
	public final void setReferencia(final int referencia) {
		this.referenciaProperty().set(referencia);
	}

	/**
	 * Método para obtener la designación de la obra
	 * 
	 * @return, devuelve una cadena de texto
	 */
	public final StringProperty designacionProperty() {
		return this.designacion;
	}
	
	/**
	 * Método para obtener la designación de la obra
	 * 
	 * @return, devuelve una cadena de texto
	 */
	public final String getDesignacion() {
		return this.designacionProperty().get();
	}

	/**
	 * Método para establecer la designación de la obra
	 * 
	 * @param designacion, debe ser una cadena de texto
	 */
	public final void setDesignacion(final String designacion) {
		this.designacionProperty().set(designacion);
	}
	
	/**
	 * Método para obtener el año de realización del estudio
	 *  
	 * @return devuelve un número entero (un año)
	 */
	public final IntegerProperty anyoProperty() {
		return this.anyo;
	}

	/**
	 * Método para obtener el año de realización del estudio
	 *  
	 * @return devuelve un número entero (un año)
	 */
	public final int getAnyo() {
		return this.anyoProperty().get();
	}
	
	/**
	 * Método para establecer el año de realización del estudio
	 *  
	 * @param anyo, debe ser un número entero(un año)
	 */
	public final void setAnyo(final int anyo) {
		this.anyoProperty().set(anyo);
	}
	
	/**
	 * Método para obtener el estado de adjudicación de la obra
	 * 
	 * @return devuelve una cadena de texto con el estado
	 */
	public final StringProperty adjudicadaProperty() {
		return this.adjudicada;
	}
	
	/**
	 * Método para obtener el estado de adjudicación de la obra
	 * 
	 * @return devuelve una cadena de texto con el estado
	 */
	public final String getAdjudicada() {
		return this.adjudicadaProperty().get();
	}
	
	/**
	 * Método para establecer el estado de adjudicación del estudio
	 *  
	 * @param adjudicada, debe ser una cadena de texto
	 */
	public final void setAdjudicada(final String adjudicada) {
		this.adjudicadaProperty().set(adjudicada);
	}

	/**
	 * Método para obtener el usuario que ha realizado/está realizando el estudio
	 * 
	 * @return devuelve un usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Método para establecer un usuario
	 * 
	 * @param usuario, debe ser un objeto de tipo usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Método para obtener la zona donde es la obra
	 * 
	 * @return devuelve una zona
	 */
	public Zona getZona() {
		return zona;
	}

	/**
	 * Método para establecer una zona del estudio
	 * 
	 * @param zona, debe ser un objeto de tipo zona
	 */
	public void setZona(Zona zona) {
		this.zona = zona;
	}
}
