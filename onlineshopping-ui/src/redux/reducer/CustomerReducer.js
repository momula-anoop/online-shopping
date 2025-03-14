import {LOGOUT} from "./ValidateReducer";

export const GET_CUSTOMER = "GET_CUSTOMER";
export const UPDATE_CUSTOMER = "UPDATE_CUSTOMER";

const initialState={
    //customer:[]
}


const CustomerReducer = (state=initialState,action) => {
    let customer = [...state];

    switch (action.type){
        case GET_CUSTOMER:
            return [...action.payload];
        case UPDATE_CUSTOMER:{
            customer ={customer,...action.payload};
            return customer;
        }
        case LOGOUT:
            return [];
    }
 
}

export default CustomerReducer
