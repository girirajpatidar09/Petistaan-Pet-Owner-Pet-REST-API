package com.giriraj.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.giriraj.enums.Gender;
import com.giriraj.enums.PetType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "id", "name", "type", "gender", "birthDate" })
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {
	private int id;
	@Size(max = 255, message = "{pet.name.length}")
	@NotBlank(message = "{pet.name.required}")
	private String name;

	@PastOrPresent(message = "{pet.birth.date.old}")
	@NotNull(message = "{pet.birth.date.required}")
	private LocalDate birthDate;

	@NotNull(message = "{pet.gender.required}")
	private Gender gender;

	@NotNull(message = "{pet.type.required}")
	private PetType type;

	private OwnerDTO ownerDTO;

	

	@Override
	public String toString() {
		if (Objects.isNull(ownerDTO)) {
			return "PetDTO [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", gender=" + gender + ", type="
					+ type + "]";
		} else {
			return "PetDTO [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", gender=" + gender + ", type="
					+ type + ", ownerDTO=" + ownerDTO + "]";
		}
	}
}