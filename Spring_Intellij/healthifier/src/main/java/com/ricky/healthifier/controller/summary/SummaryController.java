package com.ricky.healthifier.controller.summary;

import com.itextpdf.text.DocumentException;
import com.ricky.healthifier.datamodel.summary.Summary;
import com.ricky.healthifier.service.summary.SummaryService;
import com.ricky.healthifier.utils.commons.BaseConstants;
import com.ricky.healthifier.utils.commons.BaseValidator;
import com.ricky.healthifier.utils.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/summary")
@CrossOrigin(BaseConstants.APP_LINK)
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    private final Logger logger = LoggerFactory.getLogger(SummaryController.class);
    private final SummaryTransformer transformer = new SummaryTransformer();

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SummaryVO> getSummary(@RequestHeader Map<String, String> headers) throws AppException {

        logger.info("Rest: Fetch summary for logged user");

        // Validate the token
        String token = headers.getOrDefault(BaseConstants.TOKEN, null);
        BaseValidator.checkObjectIsNotNull(token, BaseConstants.TOKEN_NULL);

        // Call the service
        List<Summary> summaryList = summaryService.getSummary(token);
        List<SummaryVO> summaryVOList = new ArrayList<>();

        // Transform to VO
        for (Summary summary : summaryList) {
            summaryVOList.add(transformer.transformToVO(summary));
        }

        return summaryVOList;
    }

    @RequestMapping(value="report", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getReport(@RequestHeader Map<String, String> headers) throws AppException, IOException, DocumentException {

        logger.info("Rest: Fetch report");

        // Validate the token
        String token = headers.getOrDefault(BaseConstants.TOKEN, null);
        BaseValidator.checkObjectIsNotNull(token, BaseConstants.TOKEN_NULL);

        // Call the service
        boolean response = summaryService.generateReport(token);
        if(response) {
            String reportFile = "D:\\CodeRepo\\PROJECTS\\GitHubProjects\\HealthifierApp\\Spring_Intellij" +
                    "\\healthifier\\src\\main\\resources\\report\\Report.pdf";
            FileInputStream fileInputStream = new FileInputStream(new File(reportFile));
            HttpHeaders httpHeaders = new HttpHeaders();
            InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);
            httpHeaders.setContentType(MediaType.APPLICATION_PDF);
            httpHeaders.put("Content-Disposition", Collections.singletonList("attachment; filename=" + reportFile));
            return new ResponseEntity<InputStreamResource>(inputStreamResource, httpHeaders, HttpStatus.OK);
        }

        throw new AppException("Report could not be prepared");
    }
}
