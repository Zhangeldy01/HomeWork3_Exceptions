import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ReceiverData {

    public static void getPersonData(Person person) throws RuntimeException {

        Scanner scanner;
        scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        // Парсим введённую строку разделением фраз по пробелам
        String[] data = input.split(" ");

        // Проверка на число фраз в строке
        if (data.length != 6) {
            throw new RuntimeException("Должно быть шесть данных, разделённых пробелом. Вы ввели " + (data.length < 6 ? "меньше" : "больше") + " данных.");
        }

        // Проверяем конкретные данные
        else {

            // ФИО проверяем на пустоту, т.к. человек мог ввести лишние пробелы
            String lastName = data[0].trim();
            if (lastName.isEmpty())
                throw new RuntimeException("Вы не ввели фамилию.");

            String firstName = data[1].trim();
            if (firstName.isEmpty())
                throw new RuntimeException("Вы не ввели имя.");

            String patronymic = data[2].trim();
            if (patronymic.isEmpty())
                throw new RuntimeException("Вы не ввели отчество.");

            // Обрабатываем и проверяем дату рождения
            LocalDate dateOfBirth;
            try {
                dateOfBirth = LocalDate.parse(data[3].trim(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Дата рождения должна быть введена в формате ДД.ММ.ГГГГ.");
            }

            // Проверяем номер телефона
            String phoneNumber = data[4].trim();
            if (!phoneNumber.matches("\\d+")) {
                throw new RuntimeException("В номере телефона должны быть только цифры.");
            }
            if (phoneNumber.length() != 10) {
                throw new RuntimeException("В номере телефона должно быть 10 цифр (вы указали "+phoneNumber.length()+").");
            }

            // Проверяем пол
            char sex;
            String s = data[5].trim();
            if (!s.equals("m") && !s.equals("f")) {
                throw new RuntimeException("Пол нужно обозначить буквой m или f.");
            }
            sex = s.charAt(0);

            // Записываем данные в пустой экземпляр класса пользователя
            person.setData(lastName, firstName, patronymic, dateOfBirth, phoneNumber, sex);
        }
    }
}



