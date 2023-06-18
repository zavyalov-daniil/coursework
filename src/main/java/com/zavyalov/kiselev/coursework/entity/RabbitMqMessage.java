package com.zavyalov.kiselev.coursework.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record RabbitMqMessage (
        @JsonProperty("from") String from,
        @JsonProperty("to") String to,
        @JsonProperty("subject") String subject,
        @JsonProperty("text") String text)  implements Serializable {
}
