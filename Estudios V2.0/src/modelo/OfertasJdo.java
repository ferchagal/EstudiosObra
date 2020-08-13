/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: OfertasJdo.java
 * Objetivo: Clase modelo - OfertaslJdo.
 * Equipo/Persona: Fernando Chacón Galea  28.614.518 - B
 */
package modelo;

import java.io.Serializable;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Clase para crear persistencia de objetos, se creará un archivo .odb por cada Estudio que hagamos
 * 
 * @author Fernando Chacón Galea
 * @version 2020.06.22 - V2
 * 
 * Entidad manejada por EntityManager
 */
@Entity
public class OfertasJdo implements Serializable {
		
	/**
	 * Atributo de clase de tipo long, define un codigo para cada industrial, propio del estudio actual.
	 */
	@Id @GeneratedValue
	private long id;
	
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
	 * Atributo de tipo cadena de texto, define la actividad a la que se dedica el industrial
	 */
	private StringProperty actividad;
	
	/**
	 * Atributo de tipo cadena de texto, define el nombre de la empresa para la que trabaja el 
	 * industrial.
	 */
	private StringProperty empresa;
	
	/**
	 * Atributo de tipo Fecha(Date), define la fecha de solicitud de oferta 
	 * industrial.
	 */
	private StringProperty solicitada;
	
	/**
	 * Atributo de tipo Fecha(Date), define la fecha de solicitud de oferta 
	 * industrial.
	 */
	private StringProperty estado;
	
	/**
	 * Atributo de tipo cadena de texto, son comentarios que se pueden insertar en el industrial
	 * para tener mas datos sobre él.
	 */
	private StringProperty comentarios;
	
	/**
	 * Constructor de clase
	 * 
	 * @param codigo_industrial, numero entero 
	 * @param nombre, cadena de texto
	 * @param apellidos, cadena de texto
	 * @param email, cadena de texto
	 * @param telefono, cadena de texto
	 * @param actividad, cadena de texto
	 * @param empresa, cadena de texto
	 * @param solicitada, cadena de texto
	 * @param estado, cadena de texto
	 * @param comentarios, cadena de texto
	 */
	public OfertasJdo(Integer codigo_industrial, String nombre, String apellidos,String email,
			String telefono,  String actividad, String empresa, String solicitada,
			String estado, String comentarios) {
		this.codigo_industrial = new SimpleIntegerProperty(codigo_industrial);
		this.nombre = new SimpleStringProperty(nombre);
		this.apellidos = new SimpleStringProperty(apellidos);
		this.email = new SimpleStringProperty(email);
		this.telefono = new SimpleStringProperty (telefono);		
		this.actividad = new SimpleStringProperty(actividad);
		this.empresa = new SimpleStringProperty (empresa);
		this.solicitada = new SimpleStringProperty (solicitada);
		this.estado = new SimpleStringProperty(estado);
		this.comentarios = new SimpleStringProperty (comentarios);		
	}
	
	/**
	 * Método para poder cargar el tableView desde los diferentes estudios
	 * 
	 * @param miConexion, objeto de tipo Connection
	 * @param lista, lista de tipo Estudio, se cargan todos los estudios en ella
	 */
	public static void datosTablaOfertasJdo(Connection miConexion, ObservableList<OfertasJdo>lista) {
		try {
			 Statement consulta = miConexion.createStatement();
			 
			 ResultSet rs = consulta.executeQuery(
					 "SELECT A.codigo_industrial, " +
					 "A.nombre, " +
					 "A.apellidos, " +
					 "A.email, " +
					 "A.telefono, " +
					 "A.actividad, " +
					 "A.empresa, " +
					 "A.solicitada, " +
					 "A.estado, " +
					 "A.comentarios, " +
					 "FROM industriales A "
					 );
					 
			 while(rs.next()) {
				 lista.add(
						 new OfertasJdo(
								 rs.getInt("codigo_industrial"),
								 rs.getString("nombre"),
								 rs.getString("apellidos"),
								 rs.getString("email"),
								 rs.getString("telefono"),
								 rs.getString("actividad"),
								 rs.getString("empresa"),
								 rs.getString("solicitada"),
								 rs.getString("estado"),
								 rs.getString("comentarios")
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
	 * Método para poder cargar el tableView Industriales filtrados para ofertas Estudio desde la tabla 
	 * industriales de la BD
	 * 
	 * @param miConexion, objeto de tipo Connection
	 * @param lista, lista de tipo Industrial, se cargan todos los industriales en ella
	 * @param actividades, cadena de texto que se le pasa como parametro parte de la consulta, la cadena
	 * se crea con los checkBox seleccionados
	 * @param zona, cadena de texto que se le pasa como parametro parte de la consulta, se obtiene de la
	 * seleccion hecho en el comboBox cbZona 
	 */
	public static void datosTablaIndustrialesEstudio (Connection miConexion, ObservableList<OfertasJdo>lista,
			String actividades, String zona) {
		
		//Creamos un objeto de tipo Date y lo transformamos al formato de fecha deseado
		Date utilDate = new Date();
		utilDate.getTime();
		String formatoFecha = "dd/MM/yyyy";
		SimpleDateFormat fecha = new SimpleDateFormat(formatoFecha);
		
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
						new OfertasJdo(
								 rs.getInt("codigo_industrial"),
								 rs.getString("nombre"),
								 rs.getString("apellidos"),
								 rs.getString("email"),
								 rs.getString("telefono"),
								 rs.getString("actividad"),
								 rs.getString("empresa"),
								 fecha.format(utilDate),
								 "Pendiente",
								 "Sin comentarios"
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
	 * Método para obtener la actividad del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty actividadProperty() {
		return this.actividad;
	}
	
	/**
	 * Método para obtener la actividad del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getActividad() {
		return this.actividadProperty().get();
	}
	
	/**
	 * Método para establecer la actividad del industrial
	 * 
	 * @param actividad, debe ser una cadena de texto
	 */
	public final void setActividad(final String actividad) {
		this.actividadProperty().set(actividad);
	}
		
	/**
	 * Método para obtener cuando ha sido solicitada la oferta al industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty solicitadaProperty() {
		return this.solicitada;
	}
	
	/**
	 * Método para obtener cuando ha sido solicitada la oferta al industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getSolicitada() {
		return this.solicitadaProperty().get();
	}
	
	/**
	 * Método para establecer cuando ha sido solicitada la oferta al industrial
	 * 
	 * @param empresa, debe ser una cadena de texto
	 */
	public final void setSolicitada(final String solicitada) {
		this.empresaProperty().set(solicitada);
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
	 * Método para obtener el estado de la oferta
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty estadoProperty() {
		return this.estado;
	}
	
	/**
	 * Método para obtener el estado de la oferta
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getEstado() {
		return this.estadoProperty().get();
	}
	
	/**
	 * Método para establecer el estado de la oferta
	 * 
	 * @param comentarios, devuelve una cadena de texto
	 */
	public final void setEstado(final String estado) {
		this.estadoProperty().set(estado);
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
}
