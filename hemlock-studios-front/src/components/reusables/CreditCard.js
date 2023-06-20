import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../../css/pages/account.css'
import'../../css/reusables/custombutton.css'

function CreditCard(props) {
  const [editable, setEditable] = useState(false);
  const [creditCardInfo, setCreditCardInfo] = useState({
    cardNumber: '',
    expirationMonth: '',
    expirationYear: '',
    cardHolderName: '',
    cvv: '',
  });
  const [creditCards, setCreditCards] = useState([]);
  const [selectedCard, setSelectedCard] = useState(null);
  const [defaultCardId, setDefaultCardId] = useState(null);
  const [formErrors, setFormErrors] = useState({
    cardNumber: '',
    expirationMonth: '',
    expirationYear: '',
    cardHolderName: '',
    cvv: '',
  });

  useEffect(() => {
    let jwtToken = localStorage.getItem("token");
    if (jwtToken) {
      console.log("creditcard JWT Token Checkpoint", jwtToken)
      axios
        .get('http://hemlock-studio.com:8080/user/findCreditCards', {
          headers: {
            'Authorization': `Bearer ${jwtToken}`
          }
        })
        .then((response) => {
          setCreditCards(response.data);
        })
        .catch((error) => {
          console.error('Error fetching credit cards', error);
        });
    }
  }, [props.user]);

  useEffect(() => {
    if (selectedCard) {
      setCreditCardInfo(selectedCard);
      setEditable(true);
    } else {
      setCreditCardInfo({
        cardNumber: '',
        expirationMonth: '',
        expirationYear: '',
        cardHolderName: '',
        cvv: '',
      });
      setEditable(false);
    }
  }, [selectedCard]);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setCreditCardInfo((prevInfo) => ({
      ...prevInfo,
      [name]: value,
    }));
  };

  const handleToggleEdit = (card) => {
    if (card) {
      setSelectedCard(card);
    } else {
      setSelectedCard(null);
      setEditable(true);
    }
  };

  const validateForm = () => {
    let valid = true;
    const errors = {
      cardNumber: '',
      expirationMonth: '',
      expirationYear: '',
      cardHolderName: '',
      cvv: '',
    };

    // Card Number Validation
    if (!/^\d{10}$/.test(creditCardInfo.cardNumber)) {
      valid = false;
      errors.cardNumber = 'Card number must be 10 digits long.';
    }

    // Expiration Month Validation
    const expirationMonth = parseInt(creditCardInfo.expirationMonth, 10);
    if (!/^\d{2}$/.test(creditCardInfo.expirationMonth) || expirationMonth > 12) {
      valid = false;
      errors.expirationMonth = 'Invalid expiration month.';
    }

    // Expiration Year Validation
    if (!/^\d{4}$/.test(creditCardInfo.expirationYear)) {
      valid = false;
      errors.expirationYear = 'Invalid expiration year.';
    }

    // Card Holder Name Validation
    if (!/^[a-zA-Z\s]+$/.test(creditCardInfo.cardHolderName)) {
      valid = false;
      errors.cardHolderName = 'Invalid card holder name.';
    }

    // CVV Validation
    if (!/^\d{3}$/.test(creditCardInfo.cvv)) {
      valid = false;
      errors.cvv = 'CVV must be 3 digits long.';
    }

    setFormErrors(errors);
    return valid;
  };

  const handleSaveClick = () => {
    console.log("in the handle save click", creditCardInfo)

    let jwtToken = localStorage.getItem("token");
    if (jwtToken) {
      console.log("JWT TOKEN", jwtToken);

      if (!validateForm()) {
        console.log('Form validation failed');
        return;
      }

      axios
        .post('http://hemlock-studio.com:8080/user/addCreditCard', creditCardInfo, {
          headers: {
            'Authorization': `Bearer ${jwtToken}`
          }
        })
        .then((response) => {
          console.log(response.data);
          props.setUpdateUser({});
          setCreditCardInfo({
            cardNumber: '',
            expirationMonth: '',
            expirationYear: '',
            cardHolderName: '',
            cvv: '',
          });
          setEditable(false);
        })
        .catch((error) => {
          console.error('Error adding credit card:', error);
        });
    }
  };

  const handleUpdateClick = () => {
    if (!props.user || !props.user.id) {
      // User object is not defined or doesn't have the necessary properties
      console.error('User object is invalid');
      return;
    }

    const updatedCard = {
      creditCardId: selectedCard.creditCardId,
      cardNumber: creditCardInfo.cardNumber,
      expirationMonth: creditCardInfo.expirationMonth,
      expirationYear: creditCardInfo.expirationYear,
      cardHolderName: creditCardInfo.cardHolderName,
      cvv: creditCardInfo.cvv,
    };
    let jwtToken = localStorage.getItem("token");
    axios
      .put('http://hemlock-studio.com:8080/user/updateCreditCard', updatedCard, {
        headers: {
          'Authorization': `Bearer ${jwtToken}`
        }
      })
      .then((response) => {
        console.log(response.data);
        props.setUpdateUser({});
        setSelectedCard(null);
      })
      .catch((error) => {
        console.error('Error updating credit card:', error);
      });
  };

  const handleCancelClick = () => {
    setSelectedCard(null);
    setEditable(false);
  };

  const handleRemove = (card) => {
    console.log("in the handle remove", card)

    let jwtToken = localStorage.getItem("token");
    const cardDeleteData = card.id;

    console.log(cardDeleteData)
    if (jwtToken) {
      console.log("JWT TOKEN", jwtToken)
      axios
        .delete(`http://hemlock-studio.com:8080/user/removeCreditCard/${cardDeleteData}`, {
          headers: {
            'Authorization': `Bearer ${jwtToken}`
          }
        })
        .then((response) => {
          console.log(response.data);
          props.setUpdateUser({});
        })
        .catch((error) => {
          console.error('Error removing credit card:', error);
        });
    }
  };

  useEffect(() => {
    let jwtToken = localStorage.getItem("token");
    if (jwtToken) {
      axios
        .get('http://hemlock-studio.com:8080/user/getDefaultCreditCard', {
          headers: {
            'Authorization': `Bearer ${jwtToken}`
          }
        })
        .then((response) => {
          setDefaultCardId(response.data.defaultCardId);
        })
        .catch((error) => {
          console.error('Error fetching default credit card', error);
        });
    }
  }, []);


  const handleSetDefault = (card) => {
    let jwtToken = localStorage.getItem('token');
    const cardId = card.id;

    if (jwtToken) {
      axios
        .put(
          'http://hemlock-studio.com:8080/user/setDefaultCreditCard',
          cardId,
          {
            headers: {
              Authorization: `Bearer ${jwtToken}`,
              'Content-Type': 'application/json',
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          props.setUpdateUser({});
          axios
            .get('http://hemlock-studio.com:8080/user/getDefaultCreditCard', {
              headers: {
                Authorization: `Bearer ${jwtToken}`,
              },
            })
            .then((response) => {
              setDefaultCardId(response.data.id); // Update the defaultCardId state
            })
            .catch((error) => {
              console.error('Error fetching default credit card', error);
            });
        })
        .catch((error) => {
          console.error('Error setting default credit card:', error);
        });
    }
  };

  return (
    <div>
      {!editable && (
        <div >
          {creditCards.map((creditCard) => (
            <div key={creditCard.id} className='credit-card-panel'>
              <p>Card Number: {creditCard.cardNumber}</p>
              <p>Expiration Month: {creditCard.expirationMonth}</p>
              <p>Expiration Year: {creditCard.expirationYear}</p>
              <p>Card Holder Name: {creditCard.cardHolderName}</p>            
              <button className='custom-button' onClick={() => handleToggleEdit(creditCard)}>Edit</button>
              <button className='custom-button'  onClick={() => handleRemove(creditCard)}>Remove</button>
              <label>
                <input
                  type="radio"
                  name="defaultCard"
                  checked={creditCard.id === defaultCardId}
                  onChange={() => handleSetDefault(creditCard)}
                />
                Set as Default
              </label>
              <hr />
            </div>
          ))}
          <button className='custom-button' onClick={() => handleToggleEdit(null)}>Add Credit Card</button>
        </div>
      )}
      {!editable && creditCards.length === 0 && (
        <p>No credit cards found.</p>
      )}
      {editable && (
        <div>
          <label>Card Number:</label>
          <input
            name="cardNumber"
            value={creditCardInfo.cardNumber}
            onChange={handleInputChange}
            placeholder="Card Number"
          />
          {formErrors.cardNumber && <p>{formErrors.cardNumber}</p>}
          <label>Expiration Year:</label>
          <input
            name="expirationYear"
            value={creditCardInfo.expirationYear}
            onChange={handleInputChange}
            placeholder="Exp Year"
          />
          {formErrors.expirationYear && <p>{formErrors.expirationYear}</p>}
          <label>Expiration Month:</label>
          <input
            name="expirationMonth"
            value={creditCardInfo.expirationMonth}
            onChange={handleInputChange}
            placeholder="Exp Month"
          />
          {formErrors.expirationMonth && <p>{formErrors.expirationMonth}</p>}
          <label>Card Holder Name:</label>
          <input
            name="cardHolderName"
            value={creditCardInfo.cardHolderName}
            onChange={handleInputChange}
            placeholder="Card Holder Name"
          />
          {formErrors.cardHolderName && <p>{formErrors.cardHolderName}</p>}
          <label>CVV:</label>
          <input
            name="cvv"
            value={creditCardInfo.cvv}
            onChange={handleInputChange}
            placeholder="CVV"
          />
          {formErrors.cvv && <p>{formErrors.cvv}</p>}
          {selectedCard ? (
            <div>
              <button className='custom-button' onClick={handleUpdateClick}>Update Credit Card</button>
              <button className='custom-button' onClick={handleCancelClick}>Cancel</button>
            </div>
          ) : (
            <div>
              <button className='custom-button' onClick={handleSaveClick}>Save Credit Card</button>
              <button className='custom-button' onClick={handleCancelClick}>Cancel</button>
            </div>
          )}
        </div>
      )}
    </div>
  );
}

export default CreditCard;