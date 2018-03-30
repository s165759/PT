import java.util.Scanner;

public class DiskViewer {
    public static void main(String[] args){

        if (args.length < 1)
            return;

        String path = args[0];
        DiskEntity de = new DiskDirectory(path);

        de.print();
    }
}
