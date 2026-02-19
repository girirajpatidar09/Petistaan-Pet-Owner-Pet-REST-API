
package com.giriraj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.giriraj.dto.OwnerPetTypeContactDTO;
import com.giriraj.entity.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

	Optional<Owner> findByEmailId(String emailId);

	@Query("SELECT COUNT(o) FROM Owner o")
	long countOwners();

	@Query("""
			    SELECT new com.giriraj.dto.OwnerPetTypeContactDTO(
			        CONCAT(o.firstName, ' ', o.lastName),
			        p.type,
			        o.mobileNumber
			    )
			    FROM Owner o
			    JOIN o.pet p
			    WHERE o.id = :id
			""")
	Optional<OwnerPetTypeContactDTO> fetchOwnerPetTypeContactById(@Param("id") int id);

}
