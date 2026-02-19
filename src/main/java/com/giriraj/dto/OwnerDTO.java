package com.giriraj.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.giriraj.enums.Gender;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(NON_NULL)
@JsonPropertyOrder({ "id", "firstName", "lastName", "gender", "emailId", "mobileNumber", "city", "state", "petDTO" })
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {

	private int id;

	@Size(max = 255, message = "{owner.first.name.length}")
	@NotBlank(message = "{owner.first.name.required}")
	private String firstName;

	@Size(max = 255, message = "{owner.last.name.length}")
	@NotBlank(message = "{owner.last.name.required}")
	private String lastName;

	@NotNull(message = "{owner.gender.required}")
	private Gender gender;

	@Size(max = 255, message = "{owner.city.length}")
	@NotBlank(message = "{owner.city.required}")
	private String city;

	@Size(max = 255, message = "{owner.state.length}")
	@NotBlank(message = "{owner.state.required}")
	private String state;

	@Size(min = 10, max = 10, message = "{owner.mobile.number.length}")
	@NotBlank(message = "{owner.mobile.number.required}")
	private String mobileNumber;

	@Email(message = "{owner.email.invalid}")
	@NotBlank(message = "{owner.email.required}")
	private String emailId;

	@Valid
	@NotNull(message = "{owner.pet.required}")
	private PetDTO petDTO;

	

	
     	@Override
	public String toString() {
		if (Objects.isNull(petDTO)) {
			return "OwnerDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
					+ ", city=" + city + ", state=" + state + ", mobileNumber=" + mobileNumber + ", emailId=" + emailId
					+ "]";
		} else {
			return "OwnerDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
					+ ", city=" + city + ", state=" + state + ", mobileNumber=" + mobileNumber + ", emailId=" + emailId
					+ ", petDTO=" + petDTO + "]";
		}
	}
}