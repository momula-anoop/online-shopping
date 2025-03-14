import { configureStore } from "@reduxjs/toolkit";
import RootReducer from "./reducer/RootReducer";
import { persistReducer, persistStore } from "redux-persist";
import storage from "redux-persist/lib/storage";
import thunk from "redux-thunk";

const persistConfig = {
    key: "root",
    storage,
    whiteList: ["customer"]
};

const persistedReducer = persistReducer(persistConfig, RootReducer);

export const store = configureStore({
    reducer: persistedReducer,
    middleware: [thunk]
});

export const persistor = persistStore(store);