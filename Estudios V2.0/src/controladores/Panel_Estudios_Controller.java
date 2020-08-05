/**
 * Módulo: Estudios de Obra. Aplicación de escritorio
 * Archivo: Panel_Estudios_Controller.java
 * Objetivo: Archivo Controller del Panel de Estudios.
 * Equipo/Persona: Fernando Chacón Galea  28.614.518 - B
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import modelo.Conexion;
import modelo.Estudio;
import modelo.Usuario;
import modelo.Zona;

public class Panel_Estudios_Controller implements Initializable {
	
	@FXML private TextField txtCodigo_Estudio, txtReferencia, txtDesignacion, txtAnyo, txtAdjudicada;
	
	@FXML Button btnGuardar, btnEliminar, btnActualizar;
	
	@FXML private Label etiEstado;
	
	@FXML private ComboBox <Usuario> cbUsuario;
	
	private ObservableList <Usuario> listaUsuarios;
	
	@FXML private ComboBox <Zona> cbZona;
	
	private ObservableList <Zona> listaZonas;	
	
	
	@FXML private TableView <Estudio> tablaEstudios;
	
	@FXML private TableColumn<Estudio, Integer> clCodigo_Estudio;
	@FXML private TableColumn<Estudio, String> clReferencia;
	@FXML private TableColumn<Estudio, String> clDesignacion;
	@FXML private TableColumn<Estudio, Integer> clAnyo;
	@FXML private TableColumn<Estudio, String> clAdjudicada;
	@FXML private TableColumn<Estudio, Usuario> clUsuario;
	@FXML private TableColumn<Estudio, Zona> clZona;
	
	private Conexion miConexion;
	
	private ObservableList <Estudio> listaEstudios;
	
	@FXML public void guardarEstudio (ActionEvent Event) {
		//Creamos una nueva instancia de estudio
		Estudio est = new Estudio(0,
				txtReferencia.getText(),
				txtDesignacion.getText(),
				Integer.valueOf(txtAnyo.getText()),
				txtAdjudicada.getAccessibleText(),
				cbUsuario.getSelectionModel().getSelectedItem(),
				cbZona.getSelectionModel().getSelectedItem()
				);
		
		//abrimos la conexion
		miConexion.conectarBD();
		//llamamos al metodo guardar de la clase estudio
		int resultado = est.guardarEstudio(miConexion.getConnection());
		//Cerramos la conexion
		miConexion.cerrarConexionBD();
		
		if(resultado == 1) {
			//Añadimos el industrial insertado en la BBDD al Tableview Industriales
			listaEstudios.add(est);
			
			etiEstado.setText("Estudio insertado correctamente");
		}else {
			etiEstado.setText("Estudio no insertado...");
		}
	}
	
	@FXML public void actualizarEstudio (ActionEvent Event) {
		//Creamos una nueva instancia de estudio
		Estudio est = new Estudio(
				Integer.valueOf(txtCodigo_Estudio.getText()),
				txtReferencia.getText(),
				txtDesignacion.getText(),
				Integer.valueOf(txtAnyo.getText()),
				txtAdjudicada.getAccessibleText(),
				cbUsuario.getSelectionModel().getSelectedItem(),
				cbZona.getSelectionModel().getSelectedItem()
				);
				
		//abrimos la conexion
		miConexion.conectarBD();
		//llamamos al metodo guardar de la clase estudio
		int resultado = est.actualizarEstudio(miConexion.getConnection());
		//Cerramos la conexion
		miConexion.cerrarConexionBD();
				
		if(resultado == 1) {
			//Añadimos el industrial actualizado en la BBDD al Tableview Industriales
			listaEstudios.add(tablaEstudios.getSelectionModel().getSelectedIndex(),est);
			listaEstudios.remove(tablaEstudios.getSelectionModel().getSelectedIndex());
					
			etiEstado.setText("Estudio actualizado correctamente");
		}else {
			etiEstado.setText("No se ha podido actualizar el estudio...");
		}
	}
	
	@FXML public void eliminarEstudio(ActionEvent Event) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Apéndice de método generado automáticamente
		
	}

}
