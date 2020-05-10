import com.sun.source.tree.BinaryTree;

import javax.xml.stream.FactoryConfigurationError;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;

public class Input implements Runnable {

    private int maxFloor = -1;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private PriorityBlockingQueue<Integer> calls;
    public boolean textFlag = false;

    Input(PriorityBlockingQueue<Integer> calls)
    {
        this.calls = calls;
    }


    @Override
    public void run()
    {
        boolean flag = true;
        do {
            if (maxFloor == -1)
            {
                Scanner in = new Scanner(System.in);
                System.out.println("Input a max floor");
                this.maxFloor = in.nextInt();
            }
            if(calls.isEmpty())
            {
                System.out.println("1 - вызвать лифт\n0 - выйти из программы");
                try {
                    String line = reader.readLine();
                    int answer = Integer.parseInt(line);
                    switch (answer){
                        case 0:
                            System.exit(0);
                            break;
                        case 1:
                             int a = request();
                             calls.put(a);
                             break;
                    }
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }else
            {
                int a = request();
                calls.put(a);
            }

        } while (flag);
    }


    private int request()
    {

        boolean flag = false;
        int floor = -1;
        do {
            try {
                if (!this.textFlag) {
                    System.out.println("На какой этаж вам надо?");
                    this.textFlag = true;
                }
                String line = reader.readLine();
                floor = Integer.parseInt(line);
            }catch (IOException e)
            {
                e.printStackTrace();
            }

            if (floor > 0 && floor <= maxFloor)
            {
                flag = true;
            }else if (floor == 0)
            {
                int a;
                System.out.println("Вы хотите выйти из программы?\n1 - да\n0 - продолжить");
                Scanner in = new Scanner(System.in);
                a = in.nextInt();
                switch (a) {
                    case 0:
                        continue;
                    case 1:
                        System.exit(0);
                }
            }else System.out.println(String.format("Такого этажа нет! Максимальный %d этаж",maxFloor));
        }while (!flag);
        return floor;
    }

}
