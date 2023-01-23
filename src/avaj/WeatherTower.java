package avaj;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    };
    void changeWeather() {
        // 예~
        this.conditionsChanged();
        // 크아아
    };

}
