package com.revature.presentation.manageFiles.controller;

import io.javalin.core.util.FileUtil;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;

public class FileController {
    private final Logger dLog = LoggerFactory.getLogger("dLog");

    public Handler handleFileUpload = ctx -> {
        byte[] returnValue = ctx.bodyAsBytes();
//        ctx.uploadedFile("files").copy(returnValue, new File("upload"))
        if(returnValue.length > 0) dLog.debug("Uploaded file Successfully");
        System.out.println(Arrays.toString(returnValue));
    };
}
