package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import sample.Utils.ImageResizer;

import java.io.File;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    //Views Objects
    @FXML
    private ListView<String> mImagesListView;
    @FXML
    private TextField savePathTextField;

    //Check Box for every Image Size
    @FXML
    private CheckBox sizeLDPI;
    @FXML
    private CheckBox sizeMDPI;
    @FXML
    private CheckBox sizeTVDPI;
    @FXML
    private CheckBox sizeHDPI;
    @FXML
    private CheckBox sizeXHDPI;
    @FXML
    private CheckBox sizeXXHDPI;
    @FXML
    private CheckBox sizeXXXHDPI;
    /**
     * List Of Images to show it in ListView
     */
    private final List<File> mImageInputList = new ArrayList<>();

    //Image Resize initialized
    private final ImageResizer mImageResizer = new ImageResizer();
    ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Method That Run When Constants Start Like Main But in UI
        onListViewSetSettings();
    }

    /**
     * @param event : Take Action When image Is Dropping
     */
    @FXML
    private void onImageDragToList(DragEvent event) {
        //Run on OnDragEntered For ListView when it sill dragging
        //Assert Is The Coming data is File
        if (event.getDragboard().hasFiles()) {
            //Accept Any Type of Transfer
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    /**
     * @param event : Take Action when Image Is Dropped
     */
    @FXML
    private void onImageDragDropped(DragEvent event) {
        //Run When Image Drag is done
        //Get ALl Images Dragged
        List<File> currentDropped = event.getDragboard().getFiles();

        //Update ListView Ui
        for (File imageFile : currentDropped) {
            //Append Current File To My List
            this.mImageInputList.add(imageFile);
            this.mImagesListView.getItems().add(imageFile.getName());
        }
    }


    /**
     * Clear ListView Item on UI
     * Clear List Of Images Files
     */
    @FXML
    private void onListImagesClearAll() {
        //First Clear The List Of Images Fiels
        this.mImageInputList.clear();
        //Then update change to UI ListView
        this.mImagesListView.getItems().clear();
    }

    /**
     * Get All Selected Items
     * and remove them from ListView and List of files
     */
    @FXML
    private void onListImagesClearItems() {
        //Get All selected Images Index
        List<Integer> selectedImagesIndex = this.mImagesListView.getSelectionModel().getSelectedIndices();
        //For each index but in reverse counter
        for (int i = selectedImagesIndex.size() - 1; i > -1; i--) {
            int currentIndex = selectedImagesIndex.get(i);
            //Delete Current Index from listView and List of Files
            deleteIndexFromLists(currentIndex);
        }
    }

    /**
     * Open Save Image Dialog And return File
     */
    @FXML
    private void onClickSaveImage() {
        File saveFilePath = FilesController.saveImageDialog();
        //Get Path on String
        String filePath = saveFilePath.toString();
        //Update UI
        this.savePathTextField.setText(filePath);
    }

    /**
     * Using My Resize Class to Resize All Images
     */
    @FXML
    private void onStartResizeButton() {
        //Array to get All Size that user need it
        boolean[] imageSizeNeeded = getAllNeededSize();
        //Get Current Image
        String imageSavePath = savePathTextField.getText().trim();
        //Resizing Image Using ImageResizier class
        mImageResizer.onImagesResizing(mImageInputList, imageSizeNeeded, imageSavePath);
    }

    /**
     * Check every check box view and see if it Selected or not
     *
     * @return : return the result in boolean array to use it in Resize
     */
    private boolean[] getAllNeededSize() {
        //7 is Number of sizes
        boolean[] imageSizes = new boolean[7];
        imageSizes[0] = sizeLDPI.isSelected();
        imageSizes[1] = sizeMDPI.isSelected();
        imageSizes[2] = sizeTVDPI.isSelected();
        imageSizes[3] = sizeHDPI.isSelected();
        imageSizes[4] = sizeXHDPI.isSelected();
        imageSizes[5] = sizeXXHDPI.isSelected();
        imageSizes[6] = sizeXXXHDPI.isSelected();
        return imageSizes;
    }

    /**
     * Set My Custom Settings For ListView
     */
    private void onListViewSetSettings() {
        //Enable Images Multi Selection
        this.mImagesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //Enable Edit Mode For ListView
        this.mImagesListView.setEditable(true);
    }

    /**
     * @param index of file should delete it
     */
    private void deleteIndexFromLists(int index) {
        //remove this image from listView
        this.mImagesListView.getItems().remove(index);
        //remove the same image from List of files
        this.mImageInputList.remove(index);
    }
}
