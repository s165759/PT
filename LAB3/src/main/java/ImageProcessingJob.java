import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ImageProcessingJob {
    private File imageToProcess;
    private SimpleStringProperty status;
    private DoubleProperty progress;

    public ImageProcessingJob(File imageToProcess) {
        this.imageToProcess = imageToProcess;
        status = new SimpleStringProperty();
        progress = new SimpleDoubleProperty();

        status.setValue("Waiting");
        progress.setValue(0);
    }

    public static List<ImageProcessingJob> fromList(List<File> lf){
        List<ImageProcessingJob> temp = new LinkedList<>();

        for (File f : lf){
            temp.add(new ImageProcessingJob(f));
        }

        return temp;
    }

    public BufferedImage processImage(){
        try {
            Platform.runLater(() -> status.set("Processing"));

            BufferedImage original = ImageIO.read(imageToProcess);

            BufferedImage grayscale = new BufferedImage(
                    original.getWidth(), original.getHeight(), original.getType()
            );

            for (int i = 0; i < original.getWidth(); i++) {
                for (int j = 0; j < original.getHeight(); j++) {

                    int red = new Color(original.getRGB(i, j)).getRed();
                    int green = new Color(original.getRGB(i, j)).getGreen();
                    int blue = new Color(original.getRGB(i, j)).getBlue();
                    int luminosity = (int) (0.21 * red + 0.71 * green + 0.07 * blue);

                    grayscale.setRGB(i, j, new Color(luminosity, luminosity, luminosity).getRGB());
                }

                double v = (1.0 + i) / original.getWidth();

                Platform.runLater(() -> progress.set(v));
            }

            Platform.runLater(() -> status.setValue("Finished"));

            return grayscale;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFileName() {
        return imageToProcess.getName();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public DoubleProperty progressProperty() {
        return progress;
    }
}
