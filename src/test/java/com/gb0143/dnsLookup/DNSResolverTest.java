package com.gb0143.dnsLookup;

import org.mockito.Mockito;
import org.testng.annotations.Test;
import java.util.Set;

import static org.testng.Assert.assertTrue;


/**
 * Test the {@link DNSResolver} class
 */
public class DNSResolverTest {

    /**
     * Test the resolve DNS method
     */
    @Test
    public void testResolve() {
        Set<String> mockSet = Mockito.mock(Set.class);
        DNSResolver resolver = new DNSResolver(mockSet);
        Mockito.when(mockSet.contains(Mockito.anyObject())).thenReturn(true);
        String result = resolver.resolve("A", "yahoo.com");
        assertTrue(result.contains("206.190.36.45"));
        assertTrue(result.contains("98.139.183.24"));
    }

    /**
     * Testing for unsupported DNS types
     */
    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testResolveUnsupportedDNSType() {
        Set<String> mockSet = Mockito.mock(Set.class);
        DNSResolver resolver = new DNSResolver(mockSet);
        Mockito.when(mockSet.contains(Mockito.anyObject())).thenReturn(false);
        resolver.resolve("A", "yahoo.com");
    }

}