import java.time.LocalDateTime;
import java.util.*;

public class Airport {
    private String nameAirport;
    private List<Aircraft> listAircraft;
    private List<LaneForAircraft> listLanesForAircraft;
    private List<Flight> listFlights;
    private Map<String, Integer> mapCountParkedAircraftByTerminalName;

    public Airport(String nameAirport) {
        this.nameAirport = nameAirport;
        listAircraft = new ArrayList<>();
        listLanesForAircraft = new ArrayList<>();
        mapCountParkedAircraftByTerminalName = new TreeMap<>();
        listFlights = new ArrayList<>();
        for (int i = 0; i < 160; i++) {
            createAircraft();
        }
        createFlight();
        createLanesForAircraft();
    }

    public Aircraft createAircraft() {
        AircraftModels[] arrayAircraftModels = AircraftModels.values();
        int randomNumberForAircraftModel = (int) (Math.random() * 8);
        AircraftModels randomAircraftModel = arrayAircraftModels[randomNumberForAircraftModel];
        double randomAircraftLength = 20 + (int) (Math.random() * 57);
        int randomCruisingSpeed = 200 + (int) (Math.random() * 801);
        double randomMaxHeightFlight = (double) ((int) ((10 + (double) (Math.random() * 6)) * 10)) / 10.0;
        int randomMaxRangeFlight = 1_000 + (int) (Math.random() * 14_001);
        int randomCountBusinessSpaces = 8 + (int) (Math.random() * 21);
        int randomCountEconomySpaces = 100 + (int) (Math.random() * 201);
        Aircraft aircraft = new Aircraft(
                randomAircraftModel,
                randomAircraftLength,
                randomCruisingSpeed,
                randomMaxHeightFlight,
                randomMaxRangeFlight,
                randomCountBusinessSpaces,
                randomCountEconomySpaces
        );
        listAircraft.add(aircraft);
        return aircraft;
    }

    public Flight createFlight() {
        Aircraft randomAircraft = createAircraft();
        int randomNumberForTypeFlight = (int) (Math.random() * 2);
        TypeFlight typeFlight = TypeFlight.values()[randomNumberForTypeFlight];
        int randomHour = (int) (Math.random() * 24);
        int randomMinute = (int) (Math.random() * 60);
        LocalDateTime timeDeparture = LocalDateTime.of(2025, 4, 28, randomHour, randomMinute);
        LocalDateTime timeArrival = timeDeparture.plusHours(2);
        String[] randomNumbersFlights = {"SU-1177", "SU-2831", "SU-1133"};
        String randomNumberFlight = randomNumbersFlights[(int) (Math.random() * 3)];
        String[] randomPlacesForArrival = {"Москва/ШРМ", "МОСКВА/ДМД", "Санкт-Петербург/Пулково"};
        String randomPlaceForArrival = randomPlacesForArrival[(int) (Math.random() * 3)];
        String[] randomStatuses = {"Регистрация", "Регистрация закончена", "Задержан"};
        String randomStatus = randomStatuses[(int) (Math.random() * 3)];
        int randomExit = 1 + (int) (Math.random() * 30);
        Flight randomFlight = new Flight(
                randomAircraft, typeFlight,
                timeDeparture, timeArrival,
                randomNumberFlight, randomPlaceForArrival,
                randomStatus, randomExit
        );
        listFlights.add(randomFlight);
        return randomFlight;
    }

    public List<LaneForAircraft> createLanesForAircraft() {
        List<LaneForAircraft> listLanesForAircraft = new ArrayList<>();
        String[] arrayNameLanesForAircraft = {"A", "B", "C", "D"};
        for (String currentNameLaneForAircraft : arrayNameLanesForAircraft) {
            LaneForAircraft currentLaneForAircraft = new LaneForAircraft(currentNameLaneForAircraft);
            int randomCountParkedAircraft = 10 + (int) (Math.random() * 11);
            for (int i = 0; i < randomCountParkedAircraft; i++) {
                currentLaneForAircraft.addParkedAircraft(createAircraft());
                currentLaneForAircraft.addFlight(createFlight());
            }
            listLanesForAircraft.add(currentLaneForAircraft);
        }
        this.listLanesForAircraft = listLanesForAircraft;
        return listLanesForAircraft;
    }

    //TODO Метод должен вернуть словарь с количеством припаркованных самолетов на каждой полосе.
    public Map<String, Integer> findMapCountParkedAircraftByTerminalName() {
        Map<String, Integer> mapCountParkedAircraftByTerminalName = new TreeMap<>();

        for (LaneForAircraft currentLaneForAircraft : listLanesForAircraft) {
            mapCountParkedAircraftByTerminalName.put(
                    currentLaneForAircraft.getNameLaneForAircraft(),
                    currentLaneForAircraft.getListParkedAircraft().size());
        }
        this.mapCountParkedAircraftByTerminalName = mapCountParkedAircraftByTerminalName;
        return mapCountParkedAircraftByTerminalName;
    }

    //TODO Получить кол-во самолётов с номером указанной модели
    public int findCountAircraftWithNumberSpecifiedModel(int numberSpecifiedModelAircraft) {
        int countAircraft = 0;

        for (Aircraft currentAircraft : listAircraft) {
            if (currentAircraft.getAircraftModel().equals(
                    AircraftModels.values()[numberSpecifiedModelAircraft - 1])) {
                countAircraft++;
            }
        }

        return countAircraft;
    }

    //TODO Получить ближайший рейс в указанную пользователем точку прибытия
    public Flight findFirstFlightToSpecifiedPlaceArrival(String namePlaceForArrival) {
        Set<Flight> setFlightsArrival = new TreeSet<>();
        for (LaneForAircraft currentLaneForAircraft : listLanesForAircraft) {
            for (Flight currentFlight : currentLaneForAircraft.getListFlights()) {
                if (currentFlight.getTimeArrival().isAfter(LocalDateTime.now()) &&
                        currentFlight.getTypeFlight().equals(TypeFlight.ARRIVAL) &&
                            currentFlight.getPlaceForArrival().equals(namePlaceForArrival)) {
                    setFlightsArrival.add(currentFlight);
                }
            }
        }

        for (Flight currentFlight : setFlightsArrival) {
            return currentFlight;
        }
        return null;
    }

    //TODO Метод должен вернуть список отправляющихся рейсов в ближайшее количество часов.

    public String getNameAirport() {
        return nameAirport;
    }

    public List<Aircraft> getListAircraft() {
        return listAircraft;
    }

    public List<LaneForAircraft> getListLanesForAircraft() {
        return listLanesForAircraft;
    }

    public List<Flight> getListFlights() {
        return listFlights;
    }

    public Map<String, Integer> getMapCountParkedAircraftByTerminalName() {
        return mapCountParkedAircraftByTerminalName;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "nameAirport='" + nameAirport + '\'' +
                ", listAircraft=" + listAircraft +
                '}';
    }
}
