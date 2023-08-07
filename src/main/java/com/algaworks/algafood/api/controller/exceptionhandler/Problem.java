package com.algaworks.algafood.api.controller.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
@Builder
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;

}
