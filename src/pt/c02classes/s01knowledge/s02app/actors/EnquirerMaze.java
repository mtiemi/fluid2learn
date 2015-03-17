package pt.c02classes.s01knowledge.s02app.actors;

import java.util.Scanner;

import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import java.util.Stack;

public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	Stack <String> stackPosition = new Stack <String>(); /* usando pilha para armazenar o caminho feito no labirinto */
	String[] direction = new String[]{"norte", "leste", "sul", "oeste"};

	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean verifyMove(String direction) /* verifica se o proximo movimento eh valido */
	{
		String position;
		position = responder.ask(direction);
		
		if((position.equalsIgnoreCase("saida"))|| (position.equalsIgnoreCase("passagem"))) /* se for um movi */
		{
			if(!(stackPosition.empty()) ) /* se a pilha estiver com elemento */
			{	
				/* para nao ter loop e ficar movendo repetidamente */
				if((direction == "norte" && stackPosition.peek() != "sul")||(direction == "sul" && stackPosition.peek() != "norte")||(direction == "leste" && stackPosition.peek() != "oeste")||(direction == "oeste" && stackPosition.peek() != "leste"))
				{
					return true;
				}		
			}else if(stackPosition.empty()) /* se a pilha estiver vazia, pode fazer tal movimento pois n teve nenhum movimento antes */
			{
				return true;
			}	
		}
		return false;
	}
	
	public boolean solutionMaze() /* resolve o maze, ou seja, encontra a saida do labirinto */
	{
		boolean solution = false;
		
		if((responder.ask("aqui")).equalsIgnoreCase("saida")) /* se achar a saida do labirinto*/
		{
			return true;
		}
		
		if(verifyMove(direction[0]) && !solution) /* se for possivel ir para o norte */
		{
			responder.move(direction[0]);
			stackPosition.push(direction[0]);
			System.out.println("Moveu para Norte!");
			
			if(solutionMaze())
			{
				return true;
			}else
			{					
				stackPosition.pop();
				responder.move(direction[2]);
				System.out.println("Voltou caminho e foi para Sul!");
			}
		}
		
		if(verifyMove(direction[1]) && !solution) /* se for possivel ir para o leste */
		{
			responder.move(direction[1]);
			stackPosition.push(direction[1]);
			System.out.println("Moveu para Leste!");
			
			if(solutionMaze())
			{
				return true;
			}else
			{
				stackPosition.pop();
				responder.move(direction[3]);
				System.out.println("Voltou caminho e foi para Oeste!");
			}
		}
		
		if(verifyMove(direction[2]) && !solution ) /* se for possivel ir para o sul*/
		{
			responder.move(direction[2]);
			stackPosition.push(direction[2]);
			System.out.println("Moveu para Sul!");
			
			if(solutionMaze())
			{
				return true;
			}else
			{
				stackPosition.pop();
				responder.move(direction[0]);
				System.out.println("Voltou caminho e foi para Norte!");
			}
		}
		
		if(verifyMove(direction[3]) && !solution) /* se for possivel ir para o oeste */
		{
			responder.move(direction[3]);
			stackPosition.push(direction[3]);
			System.out.println("Moveu para Oeste!");
			
			if(solutionMaze())
			{
				return true;
			}else
			{
				stackPosition.pop();
				responder.move(direction[1]);
				System.out.println("Voltou caminho e foi para Leste!");
			}
		}
		return solution;
	}

	public boolean discover() 
	{
		if(solutionMaze()) /* encontrou saida */
		{
			System.out.println("Você encontrou a saida!");
			
		}else 
		{
			System.out.println("Fuém fuém fuém!");	
		}
		return true;
	}
	
}
