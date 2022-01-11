package com.revature.repository.DTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name ="request_type", schema = "project_1")
public class RequestTypeEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "request_type_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(allocationSize = 1, name = "request_type_seq", sequenceName = "request_type_id_seq")
    private int id;
    @Column(name = "type")
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
