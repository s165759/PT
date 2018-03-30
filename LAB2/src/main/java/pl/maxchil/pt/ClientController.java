package pl.maxchil.pt;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientController {
    @FXML private Stage stage;
    @FXML private Label statusLabel;
    @FXML private ProgressBar progressBar;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public void chooseFile(ActionEvent actionEvent) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);

        Task<Void> sendTask = new SendTask(file);

        statusLabel.textProperty().bind(sendTask.messageProperty());
        progressBar.progressProperty().bind(sendTask.progressProperty());

        executor.submit(sendTask);

    }
}
