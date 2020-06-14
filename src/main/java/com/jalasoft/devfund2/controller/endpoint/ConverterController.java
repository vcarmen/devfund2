package com.jalasoft.devfund2.controller.endpoint;

import com.jalasoft.devfund2.controller.component.Properties;
import com.jalasoft.devfund2.controller.exception.FileException;
import com.jalasoft.devfund2.controller.exception.RequestParamInvalidException;
import com.jalasoft.devfund2.controller.request.RequestConvertFileParameter;
import com.jalasoft.devfund2.controller.request.RequestConvertImageParameter;
import com.jalasoft.devfund2.controller.response.ErrorResponse;
import com.jalasoft.devfund2.controller.response.OKResponse;
import com.jalasoft.devfund2.controller.service.FileService;
import com.jalasoft.devfund2.model.convert.ConverterFileToPDF;
import com.jalasoft.devfund2.model.convert.ConverterImageToPsd;
import com.jalasoft.devfund2.model.convert.IConverter;
import com.jalasoft.devfund2.model.convert.exception.ConvertException;
import com.jalasoft.devfund2.model.convert.exception.ParameterInvalidException;
import com.jalasoft.devfund2.model.convert.parameter.ConvertFileParam;
import com.jalasoft.devfund2.model.convert.parameter.ConvertImageParam;
import com.jalasoft.devfund2.model.convert.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity convert(RequestConvertImageParameter parameter) {
        try {
            parameter.validate();
            File imageFile = fileService.store(parameter.getFile(), parameter.getMd5());
            String outputDir = properties.getOutputFolder();

            IConverter<ConvertImageParam> con = new ConverterImageToPsd();
            Result result = con.convert(new ConvertImageParam(imageFile, parameter.getConvertTo(), outputDir));

            String fileDownloadUri = fileService.getDownloadLink(new File(result.getText()));

            return ResponseEntity.ok().body(
                    new OKResponse<Integer>(fileDownloadUri, HttpServletResponse.SC_OK)
            );
        } catch (RequestParamInvalidException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        } catch (FileException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        } catch (ParameterInvalidException ex){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );

        } catch (ConvertException ex){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        }
    }

    @PostMapping("/converter/file")
    public ResponseEntity convertFile(RequestConvertFileParameter parameter) {
        try {
            File imageFile = fileService.store(parameter.getFile(), parameter.getMd5());

            IConverter<ConvertFileParam> con = new ConverterFileToPDF();
            Result result = con.convert(
                    new ConvertFileParam(
                            imageFile,
                            parameter.getOptions(),
                            properties.getOutputFolder())
            );

            return ResponseEntity.ok().body(
                    new OKResponse<Integer>(result.getText(), HttpServletResponse.SC_OK)
            );
        } catch (FileException ex) {
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        } catch (ParameterInvalidException ex){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );

        } catch (ConvertException ex){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(
                    new ErrorResponse<Integer>(ex.getMessage(), HttpServletResponse.SC_BAD_REQUEST)
            );
        }
    }
}
