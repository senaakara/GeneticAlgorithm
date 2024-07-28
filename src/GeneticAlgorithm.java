import java.util.*;

public class GeneticAlgorithm {
    private static final String GENES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ 1234567890, .-;:_!" + "\"#%&/()=?@${[]}";
    private static String TARGET;
    private static int POPULATION_SIZE;
    private int generationCount = 0;
    private List<Chromosome> population;
    private final Random rand = new Random();

    public GeneticAlgorithm(String TARGET, int POPULATION_SIZE) {
        GeneticAlgorithm.TARGET = TARGET;
        GeneticAlgorithm.POPULATION_SIZE = POPULATION_SIZE;
        initializePopulation();
    }

    private void initializePopulation() {
        population = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            String genes = generateRandomGenes();
            population.add(new Chromosome(genes, calculateFitness(genes)));
        }
    }

    public String generateRandomGenes() {
        StringBuilder genes = new StringBuilder();
        for (int i = 0; i < TARGET.length(); i++) {
            int randomIndex = rand.nextInt(GENES.length());
            genes.append(GENES.charAt(randomIndex));
        }
        return genes.toString();
    }

    public void evolve() {
        long startTime = System.nanoTime();  // Milisaniye yerine nanosaniye kullanımı

        while (true) {
            population.sort(Comparator.comparingInt(Chromosome::getFitness));

            if (population.get(0).getFitness() == 0) {
                break;
            }

            printCurrentGenerationInfo(generationCount);
            population = createNewGeneration();
            generationCount++;
        }

        long endTime = System.nanoTime();  // Süre ölçümü için nanoTime
        printSolution(generationCount, (endTime - startTime) / 1000000);  // Nanosaniyeyi milisaniyeye çevirme
    }



    public int getGenerationCount() {
        return generationCount;
    }

    private void printCurrentGenerationInfo(int generationCount) {
        System.out.println("Generation " + generationCount + ": Best fitness = " + population.get(0).getFitness() + ", Genes = " + population.get(0).getGenes());
    }

    private void printSolution(int generationCount, long timeTaken) {
        System.out.println("Solution found in generation " + generationCount + ": " + population.get(0).getGenes());
        System.out.println("Time taken: " + timeTaken + "ms");
    }

    private List<Chromosome> createNewGeneration() {
        List<Chromosome> newGeneration = new ArrayList<>();
        int s = (10 * POPULATION_SIZE) / 100;  // Elitism, en iyi %10'u sakla
        for (int i = 0; i < s; i++) {
            newGeneration.add(population.get(i));
        }

        for (int i = s; i < POPULATION_SIZE; i++) {
            int a = rand.nextInt(s);
            int b = rand.nextInt(s);
            Chromosome parent1 = population.get(a);
            Chromosome parent2 = population.get(b);
            Chromosome offspring = crossover(parent1, parent2);
            mutate(offspring);
            newGeneration.add(offspring);
        }
        return newGeneration;
    }

    private Chromosome crossover(Chromosome one, Chromosome two) {
        StringBuilder genes = new StringBuilder();
        for (int i = 0; i < TARGET.length(); i++) {
            genes.append(rand.nextBoolean() ? one.getGenes().charAt(i) : two.getGenes().charAt(i));
        }
        return new Chromosome(genes.toString(), calculateFitness(genes.toString()));
    }

    private int calculateFitness(String genes) {
        int fitness = 0;
        for (int i = 0; i < TARGET.length(); i++) {
            if (genes.charAt(i) != TARGET.charAt(i)) {
                fitness++; // Hedef string ve kromozom farklı ise fitness artır
            }
        }
        return fitness;
    }

    private void mutate(Chromosome chromosome) {
        char[] chars = chromosome.getGenes().toCharArray();
        int index = rand.nextInt(chars.length);
        char newChar = GENES.charAt(rand.nextInt(GENES.length()));
        chars[index] = newChar;
        chromosome.setGenes(String.valueOf(chars));
        chromosome.setFitness(calculateFitness(String.valueOf(chars)));
    }
}
