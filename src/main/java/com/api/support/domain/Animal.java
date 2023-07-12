package com.api.support.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Animal {
    @Builder.Default
    private int id = 10;
    @Builder.Default
    private String nome = "Ave";
    @Builder.Default
    private Categoria categoria = new Categoria();
    @Builder.Default
    private String status = "Available";
    @Builder.Default
    private List<String> photoUrls = Arrays.asList("url1","url2");

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Categoria{
        @Builder.Default
        private int id = 1;
        @Builder.Default
        private String nome = "Coruja";
    }
}
