package organ.project;
import java.io.File;

public class Arquivo {

  String nome;
  String tipo;
  String tipoMime;
  File caminhoCompleto;

  public Arquivo(String nome, String tipo, String tipoMime, File caminhoCompleto){
    this.nome = nome;
    this.tipo = tipo;
    this.tipoMime = tipoMime;
    this.caminhoCompleto = caminhoCompleto;
  };

  public void display(){
    System.out.println("\n---");
    System.out.println("Nome do arquivo: " + nome);
    System.out.println("Tipo do arquivo: " + tipo);
    System.out.println("Tipo MIME do arquivo: " + tipoMime);
    System.out.println("---");
  };
  public File getFileOriginal() {
    return this.caminhoCompleto;
  }
  public String getExtensionType(){
    return tipo;
  };
  public String getMimeType(){
    return tipoMime;
  };
  public String getName(){
    return nome;
  };

}
