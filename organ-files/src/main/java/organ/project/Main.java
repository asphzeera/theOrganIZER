package organ.project;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;

import com.sun.java.swing.action.ExitAction;

public class Main {

  static WinInicial novaWin =  new WinInicial();
  static WinOrganize startDialog = new WinOrganize();

  public static void main(String[] args) throws Exception {

    List<Pasta> folder = new ArrayList<>();
    Map<String, Pasta> folderMap = new HashMap<>();

    try {
      Functions.startWindow(novaWin);;
      if(novaWin.shouldClose()){
        System.exit(0);
      };
      Functions.validateDirectory(novaWin);

      Functions.addRootFolder(folder, folderMap, novaWin);  
      Pasta rootFolder = folderMap.get("Root");

      Functions.startFolderDialog(startDialog);
      boolean organizeSubFolders =  startDialog.getResult();

      String[] data = rootFolder.diretorio.list();

      Functions.addItens(data, rootFolder, rootFolder.arquivos, folder, organizeSubFolders);
      
      Functions.createFolders(rootFolder, folderMap);
      
      System.out.println("\nPastas e arquivos gerados e movidos com sucesso!");
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
