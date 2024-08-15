package com.msd.restservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.msd.restservice.domain.OwnerDetails;
import com.msd.restservice.dto.GithubOwnerDto;
import com.msd.restservice.repository.OwnerDetailsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@ContextConfiguration(classes = {OwnerDetailsServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class OwnerDetailsServiceTest {
    @MockBean
    private WebClient.Builder builder;

    @MockBean
    private OwnerDetailsRepository ownerDetailsRepository;

    @Autowired
    private OwnerDetailsServiceImpl ownerDetailsServiceImpl;


    @Test
    void testGetGithubOwnerDetails() {

        OwnerDetails ownerDetails = new OwnerDetails();
        ownerDetails.setCloneUrl("https://github.com/mahendramsd/mahendramsd.git");
        LocalDate ofResult = LocalDate.of(2024, 1, 1);
        ownerDetails.setCreatedAt(ofResult.atStartOfDay());
        ownerDetails.setDescription("Config files for my GitHub profile.");
        ownerDetails.setFullName("mahendramsd");
        ownerDetails.setId(1L);
        ownerDetails.setStars(1);
        Optional<OwnerDetails> ofResult2 = Optional.of(ownerDetails);
        OwnerDetailsRepository ownerDetailsRepository = mock(OwnerDetailsRepository.class);
        when(ownerDetailsRepository.findByFullName(Mockito.<String>any())).thenReturn(ofResult2);

        GithubOwnerDto actualGithubOwnerDetails = (new OwnerDetailsServiceImpl(ownerDetailsRepository,
                mock(WebClient.Builder.class))).getGithubOwnerDetails("Owner", "Repo Name");

        verify(ownerDetailsRepository).findByFullName(eq("Owner/Repo Name"));
        LocalDateTime created_at = actualGithubOwnerDetails.getCreated_at();
        assertEquals("00:00", created_at.toLocalTime().toString());
        LocalDate toLocalDateResult = created_at.toLocalDate();
        assertEquals("2024-01-01", toLocalDateResult.toString());
        assertEquals("mahendramsd", actualGithubOwnerDetails.getFull_name());
        assertEquals("Config files for my GitHub profile.", actualGithubOwnerDetails.getDescription());
        assertEquals("https://github.com/mahendramsd/mahendramsd.git", actualGithubOwnerDetails.getClone_url());
        assertEquals(1, actualGithubOwnerDetails.getStars());
        assertEquals(1L, actualGithubOwnerDetails.getId().longValue());
        assertSame(ofResult, toLocalDateResult);
    }

    @Test
    void testGetGithubOwnerDetails2() {

        OwnerDetails ownerDetails = new OwnerDetails();
        ownerDetails.setCloneUrl("https://github.com/mahendramsd/mahendramsd.git");
        ownerDetails.setCreatedAt(LocalDate.of(2024, 1, 1).atStartOfDay());
        ownerDetails.setDescription("Config files for my GitHub profile.");
        ownerDetails.setFullName("mahendramsd");
        ownerDetails.setId(1L);
        ownerDetails.setStars(1);
        OwnerDetailsRepository ownerDetailsRepository = mock(OwnerDetailsRepository.class);
        when(ownerDetailsRepository.save(Mockito.<OwnerDetails>any())).thenReturn(ownerDetails);
        Optional<OwnerDetails> emptyResult = Optional.empty();
        when(ownerDetailsRepository.findByFullName(Mockito.<String>any())).thenReturn(emptyResult);
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        GithubOwnerDto githubOwnerDto = new GithubOwnerDto();
        Mono<GithubOwnerDto> justResult = Mono.just(githubOwnerDto);
        when(responseSpec.bodyToMono(Mockito.<Class<GithubOwnerDto>>any())).thenReturn(justResult);
        WebClient.RequestBodySpec requestBodySpec = mock(WebClient.RequestBodySpec.class);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        WebClient.RequestHeadersUriSpec<WebClient.RequestBodySpec> requestHeadersUriSpec = mock(
                WebClient.RequestHeadersUriSpec.class);
        when(requestHeadersUriSpec.uri(Mockito.<String>any(), isA(Object[].class))).thenReturn(requestBodySpec);
        WebClient webClient = mock(WebClient.class);
        Mockito.<WebClient.RequestHeadersUriSpec<?>>when(webClient.get()).thenReturn(requestHeadersUriSpec);
        WebClient.Builder webClientBuilder = mock(WebClient.Builder.class);
        when(webClientBuilder.build()).thenReturn(webClient);


        GithubOwnerDto actualGithubOwnerDetails = (new OwnerDetailsServiceImpl(ownerDetailsRepository, webClientBuilder))
                .getGithubOwnerDetails("Owner", "Repo Name");

        verify(ownerDetailsRepository).findByFullName(eq("Owner/Repo Name"));
        verify(ownerDetailsRepository).save(isA(OwnerDetails.class));
        verify(webClient).get();
        verify(webClientBuilder).build();
        verify(requestBodySpec).retrieve();
        verify(responseSpec).bodyToMono(isA(Class.class));
        verify(requestHeadersUriSpec).uri(eq("https://api.github.com/repos/Owner/Repo Name"), isA(Object[].class));
        assertSame(githubOwnerDto, actualGithubOwnerDetails);
    }

    @Test
    void testGetGithubOwnerDetails3() {

        OwnerDetails ownerDetails = new OwnerDetails();
        ownerDetails.setCloneUrl("https://github.com/mahendramsd/mahendramsd.git");
        ownerDetails.setCreatedAt(LocalDate.of(2024, 1, 1).atStartOfDay());
        ownerDetails.setDescription("Config files for my GitHub profile.");
        ownerDetails.setFullName("mahendramsd");
        ownerDetails.setId(1L);
        ownerDetails.setStars(1);
        OwnerDetailsRepository ownerDetailsRepository = mock(OwnerDetailsRepository.class);
        when(ownerDetailsRepository.save(Mockito.<OwnerDetails>any())).thenReturn(ownerDetails);
        Optional<OwnerDetails> emptyResult = Optional.empty();
        when(ownerDetailsRepository.findByFullName(Mockito.<String>any())).thenReturn(emptyResult);
        GithubOwnerDto githubOwnerDto = mock(GithubOwnerDto.class);
        when(githubOwnerDto.getStars()).thenReturn(1);
        when(githubOwnerDto.getId()).thenReturn(1L);
        when(githubOwnerDto.getDescription()).thenReturn("Config files for my GitHub profile.");
        when(githubOwnerDto.getFull_name()).thenReturn("mahendramsd");
        when(githubOwnerDto.getCreated_at()).thenReturn(LocalDate.of(2024, 1, 1).atStartOfDay());
        Mono<GithubOwnerDto> justResult = Mono.just(githubOwnerDto);
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        when(responseSpec.bodyToMono(Mockito.<Class<GithubOwnerDto>>any())).thenReturn(justResult);
        WebClient.RequestBodySpec requestBodySpec = mock(WebClient.RequestBodySpec.class);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        WebClient.RequestHeadersUriSpec<WebClient.RequestBodySpec> requestHeadersUriSpec = mock(
                WebClient.RequestHeadersUriSpec.class);
        when(requestHeadersUriSpec.uri(Mockito.<String>any(), isA(Object[].class))).thenReturn(requestBodySpec);
        WebClient webClient = mock(WebClient.class);
        Mockito.<WebClient.RequestHeadersUriSpec<?>>when(webClient.get()).thenReturn(requestHeadersUriSpec);
        WebClient.Builder webClientBuilder = mock(WebClient.Builder.class);
        when(webClientBuilder.build()).thenReturn(webClient);

        (new OwnerDetailsServiceImpl(ownerDetailsRepository, webClientBuilder)).getGithubOwnerDetails("Owner", "Repo Name");

        verify(githubOwnerDto).getCreated_at();
        verify(githubOwnerDto).getDescription();
        verify(githubOwnerDto).getFull_name();
        verify(githubOwnerDto).getId();
        verify(githubOwnerDto).getStars();
        verify(ownerDetailsRepository).findByFullName(eq("Owner/Repo Name"));
        verify(ownerDetailsRepository).save(isA(OwnerDetails.class));
        verify(webClient).get();
        verify(webClientBuilder).build();
        verify(requestBodySpec).retrieve();
        verify(responseSpec).bodyToMono(isA(Class.class));
        verify(requestHeadersUriSpec).uri(eq("https://api.github.com/repos/Owner/Repo Name"), isA(Object[].class));
    }
}
