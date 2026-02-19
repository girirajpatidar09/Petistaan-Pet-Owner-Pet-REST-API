package com.giriraj.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.giriraj.dto.PetDTO;
import com.giriraj.entity.Pet;
import com.giriraj.enums.PetType;
import com.giriraj.exception.PetNotFoundException;
import com.giriraj.repository.PetRepository;
import com.giriraj.service.PetService;
import com.giriraj.util.MapperUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

	private final PetRepository petRepository;

	@Override
	public PetDTO getPetById(int petId) throws PetNotFoundException {
		Pet pet = petRepository.findById(petId)
				.orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + petId));
		return MapperUtil.convertPetEntityToDto(pet);
	}

	@Override
	public List<PetDTO> getPetsByType(PetType type) {
		return petRepository.findByType(type).stream().map(MapperUtil::convertPetEntityToDto).toList();
	}

}
