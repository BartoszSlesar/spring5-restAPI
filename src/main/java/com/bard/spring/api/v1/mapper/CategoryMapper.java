package com.bard.spring.api.v1.mapper;

import com.bard.spring.api.v1.model.CategoryDTO;
import com.bard.spring.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
