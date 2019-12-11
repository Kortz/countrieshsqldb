package com.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.countries.countriesAPI.models.Country;

public class CountryDbDataTransfer {

    public CountryDbDataTransfer() {

    }

    public List<Country> getCountryList(Connection connection) throws SQLException {
 
        ArrayList<Country> countryList = new ArrayList<>();
        
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM country");){
            
            try(ResultSet rs = ps.executeQuery();){
                while(rs.next()){
                    Country country = new Country();
                    country.setId(rs.getInt("id"));
                    country.setName(rs.getString("name"));
                    country.setCapital(rs.getString("capital"));
                    country.setPopulation((long)rs.getInt("population"));
                    countryList.add(country);
                }
            }
            
        }
        return countryList;
    }

    public void populateCountryTable(List<Country> countryList, Connection connection) throws SQLException {
           
            InputStream is = getClass().getResourceAsStream("sqlScripts/populateCountryTable.sql");
            
            Scanner sc = new Scanner(is);
        try{
            StringBuffer sb = new StringBuffer();
            
            while(sc.hasNext()){
                sb.append(sc.nextLine());
            }
            
            PreparedStatement ps = connection.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            
            

            for(Country c: countryList){
                ps.setString(1, c.getName());
                ps.setString(2, c.getCapital());
                ps.setLong(3, c.getPopulation());
                ps.setString(4, c.getRegion());
                ps.setString(5, c.getFlag());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int returnedId = rs.getInt("id");
                c.setId(returnedId);
                
            }
            
            
    
            } finally {
               
                sc.close();
            }

    }
  
}