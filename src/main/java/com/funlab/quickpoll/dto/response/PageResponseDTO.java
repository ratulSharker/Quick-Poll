package com.funlab.quickpoll.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResponseDTO<T> {

    private List<T> content;

    private Long number;
    private Long size;
    private Long totalPages;
    private Long totalElements;

    public PageResponseDTO() {
        content = new ArrayList<T>();
    }
}
