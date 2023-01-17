package com.hackerrank.api.service.impl;

import com.hackerrank.api.exception.BadRequestException;
import com.hackerrank.api.model.Covid;
import com.hackerrank.api.model.Report;
import com.hackerrank.api.repository.CovidRepository;
import com.hackerrank.api.service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultCovidService implements CovidService {
  private final CovidRepository covidRepository;

  @Autowired
  DefaultCovidService(CovidRepository covidRepository) {
    this.covidRepository = covidRepository;
  }

  @Override
  public List<Covid> getAllCovid() {
    return covidRepository.findAll();
  }

  @Override
  public Covid createNewCovid(Covid covid) {
    if (covid.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new Scan");
    }

    return covidRepository.save(covid);
  }

  @Override
  public Optional<Covid> getCovidById(Long id) {
    return covidRepository.findById(id);
  }

  @Override
  public List<Covid> top5By(String by) {
	  List<Covid> covids=null;
		  covids=covidRepository.getTop5(by);
 
	return covids;
  }

  @Override
  public Integer totalBy(String by) {
    return covidRepository.getTotal(by);
  }

  @Override
  public List<Report> getReport() {
    return null;
  }
}
