import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Airport {
    private String nameAirport;
    private List<Aircraft> listAircraft;
    private List<LaneForAircraft> listLanesForAircraft;
    private List<Flight> listFlights;
    private Map<String, Integer> mapCountParkedAircraftByTerminalName;
    private Set<Flight> setSortedFlightsDeparture;

    public Airport(String nameAirport) {
        this.nameAirport = nameAirport;
        listAircraft = new ArrayList<>();
        listLanesForAircraft = new ArrayList<>();
        mapCountParkedAircraftByTerminalName = new TreeMap<>();
        listFlights = new ArrayList<>();
        setSortedFlightsDeparture = new TreeSet<>();
        for (int i = 0; i < 360; i++) {
            createAircraft();
        }

        for (int i = 0; i < 500; i++) {
            createFlight();
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
        TypeFlight randomTypeFlight = TypeFlight.values()[(int) (Math.random() * 2)];
        LocalDateTime randomTimeDeparture = LocalDateTime.of(
                LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(),
                ((int) (Math.random() * 24)), ((int) (Math.random() * 60))
        );
        LocalDateTime randomTimeArrival = randomTimeDeparture.plusHours(2 + (int) (Math.random() * 15));
        String[] arrayNumberFlight = {"SU-1139", "SU-1529", "SU-1117"};
        String randomNumberFlight = arrayNumberFlight[(int) (Math.random() * 3)];
        String[] arrayPlaceForArrival = {"Москва/ШРМ", "МОСКВА/ДМД", "Санкт-Петербург/Пулково"};
        String randomPlaceForArrival = arrayPlaceForArrival[(int) (Math.random() * 3)];
        String[] arrayStatus = {
                "Регистрация", "Посадка окончена", "Посадка",
                "Регистрация закончена", "Задержан", "По расписанию"};
        String randomStatus = arrayStatus[(int) (Math.random() * 6)];
        int randomCountGates = 1 + (int) (Math.random() * 2);
        Integer[] randomGates = new Integer[randomCountGates];
        int randomValueGate = 1 + (int) (Math.random() * 30);
        randomGates[0] = randomValueGate;
        if (randomGates.length == 2) {
            if (randomGates[0] == 30) {
                int temp = randomGates[0];
                randomGates[0] = randomGates[0] - 1;
                randomGates[1] = temp;
            } else if (randomGates[0] != 30) {
                randomGates[1] = randomGates[0] + 1;
            }
        }
        Flight randomFlight = new Flight(
                randomAircraft, randomTypeFlight,
                randomTimeDeparture, randomTimeArrival,
                randomNumberFlight, randomPlaceForArrival,
                randomStatus, randomGates
        );
        listFlights.add(randomFlight);
        return randomFlight;
    }

    public List<LaneForAircraft> createLanesForAircraft() {
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
        return listLanesForAircraft;
    }

    /*
    TODO Метод должен найти
     количество припаркованных самолетов на каждой полосе
     и вернуть такой Map
     */
    public Map<String, Integer> findMapCountParkedAircraftByTerminalName() {
        for (LaneForAircraft currentLaneForAircraft : listLanesForAircraft) {
            mapCountParkedAircraftByTerminalName.put(
                    currentLaneForAircraft.getNameLaneForAircraft(),
                    currentLaneForAircraft.getListParkedAircraft().size());
        }
        return mapCountParkedAircraftByTerminalName;
    }

    /*
    TODO Найти кол-во самолётов с номером указанной модели
     */
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

    /*
    TODO Найти ближайший рейс в указанную пользователем точку прибытия
     */
    public Flight findFirstFlightToSpecifiedPlaceArrival(String namePlaceForArrival) {
        setSortedFlightsDeparture = new TreeSet<>();
        //Ниже пишем код
        for (Flight currentFlight : listFlights) {
            if (currentFlight.getTypeFlight().equals(TypeFlight.ARRIVAL) &&
                    currentFlight.getTimeDeparture().isAfter(LocalDateTime.now().plusHours(3)) &&
                        currentFlight.getPlaceForArrival().equals(namePlaceForArrival)) {
                setSortedFlightsDeparture.add(currentFlight);
            }
        }


        for (Flight flight : setSortedFlightsDeparture) {
            return flight;
        }
        return null;
    }

    /*
    TODO Метод должен найти и вернуть список отправляющихся рейсов
     в ближайшее количество часов,
     в указанное место.
     */
    public List<Flight> findListFlightsDepartureInNextCountHours(int countHours, String namePlaceForArrival) {
        setSortedFlightsDeparture = new TreeSet<>();
        findFirstFlightToSpecifiedPlaceArrival(namePlaceForArrival);
        List<Flight> listFlightsDepartureInNextCountHours = new ArrayList<>();
        //Ниже пишем код
        for (Flight currentFlight : listFlights) {
            if (currentFlight.getPlaceForArrival().equals(namePlaceForArrival) &&
                    currentFlight.getTimeDeparture().isAfter(LocalDateTime.now()) &&
                        currentFlight.getTimeDeparture().isBefore(LocalDateTime.now().plusHours(countHours))) {
                listFlightsDepartureInNextCountHours.add(currentFlight);
            }
        }
        return listFlightsDepartureInNextCountHours;
    }

        public String getNameAirport () {
            return nameAirport;
        }

        public List<Aircraft> getListAircraft () {
            return listAircraft;
        }

        public List<LaneForAircraft> getListLanesForAircraft () {
            return listLanesForAircraft;
        }

        public List<Flight> getListFlights () {
            return listFlights;
        }

        public Map<String, Integer> getMapCountParkedAircraftByTerminalName () {
            return mapCountParkedAircraftByTerminalName;
        }

        public Set<Flight> getSetSortedFlightsDeparture () {
            return setSortedFlightsDeparture;
        }

        @Override
        public String toString () {
            return "Airport{" +
                    "nameAirport='" + nameAirport + '\'' +
                    ", listAircraft=" + listAircraft +
                    ", listLanesForAircraft=" + listLanesForAircraft +
                    ", listFlights=" + listFlights +
                    ", mapCountParkedAircraftByTerminalName=" + mapCountParkedAircraftByTerminalName +
                    ", setSortedFlightsDeparture=" + setSortedFlightsDeparture +
                    '}';
        }
    }
