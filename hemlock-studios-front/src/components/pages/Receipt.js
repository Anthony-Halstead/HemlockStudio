import React from 'react'
import { Link } from 'react-router-dom'

function Receipt() {
  return (
    <div>
    <div>Thank you for your purchase!</div>
    <div>Your receipt was sent to your email</div>
    <Link to='/store'>-return to shopping-</Link>
    </div>
  )
}

export default Receipt