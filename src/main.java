import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		Menu();
	}

	public static void RelatorioCidade(String cidade) {
		try {
			FileReader fileReader = new FileReader("consulta.txt");
			BufferedReader reader = new BufferedReader(fileReader);

			boolean encontrou = false;
			String linha;

			while ((linha = reader.readLine()) != null) {
				if (linha.contains(cidade)) {
					encontrou = true;
					break;
				}
			}

			if (encontrou) {
				String[] partes = linha.split(";");

				String cpf = partes[0];
				String cidadeCirurgia = partes[1];
				String data = partes[2];
				String cirurgia = partes[3];

				System.out.print("\nCPF: " + cpf);
				System.out.print("\nCidade: " + cidadeCirurgia);
				System.out.print("\nData da Cirurgia: " + data);
				System.out.print("\nCirurgia: " + cirurgia);
				System.out.print("\n");
			} else {
				System.out.print("Nenhuma consulta marcada para essa cidade.");
			}

			reader.close();
		} catch (IOException e) {
			System.err.println("Erro ao ler o arquivo: " + e.getMessage());
		}
	}

	public static void RelatorioConsulta(String cpf) {
		try {
			FileReader fileReader = new FileReader("consulta.txt");
			BufferedReader reader = new BufferedReader(fileReader);

			String linha;
			boolean encontrou = false;

			while ((linha = reader.readLine()) != null) {
				if (linha.contains(cpf)) {
					encontrou = true;
					break;
				}
			}

			if (encontrou) {
				String[] partes = linha.split(";");

				String cpfPaciente = partes[0];
				String cidade = partes[1];
				String data = partes[2];
				String cirurgia = partes[3];

				System.out.print("\nCPF: " + cpfPaciente);
				System.out.print("\nCidade: " + cidade);
				System.out.print("\nData da Cirurgia: " + data);
				System.out.print("\nCirurgia: " + cirurgia);
				System.out.print("\n");
			} else {
				System.out.print("Nenhuma consulta marcada no CPF: " + cpf);
			}

			reader.close();
		} catch (IOException e) {
			System.err.println("Erro ao ler o arquivo: " + e.getMessage());
		}
	}

	public static void Consulta() {
		System.out.print("[Consulta]");
		System.out.print("\n\nCPF do Paciente: ");

		Scanner scanCPF = new Scanner(System.in);
		String cpf = scanCPF.next();

		if (verificarCadastro(cpf) == false) {
			System.out.print("O paciente não está cadastrado.");
			return;
		}

		System.out.print("\nData da Consulta: ");
		Scanner scanDataConsulta = new Scanner(System.in);
		String dataConsulta = scanDataConsulta.next();

		System.out.print("\nCidade: ");
		Scanner scanCidade = new Scanner(System.in);
		String cidade = scanDataConsulta.next();

		System.out.print("\n1- Joelho\n2- Tornozelo\n3- Cotovelo\n4-Ombro\nTipo da Cirurgia: ");
		Scanner scanTipoCirurgia = new Scanner(System.in);
		int tipoCirurgia = Integer.parseInt(scanTipoCirurgia.next());

		switch (tipoCirurgia) {
		case 1:
			gravarDadosConsulta(cpf, cidade, dataConsulta, "Joelho");
			break;
		case 2:
			gravarDadosConsulta(cpf, cidade, dataConsulta, "Tornozelo");
			break;
		case 3:
			gravarDadosConsulta(cpf, cidade, dataConsulta, "Cotovelo");
			break;
		case 4:
			gravarDadosConsulta(cpf, cidade, dataConsulta, "Ombro");
			break;
		default:
			System.out.print("\nOpção inválida.");
			break;
		}
	}

	public static void gravarDadosConsulta(String cpf, String cidade, String dataConsulta, String tipoCirurgia) {
		try {
			FileWriter fileWriter = new FileWriter("consulta.txt", true);
			BufferedWriter writer = new BufferedWriter(fileWriter);

			writer.write(cpf + ";" + cidade + ";" + dataConsulta + ";" + tipoCirurgia + ";");
			writer.newLine();
			writer.close();

			System.out.println("Consulta marcada com sucesso!");
		} catch (IOException e) {
			System.err.println("Erro ao gravar no arquivo: " + e.getMessage());
		}
	}

	public static void gravarDadosPaciente(String cpf, String nome, String cidade, String nascimento) {
		try {
			FileWriter fileWriter = new FileWriter("paciente.txt", true);
			BufferedWriter writer = new BufferedWriter(fileWriter);

			writer.write(cpf + ";" + nome + ";" + cidade + ";" + nascimento + ";");
			writer.newLine();
			writer.close();

			System.out.println("Paciente cadastrado com sucesso!");
		} catch (IOException e) {
			System.err.println("Erro ao gravar no arquivo: " + e.getMessage());
		}
	}

	public static boolean verificarCadastro(String cpf) {
		boolean encontrou = false;

		try {
			FileReader fileReader = new FileReader("paciente.txt");
			BufferedReader reader = new BufferedReader(fileReader);

			String linha;

			while ((linha = reader.readLine()) != null) {
				if (linha.contains(cpf)) {
					encontrou = true;
					break;
				}
			}

			reader.close();
		} catch (IOException e) {
			System.err.println("Erro ao ler o arquivo: " + e.getMessage());
		}

		return encontrou;
	}

	public static void Paciente() {
		System.out.print("[Cadastro do Paciente]\nDigite as informações a seguir:");
		System.out.print("\n\nCPF do Paciente: ");

		Scanner scanCPF = new Scanner(System.in);
		String cpf = scanCPF.next();

		if (verificarCadastro(cpf) == true) {
			System.out.print("O paciente com o CPF " + cpf + " já está cadastrado.");
			return;
		}

		System.out.print("\nNome do Paciente: ");

		Scanner scanNome = new Scanner(System.in);
		String nome = scanNome.next();

		System.out.print("Cidade natural do Paciente: ");
		Scanner scanCidade = new Scanner(System.in);
		String cidade = scanCidade.next();

		System.out.print("Data de nascimento do Paciente: ");
		Scanner scanNascimento = new Scanner(System.in);
		String nascimento = scanNascimento.next();

		System.out.print("Paciente: " + nome + "\nCPF: " + cpf + "\nCidade: " + cidade + "\nNascimento: " + nascimento);

		gravarDadosPaciente(cpf, nome, cidade, nascimento);
	}

	public static void Menu() {
		while (true) {
			System.out.print(
					"\n1- Paciente\n2- Consulta\n3- Relatório do Paciente\n4- Relatório por Cidade\n5- Sair\nDigite a opção desejada: ");
			Scanner option = new Scanner(System.in);
			int m = Integer.parseInt(option.next());

			if (m == 5)
				break;

			switch (m) {
			case 1:
				Paciente();
				break;
			case 2:
				Consulta();
				break;
			case 3:
				System.out.print("\nCPF do Paciente: ");

				Scanner scanCPF = new Scanner(System.in);
				String cpf = scanCPF.next();

				RelatorioConsulta(cpf);
				break;
			case 4:
				System.out.print("\nCidade: ");

				Scanner scanCidade = new Scanner(System.in);
				String cidade = scanCidade.next();

				RelatorioCidade(cidade);
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		}
	}
}
