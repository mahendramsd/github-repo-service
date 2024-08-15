package com.msd.restservice.service;

import com.msd.restservice.domain.OwnerDetails;
import com.msd.restservice.dto.GithubOwnerDto;
import com.msd.restservice.repository.OwnerDetailsRepository;
import com.msd.restservice.service.OwnerDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

/**
 * @author mahendrasridayarathna
 * @created 15/08/2024 - 8:44 am
 * @project IntelliJ IDEA
 */

@Service
public class OwnerDetailsServiceImpl implements OwnerDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(OwnerDetailsServiceImpl.class);


    private final OwnerDetailsRepository ownerDetailsRepository;
    private final WebClient.Builder webClientBuilder;

    public OwnerDetailsServiceImpl(OwnerDetailsRepository ownerDetailsRepository, WebClient.Builder webClientBuilder) {
        this.ownerDetailsRepository = ownerDetailsRepository;
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * Get GitHub owner details
     * @param owner
     * @param repoName
     * @return
     */
    public GithubOwnerDto getGithubOwnerDetails(String owner, String repoName) {
        logger.info("Fetching GitHub owner details for {}/{}", owner, repoName);
        String url = "https://api.github.com/repos/" + owner + "/" + repoName;
        Optional<OwnerDetails> ownerDetailsOptional = ownerDetailsRepository.findByFullName(owner + "/" + repoName);
        if(ownerDetailsOptional.isPresent()) {
            logger.debug("Found details in cache for {}/{}", owner, repoName);
            return new GithubOwnerDto(ownerDetailsOptional.get());
        } else {
            logger.debug("Details not found in cache, fetching from GitHub API for {}/{}", owner, repoName);
            GithubOwnerDto details = webClientBuilder.build()
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(GithubOwnerDto.class)
                    .block();
            ownerDetailsRepository.save(buildOwnerDetails(details));
            logger.info("Fetched and saved details for {}/{}", owner, repoName);
            return details;
        }

    }

    /**
     * Build owner details from the response
     * @param details
     * @return
     */
    private OwnerDetails buildOwnerDetails(GithubOwnerDto details) {
        logger.debug("Building OwnerDetails from GithubOwnerDto");
        OwnerDetails ownerDetails = new OwnerDetails();
        ownerDetails.setId(details.getId());
        ownerDetails.setFullName(details.getFull_name());
        ownerDetails.setDescription(details.getDescription());
        ownerDetails.setStars(details.getStars());
        ownerDetails.setCreatedAt(details.getCreated_at());
        return ownerDetails;
    }

}
