import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import '../../css/reusables/sortingpanel.css';

function SortingPanel({ products, setFilteredProducts }) {
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [selectedSubcategory, setSelectedSubcategory] = useState(null);
  const [selectedSize, setSelectedSize] = useState(null);

  const [isCategoryOpen, setIsCategoryOpen] = useState(false);
  const [isSubcategoryOpen, setIsSubcategoryOpen] = useState(false);
  const [isSizeOpen, setIsSizeOpen] = useState(false);

  const [data, setData] = useState({
    categories: [],
    subcategories: [],
    sizes: [],
    announcements: [],
  });

  const categoriesMap = {
    CLOTHING: {
      subcategories: ['SHIRT', 'PANTS', 'HAT', 'OUTERWEAR', 'SHOES'],
      sizes: {
        SHIRT: ['SMALL', 'MEDIUM', 'LARGE', 'XL'],
        PANTS: ['WAIST_28', 'WAIST_30', 'WAIST_32', 'WAIST_34', 'WAIST_36'],
        HAT: ['SMALL', 'MEDIUM', 'LARGE', 'XL'],
        OUTERWEAR: ['SMALL', 'MEDIUM', 'LARGE', 'XL'],
        SHOES: ['US_7', 'US_7_5', 'US_8', 'US_8_5', 'US_9', 'US_9_5', 'US_10', 'US_10_5', 'US_11'],
      },
    },
  };

  const panelRef = useRef();

  useEffect(() => {
    let jwtToken = localStorage.getItem('token');
    axios
      .get('http://hemlock-studio.com:8080/enums/findAll', {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
        },
      })
      .then(response => {
        const { categories, subcategories, sizes, announcements } = response.data;

        setData({
          categories: categories || [],
          subcategories: subcategories || [],
          sizes: sizes || [],
          announcements: announcements || [],
        });
      })
      .catch(error => {
        console.error('There was an error!', error);
      });

    const handleClickOutside = event => {
      if (panelRef.current && !panelRef.current.contains(event.target)) {
        setIsCategoryOpen(false);
        setIsSubcategoryOpen(false);
        setIsSizeOpen(false);
      }
    };

    window.addEventListener('click', handleClickOutside);
    return () => {
      window.removeEventListener('click', handleClickOutside);
    };
  }, []);

  const handleCategoryHover = category => {
    setIsCategoryOpen(true);
    setSelectedCategory(category);
    setSelectedSubcategory(null);
    setSelectedSize(null);
  };

  const handleSubcategoryHover = subcategory => {
    setIsSubcategoryOpen(true);
    setSelectedSubcategory(subcategory);
    setSelectedSize(null);
  };

  const handleSizeHover = size => {
    setIsSizeOpen(true);
    setSelectedSize(size);
  };

  const handleReset = () => {
    setSelectedCategory(null);
    setSelectedSubcategory(null);
    setSelectedSize(null);
  };

  useEffect(() => {
    // Filter the products based on the selected values
    let filteredProducts = products;

    if (selectedCategory) {
      filteredProducts = filteredProducts.filter(product => product.category === selectedCategory);
    }

    if (selectedSubcategory) {
      filteredProducts = filteredProducts.filter(product => product.subcategory === selectedSubcategory);
    }

    if (selectedSize) {
      filteredProducts = filteredProducts.filter(product => product.size === selectedSize);
    }

    setFilteredProducts(filteredProducts);
  }, [selectedCategory, selectedSubcategory, selectedSize, products, setFilteredProducts]);

  return (
    <div>
      <div ref={panelRef} className="dropdown-content dropdown">
        <h2>Search</h2>
        {data.categories.map((category, i) => (
          <div
            key={i}
            className={`dropdown-item ${selectedCategory === category ? 'highlighted' : ''}`}
            onMouseEnter={() => handleCategoryHover(category)}
          >
            {category}
            {isCategoryOpen && selectedCategory === category && categoriesMap[category] && (
              <div className="dropdown-content">
                {categoriesMap[category].subcategories.map((subcategory, j) => (
                  <div
                    key={j}
                    className={`dropdown-item ${selectedSubcategory === subcategory ? 'highlighted' : ''}`}
                    onMouseEnter={() => handleSubcategoryHover(subcategory)}
                  >
                    {subcategory}
                    {isSubcategoryOpen && selectedSubcategory === subcategory && categoriesMap[category].sizes[subcategory] && (
                      <div className="dropdown-content">
                        {categoriesMap[category].sizes[subcategory].map((size, k) => (
                          <div
                            key={k}
                            className={`dropdown-item ${selectedSize === size ? 'highlighted' : ''}`}
                            onMouseEnter={() => handleSizeHover(size)}
                          >
                            {size}
                          </div>
                        ))}
                      </div>
                    )}
                  </div>
                ))}
              </div>
            )}
          </div>
        ))}
        <div
          className={`dropdown-item ${selectedCategory === null ? 'highlighted' : ''}`}
          onMouseEnter={handleReset}
        >
          All Products
        </div>
      </div>
    </div>
  );
}

export default SortingPanel;