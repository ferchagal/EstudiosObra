/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: Industrial.java
 * Objetivo: Clase modelo - industrial.
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
 * @author Fernando Chacón Galea
 * @version 2020.06.22 - V2
 */
public class Industrial {
	
	/**
	 * Atributo de tipo entero, define un código para cada industrial. Es asignado automaticamente
	 * por la BD.
	 */
	private IntegerProperty codigo_industrial;
	
	/**
	 * Atributo de tipo cadena de texto, define el nombre de pila del industrial.
	 */
	private StringProperty nombre;
	
	/**
	 * Atributo de tipo cadena de texto, define los apellidos del industrial.
	 */
	private StringProperty apellidos;
	
	/**
	 * Atributo de tipo cadena de texto, define el email del industrial.
	 */
	private StringProperty email;
	
	/**
	 * Atributo de tipo cadena de texto, define un telefono del industrial
	 */
	private StringProperty telefono;
	
	/**
	 * Atributo de tipo cadena de texto, define un telefono del industrial
	 */
	private StringProperty telefono02;
	
	/**
	 * Atributo de tipo Actividad, define la actividad a la que se dedica el industrial
	 */
	private Actividad actividad;
	
	/**
	 * Atributo de tipo cadena de texto, define el nombre de la empresa para la que trabaja el 
	 * industrial.
	 */
	private StringProperty empresa;
	
	/**
	 * Atributo de tipo cadena de texto, define el nombre de la localidad donde se ubica la
	 * empresa a la que pertenece el industrial, o su delegación.
	 */
	private StringProperty localidad;
	
	/**
	 * Atributo de tipo Zona, define la zona donde actua la empresa a la que pertenece el
	 * el industrial
	 */
	private Zona zona;
	
	/**
	 * Atributo de tipo cadena de texto, son comentarios que se pueden insertar en el industrial
	 * para tener mas datos sobre él.
	 */
	private StringProperty comentarios;
	
	/**
	 * Constructor de clase
	 * 
	 * @param codigo_industrial, numero entero definido automaticamente por la BD
	 * @param nombre, cadena de texto
	 * @param apellidos, cadena de texto
	 * @param email, cadena de texto
	 * @param telefono, cadena de texto
	 * @param telefono02, cadena de texto
	 * @param actividad, obtejo de tipo actividad
	 * @param empresa, cadena de texto
	 * @param localidad, cadena de texto
	 * @param zona, objeto de tipo zona
	 * @param comentarios, cadena de texto
	 */
	public Industrial(Integer codigo_industrial, String nombre, String apellidos,String email,
			String telefono, String telefono02, Actividad actividad, String empresa, 
			String localidad, Zona zona, String comentarios) {
		this.codigo_industrial = new SimpleIntegerProperty(codigo_industrial);
		this.nombre = new SimpleStringProperty(nombre);
		this.apellidos = new SimpleStringProperty(apellidos);
		this.email = new SimpleStringProperty(email);
		this.telefono = new SimpleStringProperty (telefono);
		this.telefono02 = new SimpleStringProperty(telefono02);
		this.actividad = actividad;
		this.empresa = new SimpleStringProperty (empresa);
		this.localidad = new SimpleStringProperty(localidad);
		this.zona = zona;
		this.comentarios = new SimpleStringProperty (comentarios);		
	}
	
