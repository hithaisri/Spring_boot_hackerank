package com.hackerrank.api.controller;

import com.hackerrank.api.model.Covid;
import com.hackerrank.api.service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/covid")
public class CovidController {
  private final CovidService covidService;

  @Autowired
  public CovidController(CovidService covidService) {
    this.covidService = covidService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Covid> getAllCovid() {
    return covidService.getAllCovid();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Covid createCovid(@RequestBody Covid covid) {
    return covidService.createNewCovid(covid);
  }
  
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Covid> getCovidById(@PathVariable Long id) {
    return covidService.getCovidById(id);
  }
  
  @GetMapping("/top")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Covid>> getCovidTop5(@RequestParam(value="by") String by) {
	  List<Covid> covids=new ArrayList<>();
	  covids = covidService.top5By(by);
	  if(covids!=null && covids.size()>0)
		  return new ResponseEntity<List<Covid>>(covids,HttpStatus.OK);
	  else
	      return new ResponseEntity(HttpStatus.BAD_REQUEST);
	 
  }
  
  @GetMapping("/total")
  public ResponseEntity<Integer> getTotal(@RequestParam(value="by") String by) {
	  Integer total=0;
	  total = covidService.totalBy(by);
	  if(total!=null && total>0)
		  return new ResponseEntity<Integer>(total,HttpStatus.OK);
	  else
	      return new ResponseEntity(HttpStatus.BAD_REQUEST);
	 
  }
  
  
  
}
