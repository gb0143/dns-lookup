package com.gb0143.dnsLookup;

import lombok.Data;

@Data
public class DNSQuery {
    private String _id;
    private String type;
    private String domain;
    private String results;

    public String generateId() {
        _id = type + ":" + domain;
        return _id;
    }
}
