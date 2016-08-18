package com.gb0143.dnsLookup;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Mapping for DNS results
 */
public interface DNSResultRepository extends MongoRepository<DNSQuery, String>{

}
