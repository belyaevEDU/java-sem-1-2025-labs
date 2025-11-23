package org.example.UserInteraction;

import org.example.task.Hero;
import org.example.task.MovementStrategy;
import org.example.task.Point;

import java.util.*;

import static org.example.UserInteraction.UIUtils.*;

public class UserInteraction implements Runnable {
    private final Scanner scanner;
    private final HashSet<Hero> heroes = new HashSet<>();
    private final ArrayList<MovementStrategy> strats = new ArrayList<>();

    public UserInteraction(Scanner scanner) {
        this.scanner = scanner;
    }

    public UserInteraction() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        this.mainMenu();
    }

    private void mainMenu() {
        boolean running = true;

        Map<String, Runnable> menu = new HashMap<>();
        menu.put("1", this::listAllHeroes);
        menu.put("2", this::listAllMovementStrats);
        menu.put("3", this::createNewHero);
        menu.put("4", this::createNewMovementStrat);
        menu.put("5", this::modifyHero);
        menu.put("6", this::moveHeroToNewPoint);
        menu.put("7", this::deleteHero);

        while (running) {
            System.out.println("Main menu: ");
            System.out.println("1 - List all heroes");
            System.out.println("2 - List all movement strats");
            System.out.println("3 - Create new hero");
            System.out.println("4 - Create new movement strat");
            System.out.println("5 - Modify hero (name / movement strat)");
            System.out.println("6 - Move hero to new point");
            System.out.println("7 - Delete hero");
            System.out.println("-1 - Exit");
            System.out.print("\nEnter command by id: ");
            String commandId = this.scanner.nextLine().strip();

            if (commandId.equals("-1")) {
                running = false;
            } else if (menu.containsKey(commandId)) {
                System.out.println();
                menu.get(commandId).run();
                waitForUserToContinue(this.scanner);
            } else {
                System.out.println("Command doesn't exist!");
            }
        }
    }

    private void listAllMovementStrats() {
        if (this.strats.isEmpty()) {
            System.out.println("No movement strategies in set!");
        } else {
            listAllArrayIndexToVal(this.strats);
        }
    }

    private void listAllHeroes() {
        if (this.heroes.isEmpty()) {
            System.out.println("No heroes in list.");
        } else {
            this.heroes.forEach(System.out::println);
        }
    }

    private Hero getHeroByName(String name) {
        for (Hero hero : this.heroes) {
            if (hero.getName().equals(name)) {
                return hero;
            }
        }
        return null;
    }

    private Optional<Integer> safeUserQueryForStrat() {
        System.out.println("\nChoose the movement strategy by id:");
        Optional<Integer> result = safeUserQueryForInt(this.scanner);

        if (result.isPresent()) {
            int index = result.get();
            if (index < 0 || index >= this.strats.size()) {
                System.out.println("ERROR: Out of bounds!");
                return Optional.empty();
            }
        }

        return result;
    }

    private void createNewHero() throws NumberFormatException {
        if (this.strats.isEmpty()) {
            System.out.println("You need to create a movement strategy first!");
            return;
        }

        System.out.println("Enter the new hero's name: ");
        String newHeroName = this.scanner.nextLine().strip();

        if (this.getHeroByName(newHeroName) != null) {
            System.out.println("Hero named " + newHeroName + " already exists!");
            return;
        }

        System.out.println("\nMovement strategies (index to strat message): ");
        this.listAllMovementStrats();

        Optional<Integer> indexOptional = this.safeUserQueryForStrat();
        if (indexOptional.isEmpty()) {
            return;
        }

        this.heroes.add(new Hero(newHeroName, this.strats.get(indexOptional.get())));
        System.out.println("Success!");
    }

    private void createNewMovementStrat() {
        System.out.println("Message example: \"moved by Horse\"");
        //System.out.println("How it will look: \"Hero !moved by Horse! from x: -1, y: 1 to x: 5, y: 15\"");
        System.out.println("Enter the new movement strat's message: ");
        String newMessage = this.scanner.nextLine().strip();
        this.strats.add(new MovementStrategy(newMessage));
        System.out.println("Success!");
    }

    private Hero selectHero() {
        System.out.println("All heroes:");
        this.listAllHeroes();

        if (this.heroes.isEmpty()) {
            return null;
        }

        System.out.println("\nSelect the hero by name:");
        String heroName = this.scanner.nextLine();

        Hero heroByName = this.getHeroByName(heroName);
        if (heroByName == null) {
            System.out.println("Hero doesn't exist.");
        }
        return heroByName;
    }

    private void modifyHero() {
        Hero heroByName = this.selectHero();
        if (heroByName == null) {
            return;
        }

        System.out.println("What do you want to modify?:");
        System.out.println("1 - name");
        System.out.println("2 - movement strategy");
        String modKey = this.scanner.nextLine().strip();

        Map<String, Runnable> modifyMethodsMap = new HashMap<>();
        modifyMethodsMap.put("1", () -> modifyHeroName(heroByName));
        modifyMethodsMap.put("2", () -> modifyHeroMovementStrat(heroByName));

        if (modifyMethodsMap.containsKey(modKey)) {
            modifyMethodsMap.get(modKey).run();
        } else {
            System.out.println("ERROR: Option doesn't exist");
        }

    }

    private void modifyHeroName(Hero hero) {
        System.out.println("Enter new name: ");
        String newName = this.scanner.nextLine();
        if (this.getHeroByName(newName) == null) {
            hero.setName(newName);
            System.out.println("Success!");
        } else {
            System.out.println("A hero with the name " + newName + " already exists!");
        }
    }

    private void modifyHeroMovementStrat(Hero hero) throws NumberFormatException {
        System.out.println("All movement strategies: ");
        this.listAllMovementStrats();

        Optional<Integer> indexOptional = this.safeUserQueryForStrat();
        if (indexOptional.isEmpty()) {
            return;
        }
        MovementStrategy strat = this.strats.get(indexOptional.get());
        hero.setMovementStrategy(strat);
        System.out.println("Success!");
    }

    private void moveHeroToNewPoint() {
        Hero heroByName = this.selectHero();
        if (heroByName == null) {
            return;
        }

        System.out.println("Enter new point's x: ");
        Optional<Integer> optX = safeUserQueryForInt(this.scanner);
        if (optX.isEmpty()) {
            return;
        }

        System.out.println("Enter new point's y: ");
        Optional<Integer> optY = safeUserQueryForInt(this.scanner);
        if (optY.isEmpty()) {
            return;
        }

        heroByName.move(new Point(optX.get(), optY.get()));
    }

    private void deleteHero() {
        Hero heroByName = this.selectHero();
        if (heroByName == null) {
            return;
        }

        System.out.println(this.heroes.remove(heroByName));
    }
}
