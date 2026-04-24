package organ.project;

public class Arquivo {

  String nome;
  String tipo;
  String tipoMime;

  public Arquivo(String nome, String tipo, String tipoMime){
    this.nome = nome;
    this.tipo = tipo;
    this.tipoMime = tipoMime;
  };

  public void display(){
    System.out.println("\n---");
    System.out.println("Nome do arquivo: " + nome);
    System.out.println("Tipo do arquivo: " + tipo);
    System.out.println("Tipo MIME do arquivo: " + tipoMime);
    System.out.println("---");
  };

  public String getExtensionType(){
    return tipo;
  };
  public String getMimeType(){
    return tipoMime;
  };

}
