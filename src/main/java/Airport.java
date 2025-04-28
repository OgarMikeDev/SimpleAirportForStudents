import java.util.ArrayList;
import java.util.List;

public class Airport {
    private String nameAirport;
    private List<Aircraft> listAircraft;

    public Airport(String nameAirport) {
        this.nameAirport = nameAirport;
        listAircraft = new ArrayList<>();
        generateAircraft();
    }

    public void generateAircraft() {
        for (int i = 0; i < 30; i++) {
            AircraftModels[] arrayAircraftModels = AircraftModels.values();
            int randomNumberForAircraftModel = (int) (Math.random() * 8);
            AircraftModels randomAircraftModel = arrayAircraftModels[randomNumberForAircraftModel];
            double randomAircraftLength = 20 + (int) (Math.random() * 57);
            int randomCruisingSpeed = 200 + (int) (Math.random() * 801);
            double randomMaxHeightFlight = 10 + (int) (Math.random() * 6);
            int randomMaxRangeFlight = 1_000 + (int) (Math.random() * 14_001);
            int randomCountBusinessSpaces = 8 + (int) (Math.random() * 21);
            int randomCountEconomySpaces = 100 + (int) (Math.random() * 201);
            listAircraft.add(new Aircraft(
                    randomAircraftModel,
                    randomAircraftLength,
                    randomCruisingSpeed,
                    randomMaxHeightFlight,
                    randomMaxRangeFlight,
                    randomCountBusinessSpaces,
                    randomCountEconomySpaces
            ));
        }
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
