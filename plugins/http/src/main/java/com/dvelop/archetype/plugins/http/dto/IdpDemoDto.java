package com.dvelop.archetype.plugins.http.dto;

import com.dvelop.archetype.plugins.http.template.TemplateName;
import com.dvelop.sdk.idp.dto.IDPUser;

@TemplateName("idpdemo")
public class IdpDemoDto {

    String title;

    IDPUser identity;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public IDPUser getIdentity() {
        return identity;
    }

    public void setIdentity(IDPUser identity) {
        this.identity = identity;
    }
}
