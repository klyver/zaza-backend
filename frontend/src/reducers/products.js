import {combineReducers} from 'redux';
import {browserHistory} from 'react-router';

const FETCH_ALL = 'products/FETCH_ALL';
const FETCH_ALL_SUCCESS = 'products/FETCH_ALL_SUCCESS';
const FETCH_ALL_FAIL = 'products/FETCH_ALL_FAIL';

const FETCH_SINGLE = 'products/FETCH_SINGLE';
const FETCH_SINGLE_SUCCESS = 'products/FETCH_SINGLE_SUCCESS';
const FETCH_SINGLE_FAIL = 'products/FETCH_SINGLE_FAIL';

const UPDATE = 'products/UPDATE';
const UPDATE_SUCCESS = 'products/UPDATE_SUCCESS';
const UPDATE_FAIL = 'products/UPDATE_FAIL';

const CREATE = 'products/CREATE';
const CREATE_SUCCESS = 'products/CREATE_SUCCESS';
const CREATE_FAIL = 'products/CREATE_FAIL';

// Reducers
const loading = (state = false, action) => {
    switch (action.type) {
        case FETCH_ALL:
        case FETCH_SINGLE:
        case UPDATE:
        case CREATE:
            return true;
        case FETCH_ALL_SUCCESS:
        case FETCH_ALL_FAIL:
        case FETCH_SINGLE_SUCCESS:
        case FETCH_SINGLE_FAIL:
        case UPDATE_SUCCESS:
        case UPDATE_FAIL:
        case CREATE_SUCCESS:
        case CREATE_FAIL:
            return false;
        default:
            return state;
    }
};

const data = (state = {}, action) => {
    switch (action.type) {
        case FETCH_ALL_SUCCESS:
            const map = {};
            for (const p of action.result.data) {
                map[p.id] = p;
            }
            return map;
        case FETCH_SINGLE_SUCCESS:
        case UPDATE_SUCCESS:
        case CREATE_SUCCESS:
            return Object.assign({}, state, {
                [action.result.data.id]: action.result.data
            });
        default:
            return state;
    }
};

const errorMessage = (state = null, action) => {
    switch (action.type) {
        case FETCH_ALL_FAIL:
        case FETCH_SINGLE_FAIL:
        case UPDATE_FAIL:
        case CREATE_FAIL:
            return `ERROR: ${action.error.data.message}`;
        case FETCH_ALL:
        case FETCH_ALL_SUCCESS:
        case FETCH_SINGLE:
        case FETCH_SINGLE_SUCCESS:
        case UPDATE:
        case UPDATE_SUCCESS:
        case CREATE:
        case CREATE_SUCCESS:
            return null;
        default:
            return state;
    }
};

export default combineReducers({data, errorMessage, loading})

// Actions
export const fetchProductsAction = () => ({
    types: [FETCH_ALL, FETCH_ALL_SUCCESS, FETCH_ALL_FAIL],
    promise: client => client.get('/api/products')
});

export const fetchProductAction = (productId) => ({
    types: [FETCH_SINGLE, FETCH_SINGLE_SUCCESS, FETCH_SINGLE_FAIL],
    promise: client => client.get(`/api/products/${productId}`),
    productId: productId
});

export const updateProductAction = (product) => ({
    types: [UPDATE, UPDATE_SUCCESS, UPDATE_FAIL],
    promise: client => client.put(`/api/products/${product.id}`, product),
    productId: product.id
});

export const createProductAction = (product) => ({
    types: [CREATE, CREATE_SUCCESS, CREATE_FAIL],
    promise: client => client.post('/api/products', product),
    afterSuccess: (dispatch, getState, response) => {
        browserHistory.push(`/products/${response.data.id}`);
    }
});



