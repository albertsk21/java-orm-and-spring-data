import java.util.LinkedList;
import java.util.Queue;

class MyStack {


    private Queue<Integer> numbers;
    public MyStack() {
        this.numbers = new LinkedList<>();
    }

    public void push(int x) {

        this.numbers.add(x);

        while (true){

            try {
                int currentNumber = this.numbers.peek();
                if(currentNumber == x){
                    break;
                }
                int popNumber = this.numbers.poll();
                this.numbers.add(popNumber);
            }catch (NullPointerException e){
                this.numbers.add(x);
                break;
            }

        }

    }

    public int pop() {

        return this.numbers.poll();
    }

    public int top() {
        return this.numbers.peek();
    }

    public boolean empty() {
        return this.numbers.isEmpty();
    }
}