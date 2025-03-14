import { useDispatch } from "react-redux";
import { LOGIN,LOGOUT } from "../reducer/ValidateReducer";
import {REQUEST_TYPE, serverRequest} from "../../axios";

import { GET_CUSTOMER } from "../reducer/CustomerReducer";
import CustomerAction from "./CustomerAction";

const ValidateAction = () => {
    const dispatch = useDispatch();
    const customerAction = CustomerAction();
    const login = async(user)=>{
        try {
            const response = await serverRequest(REQUEST_TYPE.POST, "/user/Login", user);
            console.log(response.data);
            if (response.status === 200) {
                customerAction.getCustomer(user.customerId);
                dispatch({ type: LOGIN, payload: response.data });
            }
        } catch (err) {
            console.log(err);
        }
    };

    const getCustomer = async(customerId) =>{
        try {
            const response = await serverRequest(REQUEST_TYPE.POST, `/Customer/ById/${customerId}`);
            console.log(response.data);
            if (response.status === 200) {
                dispatch({ type: GET_CUSTOMER, payload: response.data });
            }
        } catch (err) {
            console.log(err);
        }
    }
    const logout = () => {
        return dispatch({ type: LOGOUT });
    };

  return Object.freeze({login,logout});//warn
}

export default ValidateAction;
