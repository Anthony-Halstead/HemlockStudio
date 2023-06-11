import React, { useEffect, useState } from 'react';
import axios from 'axios';

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

  useEffect(() => {
    let jwtToken = localStorage.getItem("token");
    if (jwtToken) {
      console.log("creditcard JWT Token Checkpoint", jwtToken)
      axios
        .get('http://localhost:8080/user/findCreditCards', {
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


  const handleSaveClick = () => {
    console.log("in the handle save click", creditCardInfo)

    let jwtToken = localStorage.getItem("token");
    if (jwtToken) {
      console.log("JWT TOKEN", jwtToken)
      axios
        .post('http://localhost:8080/user/addCreditCard', creditCardInfo, {
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

    axios
      .put('http://localhost:8080/user/updateCreditCard', updatedCard)
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
        .delete(`http://localhost:8080/user/removeCreditCard/${cardDeleteData}`, {
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


  const handleSetDefault = (card) => {
    let jwtToken = localStorage.getItem('token');

    if (jwtToken) {
      axios.put(
        'http://localhost:8080/user/setDefaultCreditCard',
        { defaultCardId: card.id },
        {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
          },
        }
      )
        .then((response) => {
          console.log(response.data);
          props.setUpdateUser({});
        })
        .catch((error) => {
          console.error('Error setting default credit card:', error);
        });
    }
  };

  return (
    <div>
      <h3>Credit Card Information</h3>
      {!editable && (
        <div>
          {creditCards.map((creditCard) => (
            <div key={creditCard.id}>
              <p>Card Number: {creditCard.cardNumber}</p>
              <p>Expiration Month: {creditCard.expirationMonth}</p>
              <p>Expiration Year: {creditCard.expirationYear}</p>
              <p>Card Holder Name: {creditCard.cardHolderName}</p>
              <p>CVV: {creditCard.cvv}</p>
              <button onClick={() => handleToggleEdit(creditCard)}>
                Edit
              </button>
              <button onClick={() => handleRemove(creditCard)}>
                Remove
              </button>
              <input
                type="radio"
                name="defaultCard"
                checked={creditCard.id === props.defaultCardId}
                onChange={() => handleSetDefault(creditCard)}
              />
              Set as Default
              <hr />
            </div>
          ))}
          <button onClick={() => handleToggleEdit(null)}>Add Credit Card</button>
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
          <label>Expiration Month:</label>
          <input
            name="expirationMonth"
            value={creditCardInfo.expirationMonth}
            onChange={handleInputChange}
            placeholder="Exp Month"
          />
          <label>Expiration Year:</label>
          <input
            name="expirationYear"
            value={creditCardInfo.expirationYear}
            onChange={handleInputChange}
            placeholder="Exp Year"
          />
          <label>Card Holder Name:</label>
          <input
            name="cardHolderName"
            value={creditCardInfo.cardHolderName}
            onChange={handleInputChange}
            placeholder="Card Holder Name"
          />
          <label>CVV:</label>
          <input
            name="cvv"
            value={creditCardInfo.cvv}
            onChange={handleInputChange}
            placeholder="CVV"
          />
          {selectedCard ? (
            <div>
              <button onClick={handleUpdateClick}>Update Credit Card</button>
              <button onClick={handleCancelClick}>Cancel</button>
            </div>
          ) : (
            <div>
              <button onClick={handleSaveClick}>Save Credit Card</button>
              <button onClick={handleCancelClick}>Cancel</button>
            </div>
          )}
        </div>
      )}
    </div>
  );
}

export default CreditCard