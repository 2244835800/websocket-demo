package com.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @author:hepo
 * @version:v1.0
 * @description:
 * @date:2022/7/23
 * @time:17:56
 */
@RestController
public class UploadController {
    private final static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @GetMapping(value = "/down/{fileName}")
    public void  downFile(HttpServletResponse response, @PathVariable("fileName") String fileName) {
        logger.info("fileName:"+fileName);
//        new File("D:\\testFile\\1658571753858temp.voice");
        String file = "D:\\testFile\\"+fileName;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            String diskfilename = fileName;
//            response.setContentType("video/avi");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + diskfilename + "\"");
            System.out.println("data.length " + data.length);
            response.setContentLength(data.length);
            response.setHeader("Content-Range", "" + Integer.valueOf(data.length - 1));
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Etag", "W/\"9767057-1323779115364\"");
            OutputStream os = response.getOutputStream();
            os.write(data);
            //先声明的流后关掉！
            os.flush();
            os.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    @PostMapping(value = "/up/")
    public void upFile(HttpServletRequest request) throws IOException {
        logger.info("yes");

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile multipartFile = null;
        if (files.size() > 0) {
            multipartFile = files.get(0);
        } else {  /*读取文件失败*/
            logger.error("临时文件上传失败,未读取到文件!");
//            mediaResult.setErrmsg("未读取到上传的文件!请确保Content-Type为multipart/form-data;并且文件参数的key为file");
//            return JsonUtils.toJson(mediaResult);
        }
        /*验证上传文件大小,防止浪费空间内存*/
        long sizeMb = multipartFile.getSize() / (1024 * 1024);
        if (sizeMb > 50) {
//            mediaResult.setErrmsg("文件过大，详情查看 https://work.weixin.qq.com/api/doc/90000/90135/90253");
//            return JsonUtils.toJson(mediaResult);
        }
        saveImg(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
        String result = "";
        /*主要业务逻辑*/
//        return result;
    }

    private void saveImg(InputStream inputStream, String fileName) {
        OutputStream os = null;
        try {
            String path = "D:\\testFile\\";
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
