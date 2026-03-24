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
public class SyncMeasurementScheduler {
    private final SyncFriomamutUseCase syncFriomamutUseCase;

    @Scheduled(cron = "0 0 9 * * *")
    public void syncLastTwoDays() {
        LocalDate ayer = LocalDate.now().minusDays(1);
        LocalDate anteayer = ayer.minusDays(1);
        syncFriomamutUseCase
                .syncMeasurementsByDateRange(anteayer, ayer)
                .doOnComplete(() -> log.info("Sync automático completado"))
                .doOnError(e -> log.error("Error en sync automático", e))
                .subscribe();
    }

}
