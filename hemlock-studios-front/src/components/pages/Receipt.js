import React from 'react'
import { Link } from 'react-router-dom'
import '../../css/pages/receipt.css'
function Receipt() {
  return (
    <div className='receipt-content'>
      <div className='receipt-container'>
    <h1>Thank you for your purchase!</h1>
    <h2>Your receipt was sent to your email</h2>
    <Link to='/store'>-return to shopping-</Link>
    </div>
    </div>
  )
}

export default Receipt