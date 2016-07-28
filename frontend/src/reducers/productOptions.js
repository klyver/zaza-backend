import {combineReducers} from 'redux';

const FETCH = 'productOptions/FETCH';
const FETCH_SUCCESS = 'productOptions/FETCH_SUCCESS';
const FETCH_FAIL = 'productOptions/FETCH_FAIL';

// Reducer
export default (state = null, action) => {
    switch (action.type) {
        case FETCH_SUCCESS:
            return action.result.data;
        case FETCH:
        case FETCH_FAIL:
            return null;
        default:
            return state;
    }
}

// Actions
export const fetchProductOptionsAction = () => ({
    types: [FETCH, FETCH_SUCCESS, FETCH_FAIL],
    promise: client => client.get('/api/productOptions')
});

