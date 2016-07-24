import {combineReducers} from 'redux';

const FETCH = 'productList/FETCH';
const FETCH_SUCCESS = 'productList/FETCH_SUCCESS';
const FETCH_FAIL = 'productList/FETCH_FAIL';

// Reducer
const loading = (state = false, action) => {
    switch (action.type) {
        case FETCH:
            return true;
        case FETCH_SUCCESS:
            return false;
        case FETCH_FAIL:
            return false;
        default:
            return state;
    }
};
const products = (state = [], action) => {
    switch (action.type) {
        case FETCH_SUCCESS:
            return action.result.data;
        default:
            return state;
    }
};

export default combineReducers({loading, products});

// Actions
export const fetchProductList = () => ({
    types: [FETCH, FETCH_SUCCESS, FETCH_FAIL],
    promise: client => client.get('/api/products')
});

