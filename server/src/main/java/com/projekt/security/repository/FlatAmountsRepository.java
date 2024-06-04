package com.projekt.security.repository;

import com.projekt.security.entities.FlatAmounts;
import com.projekt.security.entities.FlatAmounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlatAmountsRepository extends JpaRepository<FlatAmounts, Integer> {


    //without price asc
    //equal year
    List<FlatAmounts> findByYearEquals(Integer year);

    //greater than year
    List<FlatAmounts> findByYearGreaterThanEqual(Integer year);

    //less than year
    List<FlatAmounts> findByYearLessThanEqual(Integer year);

    //region
    List<FlatAmounts> findByRegion(String region);
    List<FlatAmounts> findBySurfaceAndRegion(String surface, String region);
    List<FlatAmounts> findByYearGreaterThanEqualAndRegion(Integer year, String region);
    List<FlatAmounts> findByYearLessThanEqualAndRegion(Integer year, String region);
    List<FlatAmounts> findByYearEqualsAndRegion(Integer year, String region);
    List<FlatAmounts> findBySurfaceAndRegionAndYearLessThanEqual(String surface, String region, Integer year);
    List<FlatAmounts> findBySurfaceAndRegionAndYearGreaterThanEqual(String surface, String region, Integer year);
    List<FlatAmounts> findBySurfaceAndRegionAndYearEquals(String surface, String region, Integer year);

    //surface
    List<FlatAmounts> findBySurface(String surface);
    List<FlatAmounts> findBySurfaceAndYear(String surface, Integer year);
    List<FlatAmounts> findByYearGreaterThanEqualAndSurface(Integer year, String surface);
    List<FlatAmounts> findByYearLessThanEqualAndSurface(Integer year, String surface);

}

