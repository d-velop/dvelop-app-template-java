package com.dvelop.archetype.plugins.http;

import java.net.URI;

public class Link {

    String href;
    boolean templated;

    public Link(URI href) {
        this(href.toString());
    }

    public Link(String href) {
        this(href, false);
    }

    public Link(URI href, boolean templated) {
        this(href.toString(), templated);
    }

    public Link(String href, boolean templated) {
        this.href = href;
        this.templated = templated;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isTemplated() {
        return templated;
    }

    public void setTemplated(boolean templated) {
        this.templated = templated;
    }

}
