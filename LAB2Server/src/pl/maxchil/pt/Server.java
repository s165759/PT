package pl.maxchil.pt;

import javafx.concurrent.Task;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {
    private ServerSocket serverSocket;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);

    }

    public void run() throws IOException {

        while (true){
            final Socket socket = serverSocket.accept();

           executorService.submit(new ReadTask(socket));
        }
    }
}
