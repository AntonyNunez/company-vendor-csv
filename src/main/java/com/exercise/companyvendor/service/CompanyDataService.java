package com.exercise.companyvendor.service;

import com.exercise.companyvendor.dto.CompanyData;
import com.exercise.companyvendor.dto.VendorData;
import com.exercise.companyvendor.util.CsvUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CompanyDataService {

    final static String REPLACE_REGEX = "\\s+";
    final static String EMPTY_STRING = "";
    final static String TRUE_VALUE = "t";
    final static String FALSE_VALUE = "f";

    public void checkData(String vendorFilePath, String companyFilePath, String outputFilePath)
            throws IOException {
        List<VendorData> vendorList = new ArrayList<>(generateVendorList(vendorFilePath)); // Obtains the Vendor List from CSV

        List<CompanyData> companyList = new ArrayList<>(generateCompanyList(companyFilePath)); // Obtains the Company List from CSV

        Long lastIndexAvailable = companyList.get(companyList.size() - 1).getId(); // Find out which is the last available Id
        List<CompanyData> newLines = new ArrayList<>();
        boolean newLine; // Flag to check if there is any difference between Company and Vendor

        for (CompanyData cd : companyList) {
            newLine = false;
            CompanyData newCd = new CompanyData(cd); // We clone the existing Company to instantiate the new one
            for (VendorData vd : vendorList) {
                if (vd.getUnlocode() != null && !vd.getUnlocode().isEmpty()) {
                    // Avoid empty spaces in the Unlocode
                    if (cd.getUnlocode().equals(vd.getUnlocode().replaceAll(REPLACE_REGEX, EMPTY_STRING))) {
                        // Checks any difference in the vendor id
                        newLine = isVendorIdEqual(cd, vd, newCd);

                        if (!cd.getName().equals(vd.getName())) {
                            newCd.setName(vd.getName());
                            newLine = true;
                        }
                    }
                } else {
                    // If Unlocode is null or empty, we try to find similar records based on the name
                    if (cd.getName().equals(vd.getName())) {
                        if (cd.getVendorPlaceId() == null || !cd.getVendorPlaceId().equals(vd.getId())) {
                            if (vd.getId() != null) {
                                newCd.setVendorPlaceId(vd.getId());
                                newLine = true;
                            }
                        }
                        // Checks any difference in the vendor id
                        newLine = isVendorIdEqual(cd, vd, newCd);
                    }
                }
            }

            // If the flag is true, we set the new values
            if (newLine) {
                newCd.setId(++lastIndexAvailable);
                newCd.setUpdatedAt(new Date());
                newCd.setIsActive(TRUE_VALUE);
                newLines.add(newCd);

                cd.setIsActive(FALSE_VALUE);
            }
        }

        // Concat the new List to the existing one
        if (!newLines.isEmpty()) {
            companyList.addAll(newLines);

            // Finally, we write the output CSV
            CsvUtil.companyWriter(companyList, outputFilePath);
        }
    }

    private static List<VendorData> generateVendorList(String vendorFilePath) throws IOException {
        return CsvUtil.genericReader(VendorData.class, new File(vendorFilePath));
    }

    private static List<CompanyData> generateCompanyList(String companyFilePath) throws IOException {
        return CsvUtil.genericReader(CompanyData.class, new File(companyFilePath));
    }

    private static boolean isVendorIdEqual(CompanyData cd, VendorData vd, CompanyData newCd) {
        if (cd.getVendorPlaceId() == null || !cd.getVendorPlaceId().equals(vd.getId())) {
            if (vd.getId() != null) {
                newCd.setVendorPlaceId(vd.getId());
                return true;
            }
        }
        return false;
    }
}
