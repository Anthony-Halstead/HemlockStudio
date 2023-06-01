package com.HemlockStudiosWebsite.enums;

public class ProductEnums {
    public enum Category {
        CLOTHING(Subcategory.SHIRT, Subcategory.PANTS, Subcategory.HAT, Subcategory.OUTERWEAR, Subcategory.SHOES),
        COLLECTIBLES,
        ACCESSORIES,
        ART,
        MISC;
    
        private Subcategory[] subcategories;
    
        Category(Subcategory... subcategories) {
            this.subcategories = subcategories;
        }
    
        public Subcategory[] getSubcategories() {
            return subcategories;
        }
    }
    
   
    public enum Subcategory {
        NULL,
        SHIRT(Size.SMALL, Size.MEDIUM, Size.LARGE, Size.XL),
        PANTS(Size.WAIST_28, Size.WAIST_30, Size.WAIST_32, Size.WAIST_34, Size.WAIST_36),
        HAT(Size.SMALL, Size.MEDIUM, Size.LARGE, Size.XL),
        OUTERWEAR(Size.SMALL, Size.MEDIUM, Size.LARGE, Size.XL),
        SHOES(Size.US_7, Size.US_7_5, Size.US_8, Size.US_8_5, Size.US_9, Size.US_9_5, Size.US_10, Size.US_10_5, Size.US_11);
      
        

        private Size[] sizes;

        Subcategory(Size... sizes) {
            this.sizes = sizes;
        }

        public Size[] getSizes() {
            return sizes;
        }
    }

    public enum Size {
        NULL,
        SMALL,
        MEDIUM,
        LARGE,
        XL,
        WAIST_28,
        WAIST_30,
        WAIST_32,
        WAIST_34,
        WAIST_36,
        US_7,
        US_7_5,
        US_8,
        US_8_5,
        US_9,
        US_9_5,
        US_10,
        US_10_5,
        US_11; 
    }
}