import java.io.File;
import java.util.Date;
import java.util.Objects;

public abstract class DiskEntity implements Comparable {
    protected File entity;
    protected char type;
    protected Date lastModified;

    public DiskEntity(String filepath) {
        this.entity = new File(filepath);
        this.type = entity.isDirectory() ? 'D' : 'F';
        this.lastModified = new Date(entity.lastModified());
    }

    public File getEntity() {
        return entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiskEntity that = (DiskEntity) o;
        return type == that.type &&
                Objects.equals(entity, that.entity) &&
                Objects.equals(lastModified, that.lastModified);
    }

    @Override
    public int hashCode() {

        return Objects.hash(entity, type, lastModified);
    }

    protected abstract void print(int depth);
    public void print(){print (0);};

    @Override
    public int compareTo(Object o) {
        DiskEntity temp = (DiskEntity) o;
        return entity.getName().compareTo(temp.getEntity().getName());
    }
}
