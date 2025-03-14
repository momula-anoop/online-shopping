export const LOGIN='LOGIN';
export const LOGOUT='LOGOUT';


const ValidateReducer = (state={},action) => {
    switch(action.type){
        case LOGIN :
            return action.payload;
        case LOGOUT :
            return {};
        default:
            return state;
    }
}

export default ValidateReducer
