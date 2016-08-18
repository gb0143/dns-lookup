package com.gb0143.dnsLookup;

/**
 * Cache for the DNS lookup application
 */
public class MongoCache {

    /**
     * Repository used to retrieve/save data
     */
    private DNSResultRepository repository;

    /**
     * Constructor
     * @param repository the default repository to use
     */
    public  MongoCache(DNSResultRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieve the DNSQuery from the cache
     * @param query query to search with
     * @return null if not in DB else the DNSQuery
     */
    public String queryCache(DNSQuery query) {
        DNSQuery databaseLookup = repository.findOne(query.get_id());
        return databaseLookup == null? null : databaseLookup.getResults();
    }

    /**
     * Save the DNSQuery to the cache
     * @param query the DNSQuery to save
     */
    public void cacheQuery(DNSQuery query) {
        repository.save(query);
    }
}
