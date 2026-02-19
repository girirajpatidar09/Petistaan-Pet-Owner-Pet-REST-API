package com.giriraj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.giriraj.dto.OwnerDTO;
import com.giriraj.dto.OwnerPetTypeContactDTO;
import com.giriraj.entity.Owner;
import com.giriraj.exception.OwnerNotFoundException;
import com.giriraj.repository.OwnerRepository;
import com.giriraj.service.OwnerService;
import com.giriraj.util.MapperUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
	public final OwnerRepository ownerRepository;

	@Value("${owner.not.found}")
	private String ownerNotFound;

	@Override
	public Integer saveOwner(OwnerDTO ownerDTO) {
		Owner owner = MapperUtil.convertOwnerDtoToEntity(ownerDTO);
		ownerRepository.save(owner);
		return owner.getId();

	}

	@Override
	public List<OwnerDTO> findAllOwners() {
		return ownerRepository.findAll().stream().map(MapperUtil::convertOwnerEntityToDto).toList();
	}

	@Override
	public void updateOwner(int ownerId, OwnerDTO ownerDTO) throws OwnerNotFoundException {

		Owner owner = ownerRepository.findById(ownerId)
				.orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));

		MapperUtil.updateOwnerFromDTO(ownerDTO, owner);

		ownerRepository.save(owner);
	}

	@Override
	public OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException {
		return ownerRepository.findById(ownerId).map(MapperUtil::convertOwnerEntityToDto)
				.orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
	}

	@Override
	public void deleteOwner(int ownerId) throws OwnerNotFoundException {
		boolean ownerExists = ownerRepository.existsById(ownerId);
		if (!ownerExists) {
			throw new OwnerNotFoundException(String.format(ownerNotFound, ownerId));
		} else {
			ownerRepository.deleteById(ownerId);
		}
	}

	@Override
	public OwnerDTO getOwnerByEmail(String email) throws OwnerNotFoundException {

		Owner owner = ownerRepository.findByEmailId(email)
				.orElseThrow(() -> new OwnerNotFoundException("Owner not found with email: " + email));

		return MapperUtil.convertOwnerEntityToDto(owner);
	}

	public long getOwnerCount() {
		return ownerRepository.count();
	}

	@Override
	public OwnerPetTypeContactDTO getOwnerPetTypeContact(int id) throws OwnerNotFoundException {

		return ownerRepository.fetchOwnerPetTypeContactById(id)
				.orElseThrow(() -> new OwnerNotFoundException("Owner not found with id: " + id));
	}

	@Override
	public void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException {
		Owner owner = ownerRepository.findById(ownerId)
				.orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFound, ownerId)));
		owner.getPet().setName(petName);
		ownerRepository.save(owner);
	}

}