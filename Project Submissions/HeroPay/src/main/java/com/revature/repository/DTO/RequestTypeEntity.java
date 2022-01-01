package com.revature.repository.DTO;

import java.util.Objects;

public class RequestTypeEntity {

    private int id;
    private String requestType;

    public RequestTypeEntity(int id, String requestType) {
        this.id = id;
        this.requestType = requestType;
    }

    public RequestTypeEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestTypeEntity)) return false;
        RequestTypeEntity that = (RequestTypeEntity) o;
        return id == that.id && Objects.equals(requestType, that.requestType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestType);
    }

    @Override
    public String toString() {
        return "{\"RequestTypeEntity\":{"
                + "\"id\":\"" + id + "\""
                + ", \"requestType\":\"" + requestType + "\""
                + "}}";
    }
}
