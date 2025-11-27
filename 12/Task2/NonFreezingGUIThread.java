import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NonFreezingGUIThread extends Application {
    
    // Внутренний класс, наследуемый от Thread
    class BackgroundThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("Выполняется в фоновом потоке (Thread)...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    break;
                }
            }
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Запустить бесконечный цикл (Thread)");
        
        button.setOnAction(e -> {
            // Создаем и запускаем поток
            BackgroundThread backgroundThread = new BackgroundThread();
            backgroundThread.setDaemon(true);
            backgroundThread.start();
        });
        
        VBox root = new VBox(10, button);
        Scene scene = new Scene(root, 300, 200);
        
        primaryStage.setTitle("Независающий GUI - Thread");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}