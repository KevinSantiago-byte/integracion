package com.safco.peru.integracion.friomamut.adapter.in.web.contoller;

import com.safco.peru.integracion.friomamut.application.port.in.MeasurementUseCase;
import com.safco.peru.integracion.friomamut.application.port.out.SyncFriomamutUseCase;
import com.safco.peru.integracion.friomamut.domain.model.Measurement;
import com.safco.peru.integracion.friomamut.adapter.in.rest.dto.MeasurementRequestDto;
import com.safco.peru.integracion.friomamut.adapter.in.rest.dto.MeasurementResponseDto;
import com.safco.peru.integracion.friomamut.adapter.out.external.dto.request.FriomamutRequestDto;
import com.safco.peru.integracion.friomamut.domain.model.Pallet;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friomamut")
public class FriomamutController {
    private final MeasurementUseCase measurementUseCase;
    private final SyncFriomamutUseCase syncFriomamutUseCase;

    @GetMapping("measurements")
    public ResponseEntity<Flux<MeasurementResponseDto>>  findAll() {
        return ResponseEntity.ok(measurementUseCase.findAll()
                .map(MeasurementResponseDto::fromDomain)
        );
    }

    @GetMapping("measurements/{id}")
    public ResponseEntity<Mono<MeasurementResponseDto>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(measurementUseCase.findById(id)
                .map(MeasurementResponseDto::fromDomain));

    }

    @PostMapping("measurements")
    public Mono<MeasurementResponseDto> save(@Valid @RequestBody MeasurementRequestDto measurementRequestDto) {
        return measurementUseCase.save(measurementRequestDto.toDomain())
                .map(MeasurementResponseDto::fromDomain);
    }

    @PutMapping("measurements/{id}")
    public Mono<MeasurementResponseDto> update(@PathVariable Long id,@Valid @RequestBody MeasurementRequestDto measurementRequestDto) {
        return measurementUseCase.update(id, measurementRequestDto.toDomain())
                .map(MeasurementResponseDto::fromDomain);
    }

    /** Synchronization de datos **/

    @PostMapping("measurements/sync")
    public Flux<Measurement> syncMeasurements(@Valid @RequestBody FriomamutRequestDto friomamutRequestDto) {
        LocalDate desde = friomamutRequestDto.desde();
        LocalDate hasta = friomamutRequestDto.hasta();
        return syncFriomamutUseCase
                .syncMeasurementsByDateRange(desde, hasta);

    }

    @PostMapping("pallets/sync")
    public Flux<Pallet> syncPallets(@Valid @RequestBody FriomamutRequestDto friomamutRequestDto) {
        LocalDate desde = friomamutRequestDto.desde();
        LocalDate hasta = friomamutRequestDto.hasta();
        return syncFriomamutUseCase.syncPalletsByDateRange(desde, hasta);
    }

}
