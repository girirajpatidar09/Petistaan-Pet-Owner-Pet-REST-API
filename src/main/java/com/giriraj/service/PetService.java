package com.giriraj.service;

import java.util.List;

import com.giriraj.dto.PetDTO;
import com.giriraj.enums.PetType;
import com.giriraj.exception.PetNotFoundException;

public interface PetService {

	PetDTO getPetById(int petId) throws PetNotFoundException;

	List<PetDTO> getPetsByType(PetType type);

}
