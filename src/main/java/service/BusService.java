package service;

import dao.BusDao;
import enumuration.BusType;
import model.Bus;

import java.util.List;
import java.util.Random;

/**
 * @author Negin Mousavi
 */
public class BusService {
    BusDao busDao = new BusDao();

    public Bus getRandomByType(BusType busType) {
        List<Bus> buses = busDao.get(busType);
        int randomIndex = generateRandomInt(buses.size());
        return buses.get(randomIndex);
    }

    public int generateRandomInt(int bound) {
        Random rnd = new Random();
        return rnd.nextInt(bound);
    }

    public void save(Bus bus) {
        busDao.save(bus);
    }
}
