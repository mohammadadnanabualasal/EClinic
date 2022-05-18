package com.example.eclinic;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
    @RequestMapping(value = "/image/logo", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(HttpServletResponse response)
    {
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        String path = System.getProperty("user.home")+"/EClinic/images/logo.png";
        String format ="png";
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
