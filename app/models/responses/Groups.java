package models.responses;


import models.GroupCamino;

import java.util.List;

public class Groups {

    List<Group> groupsCreated;
    List<Group> groupsAssociated;
    List<Group> otherGroups;

    public Groups(List<GroupCamino> groupsCreated, List<GroupCamino> groupsAssociated, List<GroupCamino> otherGroups) {
        this.groupsCreated = Group.mapToGroupList(groupsCreated);
        this.groupsAssociated = Group.mapToGroupList(groupsAssociated);
        this.otherGroups = Group.mapToGroupList(otherGroups);
    }

    public List<Group> getGroupsCreated() {
        return groupsCreated;
    }

    public List<Group> getGroupsAssociated() {
        return groupsAssociated;
    }

    public List<Group> getOtherGroups() {
        return otherGroups;
    }
}
