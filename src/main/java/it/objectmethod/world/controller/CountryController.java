package it.objectmethod.world.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.world.entity.Country;
import it.objectmethod.world.repository.CountryRepository;

@RestController
public class CountryController {

	@Autowired
	private CountryRepository countryRepo;

	@GetMapping("continents")
	public List<String> getAllContinents() {
		List<String> continentList = countryRepo.findContinents();
		return continentList;
	}

	@GetMapping("/countries")
	public List<Country> getCountryByContinent(@RequestParam(name = "continent", required = false) String continent,
			HttpSession session) {
		if (continent != null) {
			session.setAttribute("continent", continent);
		} else {
			continent = (String) session.getAttribute("continent");
		}
		List<Country> countryList = countryRepo.findByContinent(continent);
		return countryList;
	}

}
