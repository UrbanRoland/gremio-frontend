package com.gremio.controller;

import com.gremio.model.dto.response.PageableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;

@RequiredArgsConstructor
public abstract class AbstractController {
    private final ConversionService conversionService;
    protected <T> PageableResponse<T> getPageableResponse(final Page<T> page) {
        return new PageableResponse<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(), page.getSize(),
                page.getContent());
    }
}