package com.gb0143.dnsLookup;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Set;

/**
 * Resolves a DNS query by first attempting to hit MongoDB
 * if MongoDB hits, it will return result from mongo
 * otherwise it will make a call to a third party to retrieve the data
 */
public class DNSResolver {

    /**
     * URL to hit for the DNS records
     */
    private static final String DNS_URL = "http://107.170.32.112/dns_lookup.php?type=%s&&url=%s";

    /**
     * Holds all the DNS TYPES in a O(1) lookup time
     */
    private Set<String> dnsTypes;

    /**
     * Constructor for DNSResolver
     * @param dnsTypes the tyeps of DNS that are allowed
     */
    public DNSResolver(Set<String> dnsTypes) {
        this.dnsTypes = dnsTypes;
    }

    /**
     * Resolve the DNS using the endpoint
     * @param type The type of DNS lookup
     * @param domain The url to look up the DNS record
     * @return Return the information for the DNS type and URL
     */
    public String resolve(String type, String domain) {
        if(!dnsTypes.contains(type)) {
            throw new IllegalArgumentException("Not a supported DNS type");
        }

        String dnsUrl = String.format(DNS_URL, type, domain);

        try {
            URL url_obj = new URL(dnsUrl);
            URLConnection conn = url_obj.openConnection();
            InputStream is = conn.getInputStream();
            String response =  IOUtils.toString(is, "UTF-8");
            return response;
        } catch (MalformedURLException e) {
            //Because of a predetermined URL, should not reach here
            throw new IllegalStateException("Malformed URL", e);
        } catch (IOException e) {
            // The URL will always return text so it should not reach here unless a timeout
            throw new IllegalStateException("Unable to read data from url", e);
        }
    }
}
