package ua.com.guide.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.guide.model.City;
import ua.com.guide.model.Image;
import ua.com.guide.model.Place;
import ua.com.guide.model.Post;
import ua.com.guide.util.enums.ImagePathLevel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Max on 10.08.2016.
 */
// TODO: 01.09.2016 Add image extension detect
public class ImageService {
    private static final String IMAGES_PATH = "static/images/";
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);
    private static final String IMAGE_NAME_PATTERN = "^reg(\\d+)(?:cit)?(\\d*)(?:pl)?(\\d*)p?(\\d*)img(\\d*)\\.\\w+$";

    @Autowired
    private CityService cityService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PostService postService;


    /**
     * Saving image in server
     *
     * @param image Contains image file and image file info
     * @param root  path with all images
     */
    public void saveImage(Image image, String root) {
        String imagesPath = root + IMAGES_PATH;
        String currentImagePath = generateImagePath(image);
        String fileExtension = FilenameUtils.getExtension(image.getImageFile().getOriginalFilename());

        File imageFolder = createFolder(currentImagePath, imagesPath);

        String imageFileName = generateImageFileName(image, imageFolder);

        File imageFile = new File(imagesPath + currentImagePath + imageFileName + "." + fileExtension);

        try {
            FileUtils.writeByteArrayToFile(imageFile, image.getImageFile().getBytes());
            LOGGER.info("Write image to {} file. Path: {} ", imageFile.getName(), imageFile.getAbsolutePath());
        } catch (IOException e) {
            LOGGER.error("Can't write image {} to file {} ", image.getImageFile().getName(), imageFile.getName());
        }
    }

    public List<String> getImagesOfRegion(String root, Integer id) {
        return getImages(root, ImagePathLevel.REGION, id);
    }

    public List<String> getImagesOfCity(String root, Integer id) {
        return getImages(root, ImagePathLevel.CITY, id);
    }

    public List<String> getImagesOfPlace(String root, Integer id) {
        return getImages(root, ImagePathLevel.PLACE, id);
    }

    public List<String> getImagesOfPost(String root, Integer id) {
        return getImages(root, ImagePathLevel.POST, id);
    }

    public void deleteImagesInPath(String root, ImagePathLevel level, Integer id) {
        File imagesPath = new File(generatePathByLevelAndId(root, level, id));
        try {
            for (File file : imagesPath.listFiles()) {
                deleteImage(root, file.getName());
            }
        } catch (NullPointerException e) {
            LOGGER.error("Directory {} is empty.", imagesPath.getAbsolutePath());
        }

        boolean isDeletePath = imagesPath.delete();
        if (isDeletePath) {
            LOGGER.info("Deleted directory {} with path {} ", imagesPath.getName(), imagesPath.getAbsolutePath());
        } else {
            LOGGER.error("Directory {} with path {} not deleted", imagesPath.getName(), imagesPath.getAbsolutePath());
        }
    }

    public void deleteImage(String root, String imageName) {
        Pattern pattern = Pattern.compile(IMAGE_NAME_PATTERN);
        Matcher matcher = pattern.matcher(imageName);

        if (!matcher.matches()) {
            LOGGER.error("Invalid image file name : {} ", imageName);
            throw new IllegalArgumentException("Invalid image file name : " + imageName);
        }

        String imagePath = root + IMAGES_PATH + generateImagePath(optionalParseInt(matcher, 1),
                optionalParseInt(matcher, 2), optionalParseInt(matcher, 3), optionalParseInt(matcher, 4)) + imageName;

        File imageFile = new File(imagePath);

        boolean isDeleted = imageFile.delete();

        if (isDeleted) {
            LOGGER.info("Image {} deleted", imageFile.getName());
        } else {
            LOGGER.error("Can't delete image {} .\n Path: {} ", imageFile.getName(), imageFile.getAbsolutePath());
        }
    }

    private String generatePathByLevelAndId(String root, ImagePathLevel level, Integer id) {
        StringBuilder builder = new StringBuilder();
        builder.append(root).append(IMAGES_PATH);

        Post post;
        Place place;
        City city;

        if (level.equals(ImagePathLevel.REGION)) {
            builder.append("/region").append(id);
        } else if (level.equals(ImagePathLevel.CITY)) {
            city = (City) cityService.getById(id);
            builder.append("/region")
                    .append(city.getRegion().getRegionId())
                    .append("/city")
                    .append(id);
        } else if (level.equals(ImagePathLevel.PLACE)) {
            place = (Place) placeService.getById(id);
            builder.append("/region")
                    .append(place.getCity().getRegion().getRegionId())
                    .append("/city")
                    .append(place.getCity().getCityId())
                    .append("/place")
                    .append(id);
        } else if (level.equals(ImagePathLevel.POST)) {
            post = (Post) postService.getById(id);
            builder.append("/region").
                    append(post.getPlace().getCity().getRegion().getRegionId())
                    .append("/city")
                    .append(post.getPlace().getCity().getCityId())
                    .append("/place")
                    .append(post.getPlace().getPlaceId())
                    .append("/post")
                    .append(id);
        }

        builder.append("/");

        return builder.toString();
    }

    private Integer optionalParseInt(Matcher matcher, int groupIndex) {
        boolean isMatches = matcher.matches();
        if (!isMatches) {
            LOGGER.error("File name {} don't match with pattern", matcher.pattern().pattern());
            throw new IllegalArgumentException("File name " + matcher.pattern().pattern() + " don't match with pattern");
        }
        String id = matcher.group(groupIndex);
        return (id == null || id.equals("")) ? null : Integer.parseInt(id);
    }

    private List<String> getImages(String root, ImagePathLevel level, Integer id) {

        File imagesFolder = new File(generatePathByLevelAndId(root, level, id));

        List<String> images = new ArrayList<>();

        try {
            for (File file : imagesFolder.listFiles()) {
                if (file.isFile() && !file.isDirectory()) {
                    images.add(file.getPath().replace(root, "\\").replace("\\", "/"));
                }
            }
        } catch (NullPointerException e) {
            LOGGER.error("Cant find file in folder {} with path {} ", imagesFolder.getName(), imagesFolder.getPath());
        }

        return images;

    }

    // TODO: 02.09.2016 Grammar. Write JavaDoc

    /**
     * @param imageFolder folder where file be save
     * @return image file name
     */
    private String generateImageFileName(Image image, File imageFolder) {
        StringBuilder builder = new StringBuilder();

        Integer[] params = {image.getRegionId(), image.getCityId(), image.getPlaceId(), image.getPostId()};
        String[] prefix = {"reg", "cit", "pl", "p"};

        for (int i = 0; i < params.length; i++) {
            if (params[i] != null) {
                builder.append(prefix[i]).append(params[i]);
            }
        }

        builder.append("img").append(getMaxImageFileIndex(imageFolder.listFiles()) + 1);

        return builder.toString();
    }

    /**
     * Parse all file name in folder and return max index.
     * Return -1 if folder empty.
     *
     * @param files all file in folder
     * @return max index of file
     */
    // TODO: 03.09.2016 Change this method
    private int getMaxImageFileIndex(File[] files) {
        int maxImageFileIndex = 0;

        Pattern pattern = Pattern.compile(IMAGE_NAME_PATTERN);
        Matcher matcher;

        if (files.length < 1)
            return -1;

        for (File file : files) {
            matcher = pattern.matcher(file.getName());
            int currentFileIndex = 0;
            try {
                currentFileIndex = optionalParseInt(matcher, 5);
            } catch (NullPointerException e) {
                LOGGER.error("{} not a number", matcher.group(5));
            }
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

    /**
     * Generate path by image info (regionId, postId...)
     *
     * @param image Image class.
     * @return path for saving image
     */
    private String generateImagePath(Image image) {
        return generateImagePath(image.getRegionId(), image.getCityId(), image.getPlaceId(), image.getPostId());
    }

    private String generateImagePath(Integer regionId, Integer cityId, Integer placeId, Integer postId) {
        StringBuilder builder = new StringBuilder();
        Integer[] params = {regionId, cityId, placeId, postId};
        String[] prefix = {"region", "city", "place", "post"};

        for (int i = 0; i < params.length; i++) {
            if (params[i] != null) {
                builder.append("/").append(prefix[i]).append(params[i]);
            }
        }

        if (builder.length() < 1)
            throw new IllegalArgumentException("At least 1 parameter should be not null");
        else {
            builder.append("/");
        }

        LOGGER.debug("Image path {}", builder.toString());
        return builder.toString();
    }
}