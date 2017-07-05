package com.itmuch.platform.controller.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "${adminPath}/common")
public class FileUploadController extends BaseController {
    @Value("${uploadPath}")
    protected String uploadPath;

    /**
     * 上传文件, 并返回文件URL
     * @param request 请求
     * @param file 文件
     * @return 返回路径
     * @throws IOException 异常
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(HttpServletRequest request, @RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(defaultValue = "80") Integer width, @RequestParam(defaultValue = "80") Integer height) throws IOException {
        byte[] bytes = file.getBytes();

        String separator = "/";
        String realPath = request.getSession().getServletContext().getRealPath(separator);
        Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(date);
        String timeStr = new SimpleDateFormat("HHmmssSSS").format(date);

        // 存储的相对路径, 用于返回到页面
        StringBuffer relativePath = new StringBuffer();
        relativePath.append(this.uploadPath).append(separator).append(dateStr).append(separator).append(timeStr);

        StringBuffer uploadDir = new StringBuffer();
        uploadDir.append(realPath).append(separator).append(relativePath);

        File dirPath = new File(uploadDir.toString());
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        //        String name = file.getName();
        String extension = FilenameUtils.getExtension(originalFilename);

        StringBuffer savedFileName = new StringBuffer();
        savedFileName.append(dateStr).append(timeStr).append(new Random().nextInt()).append(".").append(extension);

        File uploadedFile = new File(uploadDir + separator + savedFileName);
        FileCopyUtils.copy(bytes, uploadedFile);

        // 生成缩略图
        // TODO 生成缩略图

        return relativePath + separator + savedFileName;
    }
}
