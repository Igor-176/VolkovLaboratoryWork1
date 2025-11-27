import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProgressBarWithThreadControl extends Application {
    
    private ProgressBar progressBar;
    private Button startButton;
    private Button pauseButton;
    private Button stopButton;
    
    private WorkerThread workerThread;
    private volatile boolean isRunning = false;
    private volatile boolean isPaused = false;
    
    @Override
    public void start(Stage primaryStage) {
        // Создание элементов интерфейса
        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(300);
        
        startButton = new Button("Старт");
        pauseButton = new Button("Пауза");
        stopButton = new Button("Стоп");
        
        pauseButton.setDisable(true);
        stopButton.setDisable(true);
        
        // Обработчики кнопок
        startButton.setOnAction(e -> startTask());
        pauseButton.setOnAction(e -> togglePause());
        stopButton.setOnAction(e -> stopTask());
        
        VBox root = new VBox(10, progressBar, startButton, pauseButton, stopButton);
        Scene scene = new Scene(root, 350, 150);
        
        primaryStage.setTitle("Управление потоком с ProgressBar");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void startTask() {
        if (workerThread != null && workerThread.isAlive()) {
            workerThread.interrupt();
        }
        
        workerThread = new WorkerThread();
        workerThread.start();
        
        startButton.setDisable(true);
        pauseButton.setDisable(false);
        stopButton.setDisable(false);
        isRunning = true;
        isPaused = false;
        pauseButton.setText("Пауза");
    }
    
    private void togglePause() {
        if (workerThread != null) {
            synchronized (workerThread) {
                if (isPaused) {
                    // Продолжить выполнение
                    isPaused = false;
                    pauseButton.setText("Пауза");
                    workerThread.notify();
                } else {
                    // Поставить на паузу
                    isPaused = true;
                    pauseButton.setText("Продолжить");
                }
            }
        }
    }
    
    private void stopTask() {
        if (workerThread != null) {
            workerThread.interrupt();
            workerThread = null;
        }
        
        isRunning = false;
        isPaused = false;
        
        // Сброс интерфейса
        Platform.runLater(() -> {
            progressBar.setProgress(0);
            startButton.setDisable(false);
            pauseButton.setDisable(true);
            stopButton.setDisable(true);
            pauseButton.setText("Пауза");
        });
    }
    
    // Класс рабочего потока
    class WorkerThread extends Thread {
        private int currentIteration = 0;
        
        @Override
        public void run() {
            try {
                for (currentIteration = 0; currentIteration < 1000; currentIteration++) {
                    // Проверка прерывания
                    if (isInterrupted()) {
                        break;
                    }
                    
                    // Обработка паузы
                    synchronized (this) {
                        while (isPaused) {
                            wait();
                        }
                    }
                    
                    // Имитация работы
                    Thread.sleep(20);
                    
                    // Обновление ProgressBar в UI потоке
                    final int iteration = currentIteration;
                    Platform.runLater(() -> {
                        progressBar.setProgress((double) iteration / 1000);
                    });
                }
                
                // Завершение задачи
                Platform.runLater(() -> {
                    if (currentIteration >= 1000) {
                        progressBar.setProgress(1.0);
                    }
                    startButton.setDisable(false);
                    pauseButton.setDisable(true);
                    stopButton.setDisable(true);
                });
                
            } catch (InterruptedException e) {
                // Поток был прерван - нормальное завершение
                System.out.println("Поток прерван");
            }
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}