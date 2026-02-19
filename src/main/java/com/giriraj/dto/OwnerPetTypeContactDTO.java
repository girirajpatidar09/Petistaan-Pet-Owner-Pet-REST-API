package com.giriraj.dto;

import com.giriraj.enums.PetType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OwnerPetTypeContactDTO {

	private String ownerName;
	private PetType petType; // 🔥 enum
	private String mobileNumber;

	
	
}
