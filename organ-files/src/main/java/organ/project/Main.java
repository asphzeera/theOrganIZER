package organ.project;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;

public class Main {

  static WinInicial novaWin =  new WinInicial();
  static WinOrganize startDialog = new WinOrganize();

  public static void main(String[] args) throws Exception {

    List<Pasta> folder = new ArrayList<>();
    Map<String, Pasta> folderMap = new HashMap<>();

    List<Arquivo> archiveList = new ArrayList<>();

    try {
      Functions.startWindow(novaWin);;
      Functions.validateDirectory(novaWin);
      Functions.addRootFolder(folder, folderMap, novaWin);
    
      Pasta rootFolder = folderMap.get("Root");
      String[] data = rootFolder.diretorio.list();

      File pathNewRootFolder = Functions.createFolder(rootFolder);

      Functions.addItens(data, rootFolder, rootFolder.arquivos, folder);
      
      Functions.printArchiveList(rootFolder.arquivos);

      Functions.printFolderList(folder);

      for(Arquivo a : rootFolder.arquivos ){
        String mimeFolderName = a.getMimeType().contains("/") ? a.getMimeType().split("/")[0] : a.getMimeType();
        String typeSubFolderName = a.getExtensionType();

        File newFolderMime = new File(pathNewRootFolder, mimeFolderName);
        File newFolderType = new File(newFolderMime, typeSubFolderName);

        if(newFolderType.mkdirs()){
          System.out.println(newFolderType.getAbsolutePath());
          Pasta newFolder = new Pasta(newFolderType.getName(), newFolderType.getAbsoluteFile());
          folderMap.put(newFolderType.getName(), newFolder);
        };
      };
      
      System.out.println(folderMap.keySet());

      Functions.startFolderDialog(startDialog);
      // Preciso pegar o tipo do arquivo e o tipo Mime do arquivo
      // quando fazer isso eu vou criar uma pasta chamada tipoMime
      // e a subpasta com o tipo do arquivo

      // Se a pasta já existir ele nao cria, porém verifica o tipo do arquivo

      System.out.println("\n");
      
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
