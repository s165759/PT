package pl.maxchil.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Disc {

    @Id
    UUID id = UUID.randomUUID();

    String title;
    String author;
    Integer nSongs;
    String genre;
    Double price;
    Integer amount;

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getnSongs() {
        return nSongs;
    }

    public void setnSongs(Integer nSongs) {
        this.nSongs = nSongs;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disc disc = (Disc) o;
        return Objects.equals(getId(), disc.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
