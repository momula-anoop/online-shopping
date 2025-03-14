import { Provider } from "react-redux";
import { PersistGate } from "redux-persist/integration/react";
import { store,persistor } from "./redux/store";
import Login from "./client/components/Login";
import {BrowserRouter,Route,Routes} from "react-router-dom";
import GetCustomer from "./client/components/GetCustomer";


const Routing = ()=>{
    return(
        <Provider store={store}>
            <PersistGate loading={<h1>Loading....</h1>} persistor={persistor}></PersistGate>
            <BrowserRouter>
            <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/" element={<GetCustomer />}></Route>
            </Routes>
            </BrowserRouter>
            </Provider>
    )
}

export default Routing;