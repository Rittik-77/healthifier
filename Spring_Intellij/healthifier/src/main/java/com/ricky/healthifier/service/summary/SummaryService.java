package com.ricky.healthifier.service.summary;

import com.itextpdf.text.DocumentException;
import com.ricky.healthifier.datamodel.summary.Summary;
import com.ricky.healthifier.utils.exception.AppException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface SummaryService {

    List<Summary> getSummary(String token) throws AppException;

    boolean generateReport(String token) throws AppException, IOException, DocumentException;
}
