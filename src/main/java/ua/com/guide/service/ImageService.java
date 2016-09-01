package ua.com.guide.service;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.guide.model.Image;

import java.io.File;
import java.io.IOException;

/**
 * Created by Max on 10.08.2016.
 */
// TODO: 01.09.2016 Add image extension detect
public class ImageService {

    private static final String IMAGE_NAME_PREFIX = "image";
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);

    public void saveImage(Image image, String imagesPath) {

        String currentImagePath = generateImagePath(image);
        File imageFolder = createFolder(currentImagePath, imagesPath);
        String imageFileName = generateImageFileName(imageFolder);

        File imageFile = new File(imagesPath + currentImagePath + imageFileName + ".jpg");

        try {
            FileUtils.writeByteArrayToFile(imageFile, image.getImageFile().getBytes());
            LOGGER.info("Write image to {} file. Path: {} ", imageFile.getName(), imageFile.getAbsolutePath());
        } catch (IOException e) {
            LOGGER.error("Can't write image {} to file {} ", image.getImageFile().getName(), imageFile.getName());
        }
    }

    private String generateImageFileName(File imageFolder) {
        return IMAGE_NAME_PREFIX + getMaxImageFileIndex(imageFolder.listFiles());
    }

    private int getMaxImageFileIndex(File[] files) {
        int maxImageFileIndex = 0;

        for (File file : files) {
            int currentFileIndex = Integer.parseInt(file.getName().replace(IMAGE_NAME_PREFIX, ""));
            if (currentFileIndex > maxImageFileIndex)
                maxImageFileIndex = currentFileIndex;
        }

        return maxImageFileIndex;
    }

// TODO: 31.08.2016 Check grammar

    /**
     * Create new folder if it not exist.
     *
     * @param folderName Name of the folder
     * @return new folder (java.io.File);
     */
    private File createFolder(String folderName, String imagesPath) {
        File newFolder = new File(imagesPath + folderName);

        if (!newFolder.exists()) {
            boolean isFolderCreated = newFolder.mkdirs();

            if (isFolderCreated) {
                LOGGER.info("Create folder {} ", newFolder.getAbsolutePath());
            } else {
                LOGGER.error("Can't create folder {} ", newFolder.getAbsolutePath());
            }
        } else {
            LOGGER.trace("File {} already exist", newFolder.getAbsolutePath());
        }

        return newFolder;
    }

    private String generateImagePath(Image image) {
        StringBuilder buffer = new StringBuilder();
        Integer[] params = {image.getRegionId(), image.getCityId(), image.getPlaceId(), image.getPostId()};
        for (Integer param : params) {
            if (param != null) {
                buffer.append("/").append(param);
            }
        }

        if (buffer.length() < 1)
            throw new IllegalArgumentException("At least 1 parameter should be not null");
        else {
            buffer.append("/");
        }
        LOGGER.debug("Image path {}", buffer.toString());
        return buffer.toString();
    }

    public static void main(String[] args) {
        ImageService imageService = new ImageService();
    }
}
