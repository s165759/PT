import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


public class MainController {
    @FXML private TableColumn<ImageProcessingJob, String> tableStatusColumn;
    @FXML private TableColumn<ImageProcessingJob, Double> tableProgressColumn;
    @FXML private TableColumn<ImageProcessingJob, String> tableNameColumn;

    @FXML private TableView tableView;

    @FXML private ChoiceBox<Integer> threadChoice;
    @FXML private CheckBox parallelCheck;

    private File targetDirectory = new File("./");

    @FXML private void initialize(){
           tableNameColumn.setCellValueFactory(
                   p -> new SimpleStringProperty(p.getValue().getFileName())
           );

           tableStatusColumn.setCellValueFactory(
                   p -> p.getValue().statusProperty()
           );

           tableProgressColumn.setCellFactory(
                   ProgressBarTableCell.<ImageProcessingJob>forTableColumn()
           );

           tableProgressColumn.setCellValueFactory(
                   p -> p.getValue().progressProperty().asObject()
           );

           parallelCheck.selectedProperty().addListener(
                   (ov, p, n) -> { threadChoice.disableProperty().setValue(!n); }
           );

           threadChoice.setItems(FXCollections.observableArrayList(
                   0, 1, 2, 3, 4, 5, 6, 7, 8, 9
           ));

           threadChoice.getSelectionModel().selectFirst();
    }

    private void processImage(ImageProcessingJob p){
        try {
            BufferedImage grayscale = p.processImage();
            Path output = Paths.get(targetDirectory.getAbsolutePath(), p.getFileName());
            ImageIO.write(grayscale, "jpg", output.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFiles(ActionEvent actionEvent) throws InterruptedException {
        ForkJoinPool temp;

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JPG images", "*.jpg")
        );

        List<File> files = fileChooser.showOpenMultipleDialog(null);
        ObservableList<ImageProcessingJob> jobs = FXCollections.observableList(ImageProcessingJob.fromList(files));

        tableView.setItems(jobs);

        if(parallelCheck.isSelected()){

            Integer selectedItem = threadChoice.getSelectionModel().getSelectedItem();

            if (selectedItem.equals(0)){
                temp = ForkJoinPool.commonPool();
            }
            else {
                temp = new ForkJoinPool(selectedItem);
            }

//            long start = System.currentTimeMillis();
            temp.submit(
                    () -> jobs.parallelStream().forEach(this::processImage)
            );
            new Thread(() -> {
                long start = System.currentTimeMillis();

                try {
                    temp.awaitTermination(1, TimeUnit.HOURS);



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();

        }
        else {
 //           long start = System.currentTimeMillis();
            Thread thread = new Thread(() -> jobs.stream().forEach(this::processImage));
            thread.start();
  //          while (thread.isAlive());
   //
            //         System.out.println("Sequential: " + (System.currentTimeMillis() - start) + "ms");
        }
    }



    public void chooseDirectory(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("./"));
        targetDirectory = directoryChooser.showDialog(null);
    }
}
