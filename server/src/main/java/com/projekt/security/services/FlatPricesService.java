package com.projekt.security.services;

import com.projekt.security.entities.FlatPrices;
import com.projekt.security.models.FlatPricesDto;
import com.projekt.security.repository.FlatPricesRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FlatPricesService {
    private final FlatPricesRepository repository;

    @Autowired
    public FlatPricesService(FlatPricesRepository repository){
        this.repository = repository;
    }

    private List<FlatPricesDto> sortResults(List<FlatPrices> prices) {
        prices.sort(Comparator.comparing(FlatPrices::getRegion)
                .thenComparing(FlatPrices::getYear)
                .thenComparing(FlatPrices::getQuarter)
                .thenComparing(FlatPrices::getSurface));
        List<FlatPricesDto> pricesDto = new ArrayList<>();
        for (FlatPrices i : prices) {
            pricesDto.add(new FlatPricesDto(i));
        }
        return pricesDto;
    }

    private List<FlatPricesDto> sortResultsByPriceAsc(List<FlatPrices> prices) {
        prices.sort(Comparator.comparing(FlatPrices::getPrice));
        List<FlatPricesDto> pricesDto = new ArrayList<>();
        for (FlatPrices i : prices) {
            pricesDto.add(new FlatPricesDto(i));
        }
        return pricesDto;
    }

    public List<FlatPricesDto> getAllPrices() {
        List<FlatPrices> prices = repository.findAll(Sort.by(Sort.Direction.ASC, "region", "year", "surface", "quarter"));
        List<FlatPricesDto> pricesDto = new ArrayList<>();
        for (FlatPrices data :
                prices) {
            pricesDto.add(new FlatPricesDto(data));
        }
        return pricesDto;
    }

    public List<FlatPricesDto> getAllSorted() {
        List<FlatPrices> prices = repository.findAll();
        return sortResultsByPriceAsc(prices);
    }

    public List<FlatPricesDto> getOneYear(Integer year) throws BadRequestException {
        List<FlatPrices> prices = repository.findByYearEquals(year);
        if(prices.isEmpty()) {
            throw new BadRequestException("no data with this year");
        }
        else {
            return sortResults(prices);
        }
    }

    public List<FlatPricesDto> getOneYearSorted(Integer year) throws BadRequestException {
        List<FlatPrices> prices = repository.findByYearEquals(year);
        if(prices.isEmpty()) {
            throw new BadRequestException("no data with this year");
        }
        else {
            return sortResultsByPriceAsc(prices);
        }
    }

    public List<FlatPricesDto> getBefore(Integer year) {
        List<FlatPrices> prices = repository.findByYearLessThanEqual(year);
        return sortResults(prices);
    }

    public List<FlatPricesDto> getBeforeSorted(Integer year) {
        List<FlatPrices> prices = repository.findByYearLessThanEqual(year);
        return sortResultsByPriceAsc(prices);
    }

    public List<FlatPricesDto> getAfter(Integer year) {
        List<FlatPrices> prices = repository.findByYearGreaterThanEqual(year);
        return sortResults(prices);
    }

    public List<FlatPricesDto> getAfterSorted(Integer year) {
        List<FlatPrices> prices = repository.findByYearGreaterThanEqual(year);
        return sortResultsByPriceAsc(prices);
    }

    public List<FlatPricesDto> getRegion(String region) {
        List<FlatPrices> prices = repository.findByRegion(region);
        return sortResults(prices);
    }

    public List<FlatPricesDto> getRegionSorted(String region) {
        List<FlatPrices> prices = repository.findByRegion(region);
        return sortResultsByPriceAsc(prices);
    }

    public List<FlatPricesDto> getSurfaceAndRegion(String surface, String region) {
        List<FlatPrices> prices = repository.findBySurfaceAndRegion(surface, region);
        return sortResults(prices);
    }

    public List<FlatPricesDto> getSurfaceAndRegionSorted(String surface, String region) {
        List<FlatPrices> prices = repository.findBySurfaceAndRegion(surface, region);
        return sortResultsByPriceAsc(prices);
    }

    public List<FlatPricesDto> getRegionAndYear(String region, Integer year) {
        List<FlatPrices> prices = repository.findByYearEqualsAndRegion(year, region);
        return sortResults(prices);
    }

    public List<FlatPricesDto> getRegionAndYearSorted(String region, Integer year) {
        List<FlatPrices> prices = repository.findByYearEqualsAndRegion(year, region);
        return sortResultsByPriceAsc(prices);
    }

    public List<FlatPricesDto> getRegionAndYearLess(String region, Integer year) {
        List<FlatPrices> prices = repository.findByYearLessThanEqualAndRegion(year, region);
        return sortResults(prices);
    }

    public List<FlatPricesDto> getRegionAndYearLessSorted(String region, Integer year) {
        List<FlatPrices> prices = repository.findByYearLessThanEqualAndRegion(year, region);
        return sortResultsByPriceAsc(prices);
    }

    public List<FlatPricesDto> getRegionAndYearGreater(String region, Integer year) {
        List<FlatPrices> prices = repository.findByYearGreaterThanEqualAndRegion(year, region);
        return sortResults(prices);
    }

    public List<FlatPricesDto> getRegionAndYearGreaterSorted(String region, Integer year) {
        List<FlatPrices> prices = repository.findByYearGreaterThanEqualAndRegion(year, region);
        return sortResultsByPriceAsc(prices);
    }

    public List<FlatPricesDto> getSurfaceAndRegionAndYear(String surface, String region, Integer year) {
        List<FlatPrices> prices = repository.findBySurfaceAndRegionAndYearEquals(surface, region, year);
        return sortResults(prices);
    }

    public List<FlatPricesDto> getSurfaceAndRegionAndYearSorted(String surface, String region, Integer year) {
        List<FlatPrices> prices = repository.findBySurfaceAndRegionAndYearEquals(surface, region, year);
        return sortResultsByPriceAsc(prices);
    }


    public List<FlatPricesDto> getSurfaceAndRegionAndYearLess(String surface, String region, Integer year) {
        List<FlatPrices> prices = repository.findBySurfaceAndRegionAndYearLessThanEqual(surface, region, year);
        return sortResults(prices);
    }


    public List<FlatPricesDto> getSurfaceAndRegionAndYearLessSorted(String surface, String region, Integer year) {
        List<FlatPrices> prices = repository.findBySurfaceAndRegionAndYearLessThanEqual(surface, region, year);
        return sortResultsByPriceAsc(prices);
    }

    public List<FlatPricesDto> getSurfaceAndRegionAndYearGreater(String surface, String region, Integer year) {
        List<FlatPrices> prices = repository.findBySurfaceAndRegionAndYearGreaterThanEqual(surface, region, year);
        return sortResults(prices);
    }

    public List<FlatPricesDto> getSurfaceAndRegionAndYearGreaterSorted(String surface, String region, Integer year) {
        List<FlatPrices> prices = repository.findBySurfaceAndRegionAndYearGreaterThanEqual(surface, region, year);
        return sortResultsByPriceAsc(prices);
    }

    public List<FlatPricesDto> getSurface(String surface) {
        List<FlatPrices> prices = repository.findBySurface(surface);
        return sortResults(prices);
    }

    public List<FlatPricesDto> getSurfaceSorted(String surface) {
        List<FlatPrices> prices = repository.findBySurface(surface);
        return sortResultsByPriceAsc(prices);
    }

    public List<FlatPricesDto> getSurfaceAndYear(String surface, Integer year) {
        List<FlatPrices> prices = repository.findBySurfaceAndYear(surface, year);
        return sortResults(prices);
    }

    public List<FlatPricesDto> getSurfaceAndYearSorted(String surface, Integer year) {
        List<FlatPrices> prices = repository.findBySurfaceAndYear(surface, year);
        return sortResultsByPriceAsc(prices);
    }

    public List<FlatPricesDto> getSurfaceAndYearLess(String surface, Integer year) {
        List<FlatPrices> prices = repository.findByYearLessThanEqualAndSurface(year, surface);
        return sortResults(prices);
    }


    public List<FlatPricesDto> getSurfaceAndYearLessSorted(String surface, Integer year) {
        List<FlatPrices> prices = repository.findByYearLessThanEqualAndSurface(year, surface);
        return sortResultsByPriceAsc(prices);
    }

    public List<FlatPricesDto> getSurfaceAndYearGreater(String surface, Integer year) {
        List<FlatPrices> prices = repository.findByYearGreaterThanEqualAndSurface(year, surface);
        return sortResults(prices);
    }

    public List<FlatPricesDto> getSurfaceAndYearGreaterSorted(String surface, Integer year) {
        List<FlatPrices> prices = repository.findByYearGreaterThanEqualAndSurface(year, surface);
        return sortResultsByPriceAsc(prices);
    }

}