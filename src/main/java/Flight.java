import java.time.LocalDateTime;

public class Flight {
    private Aircraft aircraft; //TODO Aeroflot-Russian Airlines PJSC
    private TypeFlight typeFlight; //TODO ARRIVAL
    private LocalDateTime timeDeparture; //TODO 20:30
    private LocalDateTime timeArrival; //TODO 22:10
    private String numberFlight; //TODO SU-1177
    private String placeForArrival; //TODO Москва/ШРМ
    private String status; //TODO регистрация
    private int exit; //TODO 5

    public Flight(
            Aircraft aircraft, TypeFlight typeFlight,
            LocalDateTime timeDeparture, LocalDateTime timeArrival,
            String numberFlight, String placeForArrival,
            String status, int exit) {
        this.aircraft = aircraft;
        this.typeFlight = typeFlight;
        this.timeDeparture = timeDeparture;
        this.timeArrival = timeArrival;
        this.numberFlight = numberFlight;
        this.placeForArrival = placeForArrival;
        this.status = status;
        this.exit = exit;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public TypeFlight getTypeFlight() {
        return typeFlight;
    }

    public void setTypeFlight(TypeFlight typeFlight) {
        this.typeFlight = typeFlight;
    }

    public LocalDateTime getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(LocalDateTime timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    public LocalDateTime getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(LocalDateTime timeArrival) {
        this.timeArrival = timeArrival;
    }

    public String getNumberFlight() {
        return numberFlight;
    }

    public void setNumberFlight(String numberFlight) {
        this.numberFlight = numberFlight;
    }

    public String getPlaceForArrival() {
        return placeForArrival;
    }

    public void setPlaceForArrival(String placeForArrival) {
        this.placeForArrival = placeForArrival;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getExit() {
        return exit;
    }

    public void setExit(int exit) {
        this.exit = exit;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "aircraft=" + aircraft +
                ", typeFlight='" + typeFlight + '\'' +
                ", timeDeparture=" + timeDeparture +
                ", timeArrival=" + timeArrival +
                ", numberFlight='" + numberFlight + '\'' +
                ", placeForArrival='" + placeForArrival + '\'' +
                ", status='" + status + '\'' +
                ", exit=" + exit +
                '}';
    }
}
