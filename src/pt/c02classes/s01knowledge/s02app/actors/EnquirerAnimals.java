package pt.c02classes.s01knowledge.s02app.actors;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IDeclaracao;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import java.util.Map;
import java.util.HashMap;

public class EnquirerAnimals implements IEnquirer {

	private IResponder responder;
	private IObjetoConhecimento obj;
	private String[] vetorAnimal; /* vetor que guarda a lista de animais */
	private Map <String,String> perguntaFeita = new HashMap <String,String>(); /* cria um hashtable para armazenar as perguntas e respostas */
	private int i = 0;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}

	
	public boolean discover() {
		 	IBaseConhecimento bc = new BaseConhecimento();
		 	bc.setScenario("animals");
			vetorAnimal = bc.listaNomes(); /* coloca todos os animais disponiveis no vetor */
			obj = bc.recuperaObjeto(vetorAnimal[i]); 
			
			IDeclaracao decl = obj.primeira();
			
			while (decl != null) 
			{
				String pergunta = decl.getPropriedade();
				String respostaEsperada = decl.getValor();
				String resposta;
				
				if (perguntaFeita.containsKey(pergunta)) /* se o hashmap estiver com alguma pergunta feita já */
				{
					resposta = perguntaFeita.get(pergunta); /* vê a resposta que foi dada anterior e assim não precisa perguntar de novo */
				}else /* se a pergunta nao foi feita ainda */
				{
					resposta = responder.ask(pergunta);
					perguntaFeita.put(pergunta, resposta); /* coloca a pergunta e resposta na hashtable */
				}
						
				if (resposta.equalsIgnoreCase(respostaEsperada)) /* compara a resposta dada e a esperada */
				{
					decl = obj.proxima();
				}else /* se nao for a resposta esperada, vai para o proximo animal */
				{
					i++;
					obj = bc.recuperaObjeto(vetorAnimal[i]);
					decl = obj.primeira();
				}	
			}
			
			boolean acertei = responder.finalAnswer(vetorAnimal[i]);
		
		if (acertei)
			System.out.println("Oba! Acertei!");
		else
			System.out.println("fuem! fuem! fuem!");
		
		return acertei;
	}

}
