package com.example.ocrdemo.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class OCRService {

    private final String path;

    public OCRService (@Value("${tesseract.data.path}") String path) {
        this.path = path;
    }

    public String process (byte[] image, String language) throws TesseractException, IOException{
        ByteArrayInputStream imageInputStream = new ByteArrayInputStream(image);
        BufferedImage bufferedImage = ImageIO.read(imageInputStream);

        if (bufferedImage == null) {
            throw new IOException("Probably input is not an image.");
        }

        Tesseract tesseract = new Tesseract();
        /*
            0 Legacy engine only.
            1 Neural nets LSTM engine only.
            2 Legacy + LSTM engines.
            3 Default, based on what is available.
         */
        tesseract.setOcrEngineMode(3);
        tesseract.setDatapath(path);
        tesseract.setLanguage(language);
        return tesseract.doOCR(bufferedImage);
    }

}
