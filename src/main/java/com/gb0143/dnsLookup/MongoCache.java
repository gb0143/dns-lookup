package com.gb0143.dnsLookup;

/**
 * Cache for the DNS lookup application
 */
public class MongoCache {

    private DNSResultRepository repository;

    public  MongoCache(DNSResultRepository repository) {
        this.repository = repository;
    }

    public String queryCache(DNSQuery query) {
        DNSQuery databaseLookup = repository.findOne(query.get_id());
        return databaseLookup == null? null : databaseLookup.getResults();
    }

    public void cacheQuery(DNSQuery query) {
        repository.save(query);
    }
}
