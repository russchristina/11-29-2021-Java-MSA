package com.revature.presentation.model.requests.response;

import java.util.Objects;

public class FailCreateRequestResponse {

    private String response;

    public FailCreateRequestResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "{\"FailCreateRequestResponse\":{"
                + "\"response\":\"" + response + "\""
                + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FailCreateRequestResponse)) return false;
        FailCreateRequestResponse that = (FailCreateRequestResponse) o;
        return Objects.equals(getResponse(), that.getResponse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResponse());
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
