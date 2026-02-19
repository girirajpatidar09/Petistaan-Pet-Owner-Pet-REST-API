package com.giriraj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giriraj.entity.Pet;
import com.giriraj.enums.PetType;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

	List<Pet> findByType(PetType type);
	


}
