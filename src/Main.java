public class Main {
    public static void main(String[] args) {
        System.out.println("Population size = 100, Genetic Algorithm is running... \n");
        System.out.println("For target =(Generative AI) \n");
        GeneticAlgorithm ga = new GeneticAlgorithm("(Generative AI) ", 100);
        ga.evolve();
        System.out.println();
        GeneticAlgorithm ga2 = new GeneticAlgorithm("(Generative AI) ", 100);
        ga2.evolve();

        System.out.println("Avarage generation: " + (ga.getGenerationCount() + ga2.getGenerationCount()) / 2);


        System.out.println();
        System.out.println("For target =GenAI \n");
        GeneticAlgorithm ga3 = new GeneticAlgorithm("GenAI", 100);
        ga3.evolve();
        System.out.println();

        System.out.println("Population size = 300, Genetic Algorithm is running... \n");
        GeneticAlgorithm ga4 = new GeneticAlgorithm("(Generative AI) ", 300);
        ga4.evolve();
        System.out.println();
        GeneticAlgorithm ga5 = new GeneticAlgorithm("GenAI", 300);
        ga5.evolve();
        System.out.println();

        System.out.println("Population size = 50, Genetic Algorithm is running... \n");
        GeneticAlgorithm ga6 = new GeneticAlgorithm("(Generative AI) ", 50);
        ga6.evolve();
        System.out.println();
        GeneticAlgorithm ga7 = new GeneticAlgorithm("GenAI", 50);
        ga7.evolve();
        System.out.println();
    }
}