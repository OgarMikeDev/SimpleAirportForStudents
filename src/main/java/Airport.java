import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Airport {
    private String nameAirport;
    private List<Aircraft> listAircraft;
    private List<LaneForAircraft> listLanesForAircraft;
    private Map<String, Integer> mapCountParkedAircraftByTerminalName;

    public Airport(String nameAirport) {
        this.nameAirport = nameAirport;
        listAircraft = new ArrayList<>();
        listLanesForAircraft = new ArrayList<>();
        mapCountParkedAircraftByTerminalName = new TreeMap<>();
        for (int i = 0; i < 160; i++) {
            generateAircraft();
        }
        createLanesForAircraft();
    }

    public Aircraft generateAircraft() {
        AircraftModels[] arrayAircraftModels = AircraftModels.values();
        int randomNumberForAircraftModel = (int) (Math.random() * 8);
        AircraftModels randomAircraftModel = arrayAircraftModels[randomNumberForAircraftModel];
        double randomAircraftLength = 20 + (int) (Math.random() * 57);
        int randomCruisingSpeed = 200 + (int) (Math.random() * 801);
        double randomMaxHeightFlight = 10 + (int) (Math.random() * 6);
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

    public Map<String, Integer> findMapCountParkedAircraftByTerminalName() {
        //TODO Метод должен вернуть словарь с количеством припаркованных самолетов на каждой полосе.
        Map<String, Integer> mapCountParkedAircraftByTerminalName = new TreeMap<>();

        for (LaneForAircraft currentLaneForAircraft : listLanesForAircraft) {
            mapCountParkedAircraftByTerminalName.put(
                    currentLaneForAircraft.getNameLaneForAircraft(),
                    currentLaneForAircraft.getListParkedAircraft().size());
        }
        this.mapCountParkedAircraftByTerminalName = mapCountParkedAircraftByTerminalName;
        return mapCountParkedAircraftByTerminalName;
    }

    public List<LaneForAircraft> createLanesForAircraft() {
        List<LaneForAircraft> listLanesForAircraft = new ArrayList<>();
        String[] arrayNameLanesForAircraft = {"A", "B", "C", "D"};
        for (String currentNameLaneForAircraft : arrayNameLanesForAircraft) {
            LaneForAircraft currentLaneForAircraft = new LaneForAircraft(currentNameLaneForAircraft);
            int randomCountParkedAircraft = 10 + (int) (Math.random() * 11);
            for (int i = 0; i < randomCountParkedAircraft; i++) {
                currentLaneForAircraft.addParkedAircraft(generateAircraft());
            }
            listLanesForAircraft.add(currentLaneForAircraft);
        }
        this.listLanesForAircraft = listLanesForAircraft;
        return listLanesForAircraft;
    }

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

    //TODO Найти ближайший прилет в указанный терминал.

    public String getNameAirport() {
        return nameAirport;
    }

    public List<Aircraft> getListAircraft() {
        return listAircraft;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "nameAirport='" + nameAirport + '\'' +
                ", listAircraft=" + listAircraft +
                '}';
    }
}
