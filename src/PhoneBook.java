import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PhoneBook extends Application{
	public void start(Stage stage) throws Exception{
		Parent root = (Parent)
				FXMLLoader.load(getClass().getResource("PhoneBook.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("PhoneBook");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
		System.out.println();
	}
}
