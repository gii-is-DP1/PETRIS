package org.springframework.samples.petclinic.user;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface AuthoritiesRepository extends  CrudRepository<Authorities, String>{

    @Query(value = "SELECT * FROM authorities WHERE username = ?1", nativeQuery = true)
	public Authorities findByName(String username);
	
}
