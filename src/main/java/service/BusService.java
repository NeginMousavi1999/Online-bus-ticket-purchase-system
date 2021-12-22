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
        if (buses.size() == 0)
            throw new RuntimeException("*** we have n't this type bus... Excuse Us... ***");
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

    public List<Bus> showAll() {
        return busDao.readAll();
    }
}
