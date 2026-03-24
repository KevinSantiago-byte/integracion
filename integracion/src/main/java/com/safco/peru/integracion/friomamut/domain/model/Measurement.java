package com.safco.peru.integracion.friomamut.domain.model;


import java.time.LocalDate;
import java.time.LocalDateTime;


public record Measurement(
        Long id,
        String tipo,
        String zona,
        String proceso,
        LocalDateTime fecha,
        String sensor,
        Float valores,
        LocalDate fechaProceso
) {


    //create a static factory method to create a MeasurementEntity object
    public static Measurement create(String tipo,
                                    String zona,
                                    String proceso,
                                    LocalDateTime fecha,
                                    String sensor,
                                    Float valores,
                                    LocalDate fechaProceso) {
        if (valores == null) {
            throw new IllegalArgumentException("Valores cannot be null");
        }
        return new Measurement(null, tipo, zona, proceso, fecha, sensor, valores, fechaProceso);
    }

    public static Measurement restore(Long id,
                                    String tipo,
                                    String zona,
                                    String proceso,
                                    LocalDateTime fecha,
                                    String sensor,
                                    Float valores,
                                    LocalDate fechaProceso) {

        return new Measurement(id, tipo, zona, proceso, fecha, sensor, valores, fechaProceso);
    }

    public static Measurement syncFechaProceso(Measurement measurement, LocalDate fechaProceso) {
        return new Measurement(
                measurement.id(),
                measurement.tipo(),
                measurement.zona(),
                measurement.proceso(),
                measurement.fecha(),
                measurement.sensor(),
                measurement.valores(),
                fechaProceso
        );
    }

}
