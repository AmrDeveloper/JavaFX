package sample.Controllers;


import javafx.stage.FileChooser;
import sample.Constants.Constants;

import java.io.File;

/**
 *  Class Contain Method To Control using Files I/O
 */
public class FilesController {

    /**
     * @return : return the file to get save path as string or Uri
     */
    public static File saveImageDialog(){
        //File Chooser Object
        FileChooser fileChooser = new FileChooser();
        //Set Title Of Save Dialog
        fileChooser.setTitle("Save Image File Name");
        //Open Dialog And Return File
        return fileChooser.showSaveDialog(null);
    }

    /**
     * @param filePath : Get file path and create new Folder
     */
    public static void createNewFolder(String filePath){
        //Create File Object
        File newFolder = new File(filePath);
        //Create new Folder
        newFolder.mkdir();
    }

    /**
     * @param mMultiSize : All size that i needed
     * @param savePath : The File Path to know where should i put result
     * @return : String array for all files path
     */
    public static String[] makeFilesReady(boolean[] mMultiSize, String savePath) {
        String[] imagesSixPath = new String[7];
        //Create New Folder In save Path
        FilesController.createNewFolder(savePath);
        //Drawable Main File Path
        String drawableFile = savePath + "\\" + Constants.DRAWABLE;
        //Then Create res-drawable file inside last new folder
        FilesController.createNewFolder(drawableFile);
        //No Create Folder For Every Size
        for (int i = 0; i < mMultiSize.length; i++) {
            String currentPath = drawableFile + "\\" + Constants.mPathArray[i];
            //Save Current Path in Array Index
            imagesSixPath[i] = currentPath;
            //Create New File
            FilesController.createNewFolder(currentPath);
        }
        return imagesSixPath;
    }

}
