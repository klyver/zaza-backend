import {combineReducers} from 'redux';
import {browserHistory} from 'react-router';

const FETCH = 'updateProduct/FETCH';
const FETCH_SUCCESS = 'updateProduct/FETCH_SUCCESS';
const FETCH_FAIL = 'updateProduct/FETCH_FAIL';

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

const errorMessage = (state = null, action) => {
    switch (action.type) {
        case FETCH_FAIL:
            return action.error.data.message;
        default:
            return null;
    }
};

export default combineReducers({loading, errorMessage})

// Actions
export const updateProductAction = (product) => ({
    types: [FETCH, FETCH_SUCCESS, FETCH_FAIL],
    promise: client => client.put(`/api/products/${product.id}`, product)
});

