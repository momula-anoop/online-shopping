import {combineReducers} from "redux";
import CustomerReducer from "./CustomerReducer";
import ValidateReducer from "./ValidateReducer";


const RootReducer = combineReducers({
    customer: CustomerReducer,
    validate: ValidateReducer
});

export default RootReducer;