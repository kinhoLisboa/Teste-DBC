package com.sicred.retaguarda.leitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sicred.retaguarda.modelo.Conta;
import com.sicred.retaguarda.service.ReceitaService;

public class SincronizacaoReceita {

	public static void main(String[] args) throws RuntimeException, InterruptedException {

		String arquivo = "C:\\arquivo\\texto.txt";
		
		ReceitaService receitaService = new ReceitaService();

		List<Conta> list = new ArrayList<Conta>();

		try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {

			String linha = br.readLine();
			
			linha = br.readLine();

			while (linha != null) {

				String[] vetor = linha.split(";");

				String agencia = vetor[0];
				String conta = vetor[1];
				Double saldo = Double.parseDouble(vetor[2].replaceAll("," , "."));
				String status = vetor[3];

				Conta cont = new Conta(agencia, conta, saldo, status);
				list.add(cont);

				linha = br.readLine();
				System.out.println(receitaService.atualizarConta(agencia, conta, saldo, status));
			}

		} catch (IOException e) {
			System.out.println(" ERROR " + e.getMessage());
		}

	}

}
