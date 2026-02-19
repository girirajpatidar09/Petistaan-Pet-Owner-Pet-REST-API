package com.giriraj.util;

import java.util.Objects;

import com.giriraj.dto.OwnerDTO;
import com.giriraj.dto.PetDTO;
import com.giriraj.entity.Owner;
import com.giriraj.entity.Pet;

public final class MapperUtil {

	private MapperUtil() {
		// utility class
	}

	/*
	 * =========================================================
	 * ENTITY → DTO
	 * =========================================================
	 */

	public static OwnerDTO convertOwnerEntityToDto(Owner owner) {
		if (owner == null) return null;

		return OwnerDTO.builder()
				.id(owner.getId())
				.firstName(owner.getFirstName())
				.lastName(owner.getLastName())
				.gender(owner.getGender())
				.city(owner.getCity())
				.state(owner.getState())
				.mobileNumber(owner.getMobileNumber())
				.emailId(owner.getEmailId())
				.petDTO(convertPetEntityToDtoWithoutOwner(owner.getPet()))
				.build();
	}

	public static PetDTO convertPetEntityToDto(Pet pet) {
		if (pet == null) return null;

		return PetDTO.builder()
				.id(pet.getId())
				.name(pet.getName())
				.birthDate(pet.getBirthDate())
				.gender(pet.getGender())
				.type(pet.getType())
				.ownerDTO(convertOwnerEntityToDtoWithoutPet(pet.getOwner()))
				.build();
	}

	private static PetDTO convertPetEntityToDtoWithoutOwner(Pet pet) {
		if (pet == null) return null;

		return PetDTO.builder()
				.id(pet.getId())
				.name(pet.getName())
				.birthDate(pet.getBirthDate())
				.gender(pet.getGender())
				.type(pet.getType())
				.build();
	}

	private static OwnerDTO convertOwnerEntityToDtoWithoutPet(Owner owner) {
		if (owner == null) return null;

		return OwnerDTO.builder()
				.id(owner.getId())
				.firstName(owner.getFirstName())
				.lastName(owner.getLastName())
				.gender(owner.getGender())
				.city(owner.getCity())
				.state(owner.getState())
				.mobileNumber(owner.getMobileNumber())
				.emailId(owner.getEmailId())
				.build();
	}

	/*
	 * =========================================================
	 * DTO → ENTITY (CREATE)
	 * =========================================================
	 */

	public static Owner convertOwnerDtoToEntity(OwnerDTO ownerDTO) {
		if (ownerDTO == null) return null;

		Owner owner = Owner.builder()
				.firstName(ownerDTO.getFirstName())
				.lastName(ownerDTO.getLastName())
				.gender(ownerDTO.getGender())
				.city(ownerDTO.getCity())
				.state(ownerDTO.getState())
				.mobileNumber(ownerDTO.getMobileNumber())
				.emailId(ownerDTO.getEmailId())
				.build();

		if (ownerDTO.getPetDTO() != null) {
			Pet pet = convertPetDtoToEntity(ownerDTO.getPetDTO());
			owner.setPet(pet);
			pet.setOwner(owner); // 🔥 maintain bidirectional mapping
		}

		return owner;
	}

	public static Pet convertPetDtoToEntity(PetDTO petDTO) {
		if (petDTO == null) return null;

		return Pet.builder()
				.name(petDTO.getName())
				.birthDate(petDTO.getBirthDate())
				.gender(petDTO.getGender())
				.type(petDTO.getType())
				.build();
	}

	/*
	 * =========================================================
	 * DTO → ENTITY (UPDATE / PARTIAL UPDATE)
	 * =========================================================
	 */

	public static void updateOwnerFromDTO(OwnerDTO ownerDTO, Owner owner) {
		if (ownerDTO == null || owner == null) return;

		if (ownerDTO.getFirstName() != null)
			owner.setFirstName(ownerDTO.getFirstName());

		if (ownerDTO.getLastName() != null)
			owner.setLastName(ownerDTO.getLastName());

		if (ownerDTO.getGender() != null)
			owner.setGender(ownerDTO.getGender());

		if (ownerDTO.getCity() != null)
			owner.setCity(ownerDTO.getCity());

		if (ownerDTO.getState() != null)
			owner.setState(ownerDTO.getState());

		if (ownerDTO.getMobileNumber() != null)
			owner.setMobileNumber(ownerDTO.getMobileNumber());

		if (ownerDTO.getEmailId() != null)
			owner.setEmailId(ownerDTO.getEmailId());

		if (Objects.nonNull(ownerDTO.getPetDTO()) && Objects.nonNull(owner.getPet())) {
			updatePetFromDTO(ownerDTO.getPetDTO(), owner.getPet());
		}
	}

	private static void updatePetFromDTO(PetDTO petDTO, Pet pet) {
		if (petDTO == null || pet == null) return;

		if (petDTO.getName() != null)
			pet.setName(petDTO.getName());

		if (petDTO.getBirthDate() != null)
			pet.setBirthDate(petDTO.getBirthDate());

		if (petDTO.getGender() != null)
			pet.setGender(petDTO.getGender());

		if (petDTO.getType() != null)
			pet.setType(petDTO.getType());
	}
}
