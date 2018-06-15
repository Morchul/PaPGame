import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.MainScene;

public class Main extends Application{

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(new MainScene().getContent(), 500, 500));
        primaryStage.setTitle("PaP Creator");
        primaryStage.show();

        //TODO Create collection with existing images don't move the images occurs in error
    }
}
