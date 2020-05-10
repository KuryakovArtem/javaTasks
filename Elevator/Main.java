import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    private Elevator elevator;
    private Input input;
    private Main(Elevator elevator, Input input)
    {
        this.elevator = elevator;
        this.input = input;
    }

    public static void main(String [] args)
    {
        PriorityBlockingQueue<Integer> calls = new PriorityBlockingQueue<>();
        Elevator elevator = new Elevator(calls);
        Input input = new Input(calls);
        Main main = new Main(elevator, input);

        main.start();
    }

    private void start()
    {
        new Thread(this.elevator).start();
        new Thread(this.input).start();
    }
}
