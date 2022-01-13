package com.revature.repository.DAOInteface;

import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;

import java.util.List;

public interface RequestTypeInterface {

    RequestTypeEntity getRequestTypeEntityWithId(RequestTypeEntity requestType);

    RequestTypeEntity getRequestTypeWithString(RequestTypeEntity requestType);

    List<RequestTypeEntity> getRequestTypes();
}
