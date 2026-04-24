package organ.project;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Pasta
 */
public class Pasta {

  String nome;
  File diretorio;
  List<Arquivo> arquivos = new ArrayList<>();

  public Pasta(String nome, File diretorio){
    this.nome = nome;
    this.diretorio = diretorio;
  };

  public void displayFolder(){
    System.out.println("\nNome da Pasta: " + nome);
    System.out.println("Caminho da Pasta: " + diretorio);
  };
}


