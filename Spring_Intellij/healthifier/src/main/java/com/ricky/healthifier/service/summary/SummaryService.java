package com.ricky.healthifier.service.summary;

import com.ricky.healthifier.datamodel.summary.Summary;
import com.ricky.healthifier.utils.exception.AppException;

import java.util.List;

public interface SummaryService {

    List<Summary> getSummary(String token) throws AppException;
}
