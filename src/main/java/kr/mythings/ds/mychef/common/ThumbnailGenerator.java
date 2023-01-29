package kr.mythings.ds.mychef.common;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import java.io.File;
import java.io.IOException;

public class ThumbnailGenerator {

    private static final int SMALL_WIDTH = 400;
    private static final int SMALL_HEIGHT = 300;
    private static final int MEDIUM_WIDTH = 400;
    private static final int MEDIUM_HEIGHT = 300;
    private static final int LARGE_WIDTH = 400;
    private static final int LARGE_HEIGHT = 300;

    private static final String SMALL_SUFFIX = "_s";
    private static final String MEDIUM_SUFFIX = "_m";
    private static final String LARGE_SUFFIX = "_l";

    public static void generate(File file, ThumbnailSize thumbSize) throws IOException {

        String name = file.getName();

        String fileName;
        String ext;
        String path = file.getCanonicalPath();

        int width;
        int height;


        if (!"".equals(name)) {

            fileName = name.substring(0, name.indexOf("."));
            ext = name.substring(name.indexOf("."));
            path = path.substring(0, path.lastIndexOf(File.separator));

            switch (thumbSize) {
                case LARGE:
                    fileName = fileName + LARGE_SUFFIX + ext;
                    width = LARGE_WIDTH;
                    height = LARGE_HEIGHT;
                    break;
                case MEDIUM:
                    fileName = fileName + MEDIUM_SUFFIX + ext;
                    width = MEDIUM_WIDTH;
                    height = MEDIUM_HEIGHT;
                    break;
                case SMALL:
                default:
                    fileName = fileName + SMALL_SUFFIX + ext;
                    width = SMALL_WIDTH;
                    height = SMALL_HEIGHT;
            }

            generateThumb(file, path + File.separator + fileName, width, height);
        }
    }

    private static void generateThumb(File file, String saveName, int width, int height) throws IOException {

        Thumbnails.of(file)
                //.sourceRegion(Positions.CENTER, 200, 200)
                .crop(Positions.CENTER)
                .size(width, height)
                .toFile(new File(saveName));

    }

    public static String getThumbnailName(String saveName, ThumbnailSize size) {

        String onlyName = saveName.substring(0, saveName.lastIndexOf("."));
        String extension = saveName.substring(saveName.lastIndexOf(".")+1);

        switch (size) {
            case LARGE:
                onlyName = onlyName + LARGE_SUFFIX;
                break;
            case MEDIUM:
                onlyName = onlyName + MEDIUM_SUFFIX;
                break;
            case SMALL:
            default:
                onlyName = onlyName + SMALL_SUFFIX;
                break;
        }


        return String.format("%s.%s", onlyName, extension);

    }

    private ThumbnailGenerator() {}
}
