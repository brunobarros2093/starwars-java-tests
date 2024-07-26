package com.tests.sw.demo.web;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tests.sw.demo.domain.Planet;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @PostMapping
    public ResponseEntity<Planet> create(@RequestBody @Valid Planet planet) {
        Planet planentCreated = planetService.create(planet);
        return ResponseEntity.status(HttpStatus.CREATED).body(planentCreated);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Planet> get(@PathParam("id") Long id){
    	return planetService.get(id).map(planet -> ResponseEntity.ok(planet))
    			.orElseGet(() -> ResponseEntity.notFound().build());
    
    }
    
    @GetMapping("/name/{planetName}")
    public ResponseEntity<Planet> getByName(@PathParam("planetName") String planetName){
    	return planetService.getByName(planetName).map(planet -> ResponseEntity.ok(planet)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<Planet>> list(@RequestParam(required=false) String terrain,  @RequestParam(required=false) String climate){
    	List<Planet> planets = planetService.list(terrain, climate);
    	return ResponseEntity.ok(planets);
    }

}
