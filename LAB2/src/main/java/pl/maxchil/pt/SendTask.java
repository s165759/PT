package pl.maxchil.pt;

import javafx.concurrent.Task;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

public class SendTask extends Task<Void>{
    private File file;

    public SendTask(File file) {
        this.file = file;
    }

    @Override
    protected Void call() throws Exception {
        updateMessage("Sending started...");
        Socket socket = new Socket("localhost", 2137);
        int sent = 0;
        int acu = 0;
        byte[] buffer = new byte[4096];

        try (InputStream in = Files.newInputStream(file.toPath());
             OutputStream outputStream = socket.getOutputStream()){

            while ((sent = in.read(buffer)) != -1){

                outputStream.write(buffer, 0, sent);
                acu += sent;

                updateProgress(sent, file.length());
            }
            updateProgress(file.length(), file.length());
            updateMessage("plik wysłano...");
        }

        return null;
    }
}