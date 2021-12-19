package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class BusServiceGenerateRandomInt {
    BusService busService = new BusService();

    @Test
    void givenBound_WhenGenerateRandomIntCalls_ThenResponseReturn() {
        int num = busService.generateRandomInt(3);
        System.out.println(num);
        assertTrue(num < 3 && num >= 0);
    }
}
