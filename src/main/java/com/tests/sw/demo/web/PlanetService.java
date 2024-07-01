package com.tests.sw.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.tests.sw.demo.domain.Planet;
import com.tests.sw.demo.domain.PlanetRepository;
import com.tests.sw.demo.domain.QueryBuilder;

@Service
public class PlanetService {
    
    private PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }
    
    public Planet create(Planet planet) {
        return planetRepository.save(planet);
    }
    
    public Optional<Planet> get(Long id) {
    	return planetRepository.findById(id);
   
    }
    
    public Optional<Planet> getByName(String planetName){
    	return planetRepository.findByName(planetName);
    }

	public List<Planet> list(String terrain, String climate) {
		Example<Planet> query = QueryBuilder.makeQuery(new Planet(terrain, climate));
		return planetRepository.findAll(query);
	}
}   
