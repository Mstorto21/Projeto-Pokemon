package rpg;

import java.util.Random;
import java.util.Scanner;

public class FigthGame {

	static int ataqueJogador() {
		Scanner leitor = new Scanner(System.in);
		System.out.println("Escolha Seu Ataque:");
		System.out.println("(1) - Soco ");
		System.out.println("(2) - Especial");
		return leitor.nextInt();

	}

	static int ataqueCpu() {
		Random gerador = new Random();
		return gerador.nextInt(3) + 1; // retorna o numero entra 1 e 3.

	}

	static void ImprimeHp(int hpJogador, int hpCpu, int contagemEspecial) {
		System.out.println("==================");
		System.out.println("-- HP Jogador: " + hpJogador);
		System.out.println("-- HP Cpu: " + hpCpu);
		System.out.println("** Contagem Especiais **" + contagemEspecial);
		System.out.println("==================");
	}

	static int turnos() {
		int hpJogador = 150;
		int hpCpu = 11;
		int contagemEspecial = 5;
		int escolhaAtaque;
		int i = 1;

		while (hpJogador > 0) {
			hpCpu = 10 + i;

			System.out.println("============");
			System.out.println("Adversário: " + i);
			System.out.println("============\n");

			while (hpJogador > 0 && hpCpu > 0) {
				ImprimeHp(hpJogador, hpCpu, contagemEspecial);
				escolhaAtaque = ataqueJogador();
				switch (escolhaAtaque) {
				case 1:
					System.out.println("O Jogador desferiu um Soco!");
					hpCpu -= 7;
					break;
				case 2:
					System.out.println("O jogador desferiu um Ataque Especial");
					hpCpu -= 20;
					contagemEspecial--; // Contagem Especia = contagemEspecial - 1
					break;
				default:
					System.out.println("Opçao Inválida");
					break;
				}
				if (hpCpu > 0) {
					escolhaAtaque = ataqueCpu();
					switch (escolhaAtaque) {
					case 1:
						System.out.println("A CPU desferiu um Soco");
						hpJogador -= 2 + (int) (i / 10);
						break;
					case 2:
						System.out.println("A CPU desferiu um Chute");
						hpJogador -= 3 + (int) (i / 10);
						break;
					case 3:
						System.out.println("A CPU desferiu um Atauqe Especial");
						hpJogador -= 4 + (int) (i / 20);
						break;
					}
				} else {
					System.out.println("Inimigo Derrotado");
				}

			}
			if (hpJogador > 0) { // Aumento de vida o Jogador após derrota do Adversário
				hpJogador += 5;
				if (hpJogador > 150) {
					hpJogador = 150;
				}
				if (i % 10 == 0) {
					contagemEspecial++;
					if (contagemEspecial > 5) {
						contagemEspecial = 5;
					}
				}
			}

			i++;
		}
		return i;

	}

	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		int continua = 1;
		int recorde = 0;
		
		while (continua == 1) {

			int pontos = turnos();
		    System.out.println("Jogador chegou a " + pontos + "pontos.");
		    if (pontos > recorde) {
		    	recorde = pontos;
		    }
		    System.out.println("RECORDE ATUAL " + recorde);

			System.out.println("Fim do turno. Deseja Continuar ? (1)Sim (2)Não");
			continua = leitor.nextInt();
		}
	}

}
