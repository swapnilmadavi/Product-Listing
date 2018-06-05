package com.example.swapyx.productlisting.db;


import com.example.swapyx.productlisting.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates data to pre-populate the database
 */
public class DataGenerator {

    private static final String[] NAMES = new String[]{"Logitech G300s Optical Gaming Mouse (USB,Black)",
            "Mobile Gear XM-502 Gaming Mouse (USB,White)",
            "Logitech G102D Optical Gaming Mouse (USB,Black)",
            "Magideal #4 Optical Gaming Mouse (Wireless,White)",
            "Dragon War Emera ELE-G11 Gaming Mouse (USB,Black)"};

    private static final String[] BRANDS = new String[]{"Logitech",
            "Mobile Gear",
            "Logitech",
            "Magideal",
            "Dragon War"};

    private static final String[] CONNECTIONS = new String[]{"USB",
            "USB",
            "USB",
            "Wireless",
            "USB"};

    private static final int[] PRICES = new int[]{1488,
            549,
            1499,
            600,
            424};

    private static final int[] IMAGES = new int[]{R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5};


    private static final int[] NUMBER_OF_REVIEWS = new int[]{456,
            120,
            341,
            78,
            529};

    private static final String[] PROS = new String[]{"Ergonomic Design, DPI Precision",
            "Two extra thumb buttons",
            "Accuracy, Performance",
            "Value for money",
            "Comfortable grip, Budget mouse"};

    private static final String[] OPINIONS = new String[]{"Extremely Positive",
            null,
            "Extremely Positive",
            "Extremely Positive",
            null};

    private static final int[] LIKES = new int[]{145,
            40,
            103,
            82,
            51};

    private static final int[] DISLIKES = new int[]{76,
            86,
            65,
            21,
            127};

    private static final String[] COLORS = new String[]{"Black",
            "White",
            "Black",
            "White",
            "Black"};

    /**
     * Generates a list of Gaming mouses (Products) using the defined arrays.
     * @return the list of products
     */
    public static List<GamingMouse> generateProducts() {
        List<GamingMouse> products = new ArrayList<>(10);
        int index;
        for (int i = 0; i < 10; i++) {
            index = i%5;
            GamingMouse product = new GamingMouse();
            product.setId(i);
            product.setName(NAMES[index]);
            product.setBrand(BRANDS[index]);
            product.setConnection(CONNECTIONS[index]);
            product.setPrice(PRICES[index]);
            product.setImageId(IMAGES[index]);
            product.setNumberOfUserReviews(NUMBER_OF_REVIEWS[index]);
            product.setPros(PROS[index]);
            product.setOpinion(OPINIONS[index]);
            product.setLikes(LIKES[index]);
            product.setDislikes(DISLIKES[index]);
            product.setColor(COLORS[index]);
            products.add(product);
        }
        return products;
    }

}
