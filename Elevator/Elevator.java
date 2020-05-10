import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Elevator implements Runnable{
    public int currentFloor;
    private boolean isAllowed;

    private PriorityBlockingQueue<Integer> calls;
    Elevator(PriorityBlockingQueue<Integer> calls)
    {
        this.calls = calls;
        this.isAllowed = true;
    }

    private void init()
    {
        this.currentFloor = 1;
        do {
            try {
                if(!calls.isEmpty())
                {
                    move(calls.take());
                    Thread.sleep(200);
                }else {
                    TimeUnit.SECONDS.sleep(1);
                }
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }while (isAllowed);
    }

    private void move(int targetFloor)
    {
        try {
            if(targetFloor == 0)
            {
                this.isAllowed = false;
            }
            if (targetFloor == currentFloor)
            {
                turnDoor();
            }
            else
                {
                if(targetFloor>currentFloor)
                {
                    moveUp(targetFloor);
                    turnDoor();
                    this.currentFloor = targetFloor;
                } else {
                    moveDown(targetFloor);
                    turnDoor();
                    this.currentFloor = targetFloor;
                }
            }

        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private void turnDoor() throws InterruptedException
    { TimeUnit.SECONDS.sleep(2);
        System.out.println("Дверь открылась");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Дверь закрылась");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Можете выбрать другой этаж");

    }

    private void moveUp(int floor) throws InterruptedException
    {
        for (int i = currentFloor; i < floor; i++)
        {
            System.out.printf(String.format("Лифт проехал %d этаж\n",i));
            TimeUnit.SECONDS.sleep(2);
        }
        System.out.println("Лифт приехал");
    }

    private void moveDown(int floor) throws InterruptedException
    {
        for (int i = currentFloor; i > floor; i--)
        {
            System.out.printf(String.format("Лифт проехал %d этаж\n",i));
            TimeUnit.SECONDS.sleep(2);
        }
        System.out.println("Лифт приехал");
    }

    @Override
    public void run()
    {
        init();
    }
}
