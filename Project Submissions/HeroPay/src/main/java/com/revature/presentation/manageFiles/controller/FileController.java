package com.revature.presentation.manageFiles.controller;

import com.revature.utility.JWTHandler;
import io.javalin.core.util.FileUtil;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.*;
import java.util.Arrays;

public class FileController {
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    public Handler handleFileUpload = ctx -> {
        if(JWTHandler.verifyUser(ctx.header("Authorization"))) {
            dLog.debug("User Verified");
            String fileKey = ctx.queryParam("fileKey");
            byte[] returnValue = ctx.bodyAsBytes();

            Region region = Region.US_EAST_2;
            S3Client s3 = S3Client.builder().region(region).build();
            String bucket = "project1revaturestorage";
            String key = fileKey;

            s3.putObject(PutObjectRequest.builder().bucket(bucket).key(key).build(),
                    RequestBody.fromBytes(returnValue));
        }else{
            dLog.debug("Unauthorized User Access Attempt");
            ctx.status(401);
        }
    };

    public Handler retrieveFile = ctx -> {
        if(JWTHandler.verifyUser(ctx.header("Authorization"))) {
            dLog.debug("User Verified");
            String fileKey = ctx.queryParam("fileKey");

            Region region = Region.US_EAST_2;
            S3Client s3 = S3Client.builder().region(region).build();
            String bucket = "project1revaturestorage";

            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucket)
                    .key(fileKey)
                    .build();
            InputStream fileRetrieved = s3.getObject(getObjectRequest);
            ctx.header("Content-disposition", "attachment; filename=file");
            ctx.header("Content-type", "image/jpeg");
            ctx.result(fileRetrieved);
        }else{
            dLog.debug("Unauthorized User Access Attempt");
            ctx.status(401);
        }
    };
}
