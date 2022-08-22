package hospital;


import hospital.core.EngineImpl;
import hospital.core.interfaces.Engine;

public class Main {
    public static void main(String[] args) {


        Engine engine = new EngineImpl();
        engine.run();


    }
}
