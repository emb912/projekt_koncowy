package com.projekt.security.services;

import com.projekt.security.entities.FlatAmounts;
import com.projekt.security.entities.FlatAmounts;
import com.projekt.security.models.FlatAmountsDto;
import com.projekt.security.models.FlatAmountsDto;
import com.projekt.security.repository.FlatAmountsRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FlatAmountsService {
    private final FlatAmountsRepository repository;

    @Autowired
    public FlatAmountsService(FlatAmountsRepository repository){
        this.repository = repository;
    }


    private List<FlatAmountsDto> sortResults(List<FlatAmounts> amounts) {
        amounts.sort(Comparator.comparing(FlatAmounts::getRegion)
                .thenComparing(FlatAmounts::getYear)
                .thenComparing(FlatAmounts::getQuarter)
                .thenComparing(FlatAmounts::getSurface));
        List<FlatAmountsDto> amountsDto = new ArrayList<>();
        for (FlatAmounts i : amounts) {
            amountsDto.add(new FlatAmountsDto(i));
        }
        return amountsDto;
    }

    private List<FlatAmountsDto> sortResultsByAmountAsc(List<FlatAmounts> amounts) {
        amounts.sort(Comparator.comparing(FlatAmounts::getAmount));
        List<FlatAmountsDto> amountsDto = new ArrayList<>();
        for (FlatAmounts i : amounts) {
            amountsDto.add(new FlatAmountsDto(i));
        }
        return amountsDto;
    }

    public List<FlatAmountsDto> getAllAmounts() {
        List<FlatAmounts> amounts = repository.findAll(Sort.by(Sort.Direction.ASC, "region", "year", "surface", "quarter"));
        List<FlatAmountsDto> amountsDto = new ArrayList<>();
        for (FlatAmounts data :
                amounts) {
            amountsDto.add(new FlatAmountsDto(data));
        }
        return amountsDto;
    }

    public List<FlatAmountsDto> getAllSorted() {
        List<FlatAmounts> amounts = repository.findAll();
        return sortResultsByAmountAsc(amounts);
    }

    public List<FlatAmountsDto> getOneYear(Integer year) throws BadRequestException {
        List<FlatAmounts> amounts = repository.findByYearEquals(year);
        if(amounts.isEmpty()) {
            throw new BadRequestException("no data with this year");
        }
        else {
            return sortResults(amounts);
        }
    }

    public List<FlatAmountsDto> getOneYearSorted(Integer year) throws BadRequestException {
        List<FlatAmounts> amounts = repository.findByYearEquals(year);
        if(amounts.isEmpty()) {
            throw new BadRequestException("no data with this year");
        }
        else {
            return sortResultsByAmountAsc(amounts);
        }
    }

    public List<FlatAmountsDto> getBefore(Integer year) {
        List<FlatAmounts> amounts = repository.findByYearLessThanEqual(year);
        return sortResults(amounts);
    }

    public List<FlatAmountsDto> getBeforeSorted(Integer year) {
        List<FlatAmounts> amounts = repository.findByYearLessThanEqual(year);
        return sortResultsByAmountAsc(amounts);
    }

    public List<FlatAmountsDto> getAfter(Integer year) {
        List<FlatAmounts> amounts = repository.findByYearGreaterThanEqual(year);
        return sortResults(amounts);
    }

    public List<FlatAmountsDto> getAfterSorted(Integer year) {
        List<FlatAmounts> amounts = repository.findByYearGreaterThanEqual(year);
        return sortResultsByAmountAsc(amounts);
    }

    public List<FlatAmountsDto> getRegion(String region) {
        List<FlatAmounts> amounts = repository.findByRegion(region);
        return sortResults(amounts);
    }

    public List<FlatAmountsDto> getRegionSorted(String region) {
        List<FlatAmounts> amounts = repository.findByRegion(region);
        return sortResultsByAmountAsc(amounts);
    }

    public List<FlatAmountsDto> getSurfaceAndRegion(String surface, String region) {
        List<FlatAmounts> amounts = repository.findBySurfaceAndRegion(surface, region);
        return sortResults(amounts);
    }

    public List<FlatAmountsDto> getSurfaceAndRegionSorted(String surface, String region) {
        List<FlatAmounts> amounts = repository.findBySurfaceAndRegion(surface, region);
        return sortResultsByAmountAsc(amounts);
    }

    public List<FlatAmountsDto> getRegionAndYear(String region, Integer year) {
        List<FlatAmounts> amounts = repository.findByYearEqualsAndRegion(year, region);
        return sortResults(amounts);
    }

    public List<FlatAmountsDto> getRegionAndYearSorted(String region, Integer year) {
        List<FlatAmounts> amounts = repository.findByYearEqualsAndRegion(year, region);
        return sortResultsByAmountAsc(amounts);
    }

    public List<FlatAmountsDto> getRegionAndYearLess(String region, Integer year) {
        List<FlatAmounts> amounts = repository.findByYearLessThanEqualAndRegion(year, region);
        return sortResults(amounts);
    }

    public List<FlatAmountsDto> getRegionAndYearLessSorted(String region, Integer year) {
        List<FlatAmounts> amounts = repository.findByYearLessThanEqualAndRegion(year, region);
        return sortResultsByAmountAsc(amounts);
    }

    public List<FlatAmountsDto> getRegionAndYearGreater(String region, Integer year) {
        List<FlatAmounts> amounts = repository.findByYearGreaterThanEqualAndRegion(year, region);
        return sortResults(amounts);
    }

    public List<FlatAmountsDto> getRegionAndYearGreaterSorted(String region, Integer year) {
        List<FlatAmounts> amounts = repository.findByYearGreaterThanEqualAndRegion(year, region);
        return sortResultsByAmountAsc(amounts);
    }

    public List<FlatAmountsDto> getSurfaceAndRegionAndYear(String surface, String region, Integer year) {
        List<FlatAmounts> amounts = repository.findBySurfaceAndRegionAndYearEquals(surface, region, year);
        return sortResults(amounts);
    }

    public List<FlatAmountsDto> getSurfaceAndRegionAndYearSorted(String surface, String region, Integer year) {
        List<FlatAmounts> amounts = repository.findBySurfaceAndRegionAndYearEquals(surface, region, year);
        return sortResultsByAmountAsc(amounts);
    }


    public List<FlatAmountsDto> getSurfaceAndRegionAndYearLess(String surface, String region, Integer year) {
        List<FlatAmounts> amounts = repository.findBySurfaceAndRegionAndYearLessThanEqual(surface, region, year);
        return sortResults(amounts);
    }


    public List<FlatAmountsDto> getSurfaceAndRegionAndYearLessSorted(String surface, String region, Integer year) {
        List<FlatAmounts> amounts = repository.findBySurfaceAndRegionAndYearLessThanEqual(surface, region, year);
        return sortResultsByAmountAsc(amounts);
    }

    public List<FlatAmountsDto> getSurfaceAndRegionAndYearGreater(String surface, String region, Integer year) {
        List<FlatAmounts> amounts = repository.findBySurfaceAndRegionAndYearGreaterThanEqual(surface, region, year);
        return sortResults(amounts);
    }

    public List<FlatAmountsDto> getSurfaceAndRegionAndYearGreaterSorted(String surface, String region, Integer year) {
        List<FlatAmounts> amounts = repository.findBySurfaceAndRegionAndYearGreaterThanEqual(surface, region, year);
        return sortResultsByAmountAsc(amounts);
    }

    public List<FlatAmountsDto> getSurface(String surface) {
        List<FlatAmounts> amounts = repository.findBySurface(surface);
        return sortResults(amounts);
    }

    public List<FlatAmountsDto> getSurfaceSorted(String surface) {
        List<FlatAmounts> amounts = repository.findBySurface(surface);
        return sortResultsByAmountAsc(amounts);
    }

    public List<FlatAmountsDto> getSurfaceAndYear(String surface, Integer year) {
        List<FlatAmounts> amounts = repository.findBySurfaceAndYear(surface, year);
        return sortResults(amounts);
    }

    public List<FlatAmountsDto> getSurfaceAndYearSorted(String surface, Integer year) {
        List<FlatAmounts> amounts = repository.findBySurfaceAndYear(surface, year);
        return sortResultsByAmountAsc(amounts);
    }

    public List<FlatAmountsDto> getSurfaceAndYearLess(String surface, Integer year) {
        List<FlatAmounts> amounts = repository.findByYearLessThanEqualAndSurface(year, surface);
        return sortResults(amounts);
    }


    public List<FlatAmountsDto> getSurfaceAndYearLessSorted(String surface, Integer year) {
        List<FlatAmounts> amounts = repository.findByYearLessThanEqualAndSurface(year, surface);
        return sortResultsByAmountAsc(amounts);
    }

    public List<FlatAmountsDto> getSurfaceAndYearGreater(String surface, Integer year) {
        List<FlatAmounts> amounts = repository.findByYearGreaterThanEqualAndSurface(year, surface);
        return sortResults(amounts);
    }

    public List<FlatAmountsDto> getSurfaceAndYearGreaterSorted(String surface, Integer year) {
        List<FlatAmounts> amounts = repository.findByYearGreaterThanEqualAndSurface(year, surface);
        return sortResultsByAmountAsc(amounts);
    }
}


