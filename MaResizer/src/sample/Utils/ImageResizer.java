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
            final int sizesLength = 7;
            for (int i = 0; i < sizesLength; i++) {
                //If User not need this resize continue to next size
                if (mMultiSize[i]) {
                    int currentSize = Constants.mSizeArray[i];
                    BufferedImage resizeImageJpg = imageBufferedResize(originalImage, currentSize, currentSize);
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
                                              int imageWidth,
                                              int imageHeight) {
        final BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(originalImage, 0, 0, imageWidth, imageHeight, null);
        graphics2D.dispose();
        return bufferedImage;
    }

    private String imageNameParser(String fullName){
        int dotIndex = fullName.indexOf('.');
        return fullName.substring(0,dotIndex);
    }

}
