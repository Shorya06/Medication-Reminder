package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * in fxml file:
 * fx:id refers to the object, like newButton
 * onAction refers to the method that is called
 */
	
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class Controller implements Initializable {

    @FXML private Label homeTimeLabel;
    @FXML private Label homeDateLabel;
    
    @FXML private Button newButton;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    
    @FXML private TableColumn<?, ?> colHomeTime;
    @FXML private TableColumn<?, ?> colHomeName;
    @FXML private TableColumn<?, ?> colHomeStatus;
    @FXML private TableColumn<Medication, String> colMyDose;
    @FXML private TableColumn<Medication, String> colMyName;
    @FXML private TableColumn<Medication, String> colMyFrequency;
    @FXML private TableColumn<Medication, String> colMyTime;
    @FXML private TableColumn<?, ?> colLogName;
    @FXML private TableColumn<?, ?> colLogTime;
    @FXML private TableColumn<?, ?> colLogDescription;
    
    @FXML private TableView<Medication> medicationTable;
    
    
    // initialize controller class
    @Override
	public void initialize(URL url, ResourceBundle resources) {
		System.out.println("Initializing...");
		colMyName.setCellValueFactory(new PropertyValueFactory<Medication, String>("name"));
		colMyDose.setCellValueFactory(new PropertyValueFactory<Medication, String>("dose"));
		colMyTime.setCellValueFactory(new PropertyValueFactory<Medication, String>("time"));
		colMyFrequency.setCellValueFactory(new PropertyValueFactory<Medication, String>("frequency"));
		
		try {
			medicationTable.setItems(getMedications());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// make columns editable
		medicationTable.setEditable(true);
		colMyName.setCellFactory(TextFieldTableCell.forTableColumn());
		colMyDose.setCellFactory(TextFieldTableCell.forTableColumn());
		colMyTime.setCellFactory(TextFieldTableCell.forTableColumn());
		colMyFrequency.setCellFactory(TextFieldTableCell.forTableColumn());
		
		System.out.println("Done initializing.");
	}
    
    /** 
     * @return List of medications for My Medications page from file.
     * @throws FileNotFoundException 
     */
    private ObservableList<Medication> getMedications() throws FileNotFoundException {
    	ObservableList<Medication> medications = FXCollections.observableArrayList();
    	Scanner scanner = new Scanner(new File("src\\library\\medications.txt"));
    	while (scanner.hasNextLine()) {
    		String[] nextLine = scanner.nextLine().split(";");
    		medications.add(new Medication(nextLine[0],nextLine[1],nextLine[2],nextLine[3]));
    	}
    	scanner.close();
    	return medications;
    }
    
    /**
     * Creates list of medications to take today on the home list.
     */
    private ObservableList<Medication> getTodaysMedications() {
    	ObservableList<Medication> medications = FXCollections.observableArrayList();
    	medications.add(new Medication("Advil", "12:00", "Daily", "2"));
    	medications.add(new Medication("Tylenol", "3:00", "Daily", "1"));
    	return medications;
    }

    /**
     * Event for editing cells.
     */
    public void changeMyNameCellEvent(CellEditEvent editedCell) {
    	Medication medicationSelected = medicationTable.getSelectionModel().getSelectedItem();
    	medicationSelected.setName(editedCell.getNewValue().toString());
    }
    
    /**
     * allows medications to be deleted.
     */
    @FXML
    public void deleteButtonClick() {
    	ObservableList<Medication> selectedRow, allMedications;
    	allMedications = medicationTable.getItems();
    	selectedRow = medicationTable.getSelectionModel().getSelectedItems();
    	allMedications.remove(selectedRow.get(0));
    }
    
    /**
     * creates pop up to add new medication!
     */
    @FXML
    private void newButtonClick() {
    	System.out.println("new");
    	newButton.setText("clicked -_-");
    }


}

