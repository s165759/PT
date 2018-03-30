package pl.maxchil.pt;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Server srv = new Server(2137);

        srv.run();
    }
}
