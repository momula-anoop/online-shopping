import { useDispatch } from "react-redux";
import { REQUEST_TYPE,serverRequest } from "../../axios";
import {GET_CUSTOMER,UPDATE_CUSTOMER} from "../../redux/reducer/CustomerReducer";




function CustomerAction() {

    const dispatch=useDispatch()

    const getCustomer = async(customerId) =>{
        try {
            const response = await serverRequest(REQUEST_TYPE.GET, `/Customer/ById/${customerId}`);
            //console.log(response.data);
            if (response.status === 200) {
                dispatch({ type: GET_CUSTOMER, payload: response.data });
            }
        } catch (err) {
            console.log(err);
        }
    }

    const updateCustomer = async(customer)=>{
        try {
            const response = await serverRequest(REQUEST_TYPE.PUT, "/Customer",customer);
            //console.log(response.data);
            if (response.status === 200) {
                dispatch({ type: GET_CUSTOMER, payload: response.data });
            }
        } catch (err) {
            console.log(err);
        }
    }
  return (
    Object.freeze({getCustomer,updateCustomer})
  )
}

export default CustomerAction;
