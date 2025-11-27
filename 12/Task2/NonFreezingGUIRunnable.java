import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NonFreezingGUIRunnable extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Запустить бесконечный цикл (Runnable)");
        
        button.setOnAction(e -> {
            // Создаем Runnable для фоновой задачи
            Runnable backgroundTask = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println("Выполняется в фоновом потоке (Runnable)...");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            break;
                        }
                    }
                }
            };
            
            // Запускаем в отдельном потоке
            Thread backgroundThread = new Thread(backgroundTask);
            backgroundThread.setDaemon(true);
            backgroundThread.start();
        });
        
        VBox root = new VBox(10, button);
        Scene scene = new Scene(root, 300, 200);
        
        primaryStage.setTitle("Независающий GUI - Runnable");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}