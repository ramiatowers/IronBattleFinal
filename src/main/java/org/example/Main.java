import org.example.Attacker;
import org.example.GameCharacter;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<GameCharacter> characters = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== MAIN MENU ===\n");
            System.out.println("1. Create character");
            System.out.println("2. Show characters");
            System.out.println("3. Start battle");
            System.out.println("4. Exit");
            System.out.println("5. Random battle");
            System.out.println("6. Import characters from CSV\n");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> createCharacter();
                case "2" -> showCharacters();
                case "3" -> startBattle();
                case "4" -> {
                    System.out.println("👋 Exiting... Goodbye!");
                    running = false;
                }
                case "5" -> randomBattle();
                case "6" -> importCharactersFromCSV();
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    static void createCharacter() {
        System.out.print("Enter character name: ");
        String name = scanner.nextLine();

        System.out.print("Choose class (1 = Warrior, 2 = Wizard): ");
        String type = scanner.nextLine();

        GameCharacter newChar = null;

        if (type.equals("1")) {
            System.out.print("HP (100-200): ");
            int hp = Integer.parseInt(scanner.nextLine());

            System.out.print("Stamina (10-50): ");
            int stamina = Integer.parseInt(scanner.nextLine());

            System.out.print("Strength (1-10): ");
            int strength = Integer.parseInt(scanner.nextLine());

            newChar = new Warrior(name, hp, stamina, strength);

        } else if (type.equals("2")) {
            System.out.print("HP (50-100): ");
            int hp = Integer.parseInt(scanner.nextLine());

            System.out.print("Mana (10-50): ");
            int mana = Integer.parseInt(scanner.nextLine());

            System.out.print("Intelligence (1-50): ");
            int intelligence = Integer.parseInt(scanner.nextLine());

            newChar = new Wizard(name, hp, mana, intelligence);

        } else {
            System.out.println("Invalid class choice.");
            return;
        }

        characters.add(newChar);
        System.out.println("✅ Character created: " + name + " (" + newChar.getClass().getSimpleName() + ")");
    }

    static void showCharacters() {
        if (characters.isEmpty()) {
            System.out.println("No characters created yet.");
            return;
        }

        System.out.println("\n--- Character List ---");
        for (int i = 0; i < characters.size(); i++) {
            GameCharacter c = characters.get(i);
            System.out.println(i + " - " + c);
        }
    }

    static void startBattle() {
        if (characters.size() < 2) {
            System.out.println("At least two characters are required to battle.");
            return;
        }

        showCharacters();

        try {
            System.out.print("Choose first character (index): ");
            int i1 = Integer.parseInt(scanner.nextLine());

            System.out.print("Choose second character (index): ");
            int i2 = Integer.parseInt(scanner.nextLine());

            if (i1 == i2 || i1 < 0 || i2 < 0 || i1 >= characters.size() || i2 >= characters.size()) {
                System.out.println("❌ Invalid selection.");
                return;
            }

            GameCharacter c1 = characters.get(i1);
            GameCharacter c2 = characters.get(i2);

            System.out.println("\n🧨 BATTLE STARTS: " + c1.getName() + " VS " + c2.getName() + "\n");

            while (c1.isAlive() && c2.isAlive()) {
                ((Attacker) c1).attack(c2);
                ((Attacker) c2).attack(c1);
                System.out.println(">> Status: " + c1.getName() + " HP: " + c1.getHp() + " | " + c2.getName() + " HP: " + c2.getHp());
                System.out.println();
            }

            if (!c1.isAlive() && !c2.isAlive()) {
                System.out.println("⚠️ It's a draw! Both characters died.");
            } else {
                GameCharacter winner = c1.isAlive() ? c1 : c2;
                System.out.println("🏆 Winner: " + winner.getName());
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input.");
        }
    }

    static void randomBattle() {
        String[] names = {"Aegon", "Lyra", "Thorne", "Nyx", "Drax", "Elira"};
        int r1 = (int) (Math.random() * names.length);
        int r2;
        do {
            r2 = (int) (Math.random() * names.length);
        } while (r2 == r1);

        GameCharacter char1 = Math.random() < 0.5
                ? new Warrior(names[r1], getRandom(100, 200), getRandom(10, 50), getRandom(1, 10))
                : new Wizard(names[r1], getRandom(50, 100), getRandom(10, 50), getRandom(1, 50));

        GameCharacter char2 = Math.random() < 0.5
                ? new Warrior(names[r2], getRandom(100, 200), getRandom(10, 50), getRandom(1, 10))
                : new Wizard(names[r2], getRandom(50, 100), getRandom(10, 50), getRandom(1, 50));

        System.out.println("\n🎲 RANDOM BATTLE STARTS: " + char1.getName() + " VS " + char2.getName() + "\n");

        while (char1.isAlive() && char2.isAlive()) {
            ((Attacker) char1).attack(char2);
            ((Attacker) char2).attack(char1);
            System.out.println(">> Status: " + char1.getName() + " HP: " + char1.getHp() + " | " + char2.getName() + " HP: " + char2.getHp());
            System.out.println();
        }

        if (!char1.isAlive() && !char2.isAlive()) {
            System.out.println("⚠️ It's a draw! Both characters died.");
        } else {
            GameCharacter winner = char1.isAlive() ? char1 : char2;
            System.out.println("🏆 Winner: " + winner.getName());
        }
    }

    static int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    static void importCharactersFromCSV() {
        System.out.print("Enter CSV file path (e.g., characters.csv): ");
        String filePath = scanner.nextLine();

        try (Scanner fileScanner = new Scanner(new java.io.File(filePath))) {
            int count = 0;

            // Skip header
            if (fileScanner.hasNextLine()) fileScanner.nextLine();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].replace("\"", "").trim();
                }

                if (parts.length != 5) {
                    System.out.println("⚠️ Invalid line: " + line);
                    continue;
                }

                String name = parts[0].trim();
                String type = parts[1].trim().toLowerCase();
                int hp = Integer.parseInt(parts[2].trim());
                int resource = Integer.parseInt(parts[3].trim());
                int power = Integer.parseInt(parts[4].trim());

                GameCharacter character;

                if (type.equals("warrior")) {
                    character = new Warrior(name, hp, resource, power);
                } else if (type.equals("wizard")) {
                    character = new Wizard(name, hp, resource, power);
                } else {
                    System.out.println("❌ Unknown type: " + type);
                    continue;
                }

                characters.add(character);
                count++;
            }

            System.out.println("✅ " + count + " characters imported successfully.");

        } catch (Exception e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }
}