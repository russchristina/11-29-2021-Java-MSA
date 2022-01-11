package com.revature.repository.DAOInteface;

import com.revature.repository.DTO.PendingRequestEntity;
import com.revature.repository.DTO.RequestTypeEntity;

public interface RequestTypeInterface {

    Integer insertRequestType(RequestTypeEntity requestTypeEntity);

    RequestTypeEntity getRequestTypeWithString(String requestTypeName);

    Integer updatePendingRequestType(RequestTypeEntity requestTypeEntity);
}
