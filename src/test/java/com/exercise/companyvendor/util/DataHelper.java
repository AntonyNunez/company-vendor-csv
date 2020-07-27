package com.exercise.companyvendor.util;

import com.exercise.companyvendor.dto.CompanyData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataHelper {

    /**
     * Creates a data set of companies.
     *
     * @return the company list.
     */
    public static List<CompanyData> createCompanyDataList() {
        List<CompanyData> companyList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            companyList.add(createCompanyData(i));
        }
        return companyList;
    }

    private static CompanyData createCompanyData(int i) {
        return new CompanyData((long) i, "Name" + i, "t", new Date(), new Date(),
                "Unlocode" + i, "PlaceIdentityId" + i, (long) i + 10);
    }
}
