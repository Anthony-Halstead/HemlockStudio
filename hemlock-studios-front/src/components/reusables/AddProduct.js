import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import axios from 'axios';
import React, { useState, useEffect, useContext } from 'react';
import '../../css/reusables/additive.css';
import { faSquareMinus, faSquarePlus } from '@fortawesome/free-solid-svg-icons';
import '../../css/pages/addproduct.css';
import { ToastContext } from '../reusables/ToastContext';

function AddProduct() {
  const [categories, setCategories] = useState([]);
  const [subcategories, setSubcategories] = useState([]);
  const [sizes, setSizes] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState('CLOTHING');
  const [selectedSubcategory, setSelectedSubcategory] = useState('NULL');
  const [selectedSize, setSelectedSize] = useState('NULL');
  const { setMessage } = useContext(ToastContext);

  const handleAddedMessage = () => {
    setMessage('New Product Was Created');
};

 
  useEffect(() => {
    let jwtToken = localStorage.getItem('token');
    axios
      .get('https://hemlock-studio.com/enums/findAll',
      {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
        },
      }
    )
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
    let jwtToken = localStorage.getItem('token');
    const productData = {
      name: newProduct.name,
      description: newProduct.description,
      price: parseFloat(newProduct.price),
      imgUrls: newProduct.imgUrls,
      category: selectedCategory,
      subcategory: selectedSubcategory,
      size: selectedSize,
    };

    axios
      .post('https://hemlock-studio.com/product/createProduct', productData,
      {
        headers: {
          Authorization: `Bearer ${jwtToken}`,
        },
      }
    )
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
        handleAddedMessage();
      });
  };
  
  return (
    
    <div className='add-product-content'>
      <h1>Add Product</h1>
      <div>Category</div>
<select value={selectedCategory} onChange={handleCategoryChange} name='category'>
  {categories.map((category) => (
    <option key={category} value={category}>
      {category}
    </option>
  ))}
</select>
<div>Sub-category</div>
<select value={selectedSubcategory} onChange={handleSubcategoryChange} name='subcategory'>
  {subcategories.map((subcategory) => (
    <option key={subcategory} value={subcategory}>
      {subcategory}
    </option>
  ))}
</select>
<div>Sizes</div>
<select value={selectedSize} onChange={handleSizeChange} name='size'>
  {sizes.map((size) => (
    <option key={size} value={size}>
      {size}
    </option>
  ))}
</select>

      <div>
        Name
        <div className='input-container'>
        <input placeholder="Name"  className='input-field' value={newProduct.name} name='name' type='text' onChange={inputFieldChangeHandler}></input>
        </div>
      </div>
      <div>
        Description
        <div className='input-container'>
        <input placeholder="Description"  className='input-field' value={newProduct.description} name='description' type='text' onChange={inputFieldChangeHandler}></input>
        </div>
      </div>
      <div>
        Price
        <div className='input-container'>
        <input placeholder="Price"  className='input-field' value={newProduct.price} name='price' type='number' onChange={inputFieldChangeHandler}></input>
      </div>
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
        <button className='submit-button' onClick={handleAddProductSubmit}>SUBMIT</button>
      </div>
    </div>
  );
}

export default AddProduct;