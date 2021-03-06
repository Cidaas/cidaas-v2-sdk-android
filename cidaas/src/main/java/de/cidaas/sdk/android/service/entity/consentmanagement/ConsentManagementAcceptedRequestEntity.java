package de.cidaas.sdk.android.service.entity.consentmanagement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsentManagementAcceptedRequestEntity implements Serializable {


    private String name = "";
    private String client_id = "";
    private String sub = "";
    private String version = "";
    private String trackId = "";
    private boolean accepted = false;
    private String requestId = "";

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public ConsentManagementAcceptedRequestEntity(String name, String client_id, String sub, String version, String trackId, boolean accepted) {
        this.name = name;
        this.client_id = client_id;
        this.sub = sub;
        this.version = version;
        this.trackId = trackId;
        this.accepted = accepted;
    }

    public ConsentManagementAcceptedRequestEntity() {
    }
}
