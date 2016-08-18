package com.gb0143.dnsLookup;

import lombok.Data;

/**
 * Pojo to hold DNSQuery data
 */
@Data
public class DNSQuery {
    /**
     * The unique ID for the query
     */
    private String _id;
    private String type;
    private String domain;
    private String results;

    /**
     * Generate the unique ID for teh query
     * @return the generated ID string
     */
    public String generateId() {
        _id = type + ":" + domain;
        return _id;
    }
}
