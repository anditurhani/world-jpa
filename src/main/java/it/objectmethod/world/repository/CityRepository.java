package it.objectmethod.world.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.objectmethod.world.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

	public List<City> findByCountrycode(String countrycode);

	@Query("select c from City c where c.name like :givenstr and ('' = :givencountrycode or countrycode = :givencountrycode)")
	public List<City> searchCities(@Param("givenstr") String searchStr, @Param("givencountrycode") String countrycode);

}
