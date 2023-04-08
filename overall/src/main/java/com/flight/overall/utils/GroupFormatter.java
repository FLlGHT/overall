package com.flight.overall.utils;

import com.flight.overall.dto.CategoryGroupDTO;
import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;

import java.util.Locale;

public class GroupFormatter implements Formatter<CategoryGroupDTO> {

    @Override
    public CategoryGroupDTO parse(@NonNull String id, @NonNull Locale locale) {
        CategoryGroupDTO group = new CategoryGroupDTO();
        try {
            group.setId(Long.parseLong(id));
            return group;
        } catch (NumberFormatException numberFormatException) {
            return group;
        }
    }

    @Override
    public String print(CategoryGroupDTO group, @NonNull Locale locale) {
        return String.valueOf(group.getId());
    }
}
