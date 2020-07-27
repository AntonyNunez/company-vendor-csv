package com.exercise.companyvendor.util;

import com.exercise.companyvendor.dto.CompanyData;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CsvUtilTest {

    @Test
    public void test_genericReader_ok() throws IOException {
        File file = new File("src/test/resources/company-place-test.csv");
        List<CompanyData> companyList = CsvUtil.genericReader(CompanyData.class, file);
        assertFalse(companyList.isEmpty());
    }

    @Test(expected = FileNotFoundException.class)
    public void test_genericReader_fileNotFound() throws IOException {
        File file = new File("src/test/resources/unknown.csv");
        List<CompanyData> companyList = CsvUtil.genericReader(CompanyData.class, file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_genericReader_nullFile() throws IOException {
        List<CompanyData> companyList = CsvUtil.genericReader(CompanyData.class, null);
    }

    @Test
    public void test_companyWriter_ok() throws IOException {
        List<CompanyData> companyList = DataHelper.createCompanyDataList();
        CsvUtil.companyWriter(companyList, "src/test/resources/output-place-test.csv");
    }

    @Test(expected = FileNotFoundException.class)
    public void test_companyWriter_fileNotFound() throws IOException {
        List<CompanyData> companyList = DataHelper.createCompanyDataList();
        CsvUtil.companyWriter(companyList, "src/test/unknown/output-place.csv");
    }

    @Test(expected = NullPointerException.class)
    public void test_companyWriter_nullFile() throws IOException {
        List<CompanyData> companyList = DataHelper.createCompanyDataList();
        CsvUtil.companyWriter(companyList, null);
    }
}
