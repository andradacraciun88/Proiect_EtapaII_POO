import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Contract;
import entities.MonthlyStats;
import entities.Play;
import entities.Simulation;
import input.ConsumersInputData;
import input.DistributorsInputData;
import input.Input;
import input.ProducersInputData;
import output.ContractOutputData;
import output.Output;
import output.MonthlyStatsOut;
import output.ProducerOutputData;
import output.DistributorOutputData;
import output.ConsumerOutputData;

import java.io.File;

/**
 * Entry point to the simulation
 */
public final class Main {

    private Main() { }

    /**
     * Main function which reads the input file and starts simulation
     *
     * @param args input and output files
     * @throws Exception might error when reading/writing/opening files, parsing JSON
     */
    public static void main(final String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Input input = objectMapper.readValue(new File(args[0]), Input.class);
        Simulation simulation = new Simulation();
        Contract contract = new Contract();
        Play play = new Play();
        play.turns(input);
        Output outputData = new Output();
        for (ConsumersInputData consumers : input.getInitialData().getConsumers()) {
            ConsumerOutputData consumersOutput = new ConsumerOutputData(consumers);
            outputData.getConsumers().add(consumersOutput);
        }
        for (DistributorsInputData distributor : input.getInitialData().getDistributors()) {
            DistributorOutputData distributorsOutputData = new DistributorOutputData(distributor);
            for (Contract contract1 : distributor.getContracts()) {
                ContractOutputData contract2 = new ContractOutputData(contract1);
                distributorsOutputData.getContracts().add(contract2);
            }
            outputData.getDistributors().add(distributorsOutputData);
        }
        for (ProducersInputData producer : input.getInitialData().getProducers()) {
            ProducerOutputData producerOutputData = new ProducerOutputData(producer);
            for (MonthlyStats monthlyStats : producer.getMonthlyStats()) {
                MonthlyStatsOut monthlyStats2 = new MonthlyStatsOut(monthlyStats);
                producerOutputData.getMonthlyStats().add(monthlyStats2);
            }
            outputData.getEnergyProducers().add(producerOutputData);
        }
        ObjectMapper objectMapper1 = new ObjectMapper();
        objectMapper1.writerWithDefaultPrettyPrinter().writeValue(new File(args[1]), outputData);

    }
}
