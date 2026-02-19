package com.giriraj.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

public class UpdatePetDTO {

	@Size(max = 255, message = "{pet.name.length}")
	@NotBlank(message = "{pet.name.required}")
	private String name;

	

	@Override
	public String toString() {
		return "UpdatePetDTO [name=" + name + "]";
	}

}
