




import { Navigate } from 'react-router-dom';
import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import CustomerAction from '../../redux/action/CustomerAction'
import ValidateAction from '../../redux/action/ValidateAction';

const GetCustomer = () => {
    const customerAction = CustomerAction();
    const customer = useSelector((state)=>state.customer)
    const user = useSelector((state)=>state.validate)
    
  useEffect(()=>{
    //customerAction.getCustomer()
  },[]);

  const logoutclick =()=>{
    ValidateAction.logout();
  }
  
    return user ?(
        <Navigate to="/login" />

    ):(
        

    <div className='fluid-container'>
     {customer.customerName} 

     <button className='primary' onClick={logoutclick}></button>

    </div>
  )
}

export default GetCustomer
