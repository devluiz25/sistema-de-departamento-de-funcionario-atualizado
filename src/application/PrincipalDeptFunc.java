package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import entidades.Contrato;
import entidades.Departamento;
import entidades.Trabalhador;
import entidades.enums.NivelFuncionario;

public class PrincipalDeptFunc {

	public static void main(String[] args) throws ParseException {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		char resp;
		do {
			System.out.print("INFORME O DEPARTAMENTO: ");
			String nomeDept = sc.nextLine();

			System.out.println("-----------------------------------------------------------------------");
			System.out.println("*** INFORMAÇÕES DO FUNCIONARIO ***");
			System.out.print("INFORME O NOME: ");
			String nomeFunc = sc.nextLine();
			System.out.print("INFORME O NIVÉL: ");
			String nivelFunc = sc.nextLine();
			System.out.print("INFORME O SALARIO BASE: R$");
			double salarioBaseFunc = sc.nextDouble();

			Trabalhador trabalhador = new Trabalhador(nomeFunc, NivelFuncionario.valueOf(nivelFunc), salarioBaseFunc,
					new Departamento(nomeDept));

			System.out.println("-----------------------------------------------------------------------");
			System.out.print("INFORME A QUANTIDADE DE CONTRATOS: ");
			int qtdContratos = sc.nextInt();

			for (int i = 0; i < qtdContratos; i++) {
				System.out.println("*** " + (i + 1) + "º CONTRATO ***");
				System.out.print("INFORME A DATA(MM/YYYY): ");
				Date dataContrato = sdf.parse(sc.next());
				System.out.print("INFORME O VALOR POR HORA TRABALHADA: R$");
				double valorHora = sc.nextDouble();
				System.out.print("INFORME A QUANTIDADE DE HORAS TOTAL NO CONTRATO: ");
				int qtdHoras = sc.nextInt();

				Contrato contrato = new Contrato(dataContrato, valorHora, qtdHoras);
				trabalhador.adicionarContrato(contrato);
			}

			System.out.println("-----------------------------------------------------------------------");
			System.out.println("*** CALCULO DE GANHO SALARIAL ***");
			System.out.print("INFORME O MÊS E O ANO PARA CALCULAR O AUMENTO SALARIAL(MM/YYYY): ");
			String monthAndYear = sc.next();
			int mes = Integer.parseInt(monthAndYear.substring(0, 2));
			int ano = Integer.parseInt(monthAndYear.substring(3));

			System.out.println("-----------------------------------------------------------------------");
			System.out.println("*** EXIBIR INFORMAÇÕES DO FUNCIONARIO ***");
			System.out.println("NOME: " + trabalhador.getNome().toUpperCase());
			System.out.println("DEPARTAMENTO: " + trabalhador.getDepartamento().getNome().toUpperCase());
			System.out.println("NIVEL: " + trabalhador.getNivel());
			System.out.println("GANHO TOTAL " + monthAndYear + ": "
					+ String.format("%.2f", trabalhador.somarValorContratos(ano, mes)));

			System.out.println("-----------------------------------------------------------------------");
			System.out.println("DESEJA REINICIAR O SISTEMA: ");
			resp = sc.next().charAt(0);
		} while (resp == 's');

		sc.close();
	}

}
