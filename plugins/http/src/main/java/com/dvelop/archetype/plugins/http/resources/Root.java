package com.dvelop.archetype.plugins.http.resources;

import com.dvelop.archetype.plugins.http.Link;
import com.dvelop.archetype.plugins.http.template.AppInfo;
import com.dvelop.archetype.plugins.http.template.TemplateName;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import javax.xml.bind.annotation.XmlAttribute;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class Root {

    @TemplateName("root")
    public static class RootDto {
        String title, version;

        @XmlAttribute(name="_links")
        Map<String, com.dvelop.archetype.plugins.http.Link> links = new HashMap<>();

        public RootDto(String title, String version) {
            this.title = title;
            this.version = version;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public Map<String, com.dvelop.archetype.plugins.http.Link> getLinks() {
            return links;
        }

        public void setLinks(Map<String, com.dvelop.archetype.plugins.http.Link> links) {
            this.links = links;
        }
    }

    @Context
    UriInfo uriInfo;

    @Inject
    AppInfo appInfo;

    @GET
    @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON, "application/hal+json"})
    public Response getRoot() {
        RootDto dto = new RootDto(appInfo.getName(), appInfo.getVersion());

        dto.getLinks().put("self", new com.dvelop.archetype.plugins.http.Link(uriInfo.getBaseUri().relativize(URI.create("/"+appInfo.getName()))));
        dto.getLinks().put("featuresdescription", new Link(uriInfo.getBaseUri().relativize(URI.create("/" + appInfo.getName() + Features.PATH))));

        return Response.ok(dto).build();
    }

}
