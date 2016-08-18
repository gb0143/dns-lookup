package com.gb0143.dnsLookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Configuraiton for the DNSLookup application
 */
@Configuration
public class DNSConfiguration {

    /**
     * THe repository for data caching/retrieval
     */
    @Autowired
    DNSResultRepository dnsResultRepository;

    /**
     * The DNS Resolver bean
     * @return new DNSResolverObject;
     */
    @Bean
    public DNSResolver dnsResolver() {
        return new DNSResolver(dnsTypeSet());
    }

    /**
     * A list of DNS Types
     * @return the list fo DNS types
     */
    @Bean
    public List<String> dnsTypes() {
        String[] dnsTypes= {
                "A",
                "AAAA",
                "AFSDB",
                "APL",
                "CAA",
                "CDNSKEY",
                "CDS",
                "CERT",
                "CNAME",
                "DHCID",
                "DLV",
                "DNAME",
                "DNSKEY",
                "DS",
                "HIP",
                "IPSECKEY",
                "KEY",
                "KX",
                "LOC",
                "MX",
                "NAPTR",
                "NS",
                "NSEC",
                "NSEC3",
                "NSEC3PARAM",
                "PTR",
                "RRSIG",
                "RP",
                "SIG",
                "SOA",
                "SRV",
                "SSHFP",
                "TA",
                "TKEY",
                "TLSA",
                "TSIG",
                "TXT",
                "URI"
        };
        return Arrays.asList(dnsTypes);
    }

    /**
     * The DNSType set populated from teh DNSTypes list
     * @return A set of all DNSTypes
     */
    @Bean
    public Set<String> dnsTypeSet() {
        return new HashSet<>(dnsTypes());
    }

    /**
     * The Mongo Cache
     * @return The mongoCache object used for operations on the repository
     */
    @Bean
    public MongoCache mongoCache() {
        return new MongoCache(dnsResultRepository);
    }
}
