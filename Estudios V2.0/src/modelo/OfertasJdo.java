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
import javax.persistence.EntityManager;
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
	private int codigo_industrial;
	
	/**
	 * Atributo de tipo cadena de texto, define el nombre de pila del industrial.
	 */
	private String nombre;
	
	/**
	 * Atributo de tipo cadena de texto, define los apellidos del industrial.
	 */
	private String apellidos;
	
	/**
	 * Atributo de tipo cadena de texto, define el email del industrial.
	 */
	private String email;
	
	/**
	 * Atributo de tipo cadena de texto, define un telefono del industrial
	 */
	private String telefono;
	
	/**
	 * Atributo de tipo cadena de texto, define la actividad a la que se dedica el industrial
	 */
	private String actividad;
	
	/**
	 * Atributo de tipo cadena de texto, define el nombre de la empresa para la que trabaja el 
	 * industrial.
	 */
	private String empresa;
	
	/**
	 * Atributo de tipo Fecha(Date), define la fecha de solicitud de oferta 
	 * industrial.
	 */
	private String solicitada;
	
	/**
	 * Atributo de tipo Fecha(Date), define la fecha de solicitud de oferta 
	 * industrial.
	 */
	private String estado;
	
	/**
	 * Atributo de tipo cadena de texto, son comentarios que se pueden insertar en el industrial
	 * para tener mas datos sobre él.
	 */
	private String comentarios;
	
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
		this.codigo_industrial = codigo_industrial;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;		
		this.actividad = actividad;
		this.empresa = empresa;
		this.solicitada = solicitada;
		this.estado = estado;
		this.comentarios = comentarios;		
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
	public static void datosTablaIndustrialesEstudio (Connection miConexion, EntityManager operador,
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
					"WHERE (" + actividades + ") AND (zona = 'España' OR zona = '"+ zona + ")"
					
					);
			
			while(rs.next()) {
				operador.persist(
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
			e.printStackTrace();
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
	public int getCodigo_industrial() {
		return codigo_industrial;
	}
	
	/**
	 * Método para establecer el codigo del industrial
	 * 
	 * @param codigo_industrial, debe ser un número entero
	 */
	public void setCodigo_industrial(int codigo_industrial) {
		this.codigo_industrial = codigo_industrial;
	}
		
	/**
	 * Método para obtener el nombre delindustrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Método para establecer el nombre del industrial
	 * 
	 * @param nombre, debe ser una cadena de texto
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	/**
	 * Método para obtener los apellidos del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public String getApellidos() {
		return apellidos;
	}
	
	/**
	 * Método para establecer los apellidos del industrial
	 * 
	 * @param apellidos, debe ser una cadena de texto
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	/**
	 * Método para obtener el email del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Método para establecer el email del industrial
	 * 
	 * @param email, debe ser una cadena de texto
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	/**
	 * Método para obtener el telefono del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Método para establecer el telefono del industrial
	 * 
	 * @param telefono, debe ser una cadena de texto
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
		
	/**
	 * Método para obtener la actividad del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public String getActividad() {
		return actividad;
	}
	
	/**
	 * Método para establecer la actividad del industrial
	 * 
	 * @param actividad, debe ser una cadena de texto
	 */
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
			
	/**
	 * Método para obtener cuando ha sido solicitada la oferta al industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public String getSolicitada() {
		return solicitada;
	}
	
	/**
	 * Método para establecer cuando ha sido solicitada la oferta al industrial
	 * 
	 * @param empresa, debe ser una cadena de texto
	 */
	public void setSolicitada(String solicitada) {
		this.solicitada = solicitada;
	}
	
	/**
	 * Método para obtener la empresa del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public String getEmpresa() {
		return empresa;
	}
	
	/**
	 * Método para establecer la empresa del industrial
	 * 
	 * @param empresa, debe ser una cadena de texto
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	/**
	 * Método para obtener el estado de la oferta
	 * 
	 * @return devuelve una cadena de texto
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Método para establecer el estado de la oferta
	 * 
	 * @param comentarios, devuelve una cadena de texto
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
		
	/**
	 * Método para obtener los comentarios del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public String getComentarios() {
		return comentarios;
	}
	
	/**
	 * Método para establecer los comentarios del industrial
	 * 
	 * @param comentarios, devuelve una cadena de texto
	 */
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
}
