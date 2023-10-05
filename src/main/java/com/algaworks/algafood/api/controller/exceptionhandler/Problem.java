package com.algaworks.algafood.api.controller.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
@Builder
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private LocalDateTime timestamp;
    private List<Object> objects;

    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Object {

        private String name;
        private String userMessage;

    }

}