	/**
	 * Método para guardar un industrial en la BD, asi evitamos que nada que no sea el Modelo
	 * interactue con la BD. Será llamado desde el controller pertinente.
	 * 
	 * @param miConexion, objeto de tipo Connection
	 * @return devuelve un numero entero, 1 ó 0, 1 cuando se ha insertado el registro correctamente,
	 * 0 cuando el registro no se ha podido insertar.
	 */
	public int guardarIndustrial(Connection miConexion) {
		
		try {
			PreparedStatement consulta = miConexion.prepareStatement("INSERT INTO industriales "
					+ "(nombre, apellidos, email, telefono, telefono02, codigo_actividad, empresa,"
					+ " localidad, codigo_zona, comentarios)" +
					"VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			consulta.setString(1, nombre.get());
			consulta.setString(2, apellidos.get());
			consulta.setString(3, email.get());
			consulta.setString(4, telefono.get());
			consulta.setString(5,  telefono02.get());
			consulta.setInt(6, actividad.getCodigo_actividad());
			consulta.setString(7,  empresa.get());
			consulta.setString(8, localidad.get());
			consulta.setInt(9, zona.getCodigo_zona());
			consulta.setString(10, comentarios.get());
			
			return consulta.executeUpdate();
			
		} catch (Exception ex) {
			
			return 0;
			
		}
		
	}
	
	/**
	 * Método para actualizar un industrial en la BD, asi evitamos que nada que no sea el Modelo
	 * interactue con la BD. Será llamado desde el controller pertinente.
	 * 
	 * @param miConexion, objeto de tipo Connection
	 * @return devuelve un numero entero, 1 ó 0, 1 cuando se ha actualizado el registro correctamente,
	 * 0 cuando el registro no se ha podido actualizar.
	 */
	public int actualizarIndustrial(Connection miConexion) {
		try {
			PreparedStatement consulta = miConexion.prepareStatement("UPDATE industriales SET "
					+ "nombre = ?, "
					+ "apellidos = ?, "
					+ "email = ?, "
					+ "telefono = ?, "
					+ "telefono02 = ?, "
					+ "codigo_actividad = ?, "
					+ "empresa = ?, "
					+ "localidad = ?, "
					+ "codigo_zona = ?, "
					+ "comentarios = ? "
					+ "WHERE codigo_industrial = ?");
			
			consulta.setString(1, nombre.get());
			consulta.setString(2, apellidos.get());
			consulta.setString(3, email.get());
			consulta.setString(4, telefono.get());
			consulta.setString(5,  telefono02.get());
			consulta.setInt(6, actividad.getCodigo_actividad());
			consulta.setString(7,  empresa.get());
			consulta.setString(8, localidad.get());
			consulta.setInt(9, zona.getCodigo_zona());
			consulta.setString(10, comentarios.get());
			consulta.setInt(11,  codigo_industrial.get());
			
			return consulta.executeUpdate();
			
	
			
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return 0;
			
		}
		
	}
	
	/**
	 * Método para eliminar un industrial en la BD, asi evitamos que nada que no sea el Modelo
	 * interactue con la BD. Será llamado desde el controller pertinente.
	 * 
	 * @param miConexion, objeto de tipo Connection
	 * @return devuelve un numero entero, 1 ó 0, 1 cuando se ha eliminado el registro correctamente,
	 * 0 cuando el registro no se ha podido eliminar.
	 */
	public int eliminarIndustrial (Connection miConexion) {
		
		try {
			PreparedStatement consulta = miConexion.prepareStatement("DELETE FROM industriales "
				+ "WHERE codigo_industrial = ?");
		
			consulta.setInt(1, codigo_industrial.get());
		
			return consulta.executeUpdate();
		
		}catch(Exception ex) {
			
			return 0;
		}
	}
		
	/**
	 * Método para poder cargar el tableView desde la tabla industriales de la BD
	 * 
	 * @param miConexion, objeto de tipo Connection
	 * @param lista, lista de tipo Industrial, se cargan todos los industriales en ella
	 */
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
					"A.comentarios, " +
					"B.actividad, " +
					"C.zona " +
					"FROM industriales A "+ 
					"INNER JOIN actividades B " +
					"ON (A.codigo_actividad = B.codigo_actividad) "+
					"INNER JOIN zonas C " +
					"ON (A.codigo_zona = C.codigo_zona)"
					
					);
			
