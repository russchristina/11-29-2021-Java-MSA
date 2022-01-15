package com.revature.presentation.manageFiles.controller;

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
        String fileKey = ctx.queryParam("fileKey");
        byte[] returnValue = ctx.bodyAsBytes();
//        ctx.uploadedFile("files").copy(returnValue, new File("upload"))

        Region region = Region.US_EAST_2;
        S3Client s3 = S3Client.builder().region(region).build();
        String bucket = "project1revaturestorage";
        String key = "imageTest";

        s3.putObject(PutObjectRequest.builder().bucket(bucket).key(key).build(),
                RequestBody.fromBytes(returnValue));

    };

    public Handler retrieveFile = ctx -> {
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
        ctx.header("Content-type", "image/png");
        ctx.result(fileRetrieved);
    };
}
