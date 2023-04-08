package com.flight.overall.utils;

import com.flight.overall.dto.CategoryDTO;
import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;

import java.util.Locale;

public class CategoryFormatter implements Formatter<CategoryDTO> {

    @Override
    public CategoryDTO parse(@NonNull String id, @NonNull Locale locale) {
        CategoryDTO category = new CategoryDTO();
        try {
            category.setId(Long.parseLong(id));
            return category;
        } catch (NumberFormatException numberFormatException) {
            return category;
        }
    }

    @Override
    public String print(CategoryDTO category, @NonNull Locale locale) {
        return String.valueOf(category.getId());
    }
}
