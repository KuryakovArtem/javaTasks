import java.io.*;
import java.sql.SQLOutput;
import java.util.HashMap;

public class Dictionary {

    public static void main(String[] args)
    {
        try {
            //FileInputStream giver = new FileInputStream("Giver.txt");
            //FileOutputStream catcher = new FileOutputStream("Catcher.txt");
            //byte [] buffer = new byte[giver.available()];
            //giver.read(buffer, 0, buffer.length);

            //catcher.write(buffer,0,buffer.length);
            //giver.close();
            //catcher.close();
            File giver = new  File("Giver.txt");
            File catcher = new File("Catcher.txt");
            FileWriter writer = new FileWriter(catcher);
            FileReader reader = new FileReader(giver);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            HashMap<Character, Integer> map = new HashMap<Character, Integer>();
            PrintWriter printWriter = new PrintWriter(catcher);
            while (line != null)
            {
                for (int i = 0; i < line.length(); ++i)
                {
                    char c = line.charAt(i);
                    if(Character.isLetter(c))
                    {
                        if(map.containsKey(c))
                        {
                            map.put(c, map.get(c) +1);
                        }
                        else
                            {
                            map.put(c,1);
                        }
                    }
                }
                printWriter.println(line);
                line = bufferedReader.readLine();
            }


            printWriter.println(map);

            String string = "asd";
            writer.append(string);
            printWriter.close();

        }catch (IOException e)
        {
            System.out.println(e);
        }
    }
}
