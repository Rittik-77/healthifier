package com.ricky.healthifier.controller.summary;

import com.ricky.healthifier.datamodel.summary.Summary;

public class SummaryTransformer {

    public SummaryVO transformToVO(Summary summary) {
        SummaryVO summaryVO = new SummaryVO();
        summaryVO.setDate(summary.getDate());
        summaryVO.setCalories(summary.getCalories());
        return summaryVO;
    }
}
