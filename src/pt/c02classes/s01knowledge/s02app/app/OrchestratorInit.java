package pt.c02classes.s01knowledge.s02app.app;
 
import java.util.Scanner;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.impl.Statistics;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerAnimals;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerMaze;
import pt.c02classes.s01knowledge.s02app.actors.ResponderAnimals;
import pt.c02classes.s01knowledge.s02app.actors.ResponderMaze;
 
public class OrchestratorInit
{
	public static void main(String[] args)
	{
		IEnquirer enq;
		IResponder resp;
		IStatistics stat;
		IBaseConhecimento base = new BaseConhecimento();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Escolha o modo de jogo : (A)nimals, (M)aze ou (F)im ?");
		String tipo = scanner.nextLine();
		
		while(!tipo.equalsIgnoreCase("F")) 
		{
		   System.out.print("  --> ");
		   String pc = scanner.nextLine();
		   
		   switch(tipo.toUpperCase()) 
		   {
		   		case "A": /* modo Animals */
					base.setScenario("animals");
					System.out.println("Digite um animal:");
					String txtAnimal = scanner.nextLine(); /* lendo o nome do animal, considerando que já tem o arquivo txt com esse animal */
					System.out.println("Enquirer com "+txtAnimal+"...");
					stat = new Statistics();
					resp = new ResponderAnimals(stat, txtAnimal);
					enq = new EnquirerAnimals();
					enq.connect(resp);
					enq.discover();
					System.out.println("----------------------------------------------------------------------------------------\n");
							
					break;
		   		case "M": /* Modo Maze */
					base.setScenario("maze");
					System.out.println("Digite um labirinto: ");
					String txtLabirinto = scanner.nextLine(); /* lendo o nome do labirinto, considerando que já tem o arquivo txt com esse labirinto*/
					System.out.println("Enquirer com "+txtLabirinto+"...");
					stat = new Statistics();
					resp = new ResponderMaze(stat, txtLabirinto);
					enq = new EnquirerMaze();
					enq.connect(resp);
					enq.discover();
					System.out.println("----------------------------------------------------------------------------------------\n");
		    	  		
				break;
		   }
		   System.out.println("Escolha o modo de jogo : (A)nimals, (M)aze ou (F)im ?");
		   tipo = scanner.nextLine();
		}
		scanner.close();
		System.out.println("Fim da execução do OrchestratorInit----------------------------------------------------------------------------------------\n");
	}	
}