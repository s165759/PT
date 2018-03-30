package pl.maxchil.pt;


import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class ReadTask implements Runnable {
    private Socket socket;

    public ReadTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[4096];
        int readSize;

        Path path = Paths.get("/tmp/" + UUID.randomUUID());
        try (OutputStream outputStream = Files.newOutputStream(path);
             InputStream in = socket.getInputStream()) {

            while ((readSize = in.read(buffer)) != -1){
                outputStream.write(buffer, 0, readSize);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
