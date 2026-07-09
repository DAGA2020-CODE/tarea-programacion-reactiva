package com.edteam.reservations.model; // Ajusta a tu paquete real

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ReservationEvent { // final para que la clase no se herede
    private final String id;
    private final String passengerName;
    private final Double price;
    private final List<String> emails;

    public ReservationEvent(String id, String passengerName, Double price, List<String> emails) {
        this.id = id;
        this.passengerName = passengerName;
        this.price = price;
        // COPIA DEFENSIVA: Evita que si alguien cambia la lista afuera, cambie aquí
        this.emails = (emails != null) ? new ArrayList<>(emails) : new ArrayList<>();
    }

    // Getters (SIN SETTERS)
    public String getId() { return id; }
    public String getPassengerName() { return passengerName; }
    public Double getPrice() { return price; }

    public List<String> getEmails() {
        // COPIA DEFENSIVA: Retornamos una lista que no se puede modificar
        return Collections.unmodifiableList(emails);
    }

    @Override
    public String toString() {
        return "Reserva [ID=" + id + ", Pasajero=" + passengerName + ", Precio=" + price + "]";
    }
}