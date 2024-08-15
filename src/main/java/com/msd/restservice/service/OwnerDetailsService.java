package com.msd.restservice.service;

import com.msd.restservice.domain.OwnerDetails;
import com.msd.restservice.dto.GithubOwnerDto;

/**
 * @author mahendrasridayarathna
 * @created 15/08/2024 - 8:43 am
 * @project IntelliJ IDEA
 */
public interface OwnerDetailsService {

    GithubOwnerDto getGithubOwnerDetails(String owner, String repositoryName);
}
