package com.carmold.core.filter;

import org.owasp.validator.html.InternalPolicy;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.model.Tag;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class XssPolicy extends InternalPolicy {

    protected XssPolicy(URL baseUrl, Policy.ParseContext parseContext) throws PolicyException {
        super(baseUrl, parseContext);
    }

    protected XssPolicy(Policy old, Map<String, String> directives, Map<String, Tag> tagRules) {
        super(old, directives, tagRules);
    }

    public static XssPolicy getInstance() throws PolicyException {
        return getInstance(DEFAULT_POLICY_URI);
    }

    public static XssPolicy getInstance(String filename) throws PolicyException {
        File file = new File(filename);
        return getInstance(file);
    }

    public static XssPolicy getInstance(File file) throws PolicyException {
        try {
            URI uri = file.toURI();
            return getInstance(uri.toURL());
        } catch (IOException e) {
            throw new PolicyException(e);
        }
    }

    public static XssPolicy getInstance(URL url) throws PolicyException {
        return new XssPolicy(url, getParseContext(getTopLevelElement(url), url));
    }

    public XssPolicy cloneWithDirective(String name, String value) {
        Map<String, String> directives = new HashMap<String, String>(this.directives);
        directives.put(name, value);
        return new XssPolicy(this, Collections.unmodifiableMap(directives), tagRules);
    }

    public XssPolicy addTagRule(Tag tag) {
        Map<String, Tag> newTagRules = new HashMap<String, Tag>(tagRules);
        newTagRules.put(tag.getName().toLowerCase(), tag);
        return new XssPolicy(this, this.directives, newTagRules);

    }

    public XssPolicy mutateTag(Tag tag) {
        Map<String, Tag> newRUles = new HashMap<String, Tag>(this.tagRules);
        newRUles.put(tag.getName().toLowerCase(), tag);
        return new XssPolicy(this, this.directives, newRUles);
    }

}
