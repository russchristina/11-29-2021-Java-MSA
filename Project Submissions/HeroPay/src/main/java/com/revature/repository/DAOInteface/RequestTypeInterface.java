package com.revature.repository.DAOInteface;

import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;

import java.util.List;

public interface RequestTypeInterface {

    Integer insertRequestType(RequestTypeEntity requestTypeEntity);

    RequestTypeEntity getRequestTypeWithString(String requestTypeName);

    Integer updatePendingRequestType(RequestTypeEntity requestTypeEntity);

    List<RequestTypeEntity> getRequestTypes();
}
