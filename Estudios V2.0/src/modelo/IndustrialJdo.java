/**
 * M�dulo: Estudios de Obra. Aplicaci�n de escritorio
 * Archivo: IndustrialJdo.java
 * Objetivo: Clase modelo - industrialJdo.
 * Equipo/Persona: Fernando Chac�n Galea  28.614.518 - B
 */
package modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase para crear persistencia de objetos, se crear� un archivo .odb por cada Estudio que hagamos
 * 
 * @author Fernando Chac�n Galea
 * @version 2020.06.22 - V2
 * 
 * Entidad manejada por EntityManager
 */
@Entity
public class IndustrialJdo implements Serializable {
	
	/**
	 * Atributo de clase de tipo long, define un codigo para cada industrial, propio del estudio actual.
	 */
	@Id @GeneratedValue
	private long id;
	
	/**
	 * Atributo de tipo entero, define un c�digo para cada industrial. Es asignado automaticamente
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
	 * empresa a la que pertenece el industrial, o su delegaci�n.
	 */
	private StringProperty localidad;
	
	/**
	 * Atributo de tipo Zona, define la zona donde actua la empresa a la que pertenece el
	 * el industrial
	 */
	private Zona zona;
	
	/**
	 * Atributo de tipo cadena de texto, son comentarios que se pueden insertar en el industrial
	 * para tener mas datos sobre �l.
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
	 * @param telefono02, cadena de texto
	 * @param actividad, obtejo de tipo actividad
	 * @param empresa, cadena de texto
	 * @param localidad, cadena de texto
	 * @param zona, objeto de tipo zona
	 * @param comentarios, cadena de texto
	 */
	public IndustrialJdo(Integer codigo_industrial, String nombre, String apellidos,String email,
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
	 * M�todo para establecer el codigo del industrial
	 * 
	 * @param codigo_industrial, debe ser un n�mero entero
	 */
	public final void setCodigo_industrial(final int codigo_industrial) {
		this.codigo_industrialProperty().set(codigo_industrial);
	}
	
	/**
	 * M�todo para obtener el nombre del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty nombreProperty() {
		return this.nombre;
	}
	
	/**
	 * M�todo para obtener el nombre delindustrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getNombre() {
		return this.nombreProperty().get();
	}
	
	/**
	 * M�todo para establecer el nombre del industrial
	 * 
	 * @param nombre, debe ser una cadena de texto
	 */
	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	
	/**
	 * M�todo para obtener los apellidos del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty apellidosProperty() {
		return this.apellidos;
	}
	
	/**
	 * M�todo para obtener los apellidos del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getApellidos() {
		return this.apellidosProperty().get();
	}
	
	/**
	 * M�todo para establecer los apellidos del industrial
	 * 
	 * @param apellidos, debe ser una cadena de texto
	 */
	public final void setApellidos(final String apellidos) {
		this.apellidosProperty().set(apellidos);
	}
	
	/**
	 * M�todo para obtener el email del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty emailProperty() {
		return this.email;
	}
	
	/**
	 * M�todo para obtener el email del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getEmail() {
		return this.emailProperty().get();
	}
	
	/**
	 * M�todo para establecer el email del industrial
	 * 
	 * @param email, debe ser una cadena de texto
	 */
	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}
	
	/**
	 * M�todo para obtener el telefono del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty telefonoProperty() {
		return this.telefono;
	}
	
	/**
	 * M�todo para obtener el telefono del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getTelefono() {
		return this.telefonoProperty().get();
	}
	
	/**
	 * M�todo para establecer el telefono del industrial
	 * 
	 * @param telefono, debe ser una cadena de texto
	 */
	public final void setTelefono(final String telefono) {
		this.telefonoProperty().set(telefono);
	}
	
	/**
	 * M�todo para obtener el telefono02 del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty telefono02Property() {
		return this.telefono02;
	}
	
	/**
	 * M�todo para obtener el telefono02 del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getTelefono02() {
		return this.telefono02Property().get();
	}
	
	/**
	 * M�todo para establecer el telefono02 del industrial
	 * 
	 * @param telefono02, debe ser una cadena de texto
	 */
	public final void setTelefono02(final String telefono02) {
		this.telefono02Property().set(telefono02);
	}	
	
	/**
	 * M�todo para obtener la empresa del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty empresaProperty() {
		return this.empresa;
	}
	
	/**
	 * M�todo para obtener la empresa del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getEmpresa() {
		return this.empresaProperty().get();
	}
	
	/**
	 * M�todo para establecer la empresa del industrial
	 * 
	 * @param empresa, debe ser una cadena de texto
	 */
	public final void setEmpresa(final String empresa) {
		this.empresaProperty().set(empresa);
	}
	
	/**
	 * M�todo para obtener la localidad del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty localidadProperty() {
		return this.localidad;
	}
	
	/**
	 * M�todo para obtener la localidad del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getLocalidad() {
		return this.localidadProperty().get();
	}
	
	/**
	 * M�todo para establecer la localidad del industrial
	 * 
	 * @param localidad, debe ser una cadena de texto
	 */
	public final void setLocalidad(final String localidad) {
		this.localidadProperty().set(localidad);
	}
	
	/**
	 * M�todo para obtener los comentarios del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final StringProperty comentariosProperty() {
		return this.comentarios;
	}
	
	/**
	 * M�todo para obtener los comentarios del industrial
	 * 
	 * @return devuelve una cadena de texto
	 */
	public final String getComentarios() {
		return this.comentariosProperty().get();
	}
	
	/**
	 * M�todo para establecer los comentarios del industrial
	 * 
	 * @param comentarios, devuelve una cadena de texto
	 */
	public final void setComentarios(final String comentarios) {
		this.comentariosProperty().set(comentarios);
	}

	/**
	 * M�todo para obtener la Actividad del industrial
	 * 
	 * @return devuelve una actividad
	 */
	public Actividad getActividad() {
		return actividad;
	}
	
	/**
	 * M�todo para establecer la actividad del Industrial
	 * 
	 * @param actividad, debe ser una actividad recogida en la tabla actividades de la BD
	 */
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	/**
	 * M�todo para obtener la Zona del industrial
	 * 
	 * @return devuelve una zona
	 */
	public Zona getZona() {
		return zona;
	}

	/**
	 * M�todo para establecer la zona del Industrial
	 * 
	 * @param zona, debe ser una zona recogida en la tabla zonas de la BD
	 */
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
}
