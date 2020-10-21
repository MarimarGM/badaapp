package com.badapp.badapp.dto.transportes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class Records {

    //contiene el array de tipo record
    @JacksonXmlElementWrapper(useWrapping = false)
    private Record[] record;

    public Records() {
    }

    public Record[] getRecord() {
        return record;
    }

    public void setRecord(Record[] record) {
        this.record = record;
    }
}
