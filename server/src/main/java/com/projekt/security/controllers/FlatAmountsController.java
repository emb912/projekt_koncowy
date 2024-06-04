package com.projekt.security.controllers;

import com.projekt.security.models.FlatAmountsDto;
import com.projekt.security.services.FlatAmountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amounts")
@AllArgsConstructor
public class FlatAmountsController {
    private final FlatAmountsService serviceAmount;


    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<FlatAmountsDto>> getFlatAmounts(@RequestParam(value = "year", required = false) String year,
                                                               @RequestParam(value = "region", required = false) String region,
                                                               @RequestParam(value = "surface", required = false) String surface,
                                                               @RequestParam(value = "before", required = false) Integer before,
                                                               @RequestParam(value = "after", required = false) Integer after,
                                                               @RequestParam(value = "sortAmount", required = false) Integer sort,
                                                               @RequestParam(value = "oneYear", required = false) Integer oneYear
    ){
        if (region != null && surface != null && year != null && oneYear == 1 && sort != null && sort == 1) {
            return new ResponseEntity<>(serviceAmount.getSurfaceAndRegionAndYearSorted(surface, region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null && surface != null && year != null && oneYear == 1) {
            return new ResponseEntity<>(serviceAmount.getSurfaceAndRegionAndYear(surface, region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null && surface != null && year != null && after == 1 && sort != null && sort == 1) {
            return new ResponseEntity<>(serviceAmount.getSurfaceAndRegionAndYearGreaterSorted(surface, region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null && surface != null && year != null && after == 1) {
            return new ResponseEntity<>(serviceAmount.getSurfaceAndRegionAndYearGreater(surface, region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null && surface != null && year != null && before == 1 && sort != null && sort == 1) {
            return new ResponseEntity<>(serviceAmount.getSurfaceAndRegionAndYearLessSorted(surface, region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null && surface != null && year != null && before == 1) {
            return new ResponseEntity<>(serviceAmount.getSurfaceAndRegionAndYearLess(surface, region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null  && year != null && before == 1 && sort != null && sort == 1) {
            return new ResponseEntity<>(serviceAmount.getRegionAndYearLessSorted(region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null  && year != null && before == 1) {
            return new ResponseEntity<>(serviceAmount.getRegionAndYearLess(region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null  && year != null && after == 1 && sort != null && sort == 1) {
            return new ResponseEntity<>(serviceAmount.getRegionAndYearGreaterSorted(region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null  && year != null && after == 1) {
            return new ResponseEntity<>(serviceAmount.getRegionAndYearGreater(region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null  && year != null && oneYear == 1 && sort != null && sort == 1) {
            return new ResponseEntity<>(serviceAmount.getRegionAndYearSorted(region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (region != null  && year != null && oneYear == 1) {
            return new ResponseEntity<>(serviceAmount.getRegionAndYear(region, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (surface != null && year != null && before == 1 && sort != null && sort == 1) {
            return new ResponseEntity<>(serviceAmount.getSurfaceAndYearLessSorted(surface, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (surface != null && year != null && before == 1) {
            return new ResponseEntity<>(serviceAmount.getSurfaceAndYearLess(surface, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (surface != null && year != null && after == 1 && sort != null && sort == 1) {
            return new ResponseEntity<>(serviceAmount.getSurfaceAndYearGreaterSorted(surface, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (surface != null && year != null && after == 1) {
            return new ResponseEntity<>(serviceAmount.getSurfaceAndYearGreater(surface, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (surface != null && year != null && oneYear == 1 && sort != null && sort == 1) {
            return new ResponseEntity<>(serviceAmount.getSurfaceAndYearSorted(surface, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (surface != null && year != null && oneYear == 1) {
            return new ResponseEntity<>(serviceAmount.getSurfaceAndYear(surface, Integer.parseInt(year)), HttpStatus.OK);
        }

        if (year != null && before == 1 && sort != null && sort == 1){
            return new ResponseEntity<>(serviceAmount.getBeforeSorted(Integer.parseInt(year)), HttpStatus.OK);
        }

        if (year != null && before == 1){
            return new ResponseEntity<>(serviceAmount.getBefore(Integer.parseInt(year)), HttpStatus.OK);
        }

        if (year != null && after == 1 && sort != null && sort == 1){
            return new ResponseEntity<>(serviceAmount.getAfterSorted(Integer.parseInt(year)), HttpStatus.OK);
        }

        if (year != null && after == 1){
            return new ResponseEntity<>(serviceAmount.getAfter(Integer.parseInt(year)), HttpStatus.OK);
        }

        if (year != null && oneYear == 1  && sort != null && sort == 1){
            try {
                return new ResponseEntity<>(serviceAmount.getOneYearSorted(Integer.parseInt(year)), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        if (year != null && oneYear == 1){
            try {
                return new ResponseEntity<>(serviceAmount.getOneYear(Integer.parseInt(year)), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        if (region != null && surface != null && sort!= null && sort == 1) {
            return new ResponseEntity<>(serviceAmount.getSurfaceAndRegionSorted(surface, region), HttpStatus.OK);
        }

        if (region != null && surface != null) {
            return new ResponseEntity<>(serviceAmount.getSurfaceAndRegion(surface, region), HttpStatus.OK);
        }

        if (region != null && sort!= null && sort == 1) {
            return new ResponseEntity<>(serviceAmount.getRegionSorted(region), HttpStatus.OK);
        }

        if (region != null) {
            return new ResponseEntity<>(serviceAmount.getRegion(region), HttpStatus.OK);
        }

        if (surface != null && sort!= null && sort == 1) {
            return new ResponseEntity<>(serviceAmount.getSurfaceSorted(surface), HttpStatus.OK);
        }

        if (surface != null) {
            return new ResponseEntity<>(serviceAmount.getSurface(surface), HttpStatus.OK);
        }

        if (sort!= null && sort == 1) {
            return new ResponseEntity<>(serviceAmount.getAllSorted(), HttpStatus.OK);
        }

        return new ResponseEntity<>(serviceAmount.getAllAmounts(), HttpStatus.OK);
    }

}


