package com.safco.peru.integracion.friomamut.domain.model;

import java.time.LocalDate;

public record Pallet(
        Long id,
        LocalDate fecha,
        Integer semana,
        Integer tunel,
        String zonificado,
        String inicio,
        String finalizado,
        String cambioFlujo,
        String duracion,
        Double presion,
        String paleta,
        String embalaje,
        String presentacion,
        String variedad,
        String envase,
        Integer caja,
        String parihuela,
        String etiqueta,
        String ubicacion,
        LocalDate fechaProceso
) {

    public static Pallet create(LocalDate fecha,
                                Integer semana,
                                Integer tunel,
                                String zonificado,
                                String inicio,
                                String finalizado,
                                String cambioFlujo,
                                String duracion,
                                Double presion,
                                String paleta,
                                String embalaje,
                                String presentacion,
                                String variedad,
                                String envase,
                                Integer caja,
                                String parihuela,
                                String etiqueta,
                                String ubicacion,
                                LocalDate fechaProceso) {
        return new Pallet(null, fecha, semana, tunel, zonificado, inicio, finalizado, cambioFlujo, duracion, presion, paleta, embalaje, presentacion, variedad, envase, caja, parihuela, etiqueta, ubicacion, fechaProceso);
    }

    public static Pallet restore(Long id,
                                LocalDate fecha,
                                Integer semana,
                                Integer tunel,
                                String zonificado,
                                String inicio,
                                String finalizado,
                                String cambioFlujo,
                                String duracion,
                                Double presion,
                                String paleta,
                                String embalaje,
                                String presentacion,
                                String variedad,
                                String envase,
                                Integer caja,
                                String parihuela,
                                String etiqueta,
                                String ubicacion,
                                LocalDate fechaProceso) {
        return new Pallet(id, fecha, semana, tunel, zonificado, inicio, finalizado, cambioFlujo, duracion, presion, paleta, embalaje, presentacion, variedad, envase, caja, parihuela, etiqueta, ubicacion, fechaProceso);
    }

    public static Pallet syncFechaProceso(Pallet pallet, LocalDate fechaProceso) {
        if (pallet == null) {
            throw new IllegalArgumentException("Pallet cannot be null");
        }

        return new Pallet(
                pallet.id(),
                pallet.fecha(),
                pallet.semana(),
                pallet.tunel(),
                pallet.zonificado(),
                pallet.inicio(),
                pallet.finalizado(),
                pallet.cambioFlujo(),
                pallet.duracion(),
                pallet.presion(),
                pallet.paleta(),
                pallet.embalaje(),
                pallet.presentacion(),
                pallet.variedad(),
                pallet.envase(),
                pallet.caja(),
                pallet.parihuela(),
                pallet.etiqueta(),
                pallet.ubicacion(),
                fechaProceso
        );
    }

}

