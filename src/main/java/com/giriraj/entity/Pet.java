package com.giriraj.entity;

import java.time.LocalDate;

import com.giriraj.enums.Gender;
import com.giriraj.enums.PetType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

@Entity
@Table(name = "pet_table")
public class Pet {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(name = "date_of_birth", nullable = false)
	private LocalDate birthDate;
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private PetType type;
	@OneToOne(mappedBy = "pet")
	private Owner owner;

	

	
	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", gender=" + gender + ", type=" + type
				+ ", owner=" + owner + "]";
	}
}