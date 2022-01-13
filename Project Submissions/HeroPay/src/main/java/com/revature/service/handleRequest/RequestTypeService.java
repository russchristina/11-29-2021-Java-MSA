package com.revature.service.handleRequest;

import com.revature.repository.DAOClasses.RequestTypeDao;
import com.revature.repository.DTO.RequestTypeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RequestTypeService {

    private final Logger dLog = LoggerFactory.getLogger("dLog");
    private final Logger tLog = LoggerFactory.getLogger("tLog");

    private final RequestTypeDao requestTypeDao;

    public RequestTypeService(RequestTypeDao requestTypeDao) {
        this.requestTypeDao = requestTypeDao;
    }

    public RequestTypeEntity getRequestTypeWithId(int typeId){
        dLog.debug("Getting RequestTypeEntity with type ID: " + typeId);
        return requestTypeDao.getRequestTypeEntityWithId(new RequestTypeEntity(typeId, ""));
    }

    public RequestTypeEntity getRequestWithString(String typeName){
        dLog.debug("Getting RequestTypeEntity with type name: " + typeName);
        return requestTypeDao.getRequestTypeWithString(new RequestTypeEntity(0, typeName));
    }

    public List<RequestTypeEntity> getAllRequestTypes() {
        dLog.debug("Getting all RequestTypeEntities as List<RequestTypeEntity>");
        return requestTypeDao.getRequestTypes();
    }
}
