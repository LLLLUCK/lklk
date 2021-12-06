package edu.soft1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @RequestMapping(value = "upload",method = {RequestMethod.POST})
    public String fileUpload(MultipartFile image, HttpServletRequest request) throws IOException {
        InputStream is = image.getInputStream();//输入流
        String filename = image.getOriginalFilename();//文件名称
        String realPath = request.getServletContext().getRealPath("/images");
        System.out.println("上传路径="+realPath);
        FileOutputStream os = new FileOutputStream(new File(realPath,doFileName(filename)));
        int size = IOUtils.copy(is,os);
        System.out.println("完成上传size="+size+"Byte");
        os.close();is.close();
        return "welcome";
    }

    private String doFileName(String filename){
        String extension = FilenameUtils.getExtension(filename);//获取文件的后缀名称
        String uuid = UUID.randomUUID().toString();//获取uuid字符，规避名称重复
        return uuid+"."+extension;
    }
    String username;
    String pwd;
    int age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date birthday;
    //自定义对象类型
    Address address;
}
