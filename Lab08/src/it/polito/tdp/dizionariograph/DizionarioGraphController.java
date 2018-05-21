/**
 * Sample Skeleton for 'DizionarioGraph.fxml' Controller Class
 */

package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtLettere"
    private TextField txtLettere; // Value injected by FXMLLoader

    @FXML // fx:id="txtParola"
    private TextField txtParola; // Value injected by FXMLLoader

    @FXML // fx:id="btnGeneraGrafo"
    private Button btnGeneraGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnTrovaVicini"
    private Button btnTrovaVicini; // Value injected by FXMLLoader

    @FXML // fx:id="btnTrovaGradoMax"
    private Button btnTrovaGradoMax; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void handleGenera(ActionEvent event) {
    	txtResult.clear();
		txtParola.clear();

		try {
			int numeroLettere = Integer.parseInt(txtLettere.getText());
			System.out.println("numero di Lettere: " + numeroLettere);

			List<String> parole = model.createGraph(numeroLettere);

			if (parole != null) {
				txtResult.setText("Trovate " + parole.size() + " parole di lunghezza " + numeroLettere);
			} else {
				txtResult.setText("Trovate 0 parole di lunghezza: " + numeroLettere);
			}

			txtLettere.setDisable(true);
			btnGeneraGrafo.setDisable(true);
			txtParola.setDisable(false);
			btnTrovaVicini.setDisable(false);
			btnTrovaGradoMax.setDisable(false);

		} catch (NumberFormatException nfe) {
			txtResult.setText("Inserire un numero corretto di lettere!");
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
    }

    @FXML
    void handleReset(ActionEvent event) {
    	txtResult.clear();
		txtLettere.clear();
		txtParola.clear();
		txtLettere.setDisable(false);
		txtParola.setDisable(true);
		btnGeneraGrafo.setDisable(false);
		btnTrovaVicini.setDisable(true);
		btnTrovaGradoMax.setDisable(true);
    }

    @FXML
    void handleTrovaGradoMax(ActionEvent event) {
    	try {
			txtResult.setText(model.findMaxDegree());

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
    }

    @FXML
    void handleTrovaVicini(ActionEvent event) {
    	try {
			String parolaInserita = txtParola.getText();
			if (parolaInserita == null || parolaInserita.length() == 0) {
				txtResult.setText("Inserire una parola da cercare");
				return;
			}

			List<String> parole = model.displayNeighbours(parolaInserita);
			if (parole != null) {
				txtResult.setText(parole.toString());
			} else {
				txtResult.setText("Non è stato trovato nessun risultato");
			}
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtLettere != null : "fx:id=\"txtLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaGradoMax\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        
        txtParola.setDisable(true);
		btnTrovaVicini.setDisable(true);
		btnTrovaGradoMax.setDisable(true);
    }

	public void setModel(Model model) {
		this.model = model;
	}
}
