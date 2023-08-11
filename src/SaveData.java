import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class SaveData {
    public static void savePersonData(Person person) {

        String fileContents = person.toString();

        // Для проверки, существует ли файл с именем в виде данной фамилии
        File file = new File("fileForWrite.txt");

        try (FileWriter writer = new FileWriter("fileForWrite.txt", true)) { // создаем файл и записываем данные
            writer.write(fileContents+"\n");
            writer.flush();
        }
        catch(IOException e) {
            System.out.println(String.format("Возникла ошибка во время записи содержимого <%s>", fileContents));
        }
    }
}
