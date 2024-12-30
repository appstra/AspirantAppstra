package com.appstra.aspirante.dto;

public class EmployeeDTO {
    private Integer companyId;
    private Integer personId;
    private Integer stateId;
    private Integer roleId;

    public String getMesagge() {
        return Mesagge;
    }

    public void setMesagge(String mesagge) {
        Mesagge = mesagge;
    }

    private String Mesagge;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
