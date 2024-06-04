package com.projekt.security.controllers;

import com.projekt.security.models.FlatPricesDto;
import com.projekt.security.services.FlatAmountsService;
import com.projekt.security.services.FlatPricesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prices")
@AllArgsConstructor
public class FlatPricesController {
    private final FlatPricesService service;
    private final FlatAmountsService serviceAmount;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<FlatPricesDto>> getFlatPrices(@RequestParam(value = "year", required = false) String year,
                                                             @RequestParam(value = "region", required = false) String region,
                                                             @RequestParam(value = "surface", required = false) String surface,
                                                             @RequestParam(value = "before", required = false) Integer before,
                                                             @RequestParam(value = "after", required = false) Integer after,
                                                             @RequestParam(value = "sortPrice", required = false) Integer sortPrice,
                                                             @RequestParam(value = "oneYear", required = false) Integer oneYear
    ){
        if (region != null && surface != null && year != null && oneYear == 1 && sortPrice != null && sortPrice == 1) {
            return new ResponseEntity<>(service.getSurfaceAndRegionAndYearSorted(surface, region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null && surface != null && year != null && oneYear == 1) {
            return new ResponseEntity<>(service.getSurfaceAndRegionAndYear(surface, region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null && surface != null && year != null && after == 1 && sortPrice != null && sortPrice == 1) {
            return new ResponseEntity<>(service.getSurfaceAndRegionAndYearGreaterSorted(surface, region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null && surface != null && year != null && after == 1) {
            return new ResponseEntity<>(service.getSurfaceAndRegionAndYearGreater(surface, region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null && surface != null && year != null && before == 1 && sortPrice != null && sortPrice == 1) {
            return new ResponseEntity<>(service.getSurfaceAndRegionAndYearLessSorted(surface, region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null && surface != null && year != null && before == 1) {
            return new ResponseEntity<>(service.getSurfaceAndRegionAndYearLess(surface, region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null  && year != null && before == 1 && sortPrice != null && sortPrice == 1) {
            return new ResponseEntity<>(service.getRegionAndYearLessSorted(region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null  && year != null && before == 1) {
            return new ResponseEntity<>(service.getRegionAndYearLess(region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null  && year != null && after == 1 && sortPrice != null && sortPrice == 1) {
            return new ResponseEntity<>(service.getRegionAndYearGreaterSorted(region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null  && year != null && after == 1) {
            return new ResponseEntity<>(service.getRegionAndYearGreater(region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null  && year != null && oneYear == 1 && sortPrice != null && sortPrice == 1) {
            return new ResponseEntity<>(service.getRegionAndYearSorted(region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null  && year != null && oneYear == 1) {
            return new ResponseEntity<>(service.getRegionAndYear(region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (surface != null && year != null && before == 1 && sortPrice != null && sortPrice == 1) {
            return new ResponseEntity<>(service.getSurfaceAndYearLessSorted(surface, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (surface != null && year != null && before == 1) {
            return new ResponseEntity<>(service.getSurfaceAndYearLess(surface, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (surface != null && year != null && after == 1 && sortPrice != null && sortPrice == 1) {
            return new ResponseEntity<>(service.getSurfaceAndYearGreaterSorted(surface, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (surface != null && year != null && after == 1) {
            return new ResponseEntity<>(service.getSurfaceAndYearGreater(surface, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (surface != null && year != null && oneYear == 1 && sortPrice != null && sortPrice == 1) {
            return new ResponseEntity<>(service.getSurfaceAndYearSorted(surface, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (surface != null && year != null && oneYear == 1) {
            return new ResponseEntity<>(service.getSurfaceAndYear(surface, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (year != null && before == 1 && sortPrice != null && sortPrice == 1){
            return new ResponseEntity<>(service.getBeforeSorted(Integer.parseInt(year)), HttpStatus.OK);
        }

        if (year != null && before == 1){
            return new ResponseEntity<>(service.getBefore(Integer.parseInt(year)), HttpStatus.OK);
        }

        if (year != null && after == 1 && sortPrice != null && sortPrice == 1){
            return new ResponseEntity<>(service.getAfterSorted(Integer.parseInt(year)), HttpStatus.OK);
        }

        if (year != null && after == 1){
            return new ResponseEntity<>(service.getAfter(Integer.parseInt(year)), HttpStatus.OK);
        }

        if (year != null && oneYear == 1  && sortPrice != null && sortPrice == 1){
            try {
                return new ResponseEntity<>(service.getOneYearSorted(Integer.parseInt(year)), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        if (year != null && oneYear == 1){
            try {
                return new ResponseEntity<>(service.getOneYear(Integer.parseInt(year)), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        if (region != null && surface != null && sortPrice != null && sortPrice == 1) {
            return new ResponseEntity<>(service.getSurfaceAndRegionSorted(surface, region), HttpStatus.OK);
        }

        if (region != null && surface != null) {
            return new ResponseEntity<>(service.getSurfaceAndRegion(surface, region), HttpStatus.OK);
        }

        if (region != null && sortPrice != null && sortPrice == 1) {
            return new ResponseEntity<>(service.getRegionSorted(region), HttpStatus.OK);
        }

        if (region != null) {
            return new ResponseEntity<>(service.getRegion(region), HttpStatus.OK);
        }

        if (surface != null && sortPrice != null && sortPrice == 1) {
            return new ResponseEntity<>(service.getSurfaceSorted(surface), HttpStatus.OK);
        }

        if (surface != null) {
            return new ResponseEntity<>(service.getSurface(surface), HttpStatus.OK);
        }

        if (sortPrice != null && sortPrice == 1) {
            return new ResponseEntity<>(service.getAllSorted(), HttpStatus.OK);
        }

        return new ResponseEntity<>(service.getAllPrices(), HttpStatus.OK);

    }

}
