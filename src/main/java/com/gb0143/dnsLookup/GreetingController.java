package com.gb0143.dnsLookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class GreetingController {

    @Autowired
    private DNSResolver dnsResolver;

    @Autowired
    private MongoCache mongoCache;

    @Autowired
    private List<String> dnsTypes;

    /**
     * Mapping for "GET" request that will display the form
     * @param model that contains request data for template
     * @return the page to display (the form).
     */
    @RequestMapping(value="/query", method=RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("DNSQuery", new DNSQuery());
        model.addAttribute("DNSTypes", dnsTypes);
        return "query_form";
    }

    /**
     * Mapping for the "POST" request that will display the results.
     * It will attempt to get a cache hit. If there is no hit, it will
     * query a website and cache the results.
     * @param dnsQuery the query object ot build with result data
     * @param model the model used to populate the template
     * @return
     */
    @RequestMapping(value="/query", method=RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute DNSQuery dnsQuery, Model model) {
        dnsQuery.generateId();
        //If not in the cache...add it to the cache...
        String results;

        //Check the cache...
        results = mongoCache.queryCache(dnsQuery);
        if (results != null) {
            dnsQuery.setResults(results);
        } else {
            results = dnsResolver.resolve(dnsQuery.getType(), dnsQuery.getDomain());
            dnsQuery.setResults(results);
            mongoCache.cacheQuery(dnsQuery);
        }

        model.addAttribute("DNSQuery", dnsQuery);
        return "query_result";
    }

}
