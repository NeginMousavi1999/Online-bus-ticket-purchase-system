import model.Manager;
import service.ManagerService;

/**
 * @author Negin Mousavi
 */
public class Main {
    static ManagerService managerService = new ManagerService();

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.setUsername("admin");
        manager.setPassword("admin");
        managerService.save(manager);
    }
}
