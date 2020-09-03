package it.objectmethod.world.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.world.entity.City;
import it.objectmethod.world.repository.CityRepository;

@RestController
public class CityController {

	@Autowired
	private CityRepository cityRepo;

	@GetMapping("/cities")
	public List<City> getCitiesByCountrycode(@RequestParam(name = "countrycode") String countrycode) {
		List<City> cityList = cityRepo.findByCountrycode(countrycode);
		return cityList;
	}

	@GetMapping("/search")
	public List<City> searchCities(@RequestParam(name = "searchStr") String searchStr,
			@RequestParam(name = "countrycode") String countrycode) {
		searchStr = "%" + searchStr + "%";
		List<City> cityList = cityRepo.searchCities(searchStr, countrycode);
		return cityList;
	}

	@PostMapping("/update-city")
	public ResponseEntity<City> updateCity(@RequestBody City city) {
		ResponseEntity<City> resp = null;
		if (city.getPopulation() < 0) {
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			cityRepo.save(city);
			resp = new ResponseEntity<>(city, HttpStatus.OK);
		}
		return resp;
	}
	
	@PutMapping("/delete-city")
	public void deleteCity(@RequestParam("id") Integer id) {
		cityRepo.deleteById(id);
	}

}
