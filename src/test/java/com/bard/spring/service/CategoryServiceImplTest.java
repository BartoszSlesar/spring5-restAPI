package com.bard.spring.service;

import com.bard.spring.api.v1.mapper.CategoryMapper;
import com.bard.spring.api.v1.model.CategoryDTO;
import com.bard.spring.domain.Category;
import com.bard.spring.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CategoryServiceImplTest {

    public final Long ID = 1L;
    public final String NAME = "Fruits";
    @Mock
    CategoryRepository categoryRepository;

    CategoryService categoryService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    void getAllCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        List<Category> categories = Arrays.asList(fruits, dried, fresh);
        when(categoryRepository.findAll()).thenReturn(categories);
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
        verify(categoryRepository, times(1)).findAll();
        assertEquals(categoryDTOS.size(), categories.size());

    }

    @Test
    void getCategoryByName() {
        Category fruits = new Category();
        fruits.setId(ID);
        fruits.setName(NAME);
        when(categoryRepository.findByName(NAME)).thenReturn(fruits);

        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);
        verify(categoryRepository, times(1)).findByName(NAME);
        assertAll(
                () -> assertEquals(NAME, categoryDTO.getName()),
                () -> assertEquals(ID, categoryDTO.getId())
        );
    }
}