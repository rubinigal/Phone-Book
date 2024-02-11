import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PhoneBookController {
	private final int MAX_COLUMS = 2;
	private final int MAX_ROWS = 9;
	private Text[] texts; 
	private int index = 0;
	private PhoneBookLogic phoneBookLogic = new PhoneBookLogic();
	
    @FXML
    private GridPane grid;

    @FXML
    private TextField nameText;

    @FXML
    private TextField phoneText;
    
    @FXML
    private Button prevButton;

    @FXML
    private Button nextButton;
   
    public void initialize() {
    	texts = new Text[(MAX_COLUMS*MAX_ROWS) - 2];
    	// Filling the grid with Text elements
    	for (int row = 1; row < MAX_ROWS; row++) {
            for (int col = 0; col < MAX_COLUMS; col++) {
                texts[(row-1)*2 + col] = new Text("");
                grid.add(texts[(row-1)*2 + col], col, row);
            }
        }
    	updatePage();
    }

    @FXML
    void addPressed(ActionEvent event) {
    	try {
    		phoneBookLogic.add(nameText.getText(), phoneText.getText());
    		updatePage();
    	} catch (NumberFormatException e) {
    		popUp("Error: Invalid phone number format.");
    	}
    }
    
    @FXML
    void deletePressed(ActionEvent event) {
    	try {
    		phoneBookLogic.delete(nameText.getText(), phoneText.getText());
    		updatePage();
    	} catch (NumberFormatException e) {
    		popUp("Error: Invalid phone number format.");
    	}
    }

    @FXML
    void nextPressed(ActionEvent event) {
    	index = index + MAX_ROWS - 1;
    	updatePage();
    }

    @FXML
    void prevPressed(ActionEvent event) {
    	index = index - MAX_ROWS + 1;
    	updatePage();
    }

    @FXML
    void searchPressed(ActionEvent event) {	
    	Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle("Sreach");
        informationAlert.setHeaderText(null);
        informationAlert.setContentText(phoneBookLogic.search(nameText.getText()));
        informationAlert.showAndWait();
    }
    
    @FXML
    void updatePressed(ActionEvent event) {
    	addPressed(event);
    }
    
    private void popUp(String str) {
    	Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle("Error");
        informationAlert.setHeaderText(null);
        informationAlert.setContentText(str);
        informationAlert.showAndWait();
    }
    
    private void updatePage() {
    	Contact[] contacts = phoneBookLogic.loadPage(MAX_ROWS - 1, index);
    	
    	for (int i = 0; i < texts.length; i += 2) {
    		texts[i].setText(contacts[i/2].getName());
    		texts[i+1].setText(contacts[i/2].getPhoneNumber());
    	}
    	
    	nextButton.setDisable(false);
    	prevButton.setDisable(false);
    	if (index == 0)
    		prevButton.setDisable(true);
    	if (texts[texts.length - 1].getText().equals(""))
    		nextButton.setDisable(true);
    }

}
