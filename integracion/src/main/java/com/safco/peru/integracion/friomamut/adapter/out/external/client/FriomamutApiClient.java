package com.safco.peru.integracion.friomamut.adapter.out.external.client;

import com.safco.peru.integracion.friomamut.adapter.out.external.dto.request.FriomamutRequestDto;
import com.safco.peru.integracion.friomamut.adapter.out.external.dto.response.MeasurementApiResponseDto;
import com.safco.peru.integracion.friomamut.adapter.out.external.dto.response.PalletApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class FriomamutApiClient {

    @Value("${friomamut.api.token}")
    private String token;
    private final WebClient webClient;

    public FriomamutApiClient(@Qualifier("friomamutWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     *
     * @param requestDto
     * @return Flux de MeasurementApiResponseDto, cada elemento representa una medición obtenida de la API de Friomamut.
     * El metodo realiza una llamada POST a la API, maneja errores y convierte la respuesta en un flujo reactivo de mediciones.
     */
    public Flux<MeasurementApiResponseDto> fetchMeasurements(FriomamutRequestDto requestDto) {
        return webClient.post()
                .uri("/measurements")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .bodyValue(requestDto)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    log.error("Error API medidas: {}", errorBody);
                                    return Mono.error(new RuntimeException(errorBody));
                                })
                )
                .bodyToMono(MeasurementApiResponseDto[].class) // <- Mono de array
                .flatMapMany(Flux::fromArray)                  // <- Convertimos a Flux
                .doOnSubscribe(s -> log.info("Fetch iniciado para {} - {}", requestDto.desde(), requestDto.hasta()))
                .doOnComplete(() -> log.info("Fetch completado para {} - {}", requestDto.desde(), requestDto.hasta()));
    }

    /**
     *
     * @param requestDto
     * @return Flux de PalletApiResponseDto, cada elemento representa un pallet obtenido de la API de Friomamut.
     */

    public Flux<PalletApiResponseDto> fetchPallets(FriomamutRequestDto requestDto) {
        return webClient.post()
                .uri("/pallets")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .bodyValue(requestDto)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    log.error("Error API pallets: {}", errorBody);
                                    return Mono.error(new RuntimeException(errorBody));
                                })
                )
                .bodyToMono(PalletApiResponseDto[].class) // <- Mono de array
                .flatMapMany(Flux::fromArray)                  // <- Convertimos a Flux
                .doOnSubscribe(s -> log.info("Fetch pallets iniciado para {} - {}", requestDto.desde(), requestDto.hasta()))
                .doOnComplete(() -> log.info("Fetch pallets completado para {} - {}", requestDto.desde(), requestDto.hasta()));
    }


}
