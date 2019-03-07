package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Model;
import io.ebean.annotation.CreatedTimestamp;
import io.ebean.annotation.UpdatedTimestamp;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.sql.Timestamp;

@MappedSuperclass
public class ModelBase extends Model {

    @Id
    private Long id;

    @JsonIgnore
    @Version
    private Long version;

    @CreatedTimestamp
    private Timestamp whenCreated;

    @JsonIgnore
    @UpdatedTimestamp
    private Timestamp whenUpdated;

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public Timestamp getWhenCreated() {
        return whenCreated;
    }

    public Timestamp getWhenUpdated() {
        return whenUpdated;
    }
}

