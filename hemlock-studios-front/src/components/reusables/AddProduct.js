import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import axios from 'axios';
import React, { useState, useEffect } from 'react';
import '../../css/reusables/additive.css';
import { faSquareMinus, faSquarePlus } from '@fortawesome/free-solid-svg-icons';
import '../../css/pages/addproduct.css';

function AddProduct() {
  const [categories, setCategories] = useState([]);
  const [subcategories, setSubcategories] = useState([]);
  const [sizes, setSizes] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState('CLOTHING');
  const [selectedSubcategory, setSelectedSubcategory] = useState('NULL');
  const [selectedSize, setSelectedSize] = useState('NULL');

 
  useEffect(() => {
    axios
      .get('http://localhost:8080/enums/findAll')
      .then((response) => {
        const { categories, subcategories, sizes } = response.data;
        setCategories(categories);
        setSubcategories(subcategories);
        setSizes(sizes);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const [newProduct, setNewProduct] = useState({
    name: '',
    description: '',
    price: 0,
    imgUrls: [],
    category: selectedCategory,
    subcategory: selectedSubcategory,
    size: selectedSize,
  });

  const inputFieldChangeHandler = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setNewProduct((prevProduct) => ({
      ...prevProduct,
      [name]: value,
    }));
  };

  const addProductChangeHandler = (event, index) => {
    const value = event.target.value;
    const newImgUrls = [...newProduct.imgUrls];
    newImgUrls[index] = value;
    setNewProduct((prevProduct) => ({
      ...prevProduct,
      imgUrls: newImgUrls,
    }));
  };

  const removeInputFieldHandler = () => {
    if (newProduct.imgUrls.length > 0) {
      const newImgUrls = [...newProduct.imgUrls];
      newImgUrls.pop();
      setNewProduct((prevProduct) => ({
        ...prevProduct,
        imgUrls: newImgUrls,
      }));
    }
  };

  const addInputFieldHandler = () => {
    setNewProduct((prevProduct) => ({
      ...prevProduct,
      imgUrls: [...prevProduct.imgUrls, ''],
    }));
  };

  const handleCategoryChange = (event) => {
   
    
      setSelectedCategory(event.target.value);
    
  };

  const handleSubcategoryChange = (event) => {
    setSelectedSubcategory(event.target.value);
  };

  const handleSizeChange = (event) => {
    setSelectedSize(event.target.value);
  };

  const handleAddProductSubmit = (event) => {
    event.preventDefault();

    const productData = {
      name: newProduct.name,
      description: newProduct.description,
      price: parseFloat(newProduct.price),
      imgUrls: newProduct.imgUrls,
      category: selectedCategory,
      subcategory: selectedSubcategory,
      size: selectedSize,
    };


    console.log("description:", productData.description)
    console.log("Name:", productData.name)
    console.log("price:", productData.price)
    console.log("imgUrls:", productData.imgUrls)
    console.log("category:", productData.category)
    console.log(" subcategory:", productData.subcategory)
    console.log("size:", productData.size)
    axios
      .post('http://localhost:8080/product/createProduct', productData)
      .then((response) => {
        console.log(response.data);
        setNewProduct({
          name: '',
          description: '',
          price: 0,
          imgUrls: [],
          category: selectedCategory,
          subcategory: selectedSubcategory,
          size: selectedSize,
        });
      })
      .catch((error) => {
        console.log(error);
      });
  };
  
  return (
    <div className='add-product-content'>
     {/* Category dropdown */}
<select value={selectedCategory} onChange={handleCategoryChange} name='category'>
  {categories.map((category) => (
    <option key={category} value={category}>
      {category}
    </option>
  ))}
</select>

{/* Subcategory dropdown */}
<select value={selectedSubcategory} onChange={handleSubcategoryChange} name='subcategory'>
  {subcategories.map((subcategory) => (
    <option key={subcategory} value={subcategory}>
      {subcategory}
    </option>
  ))}
</select>

{/* Size dropdown */}
<select value={selectedSize} onChange={handleSizeChange} name='size'>
  {sizes.map((size) => (
    <option key={size} value={size}>
      {size}
    </option>
  ))}
</select>

      <div>
        Name
        <input value={newProduct.name} name='name' type='text' onChange={inputFieldChangeHandler}></input>
      </div>
      <div>
        Description
        <input value={newProduct.description} name='description' type='text' onChange={inputFieldChangeHandler}></input>
      </div>
      <div>
        Price
        <input value={newProduct.price} name='price' type='number' onChange={inputFieldChangeHandler}></input>
      </div>
      <div className='add-flex-column'>
        Images
        {newProduct.imgUrls.map((url, index) => (
          <div key={index}>
            <input value={url} onChange={(event) => addProductChangeHandler(event, index)} />
          </div>
        ))}
        <FontAwesomeIcon className='additive-icon' icon={faSquareMinus} onClick={removeInputFieldHandler} />

        <FontAwesomeIcon className='additive-icon' icon={faSquarePlus} onClick={addInputFieldHandler} />
      </div>
      <div>
        <button onClick={handleAddProductSubmit}>SUBMIT</button>
      </div>
    </div>
  );
}

export default AddProduct;