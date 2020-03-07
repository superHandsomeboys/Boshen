package com.gpnu.boshen.service.impl;

import com.gpnu.boshen.dto.CompanyDTO;
import com.gpnu.boshen.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CompanyServiceImplTest {
    @Autowired
    CompanyService companyService;

    @Test
    void findById() {
        companyService.findById(1);
    }
}