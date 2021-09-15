package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadIngresso extends Thread {

	Random gerador = new Random();
	private int idComprador;
	private Semaphore espera;
	private static int quantIngressos = 100;
	int compraIngresso = gerador.nextInt(4) + 1;
	
	public ThreadIngresso() {
		
	}
	
	public ThreadIngresso(int idComprador, Semaphore espera) {
		this.idComprador = idComprador;
		this.espera = espera;
		
	}
	
	@Override
	public void run() {
		login();
		try {
			espera.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			espera.release();
		}
	}
	
	private void login() {
//		System.out.println("===== Comprador Novo =====");
		int tempo = (int) ((gerador.nextInt(2000) + 50));
		if(tempo < 1000) {
			try {
				sleep(tempo);
				comprandoIngresso();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Time-out: Compra não efetuada!");
		}
	}
	
	private void comprandoIngresso() {
		System.out.println("#" + idComprador + " Comprando Ingresso");
		int tempo = (int) ((gerador.nextInt(3000) + 1000));
		if(tempo < 2500) {
			try {
				sleep(tempo);
				validacaoCompra();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}else {
			System.out.println("Time-out: Compra não efetuada!");
		}
	}
	
	private void validacaoCompra() {
		if(quantIngressos > 0 && compraIngresso <= quantIngressos) {
			quantIngressos -= compraIngresso;
			System.out.println("Compra efetuada! de " + compraIngresso + " ingressos");
			System.out.println("Ingressos Dispónivis: " + quantIngressos);
		}
	}
}
