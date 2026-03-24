package com.safco.peru.integracion.friomamut.application.service;

import com.safco.peru.integracion.friomamut.application.port.out.SyncFriomamutUseCase;
import com.safco.peru.integracion.friomamut.domain.model.Measurement;
import com.safco.peru.integracion.friomamut.domain.model.Pallet;
import com.safco.peru.integracion.friomamut.domain.port.in.ExternalFriomamutApiPort;
import com.safco.peru.integracion.friomamut.domain.port.out.MeasurementRepositoryPort;
import com.safco.peru.integracion.friomamut.domain.port.out.PalletRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyncFriomamutService implements SyncFriomamutUseCase {

    private final ExternalFriomamutApiPort externalFriomamutApiPort; //falta implementar metodo de data
    private final MeasurementRepositoryPort measurementRepositoryPort;
    private final PalletRepositoryPort palletRepositoryPort;


    /****************************************************************************************
     * MÉTODOS PARA SYNC  DE MEDIDAS DE FRIOMAMUT
     **************************************************************************************/
    /**
     *
     * @param startDate
     * @param endDate
     * @return Flux de Measurement sincronizados para el rango de fechas dado.
     * El proceso se realiza día a día, eliminando los registros existentes
     * para cada día antes de guardar los nuevos datos.
     */
    @Override
    public Flux<Measurement> syncMeasurementsByDateRange(LocalDate startDate, LocalDate endDate) {

        return Flux.fromIterable(buildDayRange(startDate, endDate))
                .flatMap(this::syncSingleDay)
                .doOnComplete(() -> log.info("Sincronización completa para rango ----: {} - {}", startDate, endDate))
                .doOnError(error -> log.error("Error para rango ----: {} - {}", startDate, endDate, error));
    }

    /**
     *
     * @param day
     * @return Flux de Measurement sincronizados para un día específico. El proceso incluye:
     * 1. Consulta a la API externa para obtener las medidas del día.
     * 2. Enriquecimiento de cada medida con la fecha de proceso (día actual).
     * 3. Buffering de las medidas en chunks de 200 para optimizar el guardado.
     * 4. Eliminación de los registros existentes para ese día en la base de datos.
     * 5. Guardado de los nuevos registros en la base de datos, con manejo de errores a nivel de chunk para asegurar que un error en un chunk no detenga el proceso completo del día.
     */

    private Flux<Measurement> syncSingleDay(LocalDate day) {

        Flux<Measurement> source = externalFriomamutApiPort.fetchMeasurementsByDateRange(day, day)
                .map(m -> enrichMeasurementsWithFechaConsulta(m, day));

        Flux<List<Measurement>> buffered = source
                .buffer(200)
                .doOnNext(chunk -> log.info("Chunk día {}: {} registros", day, chunk.size()));

        Flux<Measurement> saved = buffered
                .flatMap(this::saveChunkMeasurements, 3);

        return measurementRepositoryPort.deleteByFechaProceso(day)
                .thenMany(saved)
                .doOnNext(m -> log.debug("Guardado: sensor={}", m.sensor()))
                .doOnComplete(() -> log.info("Día {} completado", day))
                .onErrorContinue((e, obj) ->
                        log.error("Error chunk día {}: {}", day, e.getMessage()));

    }

    private Flux<Measurement> saveChunkMeasurements(List<Measurement> chunk) {
        return measurementRepositoryPort.saveAll(Flux.fromIterable(chunk));
    }

    private Measurement enrichMeasurementsWithFechaConsulta(Measurement measurement, LocalDate fechaProceso) {
        return Measurement.syncFechaProceso(
                measurement,
                fechaProceso
        );
    }

    /****************************************************************************************
                            * MÉTODOS PARA SYNC  DE PALLETS DE FRIOMAMUT
     ***************************************************************************************/

    @Override
    public Flux<Pallet> syncPalletsByDateRange(LocalDate startDate, LocalDate endDate) {
        return Flux.fromIterable(buildDayRange(startDate, endDate))
                .flatMap(this::syncPalletsSingleDay)
                .doOnComplete(() -> log.info("Sincronización completa para rango ----: {} - {}", startDate, endDate))
                .doOnError(error -> log.error("Error para rango ----: {} - {}", startDate, endDate, error));
    }

    private Flux<Pallet> syncPalletsSingleDay(LocalDate day) {

        Flux<Pallet> source = externalFriomamutApiPort.fetchPalletsByDateRange(day, day)
                .map(m -> enrichPalletsWithFechaConsulta(m, day));

        Flux<List<Pallet>> buffered = source
                .buffer(200)
                .doOnNext(chunk -> log.info("Chunk día {}: {} registros", day, chunk.size()));

        Flux<Pallet> saved = buffered
                .flatMap(this::saveChunkPallets, 3);

        return palletRepositoryPort.deleteByFechaProceso(day)
                .thenMany(saved)
                .doOnNext(m -> log.debug("Guardado: sensor={}", m.paleta()))
                .doOnComplete(() -> log.info("Día {} completado", day))
                .onErrorContinue((e, obj) ->
                        log.error("Error chunk día {}: {}", day, e.getMessage()));
    }


    private Pallet enrichPalletsWithFechaConsulta(Pallet pallet, LocalDate fechaProceso) {
        return Pallet.syncFechaProceso(
                pallet,
                fechaProceso
        );
    }

    private Flux<Pallet> saveChunkPallets(List<Pallet> chunk) {
        return palletRepositoryPort.saveAll(Flux.fromIterable(chunk));
    }

    /***************************************************************************************
                                    * MÉTODOS AUXILIARES
     **************************************************************************************/

    private List<LocalDate> buildDayRange(LocalDate start, LocalDate end) {
        return start.datesUntil(end.plusDays(1))
                .toList();
    }

}
