package controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modelo.Actividad;
import modelo.Zona;

public class Panel_Industriales_Controller implements Initializable{
	
	@FXML TextField txtCodigoIndustrial, txtNombre, txtApellidos, txtEmail, txtTelefono, txtTelefono02,
					txtEmpresa,txtLocalidad,txtComentarios;
	
	@FXML ComboBox <Zona> cbZona;
	
	@FXML ComboBox <Actividad> cbActividad;
	
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Apéndice de método generado automáticamente
		
	}

}
