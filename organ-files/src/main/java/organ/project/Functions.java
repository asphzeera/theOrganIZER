package organ.project;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.tika.Tika;

/**
 * Functions
 */
public class Functions {

  public static void startWindow(WinInicial novaWin){
    novaWin.setVisible(true);
  };

  public static void startFolderDialog(WinOrganize startDialog){
    startDialog.setVisible(true);
  };

  public static String directoryName(){
    System.out.println("Digite o nome da pasta OUTPUT: ");
    return ScanLine();
  }

  public static String ScanLine(){
    Scanner sc = new Scanner(System.in);
    String name = sc.nextLine();
    return name;
  };

  public static void addItens(String[] data, Pasta rootFolder, List<Arquivo> archiveList, List<Pasta> folder) {
      Tika tika = new Tika();
      for(String d : data){
        File file = new File(rootFolder.diretorio + "/" + d);
        verifyAndAdd(file, tika, archiveList, folder);
      };
  }
  
  public static void verifyAndAdd(File file, Tika tika, List<Arquivo> archiveList, List<Pasta> folder){
        if(file.isFile()){
          addArchive(file, tika, archiveList);
        };
        if(file.isDirectory()){
          addFolder(folder, file, tika, archiveList);
        };
  };
  
  public static void addFolder(List<Pasta> folder, File file, Tika tika, List<Arquivo> archiveList) {
    try {
      String name = file.getName();
      File directory = file.getAbsoluteFile();
      folder.add(new Pasta(name, directory));

      File[] subItens = directory.listFiles();

      if(subItens != null){
        for(File subItem : subItens){
           verifyAndAdd(subItem, tika, archiveList, folder);
        };
      };


    } catch (Exception e){
      System.out.println("Erro ao adicionar pasta: " + e);
    };
  };
  
  public static void addArchive(File file, Tika tika, List<Arquivo> archiveList) {
    try {
      String name = file.getName();
      int dotIndex = name.lastIndexOf('.');

      String extension = (dotIndex == -1) ? "Sem extensão" : name.substring(dotIndex + 1).toLowerCase();
      String mimeType = tika.detect(file);
      
      archiveList.add(new Arquivo(name, extension, mimeType));
    } catch (Exception e){
      System.out.println("Erro ao processar o arquivo: " + e);
    };
  };

  public static void validateDirectory(WinInicial novaWin){
      if(novaWin.selectDirectory == null){
        System.out.println("Erro: Nenhum diretorio selecionado; escolha ou encerre o programa");
      };
  }

  public static void addRootFolder(List<Pasta> folder, Map<String, Pasta> folderMap, WinInicial novaWin){
      String rootName = directoryName();
      Pasta rootFolder = new Pasta(rootName, novaWin.selectDirectory);
      folder.add(rootFolder);
      folderMap.put("Root", rootFolder);
  };

  public static void printArchiveList(List<Arquivo> archiveList){
      for(Arquivo a : archiveList){
        a.display();
      };
  };

  public static void printFolderList(List<Pasta> folder){
    for(Pasta p : folder){
      p.displayFolder();
    };
  };

  public static File createFolder(Pasta rootFolder){
      File newFolder = new File(rootFolder.diretorio, rootFolder.nome);
      if (newFolder.mkdir()) {
          System.out.println("Pasta criada com sucesso!");
      } else {
          System.out.println("Não foi possível criar a pasta (talvez ela já exista).");
      }
      return newFolder;
  };
}
