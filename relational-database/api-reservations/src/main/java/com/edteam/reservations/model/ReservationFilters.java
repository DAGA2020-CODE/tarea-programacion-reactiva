package com.edteam.reservations.util;

import com.edteam.reservations.model.ReservationEvent;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ReservationFilters {
    // Predicado: Valida precio > 0 y lista de correos no vacía
    public static final Predicate<ReservationEvent> isValidReservation =
            event -> event.getPrice() > 0 && !event.getEmails().isEmpty();

    // Consumidor: Imprime en consola
    public static final Consumer<ReservationEvent> logEvent =
            event -> System.out.println("Procesando Evento: " + event);
}