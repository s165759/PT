import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class DiskDirectory extends DiskEntity {
    Set<DiskEntity> children;

    public DiskDirectory(String filepath) {
        super(filepath);

        children = new TreeSet<>();

        File[] ch = entity.listFiles();

        for (File f : ch){
            children.add(
                    f.isDirectory()
                            ? new DiskDirectory(f.getAbsolutePath())
                            : new DiskFile(f.getAbsolutePath())
            );
        }

    }

    protected void print(int depth) {

        int indent = 80 - depth - entity.getName().length();
        StringBuilder strb = new StringBuilder();

        for (int i = 0; i < depth; i++) {
            strb.append('-');
        }

        strb.append(entity.getName());

        for (int i = 0; i < indent; i++) {
            strb.append(' ');
        }

        strb.append(this.type + " ");
        strb.append(new SimpleDateFormat("yyyy-MM-dd").format(this.lastModified));

        System.out.println(strb);

        //for( DiskEntity ch : children){
        //    ch.print((depth + 1));
        //}

        children.stream()
                .sorted()
                .forEach(i ->{i.print(depth + 1);});

    }
}
