package cn.Hlmove.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

@Controller
@RequestMapping("/")
public class ImageRepoController {

    private static String imgRepoRoot = "G:\\imageserver\\";

    @GetMapping(value = "/imgrepo", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    public BufferedImage imgrepo(String imagePath) throws Exception {
        BufferedImage fileContent = ImageIO.read(new FileInputStream(new File(imgRepoRoot + imagePath)));
        return fileContent;
    }


}
