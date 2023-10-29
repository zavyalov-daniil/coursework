package com.example.courseworkmailer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RabbitMqMessage(
        @JsonProperty("from") String from,
        @JsonProperty("to") String to,
        @JsonProperty("subject") String subject,
        @JsonProperty("text") String text) implements Serializable {
}