			while(rs.next()) {
				lista.add(
						new Industrial(
								rs.getInt("codigo_industrial"),
								rs.getString("nombre"),
								rs.getString("apellidos"),
								rs.getString("email"),
								rs.getString("telefono"),
								rs.getString("telefono02"),
								new Actividad(rs.getInt("codigo_actividad"),rs.getString("actividad")),
								rs.getString("empresa"),
								rs.getString("localidad"),
								new Zona(rs.getInt("codigo_zona"),rs.getString("zona")),
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
	
	/**
	 * Método para poder cargar el tableView Industriales filtrados para ofertas Estudio desde la tabla 
	 * industriales de la BD
	 * 
	 * @param miConexion, objeto de tipo Connection
	 * @param lista, lista de tipo Industrial, se cargan todos los industriales en ella
	 * @param cadena de texto, se le pasa como parametro parte de la consulta, la cadena se crea con los
	 * checkBox seleccionados
	 * @param cadena de texto, se le pasa como parametro parte de la consulta, la zona seleccionada en el
	 * cbZona.
	 */
	public static void datosTablaIndustrialesEstudio (Connection miConexion, ObservableList<Industrial>lista,
			String actividades, String zona) {
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
					"A.comentarios, " +
					"B.actividad, " +
					"C.zona " +
					"FROM industriales A "+ 
					"INNER JOIN actividades B " +
					"ON (A.codigo_actividad = B.codigo_actividad) "+
					"INNER JOIN zonas C " +
					"ON (A.codigo_zona = C.codigo_zona) " +
					"WHERE" + actividades + " AND zona = 'España' OR zona = '"+ zona
					
					);
			
			while(rs.next()) {
				lista.add(
						new Industrial(
								rs.getInt("codigo_industrial"),
								rs.getString("nombre"),
								rs.getString("apellidos"),
								rs.getString("email"),
								rs.getString("telefono"),
								rs.getString("telefono02"),
								new Actividad(rs.getInt("codigo_actividad"),rs.getString("actividad")),
								rs.getString("empresa"),
								rs.getString("localidad"),
								new Zona(rs.getInt("codigo_zona"),rs.getString("zona")),
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

	/**
	 * Metodo para obtener el codigo del industrial
	 * 
	 * @return devuelve el codigo
	 */
	public final IntegerProperty codigo_industrialProperty() {
		return this.codigo_industrial;
	}
	
	/**
	 * Metodo para obtener el codigo del industrial
	 * 
	 * @return devuelve el codigo
	 */
	public final int getCodigo_industrial() {
		return this.codigo_industrialProperty().get();
	}
	
	/**
	 * Método para establecer el codigo del industrial
	 * 
	 * @param codigo_industrial, debe ser un número entero
	 */
	public final void setCodigo_industrial(final int codigo_industrial) {
		this.codigo_industrialProperty().set(codigo_industrial);
	}
	
	/**
	 * Método para obtener el nombre del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty nombreProperty() {
		return this.nombre;
	}
	
	/**
	 * Método para obtener el nombre delindustrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getNombre() {
		return this.nombreProperty().get();
	}
	
	/**
	 * Método para establecer el nombre del industrial
	 * 
	 * @param nombre, debe ser una cadena de texto
	 */
	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	
	/**
	 * Método para obtener los apellidos del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty apellidosProperty() {
		return this.apellidos;
	}
	
	/**
	 * Método para obtener los apellidos del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getApellidos() {
		return this.apellidosProperty().get();
	}
	
	/**
	 * Método para establecer los apellidos del industrial
	 * 
	 * @param apellidos, debe ser una cadena de texto
	 */
	public final void setApellidos(final String apellidos) {
		this.apellidosProperty().set(apellidos);
	}
	
	/**
	 * Método para obtener el email del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty emailProperty() {
		return this.email;
	}
	
	/**
	 * Método para obtener el email del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getEmail() {
		return this.emailProperty().get();
	}
	
	/**
	 * Método para establecer el email del industrial
	 * 
	 * @param email, debe ser una cadena de texto
	 */
	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}
	
	/**
	 * Método para obtener el telefono del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty telefonoProperty() {
		return this.telefono;
	}
	
	/**
	 * Método para obtener el telefono del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getTelefono() {
		return this.telefonoProperty().get();
	}
	
	/**
	 * Método para establecer el telefono del industrial
	 * 
	 * @param telefono, debe ser una cadena de texto
	 */
	public final void setTelefono(final String telefono) {
		this.telefonoProperty().set(telefono);
	}
	
	/**
	 * Método para obtener el telefono02 del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty telefono02Property() {
		return this.telefono02;
	}
	
	/**
	 * Método para obtener el telefono02 del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getTelefono02() {
		return this.telefono02Property().get();
	}
	
	/**
	 * Método para establecer el telefono02 del industrial
	 * 
	 * @param telefono02, debe ser una cadena de texto
	 */
	public final void setTelefono02(final String telefono02) {
		this.telefono02Property().set(telefono02);
	}	
	
	/**
	 * Método para obtener la empresa del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty empresaProperty() {
		return this.empresa;
	}
	
	/**
	 * Método para obtener la empresa del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getEmpresa() {
		return this.empresaProperty().get();
	}
	
	/**
	 * Método para establecer la empresa del industrial
	 * 
	 * @param empresa, debe ser una cadena de texto
	 */
	public final void setEmpresa(final String empresa) {
		this.empresaProperty().set(empresa);
	}
	
	/**
	 * Método para obtener la localidad del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty localidadProperty() {
		return this.localidad;
	}
	
	/**
	 * Método para obtener la localidad del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getLocalidad() {
		return this.localidadProperty().get();
	}
	
	/**
	 * Método para establecer la localidad del industrial
	 * 
	 * @param localidad, debe ser una cadena de texto
	 */
	public final void setLocalidad(final String localidad) {
		this.localidadProperty().set(localidad);
	}
	
	/**
	 * Método para obtener los comentarios del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty comentariosProperty() {
		return this.comentarios;
	}
	
	/**
	 * Método para obtener los comentarios del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getComentarios() {
		return this.comentariosProperty().get();
	}
	
	/**
	 * Método para establecer los comentarios del industrial
	 * 
	 * @param comentarios, devuelve una cadena de texto
	 */
	public final void setComentarios(final String comentarios) {
		this.comentariosProperty().set(comentarios);
	}

	/**
	 * Método para obtener la Actividad del industrial
	 * 
	 * @return devuelve una actividad
	 */
	public Actividad getActividad() {
		return actividad;
	}
	
	/**
	 * Método para establecer la actividad del Industrial
	 * 
	 * @param actividad, debe ser una actividad recogida en la tabla actividades de la BD
	 */
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	/**
	 * Método para obtener la Zona del industrial
	 * 
	 * @return devuelve una zona
	 */
	public Zona getZona() {
		return zona;
	}

	/**
	 * Método para establecer la zona del Industrial
	 * 
	 * @param zona, debe ser una zona recogida en la tabla zonas de la BD
	 */
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
	
}
