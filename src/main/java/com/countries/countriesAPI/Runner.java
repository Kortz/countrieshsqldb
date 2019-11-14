package com.countries.countriesAPI;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.countries.Helpers.HCHandler;
import com.db.BorderDbDataTransfer;
import com.db.CountryDbDataTransfer;
import com.db.DbConnection;

import org.hsqldb.cmdline.SqlToolError;

public class Runner {
    public static void main(String[] args) throws SqlToolError, SQLException {
        DbConnection dbc = new DbConnection();
        dbc.createDatabase();
        HCHandler hch = new HCHandler();
        String jsonString = hch.getAPIData("https://restcountries.eu/rest/v2/all");
        List<Country> countryList = new ArrayList<Country>();
        countryList = hch.jsonToCountry(jsonString);
        CountryDbDataTransfer cddt = new CountryDbDataTransfer();
        cddt.populateCountryTable(countryList);
        BorderDbDataTransfer bddt = new BorderDbDataTransfer();
        bddt.populateBorderTable(countryList);
        
    }
}