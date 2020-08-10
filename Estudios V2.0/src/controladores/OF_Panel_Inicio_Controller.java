package controladores;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import modelo.Actividad;
import modelo.Estudio;
import modelo.IndustrialJdo;

public class OF_Panel_Inicio_Controller implements Initializable{
	
	@FXML private ComboBox <Estudio> cbEstudios;
	
	@FXML private TableColumn <IndustrialJdo,Integer> clCodigo_Industrial;
	@FXML private TableColumn <IndustrialJdo, String> clNombre;
	@FXML private TableColumn <IndustrialJdo, String> clApellidos;
	@FXML private TableColumn <IndustrialJdo, String> clEmail;
	@FXML private TableColumn <IndustrialJdo, String> clTelefono;
	@FXML private TableColumn <IndustrialJdo, Actividad> clActividad;
	@FXML private TableColumn <IndustrialJdo, Date> clSolicitada;
	@FXML private TableColumn <IndustrialJdo, String> clEstado;
	@FXML private TableColumn <IndustrialJdo, String> clComentarios;
	
	
	@FXML private void cargarListaEstudio() {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Apéndice de método generado automáticamente
		
	}

}
