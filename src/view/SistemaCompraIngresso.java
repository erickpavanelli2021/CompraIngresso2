package view;

import java.util.concurrent.Semaphore;

import controller.ThreadIngresso;

public class SistemaCompraIngresso {

	public static void main(String[] args) {
		
		Semaphore espera = new Semaphore(1);
			
		for(int idComprador = 1; idComprador <= 300; idComprador++) {
			Thread ingressos = new ThreadIngresso(idComprador, espera);
			ingressos.start();
			
		}

	}

}
