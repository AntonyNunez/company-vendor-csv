package com.exercise.companyvendor.service;

import com.exercise.companyvendor.dto.CompanyData;
import com.exercise.companyvendor.util.CsvUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CompanyDataServiceTest {

    final static String VENDOR_FILE_PATH = "src/test/resources/vendor-place-test.csv";
    final static String COMPANY_FILE_PATH = "src/test/resources/company-place-test.csv";
    final static String OUTPUT_FILE_PATH = "src/test/resources/output-place-test.csv";

    @InjectMocks
    CompanyDataService companyDataService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_checkData_ok() throws IOException {
        companyDataService.checkData(VENDOR_FILE_PATH, COMPANY_FILE_PATH, OUTPUT_FILE_PATH);

        List<CompanyData> companyDataList = CsvUtil.genericReader(CompanyData.class, new File(OUTPUT_FILE_PATH));
        assertEquals(31, companyDataList.size());
    }
}
