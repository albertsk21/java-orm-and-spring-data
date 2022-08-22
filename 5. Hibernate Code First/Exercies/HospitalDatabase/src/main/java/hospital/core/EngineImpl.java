package hospital.core;

import hospital.common.constants.OutputMessages;
import hospital.common.enums.CommandType;
import hospital.core.interfaces.Controller;
import hospital.core.interfaces.Engine;
import hospital.io.ConsoleReader;
import hospital.io.ConsoleWriter;
import hospital.io.interfaces.InputReader;
import hospital.io.interfaces.OutputWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

public class EngineImpl implements Engine {
    private InputReader reader;
    private OutputWriter writer;
    private Controller controller;


    public EngineImpl() {
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
        this.controller = new ControllerImpl();
    }

    @Override
    public void run() {


        while(true){
            String result = null;

            try {
                result = processInput();
                if("Close".equals(result)){
                    break;
                }
            } catch (IOException | IllegalArgumentException | NullPointerException e) {
                result = e.getMessage();
            }
            this.writer.writeLine(result);
        }

    }

    private String processInput() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split(", ");

        CommandType command = CommandType.valueOf(tokens[0]);
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        switch (command){
            case AddMedicament:
                return this.controller.addMedicament(data[0]);
            case AddPatient:
                return this.controller.addPatient(data[0],data[1],data[2],data[3], LocalDate.parse(data[4]),data[5],data[6]);
            case AddVisitation:
                return this.controller.addVisitation(data[0],LocalDate.parse(data[4]));
            case RemoveMedicament:
                return this.controller.removeMedicament(data[0]);
            case RemovePatient:
                return this.controller.removePatient(data[0],data[1]);
            case RemoveVisitation:
                return this.controller.removeVisitation(LocalDate.parse(data[0]));
            case PrintMedicament:
                return this.controller.printMedicament(data[0]);
            case PrintPatient:
                return this.controller.printPatient(data[0],data[1]);
            case PrintVisitation:
                return this.controller.printVisitation(LocalDate.parse(data[0]));
            case Close:
                return this.controller.close();
            default:
                return OutputMessages.COMMAND_NOT_FOUND;

        }

    }
}
