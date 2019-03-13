package models.requests;


import models.GroupCamino;

import java.util.List;

public class Groups {

    List<GroupCamino> groupsCreated;
    List<GroupCamino> groupsAssociated;
    List<GroupCamino> otherGroups;

    public Groups(List<GroupCamino> groupsCreated, List<GroupCamino> groupsAssociated, List<GroupCamino> otherGroups) {
        this.groupsCreated = groupsCreated;
        this.groupsAssociated = groupsAssociated;
        this.otherGroups = otherGroups;
    }

    public List<GroupCamino> getGroupsCreated() {
        return groupsCreated;
    }

    public List<GroupCamino> getGroupsAssociated() {
        return groupsAssociated;
    }

    public List<GroupCamino> getOtherGroups() {
        return otherGroups;
    }
}
