import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Homework9 {

    public static void main(String[] args) {
        HashMap<String, String> userDatabase = loadUserDatabase();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("id와 password를 입력해주세요.");

            System.out.print("id : ");
            String inputId = scanner.nextLine().trim();

            if (!userDatabase.containsKey(inputId)) {
                System.out.println("입력하신 id는 존재하지 않습니다. 다시 입력해주세요.");
                continue;
            }

            System.out.print("password : ");
            String inputPassword = scanner.nextLine().trim();

            if (validatePassword(userDatabase, inputId, inputPassword)) {
                System.out.println("id와 비밀번호가 일치합니다.");
                break;
            } else {
                System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
            }
        }

        scanner.close();
    }

    private static HashMap<String, String> loadUserDatabase() {
        HashMap<String, String> userDatabase = new HashMap<>();

        try {
            Scanner fileScanner = new Scanner(new File("db.txt"));

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] tokens = line.split("\\s+");

                if (tokens.length == 2) {
                    String id = tokens[0].trim();
                    String password = tokens[1].trim();
                    userDatabase.put(id, password);
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return userDatabase;
    }

    private static boolean validatePassword(HashMap<String, String> userDatabase, String id, String password) {
        return userDatabase.get(id).equals(password);
    }
}
