package com.example.ocrdemo.controller;

import com.example.ocrdemo.service.OCRService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Controller
@RequestMapping("/ocr")
public class OCRController {

    private final OCRService ocrService;

    public OCRController(OCRService ocrService) {
        this.ocrService = ocrService;
    }

    @PostMapping("/process")
    public String uploadImage(@RequestParam("file") MultipartFile file, @RequestParam(value = "language", defaultValue = "eng") String language, Model model) {
        try {
            String result;
            if (!file.isEmpty() && !StringUtils.isEmpty(file.getContentType()) && file.getContentType().startsWith("image")) {
                result = ocrService.process(file.getBytes(), language);
            } else {
                result = "Invalid image file.";
            }

            model.addAttribute("result", result);
            return "upload";

        } catch (TesseractException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
