package pt.c01interfaces.s01knowledge.s02app.actors;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;

public class Enquirer implements IEnquirer
{
    IObjetoConhecimento obj;
	String[] vetorAnimal;
	int i = 0;
	public Enquirer()
	{
	}
	
	
	@Override
	public void connect(IResponder responder)
	{
        IBaseConhecimento bc = new BaseConhecimento();
		vetorAnimal = bc.listaNomes();
		obj = bc.recuperaObjeto(vetorAnimal[i]);
		
		IDeclaracao decl = obj.primeira();
		
        //boolean animalEsperado = true;
		while (decl != null) {
			String pergunta = decl.getPropriedade();
			String respostaEsperada = decl.getValor();
			
			String resposta = responder.ask(pergunta);
			if (resposta.equalsIgnoreCase(respostaEsperada))
			{
				decl = obj.proxima();
			}
			else
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
