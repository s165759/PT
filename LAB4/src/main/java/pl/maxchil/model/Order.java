package pl.maxchil.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {
    @Id
    UUID id = UUID.randomUUID();

    @ManyToMany(cascade = {CascadeType.MERGE})
    List<Disc> discs = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    Date creationDate;

    @PrePersist
    public void prePersist() {this.creationDate = new Date();}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Disc> getDiscs() {
        return discs;
    }

    public void setDiscs(List<Disc> discs) {
        this.discs = discs;
    }
}
