package com.example.eclinic;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Controller
public class ImageController {

    @ResponseBody
    @RequestMapping(value = "/image/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(HttpServletResponse response, @PathVariable(value = "name") String name)
    {
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        String format ="jpg";
        if (name.equals("logo")){
            format = "png";
        }
        String path = System.getProperty("user.home")+"/EClinic/images/"+name+"."+format;
        byte[] bytes = null;
        try {
            bytes = getImageBytes(path,format);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public byte[] getImageBytes( String path, String format) throws IOException{
        BufferedImage bImage = ImageIO.read(new File(path));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, format, bos);
        byte[] data = bos.toByteArray();
        return data;
    }
}
