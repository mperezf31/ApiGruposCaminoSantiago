package models.responses;

import models.GroupCamino;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Group {

    private Long id;
    private Timestamp whenCreated;
    private String title;
    private String description;
    private String departurePlace;
    private Double latitude;
    private Double longitude;
    private Long departureDate;
    private Long arrivalDate;
    private String photo;
    private String mode;

    public Group(Long id, Timestamp whenCreated, String title, String description, String departurePlace, Double latitude, Double longitude, Long departureDate, Long arrivalDate, String photo, String mode) {
        this.id = id;
        this.whenCreated = whenCreated;
        this.title = title;
        this.description = description;
        this.departurePlace = departurePlace;
        this.latitude = latitude;
        this.longitude = longitude;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.photo = photo;
        this.mode = mode;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getWhenCreated() {
        return whenCreated;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Long getDepartureDate() {
        return departureDate;
    }

    public Long getArrivalDate() {
        return arrivalDate;
    }

    public String getPhoto() {
        return photo;
    }

    public String getMode() {
        return mode;
    }


    public static List<Group> mapToGroupList(List<GroupCamino> data) {
        if (data == null) return new ArrayList<>();

        List<Group> groups = new ArrayList<>();
        for (GroupCamino groupCamino : data) {
            groups.add(new Group(groupCamino.getId(), groupCamino.getWhenCreated(), groupCamino.getTitle(), groupCamino.getDescription(), groupCamino.getDeparturePlace(),
                    groupCamino.getLatitude(), groupCamino.getLongitude(), groupCamino.getDepartureDate(), groupCamino.getArrivalDate(), groupCamino.getPhoto(), groupCamino.getMode()));
        }
        return groups;
    }
}
