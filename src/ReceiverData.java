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
        if (data.length < 6 || data.length > 6) {
            throw new RuntimeException("Должно быть шесть данных, разделённых пробелом. Вы ввели " + ( data.length < 6 ? "меньше" : "больше") + " данных.");
        }

        // Проверяем конкретные данные
        else {

            // ФИО проверяем на пустоту, т.к. человек мог ввести лишние пробелы
            String lastName = data[0].trim();
            if (lastName.isEmpty())
                throw new RuntimeException("Вы не ввели фамилию.");
            if(!lastName.matches("[a-zA-Zа-яА-Я]+"))
                throw new RuntimeException("Фамилия не должна содержать цифры.");

            String firstName = data[1].trim();
            if (firstName.isEmpty())
                throw new RuntimeException("Вы не ввели имя.");
            if (!firstName.matches("[a-zA-Zа-яА-Я]+"))
                throw new RuntimeException("Имя не должно содержать цифры.");

            String patronymic = data[2].trim();
            if (patronymic.isEmpty())
                throw new RuntimeException("Вы не ввели отчество.");
            if (!patronymic.matches("[a-zA-Zа-яА-Я]+"))
                throw new RuntimeException("Отчество не долно содержать цифры.");

            // Обрабатываем и проверяем дату рождения
            LocalDate dateOfBirth;
            try {
                dateOfBirth = LocalDate.parse(data[3].trim(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Дата рождения должна быть введена в формате ДД.ММ.ГГГГ.");
            }

            // Проверяем номер телефона
            String phoneNumberStr = data[4].trim(); // получаем номер телефона из массива data как строку и убираем пробелы в начале и конце.

            if (!phoneNumberStr.matches("\\d+")) {  // Проверяем, что в строка содержит только цифры,
                // используя метод matches() и регулярное выражение \\d+ (\d означает “цифра”, + означает “один или более раз”).
                throw new RuntimeException("В номере телефона должны быть только цифры.");
            }

            if (phoneNumberStr.length() != 10) { // Проверяем длину номера телефона (должно быть 10 цифр)
                throw new RuntimeException("В номере телефона должно быть 10 цифр (вы указали " + phoneNumberStr.length() + ").");
            }

            // Убедившись, что строка содержит только цифры и имеет правильную длину, мы преобразуем ее в числовое
            long phoneNumber = Long.parseLong(phoneNumberStr);

            // Проверяем пол
            String s = data[5].trim();

            if (!s.equals("m") && !s.equals("f")) {
                throw new RuntimeException("Пол нужно обозначить буквой m или f.");
            }
            char sex = s.charAt(0);

            // Записываем данные в пустой экземпляр класса пользователя
            person.setData(lastName, firstName, patronymic, dateOfBirth, phoneNumber, sex);
        }
    }
}



