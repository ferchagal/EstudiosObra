package controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo.Actividad;
import modelo.Conexion;
import modelo.Zona;

public class Panel_Industriales_Controller implements Initializable{
	
	@FXML TextField txtCodigoIndustrial, txtNombre, txtApellidos, txtEmail, txtTelefono, txtTelefono02,
					txtEmpresa,txtLocalidad,txtComentarios;
	
	@FXML Label etiEstado;
	
	@FXML ComboBox <Zona> cbZona;
	
	ObservableList <Zona> listaZonas;
	
	@FXML ComboBox <Actividad> cbActividad;
	
	ObservableList <Actividad> listaActividades;
	
	private Conexion miConexion;
	
	@FXML public void guardarIndustrial (ActionEvent Event) {
		
	}
	
	@FXML public void actualizarIndustrial (ActionEvent Event) {
		
	}
	
	@FXML public void eliminarIndustrial (ActionEvent Event) {
		
	}
	
	@FXML public void nuevoIndustrial (ActionEvent Event) {
		
	}
	
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Conectamos con la BD
		miConexion = new Conexion();
		miConexion.conectarBD();
		
		//Inicializamos los dos ObservableList
		listaActividades = FXCollections.observableArrayList();
		listaZonas = FXCollections.observableArrayList();
		
		//Llenamos los combobox
		Actividad.datosComboActividades(miConexion.getConnection(), listaActividades);
		Zona.datosComboZonas(miConexion.getConnection(), listaZonas);
		
		//Añadimos los objetos a los comboBox
		cbActividad.setItems(listaActividades);
		cbZona.setItems(listaZonas);
		
		//cerramos la conexion
		miConexion.cerrarConexionBD();
	}

}
