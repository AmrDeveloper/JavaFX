package sample.Utils;

import com.sun.istack.internal.NotNull;
import sample.Constants.Constants;
import sample.Controllers.Debugger;
import sample.Controllers.FilesController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImageResizer {


    public ImageResizer() {}

    public void onImagesResizing(@NotNull List<File> imagesList, boolean[] mMultiSize, String savePath) {
        //Check if All objects is valid
        if (imagesList.size() == 0 || savePath.equals("")) {
            return;
        }

        //All Images Path
        String[] allImagesPath = FilesController.makeFilesReady(mMultiSize, savePath);
        //For each file List
        for(File currentImage : imagesList){
            //Resize Current Image
            oneImageResizing(mMultiSize,allImagesPath,currentImage);
        }
    }

    private void oneImageResizing(boolean[] mMultiSize, String[] allImagesPath , File image) {
        try {
            BufferedImage originalImage = ImageIO.read(image);
            int type = (originalImage.getType() == 0) ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            final int sizesLength = 7;
            for (int i = 0; i < sizesLength; i++) {
                //If User not need this resize continue to next size
                if (mMultiSize[i]) {
                    int currentSize = Constants.mSizeArray[i];
                    BufferedImage resizeImageJpg = imageBufferedResize(originalImage, type, currentSize, currentSize);
                    String resultImagePath = allImagesPath[i] + "\\" +  imageNameParser(image.getName()) + "." + Constants.JPG;
                    boolean isCreated = ImageIO.write(resizeImageJpg, Constants.JPG, new File(resultImagePath));
                }
            }
        }
        //if can't read image
        catch (IOException e) {
            Debugger.showErrorDialog("Resize Error", "Can't Resize This Image");
        }
    }

    private BufferedImage imageBufferedResize(BufferedImage originalImage,
                                              int imageType,
                                              int imageWigth,
                                              int imageHeight) {

        BufferedImage resizedImage = new BufferedImage(imageWigth, imageHeight, imageType);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, imageWigth, imageHeight, null);
        graphics2D.setBackground(Color.white);
        graphics2D.dispose();
        return resizedImage;
    }

    private String imageNameParser(String fullName){
        int dotIndex = fullName.indexOf('.');
        return fullName.substring(0,dotIndex);
    }

}
