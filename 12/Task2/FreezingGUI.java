import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FreezingGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Запустить бесконечный цикл");
        
        button.setOnAction(e -> {
            // Бесконечный цикл, который заблокирует GUI
            while (true) {
                System.out.println("Выполняется в основном потоке...");
            }
        });
        
        VBox root = new VBox(10, button);
        Scene scene = new Scene(root, 300, 200);
        
        primaryStage.setTitle("Зависающий GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}