package com.safco.peru.integracion.friomamut.adapter.in.scheduler;

import com.safco.peru.integracion.friomamut.application.port.out.SyncFriomamutUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class SyncFriomamutScheduler {
    private final SyncFriomamutUseCase syncFriomamutUseCase;

    @Scheduled(cron = "0 0 9 * * *")
    public void syncMeasurementLastTwoDays() {
        LocalDate ayer = LocalDate.now().minusDays(1);
        LocalDate anteayer = ayer.minusDays(1);
        syncFriomamutUseCase
                .syncMeasurementsByDateRange(anteayer, ayer)
                .doOnComplete(() -> log.info("Sync de medidas automático completado"))
                .doOnError(e -> log.error("Error en sync automático", e))
                .subscribe();
    }

    @Scheduled(cron = "0 0 9 * * *")
    public void synPalletsLastTwoDays(){
        LocalDate ayer = LocalDate.now().minusDays(1);
        LocalDate anteayer = ayer.minusDays(1);
        syncFriomamutUseCase
                .syncPalletsByDateRange(anteayer, ayer)
                .doOnComplete(() -> log.info("Sync de pallets automático completado"))
                .doOnError(e -> log.error("Error en sync automático", e))
                .subscribe();
    }





}
