package com.gb0143.dnsLookup;

import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Test the {@link MongoCache} class
 */
public class MongoCacheTest {

    /**
     * Verify query results properly
     */
    @Test
    public void testQueryCacheForObject() {
        DNSQuery dnsQuery = new DNSQuery();
        String testString = "test_string";
        dnsQuery.setResults(testString);
        DNSResultRepository mockRepo = Mockito.mock(DNSResultRepository.class);
        Mockito.when(mockRepo.findOne(Mockito.anyString())).thenReturn(dnsQuery);
        MongoCache cache = new MongoCache(mockRepo);
        assertEquals(cache.queryCache(dnsQuery), testString);
    }

    /**
     * Verify query results for null
     */
    @Test
    public void testQueryCacheForNull() {
        DNSResultRepository mockRepo = Mockito.mock(DNSResultRepository.class);
        Mockito.when(mockRepo.findOne(Mockito.anyString())).thenReturn(null);
        MongoCache cache = new MongoCache(mockRepo);
        assertNull(cache.queryCache(new DNSQuery()));
    }

    /**
     * Make sure save is being called properly
     */
    @Test
    public void testCacheQuery() {
        DNSResultRepository mockRepo = Mockito.mock(DNSResultRepository.class);
        MongoCache cache = new MongoCache(mockRepo);
        cache.cacheQuery(new DNSQuery());
        Mockito.verify(mockRepo, Mockito.atLeastOnce()).save(Mockito.any(DNSQuery.class));
    }
}