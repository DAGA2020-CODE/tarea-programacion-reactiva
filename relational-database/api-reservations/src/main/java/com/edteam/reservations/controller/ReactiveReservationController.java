package com.edteam.reservations.controller;

import com.edteam.reservations.model.ReservationEvent;
import com.edteam.reservations.util.ReservationFilters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReactiveReservationController {

    @GetMapping(value = "/stream", produces = "application/json")
    public Flux<ReservationEvent> getReservationStream() {
        // Datos de prueba (3 válidos, 2 inválidos)
        ReservationEvent r1 = new ReservationEvent("1", "Juan", 150.0, List.of("juan@mail.com"));
        ReservationEvent r2 = new ReservationEvent("2", "Maria", 200.0, List.of("maria@mail.com"));
        ReservationEvent r3 = new ReservationEvent("3", "Pedro", 0.0, List.of()); // Inválido
        ReservationEvent r4 = new ReservationEvent("4", "Ana", -10.0, List.of("ana@mail.com")); // Inválido
        ReservationEvent r5 = new ReservationEvent("5", "Luis", 300.0, List.of("luis@mail.com"));

        // Objeto genérico por si el flujo queda vacío
        ReservationEvent defaultReservation = new ReservationEvent("0", "Sistema", 0.0, List.of("admin@airline.com"));

        return Flux.just(r1, r2, r3, r4, r5)
                .filter(ReservationFilters.isValidReservation) // Filtro
                .doOnNext(ReservationFilters.logEvent)         // Logueo
                .defaultIfEmpty(defaultReservation);           // Si no queda nada
    }
}