package com.giriraj.service;

import java.util.List;

import com.giriraj.dto.OwnerDTO;
import com.giriraj.dto.OwnerPetTypeContactDTO;
import com.giriraj.exception.OwnerNotFoundException;

public interface OwnerService {

	Integer saveOwner(OwnerDTO ownerDTO);

	void updateOwner(int ownerId, OwnerDTO ownerDTO) throws OwnerNotFoundException;

	OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException;

	void deleteOwner(int ownerId) throws OwnerNotFoundException;

	public OwnerDTO getOwnerByEmail(String email) throws OwnerNotFoundException;

	public long getOwnerCount();

	public OwnerPetTypeContactDTO getOwnerPetTypeContact(int id) throws OwnerNotFoundException;

	void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException;

	List<OwnerDTO> findAllOwners();
}
