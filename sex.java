import javax.swing.text.html.HTMLDocument;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.Date;



public class sex {
    private String firstName;
    private String secondName;
    private String fathersName;
    private int age;
    private boolean gender;



    sex (String secondName, String firstName, String fathersName, int age)
    {
        this.secondName = secondName;
        this.fathersName = fathersName;
        this.firstName = firstName;
        this.age = age;
    }

    sex (String secondName, String firstName, String fathersName)
    {
        this.secondName = secondName;
        this.fathersName = fathersName;
        this.firstName = firstName;
    }
    int calcAge(LocalDate birthDate, LocalDate currentDate)
   {
       if((birthDate!= null) && (currentDate != null))
       {
           return Period.between(birthDate, currentDate).getYears();
       }
       else
           return 0;
   }

   public void getGender()
    {
        String ending = fathersName;
        char [] last = new char[1];
        char [] female = {'а'};
        char [] male2 = {'ч'};

        int lastSymbol = fathersName.length();
        char symbol = ending.charAt(lastSymbol - 1);
        System.out.println(symbol);
        last[0] = symbol;

        if (last[0] == male2[0])
        {
            gender = true;
        }
        else if(last[0] == female[0])  gender = false;
        else {
            System.out.println("Вы сделали ошибку в отчестве! \n");
            System.exit(0);
        }

    }

    public class NameExeption extends Exception
    {
        private String cause;
        NameExeption (String cause) {this.cause = cause;}
        public String cause() {return this.cause;}
    }

     LocalDate convertDateToLocalDate(Date dateToConvert)
    {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    static{
        DATE_FORMAT.setLenient(true);
    }

    public static boolean isDateValid(String date)
    {
        if (date==null)
        {
            return false;
        }
        DATE_FORMAT.setLenient(false);
        try
        {
            DATE_FORMAT.format(DATE_FORMAT.parse(date)).equals(date);
            Date new_date = DATE_FORMAT.parse(date);
            return true;
        }
        catch (ParseException e) {
            System.out.println("Date is incorrect!");
            System.exit(0);
            return false;
        }
    }


   public static void main(String [] args)
    {
        System.out.println("Введите ваше ФИО: \n");
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String [] FIOD = string.split(" ");
        System.out.println("Введите дату рождения дд.мм.гггг: \n");
        Scanner scanner1 = new Scanner(System.in);
        String date = scanner1.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date2 = null;
        Date nowDate = new Date();

        if (isDateValid(date))
        try
        {
            date2 = dateFormat.parse(date);

        }
        catch (ParseException e)
        {
            System.out.println("Input date as dd.mm.yyyy");
            System.exit(0);
        }
        else
            System.out.println("Date is not correct");



        //System.out.println("string date in Date" + date2);



        String secondName = FIOD[0];
        String firstName = FIOD[1];
        String fathersName = FIOD[2];
        sex person = new sex(secondName, firstName, fathersName);
        person.getGender();

        person.age = person.calcAge(person.convertDateToLocalDate(date2),person.convertDateToLocalDate(nowDate));
        if (person.gender == true)
        {
            System.out.println(person.firstName + " Мужчина " + person.age);
        }
        else
        {
            System.out.println(person.firstName + " Женщина " + person.age);
        }

    }
}

// Корректность введенной даты
