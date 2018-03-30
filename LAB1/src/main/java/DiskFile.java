import java.text.SimpleDateFormat;

public class DiskFile extends DiskEntity {


    public DiskFile(String filepath) {
        super(filepath);
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
    }
}
