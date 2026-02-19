package com.giriraj.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.giriraj.dto.ErrorDTO;
import com.giriraj.dto.OwnerDTO;
import com.giriraj.dto.OwnerPetTypeContactDTO;
import com.giriraj.dto.UpdatePetDTO;
import com.giriraj.exception.OwnerNotFoundException;
import com.giriraj.service.OwnerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
@Tag(name = "Owner", description = "APIs for managing owners and their pets")

public class OwnerController {

	public final OwnerService ownerService;

	@Operation(summary = "Create owner", description = "Save a new owner and their pet to the database.")
	@ApiResponse(responseCode = "201", description = "Owner Successfully Created")
	@ApiResponse(responseCode = "400", description = "Constraint Violation Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@PostMapping
	public ResponseEntity<Integer> saveOwner(@Valid @RequestBody OwnerDTO ownerDTO) {
		Integer ownerId = ownerService.saveOwner(ownerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(ownerId);
	}

	@Operation(summary = "Update owner", description = "Update  owner and their pet to the database.")
	@ApiResponse(responseCode = "200", description = "Owner Successfully Updated")
	@ApiResponse(responseCode = "400", description = "Constraint Violation Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateOwner(@PathVariable @Min(value = 1, message = "{owner.id.positive}") int id,
			@RequestBody OwnerDTO ownerDTO) throws OwnerNotFoundException {

		ownerService.updateOwner(id, ownerDTO);
		return ResponseEntity.ok().build();

	}

	@Operation(summary = "Delete  owner", description = "Delete a owner and their pet to the database.")
	@ApiResponse(responseCode = "200", description = "Owner Successfully Deleted")
	@ApiResponse(responseCode = "400", description = "Constraint Violation Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))

	@DeleteMapping("/{ownerId}")
	public ResponseEntity<Void> deleteOwner(@PathVariable @Min(value = 1, message = "{owner.id.positive}") int ownerId)
			throws OwnerNotFoundException {

		ownerService.deleteOwner(ownerId);
		return ResponseEntity.status(HttpStatus.OK).build();

	}

	@Operation(summary = "Get owner by owner ID", description = "Retrieve owner details using their unique id.")
	@ApiResponse(responseCode = "200", description = "Owner Details Retrieved Successfully")
	@ApiResponse(responseCode = "400", description = "Constraint Violation Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "404", description = "Owner Not Found", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@GetMapping("/{ownerId}")
	public ResponseEntity<OwnerDTO> findOwner(
			@PathVariable @Min(value = 1, message = "{owner.id.positive}") int ownerId) throws OwnerNotFoundException {

		OwnerDTO ownerDTO = ownerService.findOwner(ownerId);
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTO);

	}

	@Operation(summary = "Get all owner ", description = "Retrieve  all owner details with their pet.")
	@ApiResponse(responseCode = "200", description = " All Owner Details Retrieved Successfully")
	@ApiResponse(responseCode = "400", description = "Constraint Violation Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "404", description = "Owner Not Found", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@GetMapping("/all")
	public ResponseEntity<List<OwnerDTO>> findAllOwners() {
		List<OwnerDTO> ownerDTOList = ownerService.findAllOwners();
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTOList);
	}

	@Operation(summary = "Get Owner by mail-id ", description = "Retrieve   owner details with their pet by mail-id")
	@ApiResponse(responseCode = "200", description = "  Owner Details Retrieved Successfully")
	@ApiResponse(responseCode = "400", description = "Constraint Violation Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "404", description = "Owner Not Found", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@GetMapping("/by-email")
	public ResponseEntity<OwnerDTO> getOwnerByEmail(
			@RequestParam @NotBlank(message = "{email.not.blank}") @Email(message = "{invalid.email}") String email)
			throws OwnerNotFoundException {

		OwnerDTO ownerDTO = ownerService.getOwnerByEmail(email);
		return ResponseEntity.ok(ownerDTO);

	}

	@Operation(summary = "Count total Owner ", description = "Count all   owner details with their pet ")
	@ApiResponse(responseCode = "200", description = "  Owner Details Successfully Counted")
	@ApiResponse(responseCode = "400", description = "Constraint Violation Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))

	@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@GetMapping("/count")
	public ResponseEntity<Long> getOwnerCount() {
		return ResponseEntity.ok(ownerService.getOwnerCount());
	}

	@Operation(summary = "Get Owner with mobile number and pet-type detail", description = "Retrieve   owner name ,owner mobile number and pet-type")
	@ApiResponse(responseCode = "200", description = "  Owner Details Retrieved Successfully")
	@ApiResponse(responseCode = "400", description = "Constraint Violation Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "404", description = "Owner Not Found", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@GetMapping("/{id}/contact")
	public ResponseEntity<OwnerPetTypeContactDTO> getOwnerContact(
			@PathVariable @Min(value = 1, message = "{owner.id.positive}") int id) throws OwnerNotFoundException {

		OwnerPetTypeContactDTO dto = ownerService.getOwnerPetTypeContact(id);

		return ResponseEntity.ok(dto);

	}

	@Operation(summary = "Update pet deatil  ", description = "Update  pet detail with owner-id.")
	@ApiResponse(responseCode = "200", description = "Owner Successfully Updated")
	@ApiResponse(responseCode = "400", description = "Constraint Violation Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@PatchMapping("/{ownerId}")
	public ResponseEntity<Void> updatePetDetails(@PathVariable int ownerId,
			@Valid @RequestBody UpdatePetDTO updatePetDTO) throws OwnerNotFoundException {

		ownerService.updatePetDetails(ownerId, updatePetDTO.getName());
		return ResponseEntity.status(HttpStatus.OK).build();

	}

}
