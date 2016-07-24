import {combineReducers} from 'redux';

const FETCH = 'product/FETCH';
const FETCH_SUCCESS = 'product/FETCH_SUCCESS';
const FETCH_FAIL = 'product/FETCH_FAIL';

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
const product = (state = null, action) => {
    switch (action.type) {
        case FETCH_SUCCESS:
            return action.result.data;
        default:
            return state;
    }
};

export default combineReducers({loading, product});

// Actions
export const fetchProduct = (productId) => ({
    types: [FETCH, FETCH_SUCCESS, FETCH_FAIL],
    promise: client => client.get(`/api/products/${productId}`)
});

