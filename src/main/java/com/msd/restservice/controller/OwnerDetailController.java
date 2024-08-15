package com.msd.restservice.controller;

import com.msd.restservice.dto.GithubOwnerDto;
import com.msd.restservice.service.OwnerDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mahendrasridayarathna
 * @created 15/08/2024 - 8:35 am
 * @project IntelliJ IDEA
 */

@RestController
public class OwnerDetailController {

    private final OwnerDetailsService ownerDetailsService;

    public OwnerDetailController(OwnerDetailsService ownerDetailsService) {
        this.ownerDetailsService = ownerDetailsService;
    }

    @Operation(summary = "Get GitHub repository owner details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the repository",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GithubOwnerDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Repository not found",
                    content = @Content) })
    @GetMapping("/repositories/{owner}/{repositoryName}")
    public ResponseEntity<GithubOwnerDto> getRepositoryDetails(@PathVariable String owner, @PathVariable String repositoryName) {
        return ResponseEntity.ok(ownerDetailsService.getGithubOwnerDetails(owner, repositoryName));
    }


}
