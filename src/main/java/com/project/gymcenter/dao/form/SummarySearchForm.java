package com.project.gymcenter.dao.form;

public class SummarySearchForm {

    private String summaryDateStart;
    private String summaryDateEnd;
    private String summarySortBy;

    public SummarySearchForm() {

        super();
    }

    public String getSummaryDateStart() {

        return summaryDateStart;
    }

    public void setSummaryDateStart(String summaryDateStart) {

        this.summaryDateStart = summaryDateStart;
    }

    public String getSummaryDateEnd() {
        return summaryDateEnd;
    }

    public void setSummaryDateEnd(String summaryDateEnd) {

        this.summaryDateEnd = summaryDateEnd;
    }

    public String getSummarySortBy() {

        return summarySortBy;
    }

    public void setSummarySortBy(String summarySortBy) {

        this.summarySortBy = summarySortBy;
    }
}
