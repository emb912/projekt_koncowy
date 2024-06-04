package com.projekt.security.repository;

import com.projekt.security.entities.FlatPrices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlatPricesRepository extends JpaRepository<FlatPrices, Integer> {

    //without price asc
    //equal year
    List<FlatPrices> findByYearEquals(Integer year);

    //greater than year
    List<FlatPrices> findByYearGreaterThanEqual(Integer year);

    //less than year
    List<FlatPrices> findByYearLessThanEqual(Integer year);

    //region
    List<FlatPrices> findByRegion(String region);
    List<FlatPrices> findBySurfaceAndRegion(String surface, String region);
    List<FlatPrices> findByYearGreaterThanEqualAndRegion(Integer year, String region);
    List<FlatPrices> findByYearLessThanEqualAndRegion(Integer year, String region);
    List<FlatPrices> findByYearEqualsAndRegion(Integer year, String region);
    List<FlatPrices> findBySurfaceAndRegionAndYearLessThanEqual(String surface, String region, Integer year);
    List<FlatPrices> findBySurfaceAndRegionAndYearGreaterThanEqual(String surface, String region, Integer year);
    List<FlatPrices> findBySurfaceAndRegionAndYearEquals(String surface, String region, Integer year);

    //surface
    List<FlatPrices> findBySurface(String surface);
    List<FlatPrices> findBySurfaceAndYear(String surface, Integer year);
    List<FlatPrices> findByYearGreaterThanEqualAndSurface(Integer year, String surface);
    List<FlatPrices> findByYearLessThanEqualAndSurface(Integer year, String surface);



}
