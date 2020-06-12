package com.jalasoft.devfund2.controller.endpoint;

import com.jalasoft.devfund2.controller.component.Properties;
import com.jalasoft.devfund2.controller.exception.FileException;
import com.jalasoft.devfund2.controller.response.ErrorResponse;
import com.jalasoft.devfund2.controller.response.OKResponse;
import com.jalasoft.devfund2.controller.service.FileService;
import com.jalasoft.devfund2.model.convert.ConverterImageToPsd;
import com.jalasoft.devfund2.model.convert.exception.ConvertException;
import com.jalasoft.devfund2.model.convert.exception.ParameterInvalidException;
import com.jalasoft.devfund2.model.convert.parameter.ConvertImageParam;
import com.jalasoft.devfund2.model.convert.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RestController
@RequestMapping("/api/v1")
public class ConverterController {

    @Autowired
    private Properties properties;

    @Autowired
    private FileService fileService;

    @PostMapping("/converter")
    public ResponseEntity convert(@RequestParam(value="file") MultipartFile file,
                                  @RequestParam(value="md5") String md5,
                                  @RequestParam(value="convertTo") String convertTo) {
        try {
            File imageFile = fileService.store(file, md5);
            String outputDir = properties.getOutputFolder();
            ConverterImageToPsd con = new ConverterImageToPsd();

            ConvertImageParam param = new ConvertImageParam(imageFile, convertTo, outputDir);
            param.validate();
            Result result = con.convert(param);

            return ResponseEntity.ok().body(
                    new OKResponse(result.getText(), Integer.toString(HttpServletResponse.SC_OK))
            );
        } catch (FileException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(ex.getMessage(), Integer.toString(HttpServletResponse.SC_BAD_REQUEST))
            );
        } catch (ParameterInvalidException ex){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(ex.getMessage(), Integer.toString(HttpServletResponse.SC_BAD_REQUEST))
            );

        } catch (ConvertException ex){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(ex.getMessage(), Integer.toString(HttpServletResponse.SC_BAD_REQUEST))
            );
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(ex.getMessage(), Integer.toString(HttpServletResponse.SC_BAD_REQUEST))
            );
        }
    }
}
