package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
public class Post extends ModelBase {

    private String content;

    @JsonBackReference
    @ManyToOne
    public GroupCamino groupCamino;

    @ManyToOne
    public Pilgrim author;

    public Post() {

    }

    @JsonIgnore
    @Override
    public Timestamp getWhenCreated() {
        return super.getWhenCreated();
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setGroupCamino(GroupCamino groupCamino) {
        this.groupCamino = groupCamino;
    }

    public void setAuthor(Pilgrim author) {
        this.author = author;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    public String getContent() {
        return content;
    }

    public Pilgrim getAuthor() {
        return author;
    }

    @JsonIgnore
    public GroupCamino getGroupCamino() {
        return groupCamino;
    }

}
