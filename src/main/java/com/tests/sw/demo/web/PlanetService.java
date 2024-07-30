package com.tests.sw.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            Planet result = this.get(planet.getId()).orElseThrow();
            if(!result.getId().equals(planet.getId()) || !result.getName().equals(planet.getName())) {
                return planetRepository.save(planet);
            }

        } catch(DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
        return null;
    }
    
    public Optional<Planet> get(Long id) {
    	return planetRepository.findById(id);
   
    }
    
    public Optional<Planet> getByName(String planetName){
    	return planetRepository.findPlanetByName(planetName);
    }

	public List<Planet> list(String terrain, String climate) {
		Example<Planet> query = QueryBuilder.makeQuery(new Planet(terrain, climate));
		return planetRepository.findAll(query);
	}
}   
