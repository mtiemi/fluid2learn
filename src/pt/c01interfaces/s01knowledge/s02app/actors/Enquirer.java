package pt.c01interfaces.s01knowledge.s02app.actors;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;
import java.util.Map;
import java.util.HashMap;

public class Enquirer implements IEnquirer
{
    IObjetoConhecimento obj;
	String[] vetorAnimal; /* vetor que guarda a lista de animais */
	Map <String,String> perguntaFeita = new HashMap <String,String>(); /* cria um hashtable para armazenar as perguntas e respostas */
	int i = 0;
	
	public Enquirer()
	{
	}
	
	@Override
	public void connect(IResponder responder)
	{
        IBaseConhecimento bc = new BaseConhecimento();
		vetorAnimal = bc.listaNomes(); /* coloca todos os animais disponiveis no vetor */
		obj = bc.recuperaObjeto(vetorAnimal[i]); 
		
		IDeclaracao decl = obj.primeira();
		
		while (decl != null) 
		{
			String pergunta = decl.getPropriedade();
			String respostaEsperada = decl.getValor();
			String resposta;
			
			if(perguntaFeita.containsKey(pergunta)) /* se o vetor estiver com alguma pergunta feita já */
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

	}

}
