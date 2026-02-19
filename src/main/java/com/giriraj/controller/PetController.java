package com.giriraj.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.giriraj.dto.ErrorDTO;
import com.giriraj.dto.PetDTO;
import com.giriraj.enums.PetType;
import com.giriraj.exception.PetNotFoundException;
import com.giriraj.service.PetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/pets")
@Tag(name = "Pet", description = "APIs for managing pets")
public class PetController {

	private final PetService petService;

	@Operation(summary = "Get pet by pet by pet-ID", description = "Retrieve pet details using their unique id.")
	@ApiResponse(responseCode = "200", description = "pet  Details Retrieved Successfully")
	@ApiResponse(responseCode = "400", description = "Constraint Violation Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "404", description = "Pet Not Found", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@GetMapping("/{petId}")
	public ResponseEntity<PetDTO> getPetById(@PathVariable @Min(value = 1, message = "{pet.id.positive}") int petId)
			throws PetNotFoundException {

		return ResponseEntity.ok(petService.getPetById(petId));
	}

	@Operation(summary = "Get pet by  type", description = "Retrieve pet details using their type.")
	@ApiResponse(responseCode = "200", description = "pet type Details Retrieved Successfully")
	@ApiResponse(responseCode = "400", description = "Constraint Violation Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))

	@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@GetMapping("/by-type")
	public ResponseEntity<List<PetDTO>> getPetsByType(@RequestParam PetType type) {

		return ResponseEntity.ok(petService.getPetsByType(type));
	}

}
