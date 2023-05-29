package com.gremio.controller;

import com.gremio.model.dto.response.PageableResponse;
import java.util.List;
import java.util.stream.Collectors;
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
    
    protected <S, T> PageableResponse<T> getPageableResponse(final Page<S> page, final Class<T> targetClass) {
        final List<T> collection = page
            .stream()
            .map(item -> conversionService.convert(item, targetClass))
            .collect(Collectors.toList());
        
        return new PageableResponse<>(page.getNumber(), page.getTotalPages(), page.getTotalElements(), page.getSize(), collection);
    }
}