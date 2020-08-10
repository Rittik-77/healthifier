package com.ricky.healthifier.service.summary;

import com.ricky.healthifier.datamodel.summary.Summary;

import java.util.Comparator;

public class SummarySorter implements Comparator<Summary> {
    @Override
    public int compare(Summary o1, Summary o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
