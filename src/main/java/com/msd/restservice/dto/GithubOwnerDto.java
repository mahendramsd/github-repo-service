package com.msd.restservice.dto;

import com.msd.restservice.domain.OwnerDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author mahendrasridayarathna
 * @created 15/08/2024 - 9:13 am
 * @project IntelliJ IDEA
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubOwnerDto  {


    private Long id;
    private String full_name;
    private String description;
    private String clone_url;
    private int stars;
    private LocalDateTime created_at;

    public GithubOwnerDto(OwnerDetails ownerDetails) {
        this.id = ownerDetails.getId();
        this.full_name = ownerDetails.getFullName();
        this.description = ownerDetails.getDescription();
        this.clone_url = ownerDetails.getCloneUrl();
        this.stars = ownerDetails.getStars();
        this.created_at = ownerDetails.getCreatedAt();
    }
}
