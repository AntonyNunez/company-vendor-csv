package com.exercise.companyvendor.controller;

import com.exercise.companyvendor.service.CompanyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataController {

    /* For a "real world" approach, this class would be annotated with @RestController.
     * A POST Rest API would also be created to allow the upload of files.
     * For this exercise, we will suppose the entry point of the application is the main method. */

    final static String VENDOR_FILE_PATH = "src/main/resources/vendor-place.csv";
    final static String COMPANY_FILE_PATH = "src/main/resources/company-place.csv";
    final static String OUTPUT_FILE_PATH = "src/main/resources/output-place.csv";

    private CompanyDataService companyDataService;

    @Autowired
    public DataController(CompanyDataService companyDataService) {
        this.companyDataService = companyDataService;
    }

    public void main() throws IOException {
        companyDataService.checkData(VENDOR_FILE_PATH, COMPANY_FILE_PATH, OUTPUT_FILE_PATH);
    }
}
