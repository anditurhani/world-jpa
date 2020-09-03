package it.objectmethod.world.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.objectmethod.world.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

	@Query("select distinct c.continent from Country c")
	public List<String> findContinents();

	public List<Country> findByContinent(String continent);

}
