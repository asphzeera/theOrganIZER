package organ.project;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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

  public static String directoryName(WinInput outputPath){
    outputPath.setVisible(true);
    return outputPath.getText();
  }

  public static String ScanLine(){
    Scanner sc = new Scanner(System.in);
    String name = sc.nextLine();
    return name;
  };

  public static void addItens(String[] data, Pasta rootFolder, List<Arquivo> archiveList, List<Pasta> folder, boolean subFolders) {
      Tika tika = new Tika();
      for(String d : data){
        File file = new File(rootFolder.diretorio + File.separator + d);
        verifyAndAdd(file, tika, archiveList, folder, subFolders);
      };
  }
  
  public static void verifyAndAdd(File file, Tika tika, List<Arquivo> archiveList, List<Pasta> folder, boolean subFolders){
        if(file.isFile()){
          addArchive(file, tika, archiveList);
        };
        if(file.isDirectory()){
          String name = file.getName();
          File directory = file.getAbsoluteFile();

          folder.add(new Pasta(name, directory));
          
          if(subFolders){
            File[] subItens = directory.listFiles();
            if(subItens != null){
              for(File subItem : subItens){
                 verifyAndAdd(subItem, tika, archiveList, folder, subFolders);
              };
            };
          };
        };
  };
 
  public static void addArchive(File file, Tika tika, List<Arquivo> archiveList) {
    try {
      String name = file.getName();
      int dotIndex = name.lastIndexOf('.');

      String extension = (dotIndex == -1) ? "Sem extensão" : name.substring(dotIndex + 1).toLowerCase();
      String mimeType = tika.detect(file);
      
      archiveList.add(new Arquivo(name, extension, mimeType, file));
    } catch (Exception e){
      System.out.println("Erro ao processar o arquivo: " + e);
    };
  };

  public static void validateDirectory(WinInicial novaWin){
      if(novaWin.selectDirectory == null){
        System.out.println("Erro: Nenhum diretorio selecionado; escolha ou encerre o programa");
      };
  }

  public static void addRootFolder(List<Pasta> folder, Map<String, Pasta> folderMap, WinInicial novaWin, WinInput outputPath){
      String rootName = directoryName(outputPath);
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
  public static void createFolders(Pasta rootFolder, Map<String, Pasta> folderMap){
      File pathNewRootFolder = Functions.createFolder(rootFolder);
      int pastasCriadas = 0;
      for(Arquivo a : rootFolder.arquivos ){
          String mimeFolderName = a.getMimeType().contains("/") ? a.getMimeType().split("/")[0] : a.getMimeType();
          String typeSubFolderName = a.getExtensionType();

          File newFolderMime = new File(pathNewRootFolder, mimeFolderName);
          File newFolderType = new File(newFolderMime, typeSubFolderName);
          
          if(newFolderType.mkdirs()){
            pastasCriadas++;
          };

          System.out.println(newFolderType.getAbsolutePath());
          Pasta newFolder = new Pasta(newFolderType.getName(), newFolderType.getAbsoluteFile());
          folderMap.put(newFolderType.getName(), newFolder);

          try {
            Path originPath = a.getFileOriginal().toPath();
            Path destinyPath = new File(newFolderType, a.getName()).toPath();

            Files.move(originPath, destinyPath, StandardCopyOption.REPLACE_EXISTING);
          } catch (Exception e) {
            System.out.println("Não foi possível mover o arquivo: " + a.getName());
          }
          
      };
      printFolderResult(folderMap, pastasCriadas);
  };

  public static void printFolderResult(Map<String, Pasta> folderMap, int pastasCriadas){
    System.out.println("Estrutura pronta!");
    System.out.println("Total de categorias/extensões mapeadas: " + (folderMap.size() - 1)); // -1 por causa da 'Root'
    System.out.println("Novas pastas físicas criadas: " + pastasCriadas);
  };
}
